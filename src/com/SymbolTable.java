package com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Symbol Table is used to store the constant or identifiers.
 * It uses an ArrayList of ArrayList of String which should represent an Hash Table
 * with Separate chaining with array lists
 * */
public class SymbolTable {
    private int capacity;
    private ArrayList<ArrayList<String>> table;

    /**
     * The Symbol Table is created.
     * @param capacity The size of the Hash Table
     * */
    public SymbolTable(int capacity) {
        this.capacity = capacity;
        this.table = new ArrayList<ArrayList<String>>();
        for(int i = 0; i<capacity;i++){
            this.table.add(new ArrayList<>());
        }
    }

    /**
     * The Hash Function return the hash value of a key in order to know the
     * position of a key in the Hash Table. The Hash Value is composed by
     * summing up all the ASCII values of the characters in the key and
     * made the modulo with the size of the Hash Table.
     * @param key - Type: String
     *            - The key for which to compute the Hash Value
     * @return Hash Value (Type: int)
     * */
    private int HashFunction (String key) {
        int asciiSum = 0;
        for (int i = 0; i < key.length(); i++){
            asciiSum = asciiSum + key.charAt(i);
        }
        return asciiSum % capacity;
    }

    /**
     * This function searches token in symbol table ST and if found then
     * return position; if not found insert in ST and return position
     * @param token - Type: String
     *              - the token to be added if doesn't already exists
     * @return an ArrayList of Integer in where the first position is the hash value
     * and the second one is the index in the ArrayList situated at the hash value index in
     * the table (Type: ArrayList of Integer)
     * */
    public ArrayList<Integer> pos(String token) {
        ArrayList<Integer> resultSearch = this.search(token);
        if(resultSearch.get(0) == -1){
            return add(token);
        }
        return resultSearch;
    }

    /**
     * This function search an element in the ST, if found it returns it position
     * in ST, if not [-1]
     * @param key -Type: String
     *            - the key to be searched
     * @return an ArrayList of Integer which signify the position in the ST
     * (Hash Value, on the first position in the list, and the index in the list,
     * on the second position) or [-1] if the key is not found
     * (Type: ArrayList of Integer)
     * */
    private ArrayList<Integer> search(String key) {
        int hashValue = HashFunction(key);
        ArrayList<Integer> notFound = new ArrayList<>();
        notFound.add(-1);
        if(table.get(hashValue).isEmpty()) {
            return notFound;
        }
        else{
            int i1 = 0;
            for(String i : table.get(hashValue)){
                if(key.equals(i)) {
                    ArrayList<Integer> found = new ArrayList<>();
                    found.add(hashValue);
                    found.add(i1);
                    return found;
                }
                i1++;
            }
        }
        return notFound;
    }

    /**
     * Add function adds a new token to the Symbol table.
     * @param key Type: String - the new token to be added
     * @return the position where the new token was added (Type: ArrayList of Integer)
     * */
    private ArrayList<Integer> add(String key) {
        int hashValue = HashFunction(key);
        table.get(hashValue).add(key);
        int indexSpot = table.get(hashValue).size() - 1;
        Integer hashI = Integer.valueOf(hashValue);
        Integer indexI = Integer.valueOf(indexSpot);
        ArrayList<Integer> indexis = new ArrayList<>();
        indexis.add(hashI);
        indexis.add(indexI);
        return indexis;
    }

    @Override
    public String toString() {
        String toDisplay = "Capacity: " + this.capacity + " \n";
        Integer i = 0;
        for(ArrayList a : this.table) {
            toDisplay += "HashValue: " + i + " | Elements: " + a + "\n";
            i++;
        }
        return toDisplay;
    }

    /**
     * Write to file the representation of the Hash Table which is representing
     * the Symbol Table
     * */
    public void writeToFile() {
        File myObj = new File("symbolTable.txt");
        try {
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("symbolTable.txt");
            myWriter.write(this.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
