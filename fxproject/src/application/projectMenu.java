package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class projectMenu extends Application{

	public void start(Stage stage) throws Exception {
		MenuBar menubar = new MenuBar();
		
		Menu file = new Menu("File");
		Menu edit = new Menu("Edit");
		Menu source = new Menu("Source");
		Menu source2 = new Menu("Source2");
		
		source.getItems().add(source2);
		
		MenuItem i1 = new MenuItem("one");
		MenuItem i2 = new MenuItem("two");
		
		source2.getItems().addAll(i1, i2);
		
		menubar.getMenus().add(file);
		menubar.getMenus().add(edit);
		menubar.getMenus().add(source);
		
		MenuItem item1 = new MenuItem("new");
		//MenuItem item1 = new MenuItem("isChecked?");
		item1.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
		item1.setOnAction(e -> {
			System.out.println("new button has been click!");
		});
		
		CheckMenuItem item2 = new CheckMenuItem("Always on Top");
		item2.setOnAction(e -> {
			System.out.println("item2 cliked!");
		});
//		MenuItem item1 = new MenuItem("isChecked?");
//		item1.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
//		item1.setOnAction(e -> {
//			if(item2.isSelected()) {
//				System.out.println("it it selected");
//			}
//			else {
//				System.out.println("it it not sclected");
//			}
//		});
		
		file.getItems().add(item1);
		file.getItems().add(item2);
		
		StackPane pane = new StackPane();
		pane.getChildren().add(menubar);
		StackPane.setAlignment(menubar, Pos.TOP_LEFT);
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("MenuBar");
		stage.setWidth(825);
		stage.setHeight(550);
		
		stage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

	
}
