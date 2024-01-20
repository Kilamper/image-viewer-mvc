package software.ulpgc.imageviewer.image;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Resizer {
    private final BufferedImage original;

    public Resizer(BufferedImage original) {
        this.original = original;
    }

    public BufferedImage resize(double factor) {
        if (factor < 1) {
            Image scaledInstance = original.getScaledInstance((int) (original.getWidth()*factor),
                    (int) (original.getHeight()*factor), Image.SCALE_SMOOTH);
            BufferedImage outputImage = new BufferedImage((int) (original.getWidth()*factor),
                    (int) (original.getHeight()*factor), BufferedImage.TYPE_INT_RGB);
            outputImage.getGraphics().drawImage(scaledInstance, 0, 0, null);
            return outputImage;
        } else {
            return original;
        }
    }
}
