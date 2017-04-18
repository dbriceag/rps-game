package com.brichak.assignments.rpsgame.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ComputerPlayer implements Player {
	private static final int MAX_PATTERN_LENGTH=7;
	private List<Byte> opponentHistory = new ArrayList<>();
	private Map<Pattern, Integer> patternToFrequencyMap = new HashMap<>();
	private Map<Pattern, List<Pattern>> maskToPatternsMap = new HashMap<>();
	
	public Choice play() {
		if(patternToFrequencyMap.size() == 0){
			return Choice.randomChoice();
		}
		Pattern lastPatternMask = new Pattern(opponentHistory, opponentHistory.size() - MAX_PATTERN_LENGTH+1, opponentHistory.size());
		List<Pattern> candidatePatterns = maskToPatternsMap.get(lastPatternMask);
		Choice result;
		if(candidatePatterns == null) {
			result = Choice.randomChoice();
		} else {
			Pattern resultPattern = candidatePatterns.stream()
						.max((p1,p2) -> 
							Integer.compare(patternToFrequencyMap.get(p1), patternToFrequencyMap.get(p1)))
						.get();
			result = decodeChoice(resultPattern.getPrediction());
		}
		return result.getWinnerOfMe();
	}
	
	@Override
	public void setOpponentLastChoice(Choice choice) {
		opponentHistory.add(encodeChoice(choice));
		if(opponentHistory.size() >= MAX_PATTERN_LENGTH){
			addLastPattern();
		}
	}

	private void addLastPattern() {
		Pattern pattern = new Pattern(opponentHistory, opponentHistory.size() - MAX_PATTERN_LENGTH, opponentHistory.size());
		addPatternToFrequencyMap(pattern);
		addPatternToPatternMaskIndex(pattern);
	}

	private void addPatternToFrequencyMap(Pattern pattern) {
		Integer frequency = patternToFrequencyMap.get(pattern);
		if(frequency == null){
			patternToFrequencyMap.put(pattern, 1);
		} else {
			patternToFrequencyMap.put(pattern, frequency+1);
		}
	}

	private void addPatternToPatternMaskIndex(Pattern pattern) {
		Pattern patternMask = pattern.getPatternMask();
		
		List<Pattern> patterns = maskToPatternsMap.get(patternMask);
		if(patterns == null) {
			List<Pattern> newPatterns = new LinkedList<>();
			newPatterns.add(pattern);
			maskToPatternsMap.put(patternMask, newPatterns);
		} else {
			patterns.add(pattern);
		}
	}
	
	private byte encodeChoice(Choice choice) {
		return (byte) choice.ordinal();
	}
	
	private Choice decodeChoice(byte encodedChoice) {
		return Choice.values()[encodedChoice];
	}
}
