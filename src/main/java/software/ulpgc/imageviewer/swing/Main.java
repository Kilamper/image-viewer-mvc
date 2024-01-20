package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.image.FileImageLoader;
import software.ulpgc.imageviewer.image.Image;
import software.ulpgc.imageviewer.command.NextImageCommand;
import software.ulpgc.imageviewer.command.PreviousImageCommand;

import java.io.File;

public class Main {
    public static String root;

    public static void main(String[] args) {
        PathFrame pathFrame = new PathFrame();
        pathFrame.setVisible(true);
        while (root == null) {
            root = pathFrame.getPath();
        }
        pathFrame.setVisible(false);
        Image image = new FileImageLoader(new File(root)).load();
        MainFrame mainFrame = new MainFrame();
        mainFrame.getImageDisplay().show(image);
        mainFrame.add("Prev", new PreviousImageCommand(mainFrame.getImageDisplay()));
        mainFrame.add("Next", new NextImageCommand(mainFrame.getImageDisplay()));
        mainFrame.setVisible(true);
    }
}
