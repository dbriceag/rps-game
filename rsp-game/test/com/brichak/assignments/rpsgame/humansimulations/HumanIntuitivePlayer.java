package com.brichak.assignments.rpsgame.humansimulations;

import com.brichak.assignments.rpsgame.core.Choice;
import com.brichak.assignments.rpsgame.core.Player;

public class HumanIntuitivePlayer implements Player {

	private Choice opponentLastChoice = null;
	private Choice myLastChoice;

	@Override
	public Choice play() {
		if(opponentLastChoice == null) {
			myLastChoice = Choice.randomChoice();
		} else {
			myLastChoice = makeIntuitiveChoice();
		}
		return myLastChoice;
	}

	private Choice makeIntuitiveChoice() {
		int isWonLastRound = myLastChoice.winsOver(opponentLastChoice);
		if(isWonLastRound == 0) {
			return Choice.randomChoice();
		} else if(isWonLastRound >0){
			return opponentLastChoice;
		} else {
			return opponentLastChoice.getWinnerOfMe();
		}
	}

	@Override
	public void setOpponentLastChoice(Choice opponentLastChoice) {
		this.opponentLastChoice = opponentLastChoice;
	}

}
