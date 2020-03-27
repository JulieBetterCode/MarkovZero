import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int myModel;

    public MarkovModel() {
        myRandom = new Random();
    }

    public void setModel(int n) { myModel = n; }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-myModel);
        String key = myText.substring(index, index+myModel);
        sb.append(key);
        for(int i=0; i< numChars-myModel; i++){
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
