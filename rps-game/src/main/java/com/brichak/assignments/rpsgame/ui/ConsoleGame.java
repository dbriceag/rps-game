package com.brichak.assignments.rpsgame.ui;

import com.brichak.assignments.rpsgame.core.ComputerPlayer;
import com.brichak.assignments.rpsgame.core.Game;

public class ConsoleGame {

	public static void main(String[] args) {
		new ConsoleGame().play();
	}

	private void play() {
		System.out.println("Welcome to Rock Paper Scissors game!");
		Game game = new Game(new ConsolePlayer(), new ComputerPlayer());
		int round = 1;
		int consolePlayerWin = 0;
		do {
			System.out.println("------------ Round " + round + " -----------");
			consolePlayerWin += game.playOneRound();
			System.out.println("Human played: " + game.getPlayer1LastChoice());
			System.out.println("Computer played: " + game.getPlayer2LastChoice());
			System.out.println("Human total win: " + consolePlayerWin);
			System.out.println("");
			round++;
		} while(true);
	}
}
