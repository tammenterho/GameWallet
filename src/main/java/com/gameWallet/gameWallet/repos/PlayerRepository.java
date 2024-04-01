package com.gameWallet.gameWallet.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gameWallet.gameWallet.entities.Player;

public interface PlayerRepository extends JpaRepository <Player, Long>{

	
}
