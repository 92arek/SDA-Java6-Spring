package com.kchmielewski.sda.java6.spring14java.player.service;

import com.kchmielewski.sda.java6.spring14java.player.entity.PlayerEntity;
import com.kchmielewski.sda.java6.spring14java.player.model.Player;
import com.kchmielewski.sda.java6.spring14java.player.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class PlayerService {
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> all() {
        return toPlayerDto(repository.findAll());
    }

    public List<Player> range(int from, int to) {
        checkArgument(from >= 0, "From must be non-negative, %s given", from);
        checkArgument(to >= 0, "From must be non-negative, %s given", to);
        checkArgument(from <= to, "From must be smaller than to: %s, %s", from, to);

        return toPlayerDto(repository.findAll().subList(from, to));
    }

    public void add(Player player) {
        checkNotNull(player, "PlayerEntity cannot be null");
        repository.save(toEntity(player));
    }

    public void remove(Player player) {
        checkNotNull(player, "PlayerEntity cannot be null");
        repository.delete(toEntity(player));
    }

    private PlayerEntity toEntity(Player player) {
        return new PlayerEntity(player.getName(), player.getSurname());
    }

    private Player toPlayerDto(PlayerEntity entity) {
        return new Player(entity.getName(), entity.getSurname());
    }

    private List<Player> toPlayerDto(List<PlayerEntity> entities) {
        return entities.stream().map(this::toPlayerDto).collect(Collectors.toList());
    }
}
