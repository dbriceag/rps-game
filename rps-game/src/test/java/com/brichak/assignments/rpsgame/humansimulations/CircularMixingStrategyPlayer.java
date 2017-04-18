package com.brichak.assignments.rpsgame.humansimulations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.brichak.assignments.rpsgame.core.Choice;
import com.brichak.assignments.rpsgame.core.Player;

public class CircularMixingStrategyPlayer implements Player {

	private int offset = 0;
	private List<Player> strategies = new LinkedList<>();
	
	public CircularMixingStrategyPlayer(Player ... strategies) {
		super();
		this.strategies = Arrays.asList(strategies);
	}

	@Override
	public Choice play() {
		offset = offset % strategies.size();
		return strategies.get(offset++).play();
	}

	@Override
	public void setOpponentLastChoice(Choice choice) {
	}

}
