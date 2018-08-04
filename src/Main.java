import java.io.*;
import java.util.ArrayList;

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

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        System.out.println("asdf");
        loadDictionary("CLAY");
    }



}
