package com.brichak.assignments.rpsgame.core;

public interface Player {
	Choice play();
	void setOpponentLastChoice(Choice choice);
}
