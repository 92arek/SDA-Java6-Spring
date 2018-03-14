package com.kchmielewski.sda.java6.spring11java.player.service;

import com.kchmielewski.sda.java6.spring11java.player.model.Player;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerServiceTest {
    private final PlayerService service = new PlayerService();

    @Test
    public void forNullAddThrowsException() {
        assertThatThrownBy(() -> service.add(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void forNullRemoveThrowsException() {
        assertThatThrownBy(() -> service.remove(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void rangeForNegativeFromThrowsException() {
        assertThatThrownBy(() -> service.range(-1, 0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void rangeForNegativeToThrowsException() {
        assertThatThrownBy(() -> service.range(0, -1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void rangeForFromGreaterThanToThrowsException() {
        assertThatThrownBy(() -> service.range(5, 3)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void addIncreasesSizeOfPlayers() {
        service.add(new Player("Adam", "Lallana"));

        assertThat(service.all()).hasSize(1).containsExactly(new Player("Adam", "Lallana"));
    }

    @Test
    public void removeDecreasesSizeOfPlayers() {
        service.add(new Player("Adam", "Lallana"));
        service.add(new Player("Philippe", "Coutinho"));
        service.remove(new Player("Adam", "Lallana"));

        assertThat(service.all()).hasSize(1).containsExactly(new Player("Philippe", "Coutinho"));
    }

    @Test
    public void rangeReturnsSublist() {
        service.add(new Player("Adam", "Lallana"));
        service.add(new Player("Philippe", "Coutinho"));

        assertThat(service.range(0, 1)).hasSize(1).containsExactly(new Player("Adam", "Lallana"));
    }
}