package com.gameWallet.gameWallet.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameWallet.gameWallet.entities.GameEvent;
import com.gameWallet.gameWallet.entities.Player;
import com.gameWallet.gameWallet.exceptions.Exceptions.InsufficientFundsException;
import com.gameWallet.gameWallet.repos.GameEventRepository;
import com.gameWallet.gameWallet.repos.PlayerRepository;

@Service
public class PurchaseService {
	
	private final PlayerRepository playerRepo;
	private final GameEventRepository gameRepo;
	
	@Autowired
	public PurchaseService (PlayerRepository playerRepo, GameEventRepository gameRepo) {
		this.playerRepo = playerRepo;
		this.gameRepo = gameRepo;
	}

	public Long purchaseGame(Long amount, Long playerId, Long eventId) throws InsufficientFundsException {
	    // For testing
		
	    
	    Player player = playerRepo.findById(playerId)
                .orElseGet(() -> {
                    Player newPlayer = new Player();
                    newPlayer.setPlayerId(playerId);
                    newPlayer.setBalance(500L);
                    // newPlayer.setName(name); if name is available
                    return playerRepo.save(newPlayer);
                });
	    
	    Optional<GameEvent> existingGameEvent = gameRepo.findById(eventId);
	    if(existingGameEvent.isPresent()) {
	        throw new RuntimeException("Game already purchased with ID: " + eventId);
	    }
	    
	    Long newBalance = player.deductFromBalance(amount);

	    GameEvent gameEvent = new GameEvent();
	    gameEvent.setEventId(eventId);
	    gameEvent.setPlayerId(playerId);
	    gameEvent.setEventTime(); 
	    gameEvent.setEventType("PURCHASE");
	    gameEvent.setAmount(amount);
		gameRepo.save(gameEvent);

	    
	    player.setPlayerId(playerId);
	    player.setBalance(newBalance);
	    playerRepo.save(player);
	    return newBalance;
	}

	
	

}
