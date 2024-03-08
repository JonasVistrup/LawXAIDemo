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
import java.net.URL;
import java.util.*;

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

    private ClauseAtom active;

    private Stack<ClauseAtom> stack;
    private AtomList query;
    private Atom activeAtom;
    private HashMap<Atom, List<Clause>> groundClausesUsed;
    @FXML
    private ListView<String> answerList;


    private ArrayList<String> facts = new ArrayList<>();

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
        }else if(factString.startsWith("case")){
            ArrayList<String> info = parseCase(factString);

            facts.addAll(info);
            factList.getItems().addAll(info);
            factInput.setText("");
            return;
        }
        facts.add(""+ factInput.getCharacters());
        System.out.println("Added to facts: "+factInput.getCharacters());


        ObservableList<String> oList = factList.getItems();
        oList.add(factInput.getCharacters()+"");
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
            throw new IllegalStateException("file is missing at location "+casePath);
        }
        return result;
    }

    @FXML
    protected void onQueryClick(){
        activeAtom = XAI.pp.parseAtomOld("BrudtLoven(X,Y,T)");
        query = new AtomList(activeAtom);
        groundClausesUsed = new HashMap<>();
        answers = XAI.query(new ArrayList<>(factList.getItems()),query, groundClausesUsed);
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
        answerInfo.setText("Select an answer for explanation");
        for(Substitution answer: answers) {
            answerList.getItems().add(query.applySub(answer).toString());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XAI.addPredicates(strPath+"res/Predicates/Køretøjer.txt");
        XAI.addPredicates(strPath+"res/Predicates/Matematik.txt");
        XAI.addPredicates(strPath+"res/Predicates/Personer.txt");
        XAI.addPredicates(strPath+"res/Predicates/Standard.txt");
        XAI.addPredicates(strPath+"res/Predicates/§2.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§3-9.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§10-13.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§14-20.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§21-40.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§31-40.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§41-50.txt");
        XAI.addPredicates(strPath+"res/Predicates/§§51-60.txt");
        XAI.addPredicates(strPath+"res/Predicates/VejDefinitioner.txt");
        XAI.addPredicates(strPath+"res/Predicates/Tid.txt");

        XAI.addUDPs(strPath+"res/UDPs.txt");

        XAI.addRules(strPath+"res/1/§2.jlaw");
        XAI.addRules(strPath+"res/2/§3.jlaw");
        XAI.addRules(strPath+"res/2/§4.jlaw");
        XAI.addRules(strPath+"res/2/§5.jlaw");
        XAI.addRules(strPath+"res/2/§6.jlaw");
        XAI.addRules(strPath+"res/2/§7.jlaw");
        XAI.addRules(strPath+"res/2/§8.jlaw");
        XAI.addRules(strPath+"res/2/§9.jlaw");

        XAI.addRules(strPath+"res/3/§10.jlaw");
        XAI.addRules(strPath+"res/3/§11.jlaw");
        XAI.addRules(strPath+"res/3/§12.jlaw");
        XAI.addRules(strPath+"res/3/§13.jlaw");

        XAI.addRules(strPath+"res/4/§14.jlaw");
        XAI.addRules(strPath+"res/4/§15.jlaw");
        XAI.addRules(strPath+"res/4/§16.jlaw");
        XAI.addRules(strPath+"res/4/§17.jlaw");
        XAI.addRules(strPath+"res/4/§18.jlaw");
        XAI.addRules(strPath+"res/4/§19.jlaw");
        XAI.addRules(strPath+"res/4/§20.jlaw");
        XAI.addRules(strPath+"res/4/§21.jlaw");
        XAI.addRules(strPath+"res/4/§22.jlaw");
        XAI.addRules(strPath+"res/4/§23.jlaw");
        XAI.addRules(strPath+"res/4/§24.jlaw");
        XAI.addRules(strPath+"res/4/§25.jlaw");
        XAI.addRules(strPath+"res/4/§26.jlaw");
        XAI.addRules(strPath+"res/4/§27.jlaw");
        XAI.addRules(strPath+"res/4/§28.jlaw");
        XAI.addRules(strPath+"res/4/§29.jlaw");
        XAI.addRules(strPath+"res/4/§30.jlaw");

        XAI.addRules(strPath+"res/4/§31.jlaw");
        XAI.addRules(strPath+"res/4/§32.jlaw");
        XAI.addRules(strPath+"res/4/§33.jlaw");
        //XAI.addRules(strPath+"res/4/§34.jlaw");
        XAI.addRules(strPath+"res/4/§35.jlaw");
        XAI.addRules(strPath+"res/4/§36.jlaw");
        XAI.addRules(strPath+"res/4/§37.jlaw");
        XAI.addRules(strPath+"res/4/§38.jlaw");
        XAI.addRules(strPath+"res/4/§39.jlaw");
        //XAI.addRules(strPath+"res/4/§40.jlaw");

        XAI.addRules(strPath+"res/5/§41.jlaw");
        XAI.addRules(strPath+"res/5/§42.jlaw");
        XAI.addRules(strPath+"res/5/§43.jlaw");
        XAI.addRules(strPath+"res/6/§44.jlaw");
        XAI.addRules(strPath+"res/6/§45.jlaw");
        XAI.addRules(strPath+"res/6/§46.jlaw");
        XAI.addRules(strPath+"res/6/§47.jlaw");
        XAI.addRules(strPath+"res/6/§48.jlaw");
        XAI.addRules(strPath+"res/7/§49.jlaw");
        XAI.addRules(strPath+"res/7/§50.jlaw");

        XAI.addRules(strPath+"res/8/§51.jlaw");
        XAI.addRules(strPath+"res/8/§52.jlaw");
        XAI.addRules(strPath+"res/10/§53.jlaw");
        XAI.addRules(strPath+"res/10/§54.jlaw");
        XAI.addRules(strPath+"res/10/§55.jlaw");
        XAI.addRules(strPath+"res/10/§56.jlaw");
        //XAI.addRules(strPath+"res/10/§57.jlaw");
        XAI.addRules(strPath+"res/10/§58.jlaw");
        XAI.addRules(strPath+"res/10/§59.jlaw");
        XAI.addRules(strPath+"res/10/§60.jlaw");
    }

    @FXML
    protected void removeFact(){
        int pos = factList.getSelectionModel().getSelectedIndex();
        factList.getSelectionModel().clearSelection(pos);
        if(pos<0) return;
        if(pos<factList.getItems().size()) factList.getItems().remove(pos);
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
            stack.add(active);
            if(active.clause == null) {
                selectClause(pos);
            }else{
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
                answerList.getItems().add("(" + i + "): " + clause.reasons.get(0));
                i++;
            }
        }
    }

    private void selectClause(int pos) {
        active = new ClauseAtom(groundClausesUsed.get(active.atom).get(pos));

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