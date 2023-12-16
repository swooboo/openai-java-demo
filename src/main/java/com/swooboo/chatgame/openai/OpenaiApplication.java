package com.swooboo.chatgame.openai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.swooboo.chatgame"})
public class OpenaiApplication {
	public static void main(String[] args) {
		SpringApplication.run(OpenaiApplication.class, args);
	}
}
