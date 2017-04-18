package com.brichak.assignments.rpsgame.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.brichak.assignments.rpsgame.core.Choice;
import com.brichak.assignments.rpsgame.core.Player;

class ConsolePlayer implements Player {

	private Map<String, Choice> validOptions = new HashMap<>();

	public ConsolePlayer() {
		validOptions.put("1", Choice.Rock);
		validOptions.put("2", Choice.Paper);
		validOptions.put("3", Choice.Scissors);
	}
	
	@Override
	public Choice play() {
		String choice;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Please select: ");
			System.out.println("1 - Rock");
			System.out.println("2 - Paper");
			System.out.println("3 - Scissors");	
	 
			choice = sc.nextLine();
		} while (!validOptions.keySet().contains(choice));
		
		return validOptions.get(choice);
	}

	@Override
	public void setOpponentLastChoice(Choice choice) {
	}

}
