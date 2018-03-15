package com.kchmielewski.sda.java6.spring14java.player.repository;

import com.kchmielewski.sda.java6.spring14java.player.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
}
