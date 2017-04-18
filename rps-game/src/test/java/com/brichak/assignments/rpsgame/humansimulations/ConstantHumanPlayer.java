package com.brichak.assignments.rpsgame.humansimulations;

import com.brichak.assignments.rpsgame.core.Choice;
import com.brichak.assignments.rpsgame.core.Player;

public class ConstantHumanPlayer implements Player {

	@Override
	public Choice play() {
		return Choice.Rock;
	}

	@Override
	public void setOpponentLastChoice(Choice choice) {
	}
	
}
