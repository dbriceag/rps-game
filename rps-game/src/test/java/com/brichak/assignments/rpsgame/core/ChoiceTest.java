package com.brichak.assignments.rpsgame.core;

import static com.brichak.assignments.rpsgame.core.Choice.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

public class ChoiceTest {

	@Test
	public void rock_winsOver_scissors(){
		assertThat(Rock.winsOver(Scissors), Is.is(1));	
	}
	
	@Test
	public void scissors_winsOver_paper(){
		assertThat(Scissors.winsOver(Paper), Is.is(1));	
	}
	
	@Test
	public void paper_winsOver_rock(){
		assertThat(Paper.winsOver(Rock), Is.is(1));	
	}
	
}
