import java.util.*;
public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;

    public EfficientMarkovWord(int order) {
        map = new HashMap<WordGram, ArrayList<String>>();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }

    public void buildMap() {
        for(int i=0; i<myText.length-(myOrder-1); i++) {
            WordGram wg = new WordGram(myText, i, myOrder);
            String next = "";
            if(i + myOrder < myText.length)
                next = myText[i + myOrder];
            if (map.containsKey(wg)) {
                map.get(wg).add(next);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(next);
                map.put(wg, list);
            }
        }
    }


     public void printHashMapInfo(){
         System.out.println("It has " +  map.size() + " keys in the HashMap");
         int maxSize = 0;
         for (WordGram wg : map.keySet()) {
             maxSize = Math.max(maxSize, map.get(wg).size());
         }
         System.out.println("The maximum number of elements following a key is " + maxSize);

         System.out.println("Keys with the maximum size value: ");
         for (WordGram wg : map.keySet()) {
             if (map.get(wg).size()==maxSize) {
                 System.out.print(wg);
                 System.out.println(" (The follow words: " + map.get(wg) + ")");
             }
         }
     }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
//			System.out.print("Key \"" + key + "\" ");
            ArrayList<String> follows = getFollows(key);
//		    System.out.println("is followed by: " + follows);
            if(follows == null){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    public int indexOf(String[] words, WordGram target, int start){
        for (int i=start; i<=words.length-target.length(); i++){
            WordGram wg = new WordGram(words, i, target.length());
            if (wg.equals(target)){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram);
    }
}