package com.swooboo.chatgame.openai;

public interface OpenAiController {
    void saySystem(String text);
    void sayUser(String text);
    String chatSummary();
}
