import edu.duke.*;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */
public class MarkovRunner {
    public void MarkovZero() {
		FileResource fr = new FileResource();
		MarkovRunner marko = new MarkovRunner();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setRandom(42);
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
		markov.setRandom(42);
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
		markov.setRandom(25);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			marko.printOut(text);
		}
	}

	public static void main(String[] args) {
		MarkovRunner marko = new MarkovRunner();
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovModel markov = new MarkovModel();
		markov.setRandom(38);
		markov.setModel(6);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			marko.printOut(text);
		}
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
	
}