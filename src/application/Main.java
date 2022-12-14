package application;

import java.util.ArrayList;

import carLot.Car;
import carLot.CarLot;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/*
 * application.Main is an application to:
 * Run a limited JavaFX implementation of the CarLot and Car classes.
 * The program instantiates a CarLot object, and provides GUI buttons to:
 *     ADD A CAR
 *     SELL A CAR
 * 	   VIEW all in INVENTORY
 *     VIEW all SOLD
 *     
 * The GUI is built with a BorderPane at root, having TOP, RIGHT, and CENTER nodes.
 * The TOP node contains buttons for creating a list view: of all cars, or all sold cars.
 * The CENTER node is the display pane (ListView, TableView, or TextArea) to display queried results.
 * The RIGHT node contains buttons for adding and selling (updating) a Car object from the instantiated CarLot.
 *     
 * Currently, the Add and Sell functions operate as expected.
 * The ListView/TableView/TextArea may not display correct results: 12/5/2022.
 * 
 * */

public class Main extends Application {

	private TextField tfcarName = new TextField();
	private TextField tfcarMileage = new TextField();
	private TextField tfcarMPG = new TextField();
	private TextField tfcarCost = new TextField();
	private TextField tfcarPrice = new TextField();
	private TextField tfcarSold = new TextField();

	CarLot carLotTest = new CarLot();

	// BorderPane with TOP,CENTER,RIGHT
	BorderPane border = new BorderPane();

	ObservableList<Car> tableData = FXCollections.observableList(carLotTest.getCarsInOrderOfEntry());

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("CarLot Inventory: Welcome!");

			//
			ListView<Car> inventoryView = new ListView<Car>(tableData);
			System.out.println(tableData);

			// tableData is updated and prints to console when adding car, but car does not
			// show in listview UI

			// set BorderPane
			border.setTop(addHBox());
			border.setCenter(inventoryView);
			border.setRight(addVBox());
			Scene scene = new Scene(border, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VBox addVBox() {
		VBox vBox = new VBox();
		Button addButton = new Button("Add Car");
		Button sellButton = new Button("Sell Car");
		addButton.setOnAction(e -> {
			String carID = tfcarName.getText();
			int mileage = Integer.parseInt(tfcarMileage.getText());
			int mpg = Integer.parseInt(tfcarMPG.getText());
			double cost = Double.parseDouble(tfcarCost.getText());
			double price = Double.parseDouble(tfcarPrice.getText());
			carLotTest.addCar(carID, mileage, mpg, cost, price);
			System.out.println(carLotTest.getInventory());
			System.out.println(tableData);
			tfcarName.setText("");
			tfcarMileage.setText("");
			tfcarMPG.setText("");
			tfcarCost.setText("");
			tfcarPrice.setText("");
		});

		sellButton.setOnAction(e -> {
			String carID = tfcarName.getText();
			double priceSold = Double.parseDouble(tfcarSold.getText());
			carLotTest.sellCar(carID, priceSold);
			System.out.println(carLotTest.getInventory());
		});
		vBox.getChildren().addAll(new Label("Car Name"), tfcarName, new Label("Mileage"), tfcarMileage,
				new Label("MPG"), tfcarMPG, new Label("Cost"), tfcarCost, new Label("Retail Price"), tfcarPrice,
				new Text("Enter ALL to Add"), addButton, new Text("Enter NAME to Sell:"), sellButton,
				new Label("Sold For:"), tfcarSold);
		return vBox;
	}

	public HBox addHBox() {
		HBox hBox = new HBox();
		Button allButton = new Button("View All");
		Button soldButton = new Button("View Sold");
		hBox.getChildren().addAll(allButton, soldButton);
		allButton.setOnAction(e -> {
			System.out.println(carLotTest.getInventory());
		});
		
		soldButton.setOnAction(e -> {
			System.out.println(carLotTest.returnSoldCars());
		});
		hBox.getChildren().addAll(new Label("Car Name"), tfcarName, new Label("Mileage"), tfcarMileage,
				new Label("MPG"), tfcarMPG, new Label("Cost"), tfcarCost, new Label("Retail Price"), tfcarPrice);
		return hBox;
	}


	/*
	 * public GridPane addGrid() { GridPane grid = new GridPane(); grid.setHgap(10);
	 * grid.setVgap(10); grid.setPadding(new Insets(0, 10, 0, 10)); Separator line =
	 * new Separator();
	 * 
	 * Text gridTitle = new Text("Current Inventory:");
	 * gridTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	 * grid.add(gridTitle, 0, 0, 4, 1);
	 * 
	 * Text gridSubtitle = new Text("Car Information"); grid.add(gridSubtitle, 0,
	 * 1); GridPane.setValignment(line, VPos.TOP); grid.add(line, 0, 2, 4, 1);
	 * 
	 * ObservableList<Car> items =
	 * FXCollections.observableArrayList(carLot.getInventory());
	 * System.out.println(items); grid.add(items, 0, 3, 4, 1); return grid; }
	 */

	public static void main(String[] args) {
		launch(args);
	}
}
