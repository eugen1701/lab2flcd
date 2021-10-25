package com;

public class Main {

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        scanner.detect("source1.txt");
        scanner.writePifToFile();
        scanner.writeStToFile();
        //System.out.println(scanner);
    }
}
