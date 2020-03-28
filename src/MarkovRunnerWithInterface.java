
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
		markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public static void main(String[] args) {
    	MarkovRunnerWithInterface markov = new MarkovRunnerWithInterface();
        /*FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 88;
		
        MarkovZero mz = new MarkovZero();
		markov.runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
		markov.runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
		markov.runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
		markov.runModel(mFour, st, size, seed);
*/
		markov.testHashMap();
    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public void testHashMap() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		//String st = "yes-this-is-a-thin-pretty-pink-thistle";
		st = st.replace('\n', ' ');
		int size = 50;
		int seed = 615;
		int order = 5;
		EfficientMarkovModel emm = new EfficientMarkovModel(order);
		runModel(emm, st, size, seed);
	}

	public void compareMethods() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		//st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 42;

		MarkovModel markov = new MarkovModel(2);
		long startTime = System.currentTimeMillis();
		runModel(markov, st, size, seed);
		long endTime = System.currentTimeMillis();
		System.out.println(markov.toString() + " took " + (endTime - startTime) + "ms");

		EfficientMarkovModel eff = new EfficientMarkovModel(2);
		startTime = System.currentTimeMillis();
		runModel(eff, st, size, seed);
		endTime = System.currentTimeMillis();
		System.out.println(eff.toString() + " took " + (endTime - startTime) + "ms");
	}
}
