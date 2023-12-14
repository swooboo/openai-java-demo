package com.swooboo.chatgame.openai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demonstration {
    @Autowired
    public Demonstration(OpenAiController openAiController) {
        System.out.println("Constructing Controller");
        System.out.println("Starting test");
        openAiController.saySystem("You are a calculator.");
        openAiController.sayUser("How much is 22 divided by 7?");
        System.out.println("Finished test, summary:");
        System.out.println(openAiController.chatSummary());
    }
}
