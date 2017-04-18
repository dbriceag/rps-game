package com.brichak.assignments.rpsgame.humansimulations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.brichak.assignments.rpsgame.core.Choice;
import com.brichak.assignments.rpsgame.core.Player;


public class CircularDeterministicHumanPlayer implements Player {

	private int cycleLength = 5;
	private List<Choice> choices = new LinkedList<>();
	private int offset = 0;
	
	public CircularDeterministicHumanPlayer(int cycleLength) {
		this.cycleLength = cycleLength;
		for (int i = 0; i < cycleLength; i++) {
			choices.add(Choice.randomChoice());	
		}
		System.out.println("CircularDeterministicHumanPlayer: " + Arrays.asList(choices));
	}

	@Override
	public Choice play() {
		offset = offset % cycleLength;
		return choices.get(offset++);
	}

	@Override
	public void setOpponentLastChoice(Choice choice) {
	}

}
