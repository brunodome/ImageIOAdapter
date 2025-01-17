package unical.demacs.Model.Filter;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class Sepia implements Filter {

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

                int newRed = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
                int newGreen = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
                int newBlue = (int) (0.272 * red + 0.534 * green + 0.131 * blue);

                newRed = Math.min(newRed, 255);
                newGreen = Math.min(newGreen, 255);
                newBlue = Math.min(newBlue, 255);

                int newRgb = (newRed << 16) | (newGreen << 8) | newBlue;
                newImage.setRGB(x, y, newRgb);
            }
        }

        return SwingFXUtils.toFXImage(newImage, null);
    }
}
