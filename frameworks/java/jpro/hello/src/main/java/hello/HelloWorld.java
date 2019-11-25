package hello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    @Override
    public void start(Stage stage) {
        TextField name = new TextField("World");
        Label hello = new Label("Hello, World!");
        Button button = new Button("Greeting");
        button.setOnAction((e) -> {
            hello.setText(String.format("Hello, %s!", name.getText()));
        });
        Scene scene = new Scene(new VBox(name, button, hello), 640, 480);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}