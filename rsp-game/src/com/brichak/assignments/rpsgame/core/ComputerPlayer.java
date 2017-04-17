package com.brichak.assignments.rpsgame.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Pattern {

	private List<Byte> list;
	private int fromIncluding;
	private int toExcluding;

	public Pattern(List<Byte> list, int fromIncluding, int toExcluding) {
		this.list = list;
		this.fromIncluding = fromIncluding;
		this.toExcluding = toExcluding;
	}

	private List<Byte> getValues() {
		return list.subList(fromIncluding, toExcluding);
	}

	public byte getPrediction() {
		return list.get(toExcluding - 1); 
	}

	public Pattern getPatternMask() {
		return new Pattern(list, fromIncluding, toExcluding - 1);
	}
	
	@Override
	public int hashCode() {
		return getValues().stream().mapToInt(a -> a).reduce(1, (a, b) -> a*b);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pattern other = (Pattern) obj;
		if (getValues() == null) {
			if (other.getValues() != null)
				return false;
		} else if (!Arrays.equals(getValues().toArray(),other.getValues().toArray()))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return Arrays.deepToString(getValues().toArray());
	}
}

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
			result = converByteToChoice(resultPattern.getPrediction());
		}
		return result.getWinnerOfMe();
	}
	
	@Override
	public void setOpponentLastChoice(Choice choice) {
		opponentHistory.add(converChoiceToByte(choice));
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
	
	public List<Byte> getPatternMask(List<Byte> pattern) {
		return pattern.subList(pattern.size() - MAX_PATTERN_LENGTH, pattern.size() - 1);
	}
	
	private byte converChoiceToByte(Choice choice) {
		return (byte) choice.ordinal();
	}
	
	private Choice converByteToChoice(byte encodedChoice) {
		return Choice.values()[encodedChoice];
	}
}
