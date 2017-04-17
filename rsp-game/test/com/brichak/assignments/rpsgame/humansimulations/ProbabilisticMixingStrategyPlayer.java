package com.brichak.assignments.rpsgame.humansimulations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.brichak.assignments.rpsgame.core.Choice;
import com.brichak.assignments.rpsgame.core.Player;


public class ProbabilisticMixingStrategyPlayer implements Player {

	private List<Player> strategies = new LinkedList<>();
	
	public ProbabilisticMixingStrategyPlayer(Player ... strategies) {
		super();
		this.strategies = Arrays.asList(strategies);
	}

	@Override
	public Choice play() {
		int index = new Random().nextInt(strategies.size());
		return strategies.get(index).play();
	}

	@Override
	public void setOpponentLastChoice(Choice choice) {
	}

}
