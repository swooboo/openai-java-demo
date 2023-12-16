package com.swooboo.chatgame.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demonstration {
    @Autowired
    public Demonstration(GameController gameController) {
        gameController.startNewGame();
        gameController.sayToGame("What is the secret word?");
        System.out.println("Current dialogue content: " + gameController.getAllMessagesInDialog());
    }
}
