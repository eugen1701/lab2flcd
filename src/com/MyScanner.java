package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyScanner {

    private SymbolTable st;
    private ArrayList<String> pifToken;
    private List<Integer> pifPos;

    private ArrayList<String> reservedWords;

    public MyScanner() {
        this.st = new SymbolTable(10);
        this.reservedWords = new ArrayList<>();
        reservedWords.add("functionalite");
    }

    public boolean isReservedWord(String word) {
        return this.reservedWords.contains(word);
    }

    public boolean isOperator(String token) {
        if(token == "=")
            return true;
        if(token == "+")
            return true;
        if(token == "-")
            return true;
        return false;
    }

    public void scanning() {
        try {
            File source = new File("source1.txt");
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String token = reader.next();
                if(isReservedWord(token) || isOperator(token))
                System.out.println(token);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
