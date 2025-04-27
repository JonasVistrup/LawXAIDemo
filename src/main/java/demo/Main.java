package demo;

import demo.Logic.High.Atom;
import demo.Logic.High.AtomList;
import demo.Logic.High.Clause;
import demo.Logic.High.Substitution;
import demo.SLD.XAI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final String strPath = "src/main/java/demo/";
    public static void main(String[] args) throws IOException {
        init();
        ArrayList<String> info = parseCase("case1");
        List<Atom> facts = info.stream().map(XAI.pp::parseAtomOld).collect(Collectors.toList());
        HashMap<Atom, List<Clause>> groundClausesUsed = new HashMap<>();
        AtomList query = new AtomList(XAI.pp.parseAtomOld("BrudtLoven(A,Lov,T)"));
        query.add(XAI.pp.parseAtomOld("BrudtLoven(X,Y,T)"));
        //List<Substitution> answers = XAI.query(query, facts, groundClausesUsed);
        List<Substitution> answers = XAI.query(new AtomList(XAI.pp.parseAtomOld("StraffesMedBøde(P,Lov,T)")), facts, groundClausesUsed);
        if (answers.size() > 0) {
            System.out.println("StraffesMedBøde(X,Y,T) is true for the following substitutions:");
            for (Substitution answer : answers) {
                System.out.println(answer);
            }
        } else {
            System.out.println("BrudtLoven(X,Y,T) is false.");
        }
        System.out.println("Facts: " + facts);

    }

    private static ArrayList<String> parseCase(String casePath){
        ArrayList<String> result = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File(strPath+"res/Cases/"+casePath));
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("file is missing at location "+casePath);
            return result;
        }
        return result;
    }

    public static void init(){
        XAI.addPredicates(strPath+"res/Predicates/Køretøjer.txt");
        //XAI.addPredicates(strPath+"res/Predicates/Matematik.txt");
        XAI.addPredicates(strPath+"res/Predicates/Lov.txt");
        XAI.addPredicates(strPath+"res/Predicates/Personer.txt");
        XAI.addPredicates(strPath+"res/Predicates/Standard.txt");
        XAI.addPredicates(strPath+"res/Predicates/Tid.txt");
        XAI.addPredicates(strPath+"res/Predicates/VejDefinitioner.txt");

        XAI.addPredicates(strPath+"res/Predicates/§2.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§3-9.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§10-13.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§14-20.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§21-40.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§31-40.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§41-50.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§51-60.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§61-90.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§91-100.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§101-116.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§117-124.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§125-133.txt");

        XAI.addUDPs(strPath+"res/UDPs.txt");

        for(String path:findAllFilesInDirectory(new File(strPath+"res/1"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/2"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/3"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/4"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/5"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/6"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/7"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/8"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/10"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/11"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/12"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/13"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/14"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/15"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/16"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/17"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/17a"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/18"))) XAI.addRules(path);
        for(String path:findAllFilesInDirectory(new File(strPath+"res/19"))) XAI.addRules(path);
    }

    private static ArrayList<String> findAllFilesInDirectory(File dir){
        assert dir.isDirectory();
        ArrayList<String> filepaths = new ArrayList<>();
        for(File f: dir.listFiles()){
            if (f.isFile()){
                filepaths.add(f.getPath());
            }else if (f.isDirectory()){
                filepaths.addAll(findAllFilesInDirectory(f));
            }
        }
        return filepaths;
    }
}
