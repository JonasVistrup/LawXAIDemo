package com.example.demo;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.Logic.High.Clause;
import com.example.demo.SLD.AndOrHistory;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

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
    private Stack<String> answerInfoStack;
    private Stack<History.HistoryNode> nodeStack;
    private History.HistoryNode currentNode;
    @FXML
    private ListView<String> answerList;

    private Stack<ArrayList<String>> explanationStack;


    private ArrayList<String> facts = new ArrayList<>();

    private List<Answer> answers;

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
        AtomList query = new AtomList(XAI.pb.parseAtomOld("BrudtLoven(X,Y,T)"));
        AndOrHistory answers = XAI.query(new ArrayList<>(factList.getItems()),query);
        for(Clause c: XAI.pb.getProgram()){
            System.out.println(c);
        }
        System.out.println("Facts Size: "+factList.getItems().size());
        System.out.println("Size: "+answers.size());


        explanationStack = new Stack<>();
        answerInfoStack = new Stack<>();
        nodeStack = new Stack<>();
        currentNode = null;
        answerList.getItems().clear();

        answerInfo.setText("Select an answer for explanation");
        for(Answer answer: answers) {
            answerList.getItems().add(query.applySub(answer.answer).toString());
        }
    }

    @FXML
    protected void onReasoningClick(){
        System.out.println("Clicked");
        if(answers==null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "You must query the system and select an answer before reasonings can be given!");
            alert.show();
        } else if(currentNode==null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "You must select an answer before reasonings can be given!");
            alert.show();
        }else{
            StringBuilder reasons = new StringBuilder();
            for(String reason: currentNode.clauseUsed.reasons){
                reasons.append(reason).append("\n");
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, reasons.toString());
            alert.show();
        }
    }

    @FXML
    protected void onBackClick(){
        if(explanationStack.empty()) return;

        this.answerList.getItems().clear();
        this.answerList.getItems().addAll(this.explanationStack.pop());

        if(nodeStack.empty()){
            this.answerInfo.setText("Select an answer for explanation");
            this.currentNode = null;
            return;
        }
        this.answerInfo.setText(this.answerInfoStack.pop());
        this.currentNode = nodeStack.pop();
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
        if(currentNode == null){
            selectAnswer();
        }else{
            selectExplanation();
        }
    }

    private void selectExplanation(){
        int pos = answerList.getSelectionModel().getSelectedIndex();
        answerList.getSelectionModel().clearSelection(pos);

        this.nodeStack.add(this.currentNode);
        this.answerInfoStack.add(this.answerInfo.getText());
        this.explanationStack.add(new ArrayList<>(this.answerList.getItems()));
        this.answerList.getItems().clear();

        this.currentNode = this.currentNode.children.get(pos);
        this.answerInfo.setText(currentNode.atomSolved.explain());
        for(History.HistoryNode child: this.currentNode.children){
            this.answerList.getItems().add(child.atomSolved.explain());
        }
    }

    private void selectAnswer(){
        int pos = answerList.getSelectionModel().getSelectedIndex();
        answerList.getSelectionModel().clearSelection(pos);
        if(pos < 0) return;

        explanationStack.add(new ArrayList<>(answerList.getItems()));
        answerList.getItems().clear();

        Answer answer = this.answers.get(pos);
        this.currentNode = answer.reason.tops.get(0);
        this.answerInfo.setText(currentNode.atomSolved.explain());
        for(History.HistoryNode child: this.currentNode.children){
            this.answerList.getItems().add(child.atomSolved.explain());
        }
    }
}