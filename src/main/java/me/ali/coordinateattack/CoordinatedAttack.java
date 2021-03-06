package me.ali.coordinateattack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import me.ali.coordinateattack.model.AgreeingProcess;
import me.ali.coordinateattack.model.OppositionProcess;
import me.ali.coordinateattack.model.Process;

public class CoordinatedAttack {
    
	public static enum Status {
		wrong_attack,//someone attack and someone don't
		wrong_agree,//all agree but not not attacking
		wrong_opposite,//all opposite but attacking
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
					
					if(random.nextDouble() >= failureProb)
						p1.receive(p2.getStates(), p2.getKey());
				}
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
			if(!d.equals(expexted))
				status = d ? Status.wrong_agree : Status.wrong_opposite;
			else
				status = d ? Status.right_agree : Status.right_opposite;
		}
	}
	
	
	public Status getStatus() {
		return status;
	}
	
}
