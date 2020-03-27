
/**
 * Write a description of class MarkovOne here.
 * 
 * @author Ling
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {
    private String myText;
	private Random myRandom;

	public MarkovFour() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}

	public String getRandomText(int numChars){
		StringBuffer sb = new StringBuffer();
		int index = myRandom.nextInt(myText.length()-4);
		String key = myText.substring(index, index+4);
		sb.append(key);
		for(int i=0; i< numChars-4; i++){
			ArrayList<String> follows = getFollows(key);
			if(follows.size()==0){
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = key.substring(1)+next;
		}
		return sb.toString();
	}

	public ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = new ArrayList<String>();
		int keyLength = key.length();
		int i = 0;
		while(i < myText.length()) {
			int index = myText.indexOf(key, i);
			if(index == -1) break;
			if(index + keyLength >= myText.length()) break;
			String follow = myText.substring(index+keyLength, index+keyLength+1);
			follows.add(follow);
			i = index + keyLength;
		}
		return follows;
	}
}
