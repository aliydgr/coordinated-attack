package me.ali.ds.hw4;

public class Analizer {
    
	private final static int GRAPH_SIZE = 10;
	private final static int ROUNDS = 100;
	private final static int N = 1000;
	private final static double AGREEING_PROB = 0.875;
	private final static double FAILURE_PROB = 0.2;
	
    public static void main(String[] args) {
    	int tcount = 0, fcount = 0, wdcount = 0, wacount = 0;
    	for (int j = 0; j < N; j++) {
    		CoordinatedAttack ca = new CoordinatedAttack(GRAPH_SIZE, ROUNDS, AGREEING_PROB);
			ca.run(FAILURE_PROB);
    		
			switch (ca.getStatus()) {
			case wrong_attack:
				wacount++;
				break;
			case wrong_decision:
				wdcount++;
				break;
			case right_agree:
				tcount++;
				break;
			case right_opposite:
				fcount++;
				break;
			}
		}
    	
    	System.out.print("wd=" + 100*wdcount/N + " t="+ 100*tcount/N + " f=" + 100*fcount/N + " wa=" + 100*wacount/N);
    	
    }
    
}
