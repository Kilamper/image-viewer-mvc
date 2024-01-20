package software.ulpgc.imageviewer.image;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;

public class FileImageLoader implements ImageLoader {
    private final File[] files;

    public FileImageLoader(File folder) {
        this.files = folder.listFiles(isImage());
    }

    private static final Set<String> imageExtensions = Set.of(".jpg", ".png", ".jpeg", ".bmp", ".tiff", ".gif");

    private static FilenameFilter isImage() {
        return (dir, name) -> imageExtensions.stream().anyMatch(name::endsWith);
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String name() {
                assert files != null;
                if (files.length == 0) {
                    System.exit(0);
                }
                return files[i].getAbsolutePath();
            }

            @Override
            public Image next() {
                assert files != null;
                return imageAt((i+1) % files.length);
            }

            @Override
            public Image prev() {
                assert files != null;
                return imageAt((i-1 + files.length) % files.length);
            }
        };
    }
}
