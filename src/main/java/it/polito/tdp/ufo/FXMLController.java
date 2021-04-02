package it.polito.tdp.ufo;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.ufo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {


	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private ComboBox<?> seglieUtente;
    //Prova:
    

    @FXML
    private Button btnConta;

    @FXML
    void doConta(ActionEvent event) {

    	if(seglieUtente==null) { //se non ha scelto la forma
    		txtRisultato.setText("Inserire una forma!");
    	}
    	Integer qvolte;
    	String forma;
    	try {
    		
    		forma= seglieUtente.getAccessibleText();
    		 qvolte = this.m.countByShape(forma);
    	
    		
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    	if(qvolte==null || forma==null) {
			txtRisultato.setText("Spiascente,non funziona");
		}
    	
    	txtRisultato.setText("La forma "+forma+" è apparsa "+qvolte+" volte");
    	
    	
    }

    @FXML
    void impostaLingua(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert seglieUtente != null : "fx:id=\"seglieUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnConta != null : "fx:id=\"btnConta\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    Model m;
	public void setModel(Model model) {
		this.m=model;
		
	}
}
