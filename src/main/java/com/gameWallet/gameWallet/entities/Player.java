package com.gameWallet.gameWallet.entities;

import com.gameWallet.gameWallet.exceptions.Exceptions.InsufficientFundsException;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Player {
	
	@Id
	private Long playerId;
	private String name;
	private Long balance;
	
	public Player () {
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}


	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	public long deductFromBalance(Long amount) throws InsufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (balance >= amount) {
            balance -= amount;
            return balance;
        } else {
            throw new InsufficientFundsException("Insufficient funds");
        }
    }
	
	public long addToBalance(long amount) {
	    if (amount < 0) {
	            throw new IllegalArgumentException("Amount cannot be negative");
	    }
	    balance += amount;
	    return balance;
	    }

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", Name=" + name + ", balance="
				+ balance + "]";
	}

}
