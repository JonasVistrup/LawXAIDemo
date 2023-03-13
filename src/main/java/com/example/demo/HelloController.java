package com.example.demo;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.SLD.Answer;
import com.example.demo.SLD.History;
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

    public XAI xai;

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
        //AtomList query = new AtomList(this.xai.pb.parseAtom("BrudtLoven(X,Y)"));
        AtomList query = new AtomList(this.xai.pb.parseAtom("BrokenLaw(X,Y)"));
        answers = this.xai.query(new ArrayList<>(factList.getItems()),query);

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
        this.xai = new XAI(strPath+ "predicates-ENG.txt");
        this.xai.addRules(strPath+"paragraf-41-ENG.txt");
        this.xai.addRules(strPath+"paragraf-42-ENG.txt");
    }

    @FXML
    protected void removeFact(){
        int pos = factList.getSelectionModel().getSelectedIndex();
        factList.getSelectionModel().clearSelection(pos);
        factList.getItems().remove(pos);
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