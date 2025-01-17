package unical.demacs.Model.Filter;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class Grayscale implements Filter {


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
                int gray = (red + green + blue) / 3;
                int newRgb = (gray << 16) | (gray << 8) | gray;
                newImage.setRGB(x, y, newRgb);
            }
        }

        return SwingFXUtils.toFXImage(newImage, null);
    }
}
