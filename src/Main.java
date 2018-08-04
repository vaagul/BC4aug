import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static ArrayList<String> extractedDict= new ArrayList<String>();
    static String START="ATMOSPHERED",END="ATMOSPHERES";

    public static void loadDictionary(String word) throws IOException {

        int wordCount=word.length();
        File sowpods=new File("sowpods.txt");
        try {
            BufferedReader br= new BufferedReader(new FileReader(sowpods));
            String st;
            while ((st = br.readLine()) != null ){
                if(st.length()==wordCount){
                    extractedDict.add(st);
                   // System.out.println(st);
                }
                //System.out.println(st);
            }

        }
         catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public static void printArrayList(ArrayList<String> ){

    }*/

    public static boolean isValidWord(String query) {
        return Collections.binarySearch(extractedDict, query) > 0 ;
    }

    public static HashMap<String,String> getAllPossibleWords(String s,String prefix) {
        HashMap<String,String> allWordsPossible = new HashMap<>();
        for(int i=0;i<s.length();i++) {
            for(int j=0;j<((int)'Z' - (int)'A');j++) {
                if(s.charAt(i) != (char)(j + (int)'A')) {
                    StringBuilder newWord = new StringBuilder(s);
                    String changedInfo = prefix + " "+Integer.toString(i)+"-"+s.charAt(i);
                    newWord.setCharAt(i, (char)(j + (int)'A'));
                    if(isValidWord(newWord.toString())) {
                        allWordsPossible.put(newWord.toString() , changedInfo);
                    }
                }
            }
        }
        return allWordsPossible;
    }
    public static void displayTracePath(Pair<String,String> finalWord,int pos){
        String path = finalWord.getValue().substring(pos+1);
        String [] paths = path.split(" ");
        String finalString = finalWord.getKey();
        StringBuilder newWord = new StringBuilder(finalString);
        System.out.println(finalString);
        for (int i=paths.length-1;i>0;i--){
            String [] stage = paths[i].split("-");

            int position = Integer.parseInt(stage[0]);
            newWord.setCharAt(position,stage[1].toCharArray()[0]);
            System.out.println(newWord.toString());
        }
    }

    public static void bfs(String startingWord, String endWord) {
        Queue<Pair<String, String>> que = new LinkedList<>();
        que.add(new Pair<>(startingWord, "0*"));
        HashMap<String, String> map = new HashMap<>();
        while(!que.isEmpty()) {
            Pair<String, String> curWord = que.peek();
            que.remove();
            if(map.get(curWord.getKey()) != null) {
                continue;
            }
            map.put(curWord.getKey(), "1*");
            if(curWord.getKey().equals(endWord)) {
                int pos = curWord.getValue().indexOf("*");
                System.out.println(curWord.getValue().substring(0,pos));
                System.out.println(curWord.getValue());
                displayTracePath(curWord,pos);
                break;
            }
            int pos = curWord.getValue().indexOf("*");

            HashMap<String,String> allPossibleWords = getAllPossibleWords(curWord.getKey(),curWord.getValue().substring(pos+1));

            int level = Integer.parseInt(curWord.getValue().substring(0,pos)) + 1;

            for(String s: allPossibleWords.keySet()) {
                String levelInfo = Integer.toString(level)+"*"+ allPossibleWords.get(s);
                que.add(new Pair<>(s, levelInfo));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        loadDictionary(START);
        bfs(START, END);
    }



}
