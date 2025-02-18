package com.example.demo;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.Logic.High.Clause;
import com.example.demo.Logic.High.Substitution;
import com.example.demo.Logic.Symbols.Predicates.PredicateUD;
import com.example.demo.Logic.Symbols.Predicates.UDNegation;
import com.example.demo.SLD.Answer;
import com.example.demo.SLD.History;
import com.example.demo.SLD.XAI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HelloController implements Initializable {

    private static final String strPath = "src/main/java/com/example/demo/";

    @FXML
    private Label factLabel;

    @FXML
    private ListView<String> factList;
    @FXML
    private TextField factInput;
    @FXML
    private Button queryButton;
    @FXML
    private Button backButton;
    @FXML
    private Label answerInfo;

    @FXML
    private ToggleButton forImod;

    private ClauseAtom active;

    private Stack<ClauseAtom> stack;
    private AtomList query;
    private Atom activeAtom;
    private HashMap<Atom, List<Clause>> groundClausesUsed;
    @FXML
    private ListView<String> answerList;


    private ArrayList<String> facts = new ArrayList<>();
    private List<Atom> atomFacts = new ArrayList<>();

    private List<Substitution> answers;

    @FXML
    protected void onFactInputClick(){
        String factString = ""+factInput.getCharacters();
        if(factString.equals("test")){
            ArrayList<String> info = new ArrayList<>();
            info.add("Kører(jonas,bil,vej)");
            info.add("Hastighed(bil,60)");
            info.add("IkkeAndenHastighedsGrænse(vej)");
            info.add("Byvej(vej)");
            info.add("Landevej(vej)");
            info.add(">(60,50)");
            info.add(">(60,80)");
            facts.addAll(info);
            factList.getItems().addAll(info);
            factInput.setText("");
            return;
        }else if(factString.equals("basic")){
            ArrayList<String> info = new ArrayList<>();
            info.add("SpeedNotReducedToMatchConditions(jonas,bil,vej)");
            info.add("LosesControl(jonas,bil)");
            facts.addAll(info);
            factList.getItems().addAll(info);
            factInput.setText("");
            return;
        }else if(factString.startsWith("case") || factString.startsWith("sag")){
            ArrayList<String> info = parseCase(factString);
            List<Atom> atoms = info.stream().map(XAI.pp::parseAtomOld).collect(Collectors.toList());
            this.atomFacts.addAll(atoms);
            for(Atom a: atoms){
                if(a.predicate().hasExplanation())  factList.getItems().add(a.explain());
                else  factList.getItems().add(a.toString());
            }
            facts.addAll(info);
            //factList.getItems().addAll(info);
            factInput.setText("");
            return;
        }
        try {
            Atom a = XAI.pp.parseAtomOld("" + factInput.getCharacters());

            this.atomFacts.add(a);
            if(a.predicate().hasExplanation()) factList.getItems().add(a.explain());
            else factList.getItems().add(a.toString());

            facts.add(""+ factInput.getCharacters());
            System.out.println("Added to facts: "+factInput.getCharacters());

        } catch (Exception e) {
            System.out.println("Invalid ATOM: "+e.toString());
        }
        factInput.setText("");
        //factList.setItems(oList);
        //factList.refresh();

    }

    private ArrayList<String> parseCase(String casePath){
        ArrayList<String> result = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File(strPath+"res/Cases/"+casePath));
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("file is missing at location "+casePath);
            return result;
        }
        return result;
    }

    @FXML
    protected void onQueryClick(){
        if(forImod.isSelected()){
            activeAtom = XAI.pp.parseAtomOld("IkkeBrudtLoven(X,Y,T)");
        }else{
            activeAtom = XAI.pp.parseAtomOld("BrudtLoven(X,Y,T)");
        }
        this.active = null;
        query = new AtomList(activeAtom);

        groundClausesUsed = new HashMap<>();
        //answers = XAI.query(new ArrayList<>(factList.getItems()),query, groundClausesUsed);
        answers = XAI.query(query,atomFacts, groundClausesUsed);
        for(Clause c: XAI.pp.getProgram()){
            System.out.println(c);
        }
        System.out.println("Facts Size: "+factList.getItems().size());
        System.out.println("Size: "+answers.size());

        stack = new Stack<>();
        answerList.getItems().clear();

        setupAnswers();
    }

    @FXML
    protected void onReasoningClick(){
        System.out.println("Clicked");
        if(answers==null || active == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "You must query the system and select an answer before reasonings can be given!");
            alert.show();
        } else if(active.clause==null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "You must select argument for "+active.atom.explain()+"!");
            alert.show();
        }else{
            StringBuilder reasons = new StringBuilder();
            for(String reason: active.clause.reasons){
                reasons.append(reason).append("\n");
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, reasons.toString());
            alert.show();
        }
    }

    @FXML
    protected void forImodToggle(){
        if(forImod.isSelected()) forImod.setText("Imod");
        else forImod.setText("For");
    }

    @FXML
    protected void onBackClick() {
        if(active == null) return;

        if(this.stack.isEmpty()){
            this.active = null;
            setupAnswers();
        }else{
            this.active = this.stack.pop();
            if(this.active.clause == null){
                setupAtom();
            }else{
                setupClause();
            }
        }
    }

    private void setupAnswers(){
        answerList.getItems().clear();
        answerInfo.setText("Vælg et svar for forklaring");
        for(Substitution answer: answers) {
            query.applySub(answer);
            for(Atom a:query.applySub(answer)){
                answerList.getItems().add(a.explain());
            }
            //answerList.getItems().add(query.applySub(answer).toString());
        }
    }

    private ArrayList<String> findAllFilesInDirectory(File dir){
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(String path: findAllFilesInDirectory(new File(strPath+"res/Predicates"))){
            XAI.addPredicates(path);
        }

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

        XAI.printStats();
        try {
            XAI.printOccurences("IkkePassendeLav");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void removeFact(){
        int pos = factList.getSelectionModel().getSelectedIndex();
        factList.getSelectionModel().clearSelection(pos);
        if(pos<0) return;
        if(pos<factList.getItems().size()) {
            factList.getItems().remove(pos);
            atomFacts.remove(pos);
        }
    }

    @FXML
    protected void onClickAnswerList(){
        int pos = answerList.getSelectionModel().getSelectedIndex();
        answerList.getSelectionModel().clearSelection(pos);
        if(pos < 0 || answerList.getItems().size() <= pos) return;
        if(active != null && active.atom != null && active.atom.predicate() instanceof PredicateUD) return;

        if(active == null){
            selectAnswer(pos);
        }else{

            //stack.add(active);
            if(active.clause == null) {
                Clause c = groundClausesUsed.get(active.atom).get(pos);
                if(c.body.size() == 1 && c.body.get(0).predicate() == c.head.predicate()) return;//TODO remove
                stack.add(active);
                selectClause(pos);
            }else{
                stack.add(active);
                selectAtom(pos);
            }
        }
    }

    private void selectAtom(int pos) {
        active = new ClauseAtom(active.clause.body.get(pos));
        setupAtom();
    }

    private void setupAtom(){
        answerList.getItems().clear();

        this.answerInfo.setText(active.atom.explain() + " begrundet:");
        if(active.atom.predicate() instanceof UDNegation){
            answerList.getItems().add(((UDNegation) active.atom.predicate()).predicate.explain(active.atom.args) + " kan ikke vises");
        } else if (active.atom.predicate() instanceof PredicateUD) {
            answerList.getItems().add("Af definitionen af "+active.atom.predicate().toString());
        } else {
            int i = 1;
            List<Clause> clauses = this.groundClausesUsed.get(active.atom);
            for (Clause clause : clauses) {
                if(clause.reasons.size() == 0){
                    answerList.getItems().add("Faktum");
                }
                else answerList.getItems().add("(" + i + "): " + clause.reasons.get(0));
                i++;
            }
        }
    }

    private void selectClause(int pos) {
        Clause c = groundClausesUsed.get(active.atom).get(pos);
        if(c.body.size() == 1 && c.body.get(0).predicate() == c.head.predicate()) return;//TODO remove
        active = new ClauseAtom(c);

        setupClause();
    }

    private void setupClause(){
        answerList.getItems().clear();

        if(active.clause.body.isEmpty()){
            this.answerInfo.setText(active.clause.head.explain() + " er givet");
        }else {
            this.answerInfo.setText(active.clause.head.explain() + " fordi");
        }
        for(Atom a: active.clause.body){
            answerList.getItems().add(a.explain());
        }
    }

    private void selectAnswer(int pos){
        active = new ClauseAtom(this.activeAtom.applySub(this.answers.get(pos)));

        setupAtom();
    }
}