package com.kchmielewski.sda.java6.spring14java.player.service;

import com.kchmielewski.sda.java6.spring14java.player.entity.PlayerEntity;
import com.kchmielewski.sda.java6.spring14java.player.model.Player;
import com.kchmielewski.sda.java6.spring14java.player.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayerServiceTest {
    private final PlayerRepository repository = mock(PlayerRepository.class);
    private final PlayerService service = new PlayerService(repository);
    private final List<PlayerEntity> players = Arrays.asList(new PlayerEntity("Adam", "Lallana"),
            new PlayerEntity("Philippe", "Coutinho"));

    @Before
    public void setUp() {
        given(repository.findAll()).willReturn(players);
    }

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
    public void allFindsAllInRepository() {
        assertThat(service.all()).containsExactly(new Player("Adam", "Lallana"), new Player("Philippe", "Coutinho"));
    }

    @Test
    public void addSavesInRepository() {
        service.add(new Player("Adam", "Lallana"));

        verify(repository).save(new PlayerEntity("Adam", "Lallana"));
    }

    @Test
    public void removeDeletesFromRepository() {
        service.remove(new Player("Adam", "Lallana"));

        verify(repository).delete(new PlayerEntity("Adam", "Lallana"));
    }

    @Test
    public void rangeReturnsSublist() {
        assertThat(service.range(0, 1)).hasSize(1).containsExactly(new Player("Adam", "Lallana"));
    }
}