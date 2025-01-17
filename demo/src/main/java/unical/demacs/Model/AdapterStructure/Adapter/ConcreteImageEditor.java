package unical.demacs.Model.AdapterStructure.Adapter;

import javafx.scene.image.Image;
import unical.demacs.Model.AdapterStructure.Target.ImageEditor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import unical.demacs.Model.Filter.Filter;

public class ConcreteImageEditor implements ImageEditor {

    @Override
    public Image loadImage(String path) {

        try {

            File file = new File(path);
            BufferedImage bufferedImage = ImageIO.read(file);
            return SwingFXUtils.toFXImage(bufferedImage, null);

        } catch (IOException e) {
            return null;
        }

    }


    @Override
    public boolean saveImage(Image imageToSave, String format, String path) {

        boolean firstCheck = Arrays.asList(ImageIO.getWriterFormatNames()).contains(format);

        if (firstCheck) {

            try {

                BufferedImage toUse;
                int width = (int) imageToSave.getWidth();
                int height = (int) imageToSave.getHeight();

                if(format.equals("jpg")){
                    toUse = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                } else{
                    toUse = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                }

                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageToSave, toUse);
                File outputFile = new File(path);
                ImageIO.write(bufferedImage, format, outputFile);
                return true;

            } catch (IOException e) {
                return false;
            }
        }

        return false;
    }


    @Override
    public Image resizeImage(Image imageToResize, int width, int height) {

        BufferedImage convertedImageToResize = SwingFXUtils.fromFXImage(imageToResize, null);
        BufferedImage resizedImage = new BufferedImage(width, height, convertedImageToResize.getType());

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(convertedImageToResize, 0, 0, width, height, null);
        g2d.dispose();

        return SwingFXUtils.toFXImage(resizedImage, null);

    }


    @Override
    public Image applyFilter(Filter filter, Image image){
        return filter.applyFilter(image);
    }
}
