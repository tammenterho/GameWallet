package com.gameWallet.gameWallet.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gameWallet.gameWallet.entities.GameEvent;

public interface GameEventRepository extends JpaRepository<GameEvent, Long>{

}
