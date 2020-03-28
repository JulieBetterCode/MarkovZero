
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed) { myRandom = new Random(seed); }

    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
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
