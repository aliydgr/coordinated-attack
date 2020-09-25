package me.ali.coordinateattack.model;

public class AgreeingProcess extends Process {

	public AgreeingProcess(int graphSize, int index, int decisionRound) {
		super(graphSize, index, decisionRound);
	}

	@Override
	protected boolean opinion() {
		return true;
	}

}
