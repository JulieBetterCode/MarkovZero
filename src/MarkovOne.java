
/**
 * Write a description of class MarkovOne here.
 * 
 * @author Ling
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    private String myText;
	private Random myRandom;

	public MarkovOne() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-1);
		String key = myText.substring(index, index+1);
		sb.append(key);
		for(int k=0; k < numChars-1; k++){
			ArrayList<String> follows = getFollows(key);
			if(follows.size()==0){
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = next;
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
