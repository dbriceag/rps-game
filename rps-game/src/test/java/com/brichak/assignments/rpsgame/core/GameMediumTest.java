package com.brichak.assignments.rpsgame.core;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

import com.brichak.assignments.rpsgame.core.ComputerPlayer;
import com.brichak.assignments.rpsgame.core.Game;
import com.brichak.assignments.rpsgame.humansimulations.CircularDeterministicHumanPlayer;
import com.brichak.assignments.rpsgame.humansimulations.CircularMixingStrategyPlayer;
import com.brichak.assignments.rpsgame.humansimulations.CircularProbabilisticHumanPlayer;
import com.brichak.assignments.rpsgame.humansimulations.ConstantHumanPlayer;
import com.brichak.assignments.rpsgame.humansimulations.HumanIntuitivePlayer;
import com.brichak.assignments.rpsgame.humansimulations.ProbabilisticMixingStrategyPlayer;

public class GameMediumTest {
	
	@Test
	public void computerPlayerVsConstantHumanPlayer() {
		Game game = new Game(new ComputerPlayer(), new ConstantHumanPlayer());
		int firstPlayerWin = play(game, 5000);
		assertThat(firstPlayerWin, greaterThan(5000 - 7*2));		
	}
	
	@Test
	public void computerPlayerVsCircularDeterministicHumanPlayerLength5() {
		Game game = new Game(new ComputerPlayer(), new CircularDeterministicHumanPlayer(5));
		int firstPlayerWin = play(game, 5000);
		assertThat(firstPlayerWin, greaterThan(4000));		
	}
	
	@Test
	public void computerPlayerVsCircularDeterministicHumanPlayerLength10() {
		Game game = new Game(new ComputerPlayer(), new CircularDeterministicHumanPlayer(10));
		int firstPlayerWin = play(game, 5000);
		assertThat(firstPlayerWin, greaterThan(3000));		
	}
	
	@Test
	public void computerPlayerVsCircularProbabilisticHumanPlayerLength5() {
		Game game = new Game(new ComputerPlayer(), new CircularProbabilisticHumanPlayer(5, 0.2f));
		int firstPlayerWin = play(game, 4000);
		assertThat(firstPlayerWin, greaterThan(1000));		
	}
	
	@Test
	public void computerPlayerVsDeterministicChangingStrategies() {
		Game game = new Game(new ComputerPlayer(), 
				new CircularMixingStrategyPlayer(
						new CircularDeterministicHumanPlayer(3),
						new CircularDeterministicHumanPlayer(5)
						));
				
		int firstPlayerWin = play(game, 5000);
		assertThat(firstPlayerWin, greaterThan(4000));		
	}
	
	@Test
	public void computerPlayerVsProbabilisticChangingStrategies() {
		Game game = new Game(new ComputerPlayer(), 
				new ProbabilisticMixingStrategyPlayer(
						new CircularDeterministicHumanPlayer(3),
						new CircularDeterministicHumanPlayer(5)
						));
				
		int firstPlayerWin = play(game, 5000);
		assertThat(firstPlayerWin, greaterThan(1000));		
	}
	
	@Test
	public void computerPlayerVsHumanIntuitivePlayer() {
		Game game = new Game(new ComputerPlayer(), 
				new HumanIntuitivePlayer());
				
		int firstPlayerWin = play(game, 5000);
		assertThat(firstPlayerWin, greaterThan(1000));		
	}
	
	private int play(Game game, int rounds) {
		int i = rounds-1;
		int firstPlayerWin = 0;
		do {
			System.out.println("------------ Round " + (rounds - i) + " -----------");
			firstPlayerWin += game.playOneRound();
			System.out.println("player1 played: " + game.getPlayer1LastChoice());
			System.out.println("player2 played: " + game.getPlayer2LastChoice());
			System.out.println("player1 totalwin: " + firstPlayerWin);
			System.out.println("");
		} while(i-- > 0);

		return firstPlayerWin;
	}
}
