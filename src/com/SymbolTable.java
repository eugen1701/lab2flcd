package com;

import java.util.Arrays;

public class SymbolTable {
    private int capacity;
    private String[] table;

    public SymbolTable(int capacity) {
        this.capacity = capacity;
        this.table = new String[capacity];
    }

    private int HashFunction (String key) {
        int asciiSum = 0;
        for (int i = 0; i < key.length(); i++){
            asciiSum = asciiSum + key.charAt(i);
        }
        return asciiSum % capacity;
    }

    public String toString() {
        return Arrays.toString(table);
    }

    public int search(String key) {
        int hashValue = HashFunction(key);
        while(table[hashValue] != null) {
            if (table[hashValue].equalsIgnoreCase(key))
                return hashValue;
            hashValue++;
            hashValue %= capacity;
        }
        return -1;
    }

    public boolean add(String key) {
        for(String entry : table) {
            if(entry != null && entry.equals(key))
                return false;
        }

        int hashValue = HashFunction(key);
        if (table[hashValue] == null){
            table[hashValue] = key;
            return true;
        }

        int hashValue2 = hashValue;
        while(table[hashValue2] != null){
            hashValue2++;
            hashValue %= capacity;
        }

        if(table[hashValue2] == null){
            table[hashValue2] = key;
            return true;
        }

        return false;
    }
}
