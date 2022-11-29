package application;

import carLot.CarLot;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {

	private TextField tfcarName = new TextField();
	private TextField tfcarMileage = new TextField();
	private TextField tfcarMPG = new TextField();
	private TextField tfcarCost = new TextField();
	private TextField tfcarPrice = new TextField();

	CarLot carLot = new CarLot();

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("CarLot X: Welcome!");
			BorderPane border = new BorderPane();
			border.setTop(addHBox());
			border.setCenter(addGrid());
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
			carLot.addCar(carID, mileage, mpg, cost, price);
		});
		// sellButton.setOnAction(e -> sellCar());
		vBox.getChildren().addAll(new Label("Car Name"), tfcarName, new Label("Mileage"), tfcarMileage,
				new Label("MPG"), tfcarMPG, new Label("Cost"), tfcarCost, new Label("Retail Price"), tfcarPrice,
				addButton, sellButton);
		return vBox;
	}

	public HBox addHBox() {
		HBox hBox = new HBox();
		Button allButton = new Button("View All");
		Button soldButton = new Button("View Sold");
		hBox.getChildren().addAll(allButton, soldButton);
		allButton.setOnAction(e -> {
			carLot.getInventory();
		});
		return hBox;
	}

	public GridPane addGrid() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		Separator line = new Separator();

		Text gridTitle = new Text("Current Inventory:");
		gridTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(gridTitle, 0, 0, 4, 1);

		Text gridSubtitle = new Text("Car Information");
		grid.add(gridSubtitle, 0, 1);
		GridPane.setValignment(line, VPos.TOP);
		grid.add(line, 0, 2, 4, 1);

		return grid;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
