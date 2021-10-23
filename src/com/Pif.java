package com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Programme Internal Form will be an array and each spot will be another array with 4 spots:
 * 1. Index
 * 2. Token
 * 3. Hash Value of the Token
 * 4. The index in the list of the Hash Table which represent the Symbol Table
 * */
public class Pif {

    private ArrayList<ArrayList> pif;
    private Integer index;

    /**
     * Programme Internal Form is made and the its index starts from 0
     * */
    public Pif(){
        this.pif = new ArrayList<>();
        this.index = 0;
    }

    /**
     * This function ads to the Programme Internal Form a new tuple as described
     * in the doc of the class
     * @param token Type: String - the token for which the PIF is generated
     * @param indexIn  The position in the Symbol Table (Hash Value)
     * @param indexST  The position in the Array of the Symbol Table
     * */
    public void genPIF(String token, int indexIn, int indexST){
        ArrayList<String> spot = new ArrayList<>();
        spot.add(index.toString());
        spot.add(token);
        Integer i1 = Integer.valueOf(indexIn);
        spot.add(i1.toString());
        i1 = Integer.valueOf(indexST);
        spot.add(i1.toString());

        pif.add(spot);
        index++;
    }

    @Override
    public String toString() {
        String toDisplay = "";
        for(ArrayList a : this.pif) {
            toDisplay += "Position: " + a.get(0) + " | Token: " + a.get(1) + " | HashValue: " + a.get(2) + " | Index: " + a.get(3)+ "\n";
        }
        return toDisplay;
    }

    /**
     * This function write a representation of the PIF to a file
     * */
    public void writeToFile() {
        File myObj = new File("pif.txt");
        try {
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("pif.txt");
            myWriter.write(this.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
