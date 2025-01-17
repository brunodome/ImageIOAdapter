package unical.demacs.Model.Filter;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class Negative implements Filter {


    @Override
    public Image applyFilter(Image image) {

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = bufferedImage.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int invertedRed = 255 - red;
                int invertedGreen = 255 - green;
                int invertedBlue = 255 - blue;

                int newRgb = (invertedRed << 16) | (invertedGreen << 8) | invertedBlue;
                newImage.setRGB(x, y, newRgb);
            }
        }

        return SwingFXUtils.toFXImage(newImage, null);
    }

}
