package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modell.Szavak;
import org.javatuples.Pair;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class SzotanuloContoller {


    private int szamlalo;
    private int randomErtek = 0;

    @FXML
    public void initialize() {
        helyes.setText("0");
        hibas.setText("0");
        megoldas.setText("");
        counter.setText("0");
        angolCimke.setText("angol");
        magyarCimke.setText("magyar");
        megoldasCimke.setText("megoldas");
        helyesCimke.setText("helyes");
        hibasCimke.setText("hibas");

        szamlalo = 0;
        Random random = new Random();
        randomErtek = random.nextInt(Szavak.szotar.size());
        Pair<String, String> elem = Szavak.szotar.get(randomErtek);
        angol.setText(elem.getValue0());
    }

    @FXML
    public AnchorPane anchor;

    @FXML
    public TextField magyar;

    @FXML
    public Button tipp;

    @FXML
    public Button befejezes;

    @FXML
    public Label helyes;

    @FXML
    public Label hibas;

    @FXML
    public Label angol;

    @FXML
    public Label megoldas;

    @FXML
    public Label counter;

    @FXML
    public Label angolCimke;

    @FXML
    public Label magyarCimke;

    @FXML
    public Label megoldasCimke;

    @FXML
    public Label helyesCimke;

    @FXML
    public Label hibasCimke;

    @FXML
    public void tipp(){

        Pair<String, String> elem = Szavak.szotar.get(randomErtek);

        if(szamlalo % 2 == 0) {
            megoldas.setText(elem.getValue1());
            if(Objects.equals(elem.getValue1(), magyar.getText())){
                helyes.setText(String.valueOf(Integer.parseInt(helyes.getText()) + 1));
            }
            else{
                hibas.setText(String.valueOf(Integer.parseInt(hibas.getText()) + 1));
            }
            megoldas.setText(elem.getValue1());
        }
        else{
            Random random = new Random();
            randomErtek = random.nextInt(Szavak.szotar.size());
            elem = Szavak.szotar.get(randomErtek);
            angol.setText(elem.getValue0());
        }
        szamlalo++;
        counter.setText(String.valueOf((int)szamlalo / 2 + 1));
    }
    @FXML
    public void befejezes() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put(helyes.getText(), hibas.getText());
        objectMapper.writeValue(new File("/src/main/resources/eredmenyek.json"), jsonNode);

        Stage stage = (Stage) befejezes.getScene().getWindow();
        stage.close();
    }
}
