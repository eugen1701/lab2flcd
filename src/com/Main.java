package com;

public class Main {

    public static void main(String[] args) {

        SymbolTable table = new SymbolTable(5);
        System.out.println(table.add("a"));
        System.out.println(table.add("b"));
        System.out.println(table.add("a"));
    }
}
