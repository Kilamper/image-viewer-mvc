package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.image.Image;
import software.ulpgc.imageviewer.image.ImageDisplay;
import software.ulpgc.imageviewer.image.Resizer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.List;

import static java.lang.Math.min;
import static java.lang.Math.round;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;
    private BufferedImage bitmap;

    @Override
    public void show(Image image) {
        this.image = image;
        this.bitmap = load(image.name());
        this.repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        Resizer resizer = new Resizer(bitmap);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        BufferedImage resized = resizer.resize(scaleFactor());
        int x = (this.getWidth() - resized.getWidth()) / 2;
        int y = (this.getHeight() - resized.getHeight()) / 2;
        g.drawImage(resized, x, y, null);
        File file = new File(this.image.name());
        g.drawString(printString(file.getName()), 0, this.getHeight() - 6);
        double scaleFactor = scaleFactor();
        String percentage = "100%";
        if (scaleFactor < 1) {
            percentage = round(scaleFactor*100) + "%";
        }
        g.drawString(printString(percentage), this.getWidth() - 50, this.getHeight() - 6);
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private double scaleFactor() {
        double widthFactor = (double) this.getWidth() / bitmap.getWidth();
        double heightFactor = (double) (this.getHeight() - 50) / bitmap.getHeight();
        return min(widthFactor, heightFactor);
    }

    private AttributedCharacterIterator printString(String string) {
        AttributedString name = new AttributedString(string);
        name.addAttribute(TextAttribute.SIZE, 18);
        name.addAttribute(TextAttribute.FONT, Font.SANS_SERIF);
        name.addAttribute(TextAttribute.FOREGROUND, Color.WHITE);
        return name.getIterator();
    }
}
