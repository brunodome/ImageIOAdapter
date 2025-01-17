package unical.demacs.Model.AdapterStructure.Target;

import javafx.scene.image.Image;
import unical.demacs.Model.Filter.Filter;

public interface ImageEditor {

    Image loadImage(String path);
    boolean saveImage(Image imageToSave, String format, String path);
    Image resizeImage(Image imageToResize, int width, int height);
    Image applyFilter(Filter filter, Image image);

}
