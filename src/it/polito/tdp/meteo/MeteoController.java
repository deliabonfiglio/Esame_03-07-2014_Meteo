package it.polito.tdp.meteo;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.meteo.bean.Model;
import it.polito.tdp.meteo.bean.Situazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MeteoController {
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCalcola;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtTemperatura;
    
    @FXML
    void doCalcola(ActionEvent event) {
    	String temp = txtTemperatura.getText();
    
    	try {
    		int temperatura = Integer.parseInt(temp);
    		String localita= "Torino";
    		
    		model.creaGrafo(localita);
    		txtResult.clear();
    		txtResult.appendText("Temperature possibili nell'arco di 2 giorni: \n");
    		Set<Integer> tempPossibili = model.getTempPossibili(temperatura);
    		for(Integer i: tempPossibili){
    			txtResult.appendText(i+"\n");
    		}
    		
    	} catch(NumberFormatException e){
    		txtResult.appendText("Errore: inserire temperatura come numero intero.\n");
    	}
    }

    @FXML
    void doSearch(ActionEvent event) {
    	String temp = txtTemperatura.getText();
    	
    	try {
    		int temperatura = Integer.parseInt(temp);
    		String localita= "Torino";
    		List<LocalDate> date = model.getDatePerTemperatura(localita, temperatura);
    		if(date.size()!=0){
    		for(LocalDate d: date){
    			int tmedia_gg_prima = model.getTempMediaGGPrima(d);
    			txtResult.appendText(d.toString()+"\n");
    			txtResult.appendText("Temperatura media del giorno prima: "+tmedia_gg_prima+"\n");
    		}
    		
    		
    		} else {
    			txtResult.appendText("Non ci sono date con temperatura media pari a :"+temp);
    		}
    		
    	} catch(NumberFormatException e){
    		txtResult.appendText("Errore: inserire temperatura come numero intero.\n");
    	}
    }


    @FXML
    void initialize() {
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtTemperatura != null : "fx:id=\"txtTemperatura\" was not injected: check your FXML file 'Meteo.fxml'.";


    }


	public void setModel(Model model) {
		this.model=model;
		
	}

}
