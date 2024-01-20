package software.ulpgc.imageviewer.image;

public interface Image {
    String name();
    Image next();
    Image prev();
}
