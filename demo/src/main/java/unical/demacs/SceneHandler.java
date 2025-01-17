package unical.demacs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import unical.demacs.Controller.EditorController;
import unical.demacs.Controller.ResizeSceneController;

public class SceneHandler {

    private static final SceneHandler instance = new SceneHandler();
    private Stage stage = null;
    private Scene scene = null;

    private SceneHandler() {};

    public static SceneHandler getInstance() {
        return instance;
    }

    public void init(Stage stage) {

        if(this.stage == null) {
            this.stage = stage;
            this.scene = new Scene(load("editor.fxml"), 1000, 600);
            this.stage.setTitle("Images Editor");
            this.stage.setScene(scene);
            this.stage.setResizable(false);
            this.stage.show();
        }
    }

    public void loadResizeScene(Image image, int width, int height) {

        if (this.scene != null) {

            FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("resize-scene.fxml"));

            try {

                Parent root = fxmlLoader.load();
                ResizeSceneController controller = fxmlLoader.getController();
                controller.setImageInfo(image, width, height);
                this.scene.setRoot(root);

            } catch (Exception e) {
                System.exit(1);
            }
        }
    }

    public void loadEditorScene(Image image){
        if (this.scene != null) {

            FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("editor.fxml"));

            try {

                Parent root = fxmlLoader.load();
                EditorController controller = fxmlLoader.getController();
                controller.setImage(image);
                this.scene.setRoot(root);

            } catch (Exception e) {
                System.exit(1);
            }
        }
    }

    public Scene getScene() {
        return scene;
    }

    private <T> T load(String fxmlFile) {

        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource(fxmlFile));
        try {
            return fxmlLoader.load();
        } catch (Exception e) {
            System.exit(1);
        }
        return null;
    }

    public void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
