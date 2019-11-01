package me.ali.ds.hw4.model;

import java.util.Random;

import lombok.Getter;

public abstract class Process {

	private final int graphSize;
	private final int index;
	
	@Getter
	private Integer key;
	@Getter
	private State[] states;
	
	public Process(int graphSize, int index, int decisionRound) {
		this.graphSize = graphSize;
		this.index = index;
		this.states = new State[graphSize];
		for (int i = 0; i < states.length; i++)
			states[i] = (i == index) ? new State(opinion(), 0) : new State(null, -1);
		
		if(index == 0)
			this.key = new Random().nextInt(decisionRound) + 1;
	}
	
	private int getLevel() {
		return this.states[index].getLevel();
	}
	
	private void setLevel(int level) {
		this.states[index].setLevel(level);
	}
	
	public void receive(State[] message, Integer key) {
		if(key != null)
			this.key = key;
		
		int level = Integer.MAX_VALUE;
		for (int i = 0; i < graphSize; i++) {
			if(i == index)
				continue;
			
			level = Math.min(message[i].getLevel(), level); 
			states[i].setLevel(Math.max(message[i].getLevel(), states[i].getLevel()));
			if(message[i].getValue() != null)
				this.states[i].setValue(message[i].getValue());
		}
		setLevel(level + 1);
	}
	
	public boolean makeDecision() {
		if(key == null || getLevel() < key)
			return false;
		for (State state : states)
			if(Boolean.FALSE.equals(state.getValue()))
				return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "i=" + index + ", level=" + getLevel() + ", key=" + key;
	}
	
	protected abstract boolean opinion();
	
}
