package com.gameWallet.gameWallet.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GameEvent {
	
	private LocalDateTime eventTime;
	private Long playerId;
	@Id
	private Long eventId;
	private String eventType;
	private Long amount;
	
	public GameEvent () {
		
	}

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public void setEventTime() {
		this.eventTime = LocalDateTime.now();;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "GameEvent [eventTime=" + eventTime + ", playerId=" + playerId + ", eventId=" + eventId + ", eventType="
				+ eventType + ", amount=" + amount + "]";
	}
	
	
}
