package vccompost.tablet;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LayoutTabletController implements Initializable{

	Actualizar_Tablet at;
	
	@FXML Button But1;
	@FXML Button updIP;
	@FXML Button But2;
	@FXML Button ButFrio;
	@FXML Button ButBien;
	@FXML Button ButTibio;
	@FXML Button ButCaliente;
	@FXML TextField numVag;
	@FXML TextField aDonde;
	@FXML TextField posActual;
	@FXML Label Label1;
	@FXML TextArea text;
	@FXML TextField IP;
	@FXML TextField Port;
	
	
	@FXML public void But1Click() {
		at.consultar();
	}
	
	@FXML public void setIP() {
		at.Actualizar_IP(IP.getText(), Integer.parseInt(Port.getText()));
	}
	
	@FXML public void But2Click() {
		at.enviarPGru(Integer.parseInt(aDonde.getText()), Integer.parseInt(posActual.getText()));;
	}
	
	@FXML public void ButFrioClick() {
		at.enviarDatos("1#"+(Integer.parseInt(numVag.getText()))+"#3#"+
				 "100"+";"+"200"+";"+"01-01-2014 12:30:20"+";"+"12"+";"+"26"+";"+"30"+";"+"31"+"&"+
				 "200"+";"+"400"+";"+"01-01-2014 12:30:20"+";"+"26"+";"+"11"+";"+"25"+";"+"32"+"&"+
				 "300"+";"+"600"+";"+"01-01-2014 12:30:20"+";"+"21"+";"+"32"+";"+"20"+";"+"33"+"&/");
	}
	
	@FXML public void ButBienClick() {
		at.enviarDatos("1#"+(Integer.parseInt(numVag.getText()))+"#3#"+
				 "100"+";"+"200"+";"+"01-01-2014 12:30:20"+";"+"35"+";"+"36"+";"+"36"+";"+"36"+"&"+
				 "200"+";"+"400"+";"+"01-01-2014 12:30:20"+";"+"36"+";"+"35"+";"+"39"+";"+"37"+"&"+
				 "300"+";"+"600"+";"+"01-01-2014 12:30:20"+";"+"38"+";"+"37"+";"+"40"+";"+"38"+"&/");
	}
	
	@FXML public void ButTibioClick() {
		at.enviarDatos("1#"+(Integer.parseInt(numVag.getText()))+"#3#"+
				 "100"+";"+"200"+";"+"01-01-2014 12:30:20"+";"+"42"+";"+"26"+";"+"30"+";"+"31"+"&"+
				 "200"+";"+"400"+";"+"01-01-2014 12:30:20"+";"+"26"+";"+"51"+";"+"25"+";"+"32"+"&"+
				 "300"+";"+"600"+";"+"01-01-2014 12:30:20"+";"+"41"+";"+"52"+";"+"40"+";"+"33"+"&/");
	}
	
	@FXML public void ButCalienteClick() {
		at.enviarDatos("1#"+(Integer.parseInt(numVag.getText()))+"#3#"+
				 "100"+";"+"200"+";"+"01-01-2014 12:30:20"+";"+"12"+";"+"86"+";"+"30"+";"+"31"+"&"+
				 "200"+";"+"400"+";"+"01-01-2014 12:30:20"+";"+"26"+";"+"51"+";"+"95"+";"+"32"+"&"+
				 "300"+";"+"600"+";"+"01-01-2014 12:30:20"+";"+"21"+";"+"92"+";"+"40"+";"+"33"+"&/");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		at = new Actualizar_Tablet(IP.getText(), Integer.parseInt(Port.getText()));
	}
	
}
