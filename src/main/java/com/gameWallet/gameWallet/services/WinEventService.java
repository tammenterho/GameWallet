package com.gameWallet.gameWallet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameWallet.gameWallet.entities.GameEvent;
import com.gameWallet.gameWallet.entities.Player;
import com.gameWallet.gameWallet.repos.GameEventRepository;
import com.gameWallet.gameWallet.repos.PlayerRepository;

@Service
public class WinEventService {
	
	private final PlayerRepository playerRepo;
	private final GameEventRepository gameRepo;
	
	@Autowired
	public WinEventService (PlayerRepository playerRepo, GameEventRepository gameRepo) {
		this.playerRepo = playerRepo;
		this.gameRepo = gameRepo;
	}

	public Long winGame(Long amount, Long playerId, Long winEventId) {
		Player player = playerRepo.findById(playerId)
	            .orElseThrow(() -> new RuntimeException("Player not found with ID: " + playerId));

	    Optional<GameEvent> existingGameEvent = gameRepo.findById(winEventId);
	    if (existingGameEvent.isPresent()) {
	        throw new RuntimeException("Event with ID: " + winEventId + " already exists");
	    }
		    
		  GameEvent winEvent = new GameEvent();
		  
		  winEvent.setEventId(winEventId);
		  winEvent.setPlayerId(playerId);
		  winEvent.setEventTime();
		  winEvent.setEventType("WIN");
		  gameRepo.save(winEvent);
		  
		  Long newBalance = player.addToBalance(amount);
		  
		  player.setBalance(newBalance);
		  playerRepo.save(player);
		  return newBalance;
	}
}
