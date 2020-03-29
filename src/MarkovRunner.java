import edu.duke.*;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */
public class MarkovRunner {
    public static void main(String[] args) {
		FileResource fr = new FileResource();
		MarkovRunner marko = new MarkovRunner();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setRandom(1024);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			marko.printOut(text);
		}
	}

	public void runMarkovOne() {
		FileResource fr = new FileResource();
		MarkovRunner marko = new MarkovRunner();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setRandom(365);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			marko.printOut(text);
		}
		//Tester test = new Tester();
		//test.testGetFollowsWithFile();
	}

	public void runMarkovFour() {
		MarkovRunner marko = new MarkovRunner();
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovFour markov = new MarkovFour();
		markov.setRandom(715);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			marko.printOut(text);
		}
	}

	public void runMarkovModel() {
		MarkovRunner marko = new MarkovRunner();
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovModel markov = new MarkovModel(7);
		markov.setRandom(953);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			marko.printOut(text);
		}
	}

	public void runModel(IMarkovModel markov, String text, int size){
		markov.setTraining(text);
		System.out.println("running with " + markov);
		for(int k=0; k < 3; k++){
			String st = markov.getRandomText(size);
			printOut(st);
		}
	}

	public void runModel(IMarkovModel markov, String text, int size, int seed){
		markov.setTraining(text);
		markov.setRandom(seed);
		System.out.println("running with " + markov);
		for(int k=0; k < 3; k++){
			String st = markov.getRandomText(size);
			printOut(st);
		}
	}
/*
	public static void main(String[] args) {
		MarkovRunner marko = new MarkovRunner();
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');

		MarkovWordOne markovWord = new MarkovWordOne();
		MarkovWordTwo markovWord2 = new MarkovWordTwo();
		MarkovWord markov = new MarkovWord(5);
		EfficientMarkovWord effi = new EfficientMarkovWord(2);
		int size = 50;
		int seed = 65;
		//marko.runModel(markovWord, st, size, seed);
		//marko.runModel(markovWord2, st, size, seed);
		//marko.runModel(markov, st, size, seed);
		marko.runModel(effi, st, size, seed);
	}*/

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
	
}
