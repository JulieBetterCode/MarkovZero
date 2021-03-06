import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel{
    private int myModel;

    public MarkovModel(int n) {
        myRandom = new Random();
        myModel = n;
    }

    //public void setModel(int n) { myModel = n; }

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

    public String toString() {
        return "MarkovModel of order " + myModel;
    }
}
