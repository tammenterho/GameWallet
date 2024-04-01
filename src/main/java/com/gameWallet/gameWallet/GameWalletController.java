package com.gameWallet.gameWallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gameWallet.gameWallet.services.WinEventService;
import com.gameWallet.gameWallet.exceptions.Exceptions.InsufficientFundsException;
import com.gameWallet.gameWallet.services.PurchaseService;


@RestController
public class GameWalletController {
	
	private final PurchaseService playerService;
	private final WinEventService gameEventService;
	
	
	 @Autowired
	  public GameWalletController(PurchaseService playerService, WinEventService gameEventService) {
	        this.playerService = playerService;
	        this.gameEventService = gameEventService;
	       
	    }
	
	
	 @PutMapping("/players/{playerId}/purchase/{eventId}")
	 public ResponseEntity<?> purchase(@PathVariable Long playerId, @PathVariable Long eventId, @RequestBody Long amount) {
	     try {
	         Long balance = playerService.purchaseGame(amount, playerId, eventId);
	         return ResponseEntity.ok("Game purchased successfully\n" + "Balance: " + balance);
	     } catch (InsufficientFundsException e) {
	         return ResponseEntity.badRequest().body("Error purchasing game: Insufficient funds");
	     } catch (RuntimeException e) {
	         return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	     }
	 }

	
	@PutMapping("/players/{playerId}/win/{winEventId}")
	public ResponseEntity<String>collectWin(@PathVariable Long playerId, @PathVariable Long winEventId, @RequestBody Long amount) {
		Long balance = gameEventService.winGame(amount, playerId, winEventId);
		return ResponseEntity.ok("Win collected\n" + "Balance: " + balance);
		
	}

}
