package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
	private Viewer viewer;
	private LinkedList<ModelPerson> modelPerson;
	private int index = -1;
	
	Controller(Stage primaryStage){
		this.modelPerson = new LinkedList<ModelPerson>();
		this.viewer = new Viewer(primaryStage, this, modelPerson);
	}
	
	public void safeFile() {
		/*
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setTitle("Safe File");
		a.setContentText("It is Working.\n SAFE FILE");
		a.showAndWait();
		*/
		FileChooser fc = new FileChooser();
		File f = new File(System.getProperty("user.home") + "/Desktop");
		fc.setInitialDirectory(f);
		f = fc.showSaveDialog(null);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write("Vorname,Nachname,Alter,Skill\n");
			for(ModelPerson m: modelPerson) {
				bw.write(m.getVorname() + "," + m.getNachname() + "," + m.getAlter() + ","  + m.getSkill() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			this.viewer.showError(e.toString());
		}
		
	}
	
	public void readFile() {
			FileChooser fc = new FileChooser();
			fc.setTitle("Choose File to read");
			
			File f = new File(System.getProperty("user.home") + "/Desktop");
			fc.setInitialDirectory(f);
			f = fc.showOpenDialog(null);
			
			String readline = "";
			
			try {
				
				modelPerson.clear();
				
				BufferedReader br = new BufferedReader(new FileReader(f));
				readline = br.readLine();
				
				while((readline = br.readLine()) != null) {
					int count = 0;
					String vname = "";
					String nname = "";
					String alter = "";
					String skill = "";
					
					for(int i = 0; i < readline.length(); i++) {
						if(readline.toCharArray()[i] == ',') {
							i++;
							count++;
						}
						
						switch(count) {
						case 0:{
							vname += readline.toCharArray()[i];
						}break;
						case 1:{
							nname += readline.toCharArray()[i];
						}break;
						case 2:{
							alter += readline.toCharArray()[i];
						}break;
						case 3:{
							skill += readline.toCharArray()[i];
						}break;
						/*
						case 4:{
							count = 0;
						}break;
						*/
						}//switch
					}
					
					getListModePerson().add(new ModelPerson(vname, nname, Integer.parseInt(alter), Integer.parseInt(skill)));
				}//While
				br.close();
				this.index = 0;
			} catch (IOException e) {
				this.viewer.showError(e.toString());
			}catch(NullPointerException e) {
				
			}
			
			this.viewer.showPerson();
	}
	
	public void addPerson() {
		this.viewer.showAdd();
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void incIndex() {
		this.index++;
		if(this.index > modelPerson.size() - 1) {
			this.index = 0;
		}
	}
	
	public void decIndex() {
		this.index--;
		if(this.index < 0) {
			this.index = modelPerson.size() - 1;
		}
	}
	
	public LinkedList<ModelPerson> getListModePerson(){
		return this.modelPerson;
	}
	
	public void AddToList(GridPane g) {
		//System.out.print(((TextField)(g.getChildren().get(4))).getText());
		getListModePerson().add(new ModelPerson(((TextField)(g.getChildren().get(4))).getText(), ((TextField)(g.getChildren().get(5))).getText(), Integer.parseInt(((TextField)(g.getChildren().get(6))).getText()), Integer.parseInt(((TextField)(g.getChildren().get(7))).getText())));
		incIndex();
		this.viewer.showPerson();
	}
}
