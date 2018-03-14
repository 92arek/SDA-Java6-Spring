package com.kchmielewski.sda.java6.spring11java.player.web;

import com.kchmielewski.sda.java6.spring11java.player.model.Player;
import com.kchmielewski.sda.java6.spring11java.player.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("players")
@SessionAttributes("playerSession")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @ModelAttribute
    public Player defaultPlayer() {
        return new Player("Fill my name...", "...and my surname!");
    }

    @ModelAttribute
    public PlayerSession session() {
        return new PlayerSession();
    }

    @GetMapping("form")
    public String display(Model model) {
        model.addAttribute("players", playerService.all());

        return "players";
    }

    @GetMapping("/get/{from}/{to}")
    public String display(@PathVariable int from, @PathVariable int to, Model model) {
        model.addAttribute("players", playerService.range(from, to));

        return "players";
    }

    @PostMapping("form")
    public String add(@Valid Player player, BindingResult bindingResult, Model model, @ModelAttribute PlayerSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("players", playerService.all());
            return "players";
        }
        playerService.add(player);
        session.setCounter(session.getCounter() + 1);
        session.setMostRecentPlayer(player);
        model.addAttribute("players", playerService.all());

        return "redirect:/players/form";
    }

    @PostMapping("remove")
    public String remove(Player player, Model model) {
        playerService.remove(player);
        model.addAttribute("players", playerService.all());

        return "players";
    }
}
