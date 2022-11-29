package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
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
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("CarLot X: Welcome!");
			
			BorderPane border = new BorderPane();			
			HBox hBox = new HBox(8); // spacing = 8
			border.setTop(hBox);
			border.setCenter(addGrid());
		    hBox.getChildren().addAll(new Label("Car Name:"), tfcarName);
		    Scene scene = new Scene(border,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	public GridPane addGrid() {
		GridPane grid = new GridPane();
			grid.setHgap(10);
		    grid.setVgap(10);
		    grid.setPadding(new Insets(0, 10, 0, 10));
		    Separator line = new Separator();
		    
		    // Category in column 2, row 1
		    Text gridTitle = new Text("Results:");
		    gridTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		    grid.add(gridTitle, 0,0,4,1); 

		    // Subtitle in columns 2-3, row 2
		    Text gridSubtitle = new Text("Car Information");
		    grid.add(gridSubtitle, 0, 1);
		    GridPane.setValignment(line, VPos.TOP);
		    grid.add(line, 0,2,4,1);

		    // House icon in column 1, rows 1-2


		    // Left label in column 1 (bottom), row 3
		    Text goodsPercent = new Text("Goods\n80%");
		    GridPane.setValignment(goodsPercent, VPos.BOTTOM);
		    grid.add(goodsPercent, 0, 2); 

		    // Chart in columns 2-3, row 3


		    // Right label in column 4 (top), row 3
		    Text servicesPercent = new Text("Services\n20%");
		    GridPane.setValignment(servicesPercent, VPos.TOP);
		    grid.add(servicesPercent, 3, 2);

		    return grid;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
