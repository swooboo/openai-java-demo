package com.swooboo.chatgame.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameMessage {
    private String role;
    private String content;

    @Override
    public String toString() {
        return role + ": " + content;
    }
}
