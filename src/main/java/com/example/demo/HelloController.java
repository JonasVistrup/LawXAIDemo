package com.example.demo;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.Logic.High.Clause;
import com.example.demo.Logic.High.Substitution;
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
        if((""+factInput.getCharacters()).equals("test")){
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
        }else if((""+factInput.getCharacters()).equals("basic")){
            ArrayList<String> info = new ArrayList<>();
            info.add("SpeedNotReducedToMatchConditions(jonas,bil,vej)");
            info.add("LosesControl(jonas,bil)");
            facts.addAll(info);
            factList.getItems().addAll(info);
            factInput.setText("");
            return;
        }else if((""+factInput.getCharacters()).equals("case1")){
            ArrayList<String> info = new ArrayList<>();
            info.add("Køretøj(reg_nr_1)");
            info.add("Befordres(tiltalte,reg_nr_1,0521)");
            info.add("På(reg_nr_1,vej4,0521)");
            //info.add("På(tiltalte,vej4,0521)");
            info.add("Vej(vej4)");

            info.add("Færdselsuheld(uheld)");
            info.add("IndblandetI(tiltalte,uheld)");
            info.add("På(uheld,vej4,0521)");
            info.add("Slut(uheld,0521)");
            info.add("Ejer(skadelidte,husmur,0521)");
            info.add("ForvoldtPå(tiltalte,skade,husmur,0521)");

            info.add("Køretøj(reg_nr_2)");
            info.add("Bil(reg_nr_2)");
            info.add("Befordres(tiltalte,reg_nr_2,1210)");
            info.add("På(reg_nr_2,vej1,1210)");
            info.add("Cykelsti(vej1)");
            info.add("Sti(vej1)");
            info.add("På(tiltalte,vej1,1210)");
            info.add("IkkeEfterkommer(tiltalte,vej1_er_fællessti,1210)");
            info.add("GivesVed(vej1_er_fællessti,skilt,T)");
            info.add("På(skilt,vej1,T)");
            info.add("Afmærkning(skilt)");

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

    @FXML
    protected void onQueryClick(){
        activeAtom = XAI.pb.parseAtomOld("BrudtLoven(X,Y,T)");
        query = new AtomList(activeAtom);
        groundClausesUsed = new HashMap<>();
        answers = XAI.query(new ArrayList<>(factList.getItems()),query, groundClausesUsed);
        for(Clause c: XAI.pb.getProgram()){
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
        int i = 1;
        List<Clause> clauses = this.groundClausesUsed.get(active.atom);
        for(Clause clause: clauses){
            answerList.getItems().add("("+i+"): "+clause.reasons.get(0));
            i++;
        }
    }

    private void selectClause(int pos) {
        active = new ClauseAtom(groundClausesUsed.get(active.atom).get(pos));

        setupClause();
    }

    private void setupClause(){
        answerList.getItems().clear();

        if(active.clause.body.isEmpty()){
            this.answerInfo.setText(active.clause.head.explain() + " er givet af "+active.clause.head.toString());
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