package me.ali.coordinateattack;

public class Analizer {
    
	private final static int GRAPH_SIZE = 10;
	private final static int ROUNDS = 100;
	private final static int N = 1000;
	private final static double AGREEING_PROB = 0.875;
	private final static double FAILURE_PROB = 0.2;
	
    public static void main(String[] args) {
    	int tcount = 0, fcount = 0, wtcount = 0, wfcount = 0, wcount = 0;
    	for (int j = 0; j < N; j++) {
    		CoordinatedAttack ca = new CoordinatedAttack(GRAPH_SIZE, ROUNDS, AGREEING_PROB);
			ca.run(FAILURE_PROB);
    		
			switch (ca.getStatus()) {
			case wrong_attack:
				wcount++;
				break;
			case wrong_agree:
				wtcount++;
				break;
			case wrong_opposite:
				wfcount++;
				break;
			case right_agree:
				tcount++;
				break;
			case right_opposite:
				fcount++;
				break;
			}
		}
    	
    	System.out.print("wt=" + 100*wtcount/N + "\tt="+ 100*tcount/N + "\twf=" + 100*wfcount/N + "\tf=" + 100*fcount/N + "\tw=" + 100*wcount/N);
    	
    }
    
}
