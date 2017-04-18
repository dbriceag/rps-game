package com.brichak.assignments.rpsgame.humansimulations;

import java.util.Random;

import com.brichak.assignments.rpsgame.core.Choice;


public class CircularProbabilisticHumanPlayer extends CircularDeterministicHumanPlayer {
	
	private float probabilisticDeviation;

	/**
	 * 
	 * @param cycleLength
	 * @param probabilisticDeviation - probability, with which the result value shall be
	 * 	changed to a random, uniformely distributed value
	 */
	public CircularProbabilisticHumanPlayer(int cycleLength, float probabilisticDeviation) {
		super(cycleLength);
		this.probabilisticDeviation = probabilisticDeviation;
	}

	@Override
	public Choice play() {
		return probabilisticChange(super.play());
	}

	private Choice probabilisticChange(Choice choice) {
		if(new Random().nextFloat() < probabilisticDeviation){
			return Choice.randomChoice();
		}
		return choice;
	}

	@Override
	public void setOpponentLastChoice(Choice choice) {
	}

}
