package com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Programme Internal Form will be an array and each spot will be another array with 4 spots:
 * 1. Index
 * 2.
 * */
public class Pif {

    private ArrayList<ArrayList> pif;
    private Integer index;

    public Pif(){
        this.pif = new ArrayList<>();
        this.index = 0;
    }

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
