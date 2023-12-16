package com.swooboo.chatgame.game;

import java.util.List;

public interface GameController {

    /**
     * Starts a new game session.
     */
    void startNewGame();

    /**
     * Processes the player's input in the game.
     *
     * @param input The input string from the player.
     * @return The response from the game.
     */
    String saySomethingToGame(String input);


    /**
     * Retrieves all messages in the game dialog.
     *
     * @return An instance of GameMessage containing all dialog messages.
     */
    List<GameMessage> getAllMessagesInDialog();
}
