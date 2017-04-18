package com.brichak.assignments.rpsgame.core;

import java.util.Arrays;
import java.util.List;

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
