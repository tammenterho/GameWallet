package com.gameWallet.gameWallet.exceptions;

public class Exceptions {
	
	   public static class InsufficientFundsException extends Exception {
	        public InsufficientFundsException(String message) {
	            super(message);
	        }
	    }

}
