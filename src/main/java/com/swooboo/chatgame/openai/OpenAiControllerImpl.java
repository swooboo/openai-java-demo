package com.swooboo.chatgame.openai;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import org.springframework.stereotype.Component;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpenAiControllerImpl implements OpenAiController {

    private final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
    private final List<ChatMessage> messages = new ArrayList<>();
    private final OpenAiService openAiService = new OpenAiService(OPENAI_API_KEY);

    @Override
    public void saySystem(String text) {
        ChatMessage userMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), text);
        messages.add(userMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo-0613")
                .messages(messages)
                .maxTokens(256)
                .build();

        ChatMessage responseMessage = openAiService.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();

        messages.add(responseMessage);
    }

    @Override
    public void sayUser(String text) {
        ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), text);
        messages.add(userMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo-0613")
                .messages(messages)
                .maxTokens(256)
                .build();

        ChatMessage responseMessage = openAiService.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();

        messages.add(responseMessage);
    }

    public String chatSummary() {
        return messages.stream()
                .map(message -> message.getRole() + ": " + message.getContent())
                .collect(Collectors.joining("\n"));
    }
}
