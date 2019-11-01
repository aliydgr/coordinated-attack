package me.ali.ds.hw4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import me.ali.ds.hw4.model.AgreeingProcess;
import me.ali.ds.hw4.model.OppositionProcess;
import me.ali.ds.hw4.model.Process;

public class CoordinatedAttack {
    
	public static enum Status {
		wrong_attack,//someone attack and someone don't
		wrong_decision,//all agree but don't attack
		right_agree,
		right_opposite
	}
	
	private static Random random = new Random();

	private List<Process> processList;
	private boolean expexted;
	private int round;
	private Status status;
	
	public CoordinatedAttack(int graphSize, int round, double agreeingProb) {
		this.processList = new ArrayList<Process>();
		this.round = round;
		
		expexted = true;
		for (int i = 0; i < graphSize; i++) {
			double prob = random.nextDouble();
			expexted = (prob < agreeingProb);
			processList.add((prob < agreeingProb) ?
					new AgreeingProcess(graphSize, i, round) :
					new OppositionProcess(graphSize, i, round));
		}
	}
	
	
	public void run(double failureProb) {
		for (int i = 0; i < round; i++) {
			for (Process p1 : processList) {
				for (Process p2 : processList) {
					if(p1 == p2)
						continue;
					
					if(random.nextDouble() > failureProb)
						p1.receive(p2.getStates(), p2.getKey());
				}
				p1.setRound(i+1);
			}
		}
		
		Set<Boolean> decision = new HashSet<Boolean>();
		for (Process process : processList)
			decision.add(process.makeDecision());

		if(decision.size() != 1) {
			status = Status.wrong_attack;
			return;
		}
		
		//decision.size()=1
		for (Boolean d : decision) {
			if(!d.equals(expexted)) {
				status = Status.wrong_decision;
				return;
			}
			status = d ? Status.right_agree : Status.right_opposite;
		}
	}
	
	
	public Status getStatus() {
		return status;
	}
	
}
