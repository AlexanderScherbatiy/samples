package helloworld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setTitle("Hello World");


        Button button = new Button("Hello");
        button.setOnAction((e) -> {
            System.out.println("Hello World!");
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root, 200, 100));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
