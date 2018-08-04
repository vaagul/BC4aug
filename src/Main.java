import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<String> extractedDict= new ArrayList<String>();

    public static void loadDictionary(String word) throws IOException {

        int wordCount=word.length();
        File sowpods=new File("sowpods.txt");
        try {
            BufferedReader br= new BufferedReader(new FileReader(sowpods));
            String st;
            while ((st = br.readLine()) != null ){
                if(st.length()==wordCount){
                    extractedDict.add(st);
                    System.out.println(st);
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

    public static ArrayList<String> getAllPossibleWords(String s) {
        ArrayList<String> allWordsPossible = new ArrayList<>();
        for(int i=0;i<s.length();i++) {
            for(int j=0;j<((int)'Z' - (int)'A');j++) {
                if(s.charAt(i) != (char)(j + (int)'A')) {
                    StringBuilder newWord = new StringBuilder(s);
                    newWord.setCharAt(i, (char)(j + (int)'A'));
                    if(isValidWord(newWord.toString())) {
                        allWordsPossible.add(newWord.toString());
                    }
                }
            }
        }
        return allWordsPossible;
    }

    public static void bfs(String startingWord, String endWord) {
        Queue<Pair<String, Integer>> que = new LinkedList<>();
        que.add(new Pair<>(startingWord, 0));
        HashMap<String, Integer> map = new HashMap<>();
        while(!que.isEmpty()) {
            Pair<String, Integer> curWord = que.peek();
            que.remove();
            if(map.get(curWord.getKey()) != null) {
                continue;
            }
            map.put(curWord.getKey(), 1);
            if(curWord.getKey().equals(endWord)) {
                System.out.println(curWord.getValue());
                break;
            }
            ArrayList<String> allPossibleWords = getAllPossibleWords(curWord.getKey());
            for(String s: allPossibleWords) {
                que.add(new Pair<>(s, curWord.getValue() + 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        System.out.println("asdf");
        loadDictionary("CLAY");
        bfs("CLAY", "GOLD");
    }



}
