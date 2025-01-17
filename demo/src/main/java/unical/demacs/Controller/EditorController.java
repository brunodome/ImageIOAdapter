package unical.demacs.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import unical.demacs.Model.AdapterStructure.Adapter.ConcreteImageEditor;
import unical.demacs.Model.AdapterStructure.Target.ImageEditor;
import unical.demacs.Model.Filter.Filter;
import unical.demacs.Model.Filter.Negative;
import unical.demacs.Model.Filter.Grayscale;
import unical.demacs.Model.Filter.Sepia;
import unical.demacs.SceneHandler;

import java.io.File;

public class EditorController {

    private final ImageEditor imageEditor = new ConcreteImageEditor();
    private Image image = null;

    @FXML
    private MenuItem negativeItem;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private MenuButton filterButton;

    @FXML
    private MenuItem grayScaleItem;

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Button openButton;

    @FXML
    private Button resizeButton;

    @FXML
    private Button saveButton;

    @FXML
    private MenuItem sepiaItem;

    @FXML
    void onClickNegativeItem(ActionEvent event) {
        applyFilter(new Negative());
    }


    @FXML
    void onClickSepiaItem(ActionEvent event) {
        applyFilter(new Sepia());
    }


    @FXML
    void onClickGrayScaleItem(ActionEvent event) {
       applyFilter(new Grayscale());
    }


    @FXML
    void onClickFilterButton(ActionEvent event) {

    }


    @FXML
    void onClickOpenButton(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = (Stage) openButton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();
            Image image  = imageEditor.loadImage(path);
            this.image = image;
            imageView.setImage(image);

        }

    }

    @FXML
    void onClickResizeButton(ActionEvent event) {

        if(image == null) {
            SceneHandler.getInstance().showErrorMessage("No image selected");
            return;
        }

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        SceneHandler.getInstance().loadResizeScene(image, width, height);
    }

    @FXML
    void onClickSaveButton(ActionEvent event) {

        if(image == null) {
            SceneHandler.getInstance().showErrorMessage("No image selected");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG", "*.jpg");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG", "*.png");

        fileChooser.getExtensionFilters().addAll(pngFilter, jpgFilter);
        fileChooser.setTitle("Save Image");

        Stage stage = (Stage) openButton.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {

            String format = fileChooser.getSelectedExtensionFilter().getDescription().toLowerCase();
            String path = file.getAbsolutePath();

            boolean check = imageEditor.saveImage(image, format, path);

            if(!check) {
                SceneHandler.getInstance().showErrorMessage("Save image failed");
            }

        }
    }


    public void setImage(Image image) {
        this.image = image;
        imageView.setImage(image);
    }

    private void applyFilter(Filter filter){

        if(image == null) {
            SceneHandler.getInstance().showErrorMessage("No image selected");
            return;
        }

        Image filteredImage  = imageEditor.applyFilter(filter, image);
        imageView.setImage(filteredImage);
        this.image = filteredImage;

    }

}
