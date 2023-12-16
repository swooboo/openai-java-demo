package com.swooboo.chatgame.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.swooboo.chatgame"})
public class ChatGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatGameApplication.class, args);
    }
}
