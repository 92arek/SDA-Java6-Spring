package com.kchmielewski.sda.java6.spring11java.player.service;

import com.kchmielewski.sda.java6.spring11java.player.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class PlayerService {
    private final List<Player> players = new ArrayList<>();

    public List<Player> all() {
        return Collections.unmodifiableList(players);
    }

    public List<Player> range(int from, int to) {
        checkArgument(from >= 0, "From must be non-negative, %s given", from);
        checkArgument(to >= 0, "From must be non-negative, %s given", to);
        checkArgument(from <= to, "From must be smaller than to: %s, %s", from, to);

        return Collections.unmodifiableList(players.subList(from, to));
    }

    public void add(Player player) {
        checkNotNull(player, "Player cannot be null");
        players.add(player);
    }

    public void remove(Player player) {
        checkNotNull(player, "Player cannot be null");
        players.remove(player);
    }
}
