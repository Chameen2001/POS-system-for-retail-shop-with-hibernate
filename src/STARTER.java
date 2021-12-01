import db.DbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class STARTER extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException, ClassNotFoundException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("view/MainForm.fxml")));
        scene.getStylesheets().add("style/addItemFormStyle.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println(DbConnection.getInstance().getConnection());
        System.out.println(DbConnection.getInstance().getConnection());

    }
}
