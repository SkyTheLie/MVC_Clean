package application;

import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Viewer {
	private LinkedList<ModelPerson> modelPerson;
	private Controller controller;
	
	private BorderPane bp;
	private GridPane g;
	private Button btnext ,btback;
	
	
	Viewer(Stage primarystage, Controller controller, LinkedList<ModelPerson> modelPerson){
		this.modelPerson = modelPerson;
		this.controller = controller;
		
		iniElements();

		Scene s = new Scene(bp, 350, 200);
		primarystage.setTitle("MVC Programm");
		primarystage.setScene(s);
		primarystage.show();
	}
	
	private void iniElements() {
		this.btback = new Button("Back");
		this.btnext = new Button("Next");
		this.g = new GridPane();
		this.bp = new BorderPane();
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(btback, btnext);
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.BOTTOM_CENTER);
		
		this.g.setAlignment(Pos.CENTER);
		this.g.setVgap(5);
		this.g.setHgap(10);
		
		this.g.add(new Label("Vorname"), 0, 1);
		this.g.add(new Label("Nachname"), 0, 2);
		this.g.add(new Label("Alter"), 0, 3);
		this.g.add(new Label("Skill"), 0, 4);
		
		/*
		// How you get Element with no ref, only in grid.
		int a = this.g.getChildren().indexOf(new Label("Skill"));
		this.g.add(new Label(Integer.toString(a)), 1, 1);
		
		((Label)this.g.getChildren().get(3)).setText("ASDFA");
		 */
		
		this.g.add(new TextField(), 1, 1);
		this.g.add(new TextField(), 1, 2);
		this.g.add(new TextField(), 1, 3);
		this.g.add(new TextField(), 1, 4);
		
		//bp.getChildren().addAll(g, hbox);
		bp.setCenter(g);
		bp.setBottom(hbox);
		
		//((HBox)this.bp.getBottom()).getChildren().add(new Button("LOL"));
		bp.setRight(new VBox());
		((VBox)bp.getRight()).setAlignment(Pos.CENTER_RIGHT);
		((VBox)bp.getRight()).setSpacing(10);
		((VBox)bp.getRight()).setPadding(new Insets(10, 10, 10, 10));
		((VBox)bp.getRight()).getChildren().addAll(new Button("Read"), new Button("Add"), new Button("Save"));
		
		setActionButton();
		btnext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				controller.incIndex();
				showPerson();
			}
		});
		
		btback.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				controller.decIndex();
				showPerson();
			}
		});
	}

	private void setActionButton() {
		for(int i = 0; i < ((VBox)bp.getRight()).getChildren().size(); i++) {
			switch(i) {
				case 0:{
					((Button)((VBox)bp.getRight()).getChildren().get(i)).setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
							controller.readFile();
						}
					});
				}break;
				case 1:{
					((Button)((VBox)bp.getRight()).getChildren().get(i)).setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
							controller.addPerson();
						}
					});
				}break;
				case 2:{
					((Button)((VBox)bp.getRight()).getChildren().get(i)).setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
							controller.safeFile();
						}
					});
				}break;
			}// switch
		}//for
	}//setActionButton
	
	public void showPerson() {
		if(controller.getListModePerson().size() != 0) {
			for(int i = 0 ; i < g.getRowCount() ; i++) {
				switch(i) {
					case 0:{
						//((TextField)g.getColumnConstraints().).setText((controller.getListModePerson().get(controller.getIndex())).getVorname());
						((TextField)g.getChildren().get(i + 4)).setText((controller.getListModePerson().get(controller.getIndex())).getVorname());
					}break;
					case 1:{
						((TextField)g.getChildren().get(i + 4)).setText((controller.getListModePerson().get(controller.getIndex())).getNachname());
					}break;
					case 2:{
						((TextField)g.getChildren().get(i + 4)).setText(Integer.toString((controller.getListModePerson().get(controller.getIndex())).getAlter()));
					}break;
					case 3:{
						((TextField)g.getChildren().get(i + 4)).setText(Integer.toString((controller.getListModePerson().get(controller.getIndex())).getSkill()));
					}break;
				}//switch
			}//for
		}
	}
	
	public void showError(String e) {
		Alert a = new Alert(AlertType.ERROR);
		a.setTitle("Error");
		a.setContentText("Somethink went wrong!\n" + e);
		a.showAndWait();
	}

	public void showAdd() {
		Stage st = new Stage();
		st.setTitle("Person Hinzufügen");
		
		BorderPane bp1 = new BorderPane();
		GridPane g1 = new GridPane();
		g1.add(new Label("Vorname"), 0, 1);
		g1.add(new Label("Nachname"), 0, 2);
		g1.add(new Label("Alter"), 0, 3);
		g1.add(new Label("Skill"), 0, 4);
		g1.add(new TextField(), 1, 1);
		g1.add(new TextField(), 1, 2);
		g1.add(new TextField(), 1, 3);
		g1.add(new TextField(), 1, 4);
		
		//bp1.getChildren().add(g);
		bp1.setCenter(g1);
		g1.setAlignment(Pos.CENTER);
		bp1.setBottom(new Button("Add"));
		
		((Button)bp1.getBottom()).setAlignment(Pos.BOTTOM_CENTER);
		((Button)bp1.getBottom()).setOnAction(e-> { controller.AddToList(g1); st.close();});
		
		Scene s = new Scene(bp1, 220, 200);
		st.setScene(s);
		st.show();
	}

	
	
}
