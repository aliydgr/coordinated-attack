package me.ali.ds.hw4.model;

public class OppositionProcess extends Process {

	public OppositionProcess(int graphSize, int index, int decisionRound) {
		super(graphSize, index, decisionRound);
	}

	@Override
	protected boolean opinion() {
		return false;
	}

}
