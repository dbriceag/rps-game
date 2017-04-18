package com.brichak.assignments.rpsgame.core;

public class Game {

	private Player player1;
	private Player player2;
	private Choice player1LastChoice;
	private Choice player2LastChoice;

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	/**
	 * 
	 * @return one, if player 1 has won, 
	 * 0 if draw
	 * -1 if loose
	 */
	public int playOneRound() {
		player1LastChoice = player1.play();
		player2LastChoice = player2.play();
		
		player1.setOpponentLastChoice(player2LastChoice);
		player2.setOpponentLastChoice(player1LastChoice);
		
		return player1LastChoice.winsOver(player2LastChoice);
	}

	public Choice getPlayer1LastChoice() {
		return player1LastChoice;
	}

	public Choice getPlayer2LastChoice() {
		return player2LastChoice;
	}
	
}
