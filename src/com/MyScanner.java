package com;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MyScanner class represents the scanner which detect the tokens in a source
 * it uses a StringTokenizer in order to do the tokenization
 * */
public class MyScanner {

    private SymbolTable st;
    public ArrayList<String> tokens;
    private String del;
    private ArrayList<String> reservedWords;
    private Pif pif;

    /**
     * MyScanner class is created. It sets the  size of the Symbol Table.
     * It loads internally from the "Token.in" file
     * the reserved words and operators. It also sets its delimiters (del).
     * */
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

    /**
     * Detect function does the tokenization of the source code and classify each
     * token whether is a reserved word / operator or if it is an identifier it adds
     * it to the system table. After this the PIF is generated with the new token.
     * If a token cannot be classified the Lexical Error is thrown.
     * @param filePath The file path to the source code.
     * */
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
                   else if(isIdentifierOrConstant(token)){
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

    /**
     * This function establish whether a token is an identifier or a constant using
     * regex. The used regex means that the token should start by a lower case letter
     * and after it can continue with lower or upper case letter or numbers and it can
     * has maximum 7 characters.
     * @param token Is the token to be test if it is an identifier or not
     * */
    public boolean isIdentifierOrConstant(String token) {
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

    /**
     * This method calls the "writeToFile" method of PIF
     * */
    public void writePifToFile() {
        this.pif.writeToFile();
    }

    /**
     * This method calls the "writeToFile" method of Symbol Table
     * */
    public void writeStToFile() {
        this.st.writeToFile();
    }
}
