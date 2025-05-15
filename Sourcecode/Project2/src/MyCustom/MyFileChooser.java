package MyCustom;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class MyFileChooser extends JFileChooser {
    private static final int ICON_SIZE = 60;
    private static final Image LOADING_IMAGE = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
    private final Pattern imageFilePattern = Pattern.compile(".+?\\.(png|jpe?g|gif|tiff?)$", Pattern.CASE_INSENSITIVE);
    private final Map imageCache = new WeakHashMap();

    public MyFileChooser() {
        super();
    }

    public MyFileChooser(String src) {
        super(src);
    }

    {
        setFileView(new ThumbnailView());
    }

    private class ThumbnailView extends FileView {
        private final ExecutorService executor = Executors.newCachedThreadPool();

        public Icon getIcon(File file) {
            if (!imageFilePattern.matcher(file.getName()).matches()) {
                return null;
            }

            synchronized (imageCache) {
                ImageIcon icon = (ImageIcon) imageCache.get(file);

                if (icon == null) {
                    icon = new ImageIcon(LOADING_IMAGE);
                    imageCache.put(file, icon);
                    executor.submit(new ThumbnailIconLoader(icon, file));
                }

                return icon;
            }
        }
    }

    private class ThumbnailIconLoader implements Runnable {
        private final ImageIcon icon;
        private final File file;

        public ThumbnailIconLoader(ImageIcon i, File f) {
            icon = i;
            file = f;
        }

        public void run() {
            ImageIcon newIcon = new ImageIcon(file.getAbsolutePath());
            Image img = newIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
            icon.setImage(img);
            SwingUtilities.invokeLater(() -> repaint());
        }
    }
}
