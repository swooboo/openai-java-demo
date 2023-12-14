package com.swooboo.chatgame.openai;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import org.springframework.stereotype.Component;
import com.theokanning.openai.service.OpenAiService;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpenAiControllerImpl implements OpenAiController {

    private final List<ChatMessage> messages = new LinkedList<>();
    OpenAiService openAiService = new OpenAiService("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");

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
