package com;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyScanner {

    private SymbolTable st;
    public ArrayList<String> tokens;
    private String del;
    private ArrayList<String> reservedWords;
    private Pif pif;

    public MyScanner() {
        this.st = new SymbolTable(17);
        this.pif = new Pif();
        this.reservedWords = new ArrayList<>();
        File source = new File("Token.in");
        Scanner reader = null;
        try {
            reader = new Scanner(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (reader.hasNextLine()) {
            String token = reader.nextLine();
            token = token.strip();
            reservedWords.add(token);
        }
        this.tokens = new ArrayList<>();

        this.del = ", ";
    }

    public void detect(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            File fileName = new File(filePath);
            if(fileName.exists()) {
                FileReader source = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(source);
                String s;
                while((s = bufferedReader.readLine()) != null)
                    lines.add(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int noLine = 0;
        for(String s : lines){
            noLine++;
            StringTokenizer stringTokenizer = new StringTokenizer(s, del, true);
            while(stringTokenizer.hasMoreTokens()){
                String token = stringTokenizer.nextToken();
                token = token.strip();
               if(!token.isEmpty()) {
                   this.tokens.add(token);
                   if(reservedWords.contains(token))
                       pif.genPIF(token, 0, 0);
                   else if(isIdentifierOrConstand(token)){
                       ArrayList<Integer> indexes = st.pos(token);
                       pif.genPIF(token, indexes.get(0), indexes.get(1));
                   }
                   else{
                       try {
                           throw new Exception("Lexical error. The token " + token + " cannot be classified at line " + noLine + "\n");
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }

               }

            }
        }

    }

    public boolean isIdentifierOrConstand(String token) {
        Pattern pattern = Pattern.compile("^[a-z]([a-zA-Z]|[0-9]){0,7}");
        Matcher matcher = pattern.matcher(token);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    /**
     * This function is just to help me to see better the tokens
     * */
    public String tokenToString() {
        String s = "[";
        for(String i : this.tokens){
            s += i + "] [";
        }
        return s;
    }

    @Override
    public String toString() {
        return "MyScanner{" +
                "st=" + st + "\n" +
                ", tokens=" + this.tokenToString() + "\n" +
                ", pif=" + pif.toString() +"\n" +
                ", reservedWords=" + reservedWords +
                '}';
    }

    public void writePifToFile() {
        this.pif.writeToFile();
    }

    public void writeStToFile() {
        this.st.writeToFile();
    }
}
