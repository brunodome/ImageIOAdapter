package unical.demacs.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import unical.demacs.Model.AdapterStructure.Adapter.ConcreteImageEditor;
import unical.demacs.Model.AdapterStructure.Target.ImageEditor;
import unical.demacs.SceneHandler;

public class ResizeSceneController {

    private final ImageEditor imageEditor = new ConcreteImageEditor();
    private Image imageToResize;

    @FXML
    private Button cancelButton;

    @FXML
    private Label currentHeightLabel;

    @FXML
    private Label currentWidthLabel;

    @FXML
    private TextField newHeightTextField;

    @FXML
    private TextField newWidthTextField;

    @FXML
    private Button nextButton;


    @FXML
    void onClickCancelButton(ActionEvent event) {
        SceneHandler.getInstance().loadEditorScene(imageToResize);
    }

    @FXML
    void onClickNextButton(ActionEvent event) {

        if(newHeightTextField.getText().isEmpty() || newWidthTextField.getText().isEmpty()) {
            SceneHandler.getInstance().showErrorMessage("Please fill all the required fields");
            return;
        }

        if(!(newHeightTextField.getText().matches("\\d+") || newWidthTextField.getText().matches("\\d+"))) {
            SceneHandler.getInstance().showErrorMessage("Please insert only numbers");
            return;
        }

        int newHeight = Integer.parseInt(newHeightTextField.getText());
        int newWidth = Integer.parseInt(newWidthTextField.getText());
        Image resizedImage = imageEditor.resizeImage(imageToResize,newWidth, newHeight);
        SceneHandler.getInstance().loadEditorScene(resizedImage);

    }

    public void setImageInfo(Image image, int width, int height) {
        this.imageToResize = image;
        currentWidthLabel.setText(String.valueOf(width));
        currentHeightLabel.setText(String.valueOf(height));
    }

}
