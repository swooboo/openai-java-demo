package com.swooboo.chatgame.web;

import com.swooboo.chatgame.game.GameController;
import com.swooboo.chatgame.game.GameMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping(GameChatWebController.CHAT_PAGE_PATH)
public class GameChatWebController {

    public static final String CHAT_TEMPLATE_NAME = "chat";
    public static final String CHAT_PAGE_PATH = "/chatgame/" + CHAT_TEMPLATE_NAME;
    @Autowired
    GameController gameController;

    @SuppressWarnings("SameReturnValue")
    @GetMapping
    public String getGameChatPage(Model model) {

        List<GameMessage> gameMessages = gameController.getAllMessagesInDialog();

        model.addAttribute("gameMessages", gameMessages);
        model.addAttribute("lastStatus", gameController.getCurrentGameStatus());

        return CHAT_TEMPLATE_NAME;
    }


    @PostMapping
    @ResponseBody
    public RedirectView sendMessage(@RequestParam("message") String message) {
        System.out.println("Request to send message with POST received: " + message);

        gameController.sayToGame(message);

        // Redirect to the GET endpoint
        return new RedirectView(CHAT_PAGE_PATH, true);
    }
}
