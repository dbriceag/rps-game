package com.brichak.assignments.rpsgame.core;

public class Game {

	private Player player1;
	private Player player2;
	private Choice player1LastChoice1;
	private Choice player2LastChoice2;

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
		player1LastChoice1 = player1.play();
		player2LastChoice2 = player2.play();
		
		player1.setOpponentLastChoice(player2LastChoice2);
		player2.setOpponentLastChoice(player1LastChoice1);
		
		return player1LastChoice1.winsOver(player2LastChoice2);
	}

	public Choice getPlayer1LastChoice1() {
		return player1LastChoice1;
	}

	public Choice getPlayer2LastChoice2() {
		return player2LastChoice2;
	}
	
}
