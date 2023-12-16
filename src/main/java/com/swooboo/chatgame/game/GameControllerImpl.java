package com.swooboo.chatgame.game;

import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.assistants.Assistant;
import com.theokanning.openai.messages.Message;
import com.theokanning.openai.messages.MessageRequest;
import com.theokanning.openai.runs.Run;
import com.theokanning.openai.runs.RunCreateRequest;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.threads.Thread;
import com.theokanning.openai.threads.ThreadRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameControllerImpl implements GameController {
    private final Assistant gameAssistant;
    private final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
    private final OpenAiService openAiService = new OpenAiService(OPENAI_API_KEY);
    private Thread currentThread;

    public GameControllerImpl() {
        gameAssistant = openAiService.retrieveAssistant("asst_F8CwRINxdF4Swh82R70d5z44");
    }

    @Override
    @Async
    public void startNewGame() {
        System.out.println("Starting game");

        ThreadRequest threadRequest = ThreadRequest.builder().build();
        currentThread = openAiService.createThread(threadRequest);
    }

    @Override
    @Async
    public void sayToGame(String input) {
        System.out.println("Sending message: " + input);

        GameMessage message = new GameMessage("user", input);

        MessageRequest messageRequest = MessageRequest.builder()
                .content(input)
                .role(message.getRole())
                .build();
        openAiService.createMessage(currentThread.getId(), messageRequest);

        RunCreateRequest runCreateRequest = RunCreateRequest.builder()
                .assistantId(gameAssistant.getId())
                .build();
        Run currentRun = openAiService.createRun(currentThread.getId(), runCreateRequest);

        System.out.println("Message sent to run " + currentRun.getId());

        String currentRunStatus = openAiService.retrieveRun(currentThread.getId(), currentRun.getId()).getStatus();
        System.out.println("Run status: " + currentRunStatus);
    }

    @Override
    public List<GameMessage> getAllMessagesInDialog() {
        OpenAiResponse<Message> listMessagesResponse = openAiService.listMessages(currentThread.getId());

        List<GameMessage> messagesInReverseOrder = listMessagesResponse.data.stream()
                .map(m -> new GameMessage(m.getRole(), m.getContent().get(0).getText().getValue()))
                .collect(Collectors.toList());

        Collections.reverse(messagesInReverseOrder);
        return messagesInReverseOrder;
    }
}
