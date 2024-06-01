package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.image.Image;
import software.ulpgc.imageviewer.image.ImageDisplay;
import software.ulpgc.imageviewer.command.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainFrame extends JFrame {
    private ImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame() throws MalformedURLException {
        URL url = new URL("https://sunrust.org/wiki/images/a/a9/Gallery_icon.png");
        ImageIcon icon = new ImageIcon(url);
        this.commands = new HashMap<>();
        this.setTitle("Image Viewer (Desktop version)");
        this.setIconImage(icon.getImage());
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(createImageDisplay());
        this.add(createWestToolbar(), BorderLayout.WEST);
        this.add(createEastToolbar(), BorderLayout.EAST);
    }

    private Component createWestToolbar() throws MalformedURLException {
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(createButton("Prev"));
        return panel;
    }

    private Component createEastToolbar() throws MalformedURLException {
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(createButton("Next"));
        return panel;
    }

    private Component createButton(String label) throws MalformedURLException {
        JButton button = new JButton(label);
        URL url;
        if (Objects.equals(label, "Prev")) {
            url = new URL("https://cdn2.iconfinder.com/data/icons/deus/24/chevron-left-32.png");
        } else {
            url = new URL("https://cdn2.iconfinder.com/data/icons/deus/24/chevron-right-32.png");
        }
        ImageIcon icon = new ImageIcon(url);
        button.setIcon(icon);
        button.setText("");
        button.setBackground(Color.DARK_GRAY);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(e -> commands.get(label).execute());
        button.addKeyListener(createArrowKeyListener());
        return button;
    }

    private KeyListener createArrowKeyListener() {
        return new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    commands.get("Prev").execute();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    commands.get("Next").execute();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }
}
