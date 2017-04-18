package com.brichak.assignments.rpsgame.core;

import java.util.Random;

public enum Choice {
	
	Rock, Paper, Scissors;

	public static Choice randomChoice() {
		int index = new Random().nextInt(3);
		return Choice.values()[index];
	}
	
	public int winsOver(Choice choice) {
		if(choice == this) {
			return 0;
		} else if(choice.getWinnerOfMe() == this) {
			return 1;
		} else {
			return -1;
		}
	}

	public Choice getWinnerOfMe() {
		if(this == Choice.Paper) {
			return Choice.Scissors;
		} else if(this == Choice.Scissors) {
			return Choice.Rock;
		} else {
			return Choice.Paper;
		}
	}
}
