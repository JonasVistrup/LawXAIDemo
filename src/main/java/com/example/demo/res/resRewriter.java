package com.example.demo.res;

import com.example.demo.Logic.Symbols.Predicates.Predicate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class resRewriter {

    public static void listOccurences(String predicate) throws IOException {
        String[] dirs = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "10", "11", "12", "13", "14", "15", "16", "17", "17a", "18", "19"};
        String prefix = "C:\\Users\\vistrup\\Desktop\\LawXAI\\src\\main\\java\\com\\example\\demo\\res\\";

        for(String dir: dirs){
            File dirF = new File(prefix + dir);
            for(File f: dirF.listFiles()){
                String ss = Files.readString(f.toPath());
                if(ss.contains(predicate)){
                    System.out.println("File "+f.getName() + " contains " + predicate);

                }
            }
        }
    }


    public static void changePredicates(Predicate from, Predicate[] to, int[] argMap) throws IOException {
        String[] dirs = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "10", "11", "12", "13", "14", "15", "16", "17", "17a", "18", "19", "Predicates"};
        String prefix = "C:\\Users\\vistrup\\Desktop\\LawXAI\\src\\main\\java\\com\\example\\demo\\res\\";

        for(String dir: dirs){
            File dirF = new File(prefix + dir);
            for(File f: dirF.listFiles()){
                String ss = Files.readString(f.toPath());
                if(ss.contains(from.toString())){
                    System.out.println("Removing "+from.toString()+" from file "+f.getName());
                    String out = swapForFile(ss, from, to, argMap);
                    System.out.println("New file content:");
                    System.out.println(out);
                }
            }
        }
    }


    public static String swapForFile(String start, Predicate from, Predicate[] to, int[] argMap){
        StringBuilder b = new StringBuilder();
        String[] startLines = start.split("\n");
        for(String line: startLines){
            if(line.contains(from.toString())){
                b.append(swapForLine(line, from, to, argMap));
            }else {
                b.append(line);
            }
            b.append('\n');
        }
        return b.toString();
    }

    private static String swapForLine(String line, Predicate from, Predicate[] to, int[] argMap) {
        return line; // TODO if possible
    }
}
