package software.ulpgc.imageviewer.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class PathFrame extends JFrame implements ActionListener {
    private String path;
    private JTextField textField;
    private JButton accept;
    private JButton cancel;
    private JButton select;

    public PathFrame() throws MalformedURLException {
        URL url = new URL("https://sunrust.org/wiki/images/a/a9/Gallery_icon.png");
        ImageIcon icon = new ImageIcon(url);
        this.setIconImage(icon.getImage());
        this.setTitle("Image Viewer (Desktop Version)");
        this.setSize(380, 160);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(createPanel());
    }

    private Component createPanel() throws MalformedURLException {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Select a directory:");
        label.setBounds(10, 10, 200, 30);
        this.add(label);
        textField = new JTextField();
        textField.setBounds(10, 40, 300, 25);
        this.add(textField);
        createAcceptButton();
        createCancelButton();
        createSelectButton();
        this.add(accept);
        this.add(cancel);
        this.add(select);
        return panel;
    }

    private void createAcceptButton() {
        accept = new JButton("Accept");
        accept.setBounds(10, 75, 80, 25);
        accept.addActionListener(this);
    }

    private void createCancelButton() {
        cancel = new JButton("Cancel");
        cancel.setBounds(100, 75, 80, 25);
        cancel.addActionListener(this);
    }

    private void createSelectButton() throws MalformedURLException {
        URL url = new URL("https://cdn1.iconfinder.com/data/icons/document-edit-line/64/Document-doc-file-folder-bundle-sheaf-archive-16.png");
        ImageIcon icon = new ImageIcon(url);
        select = new JButton("");
        select.setIcon(icon);
        select.setBounds(310, 40, 40, 25);
        select.addActionListener(this);
    }

    private void createFileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("/C:/Users/-/Pictures/"));
        chooser.setDialogTitle("Select a folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            this.textField.setText(chooser.getSelectedFile().getPath());
        }
    }

    public String getPath() {
        return path;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
        File file = new File(text);
        if (file.isDirectory() && e.getSource() == accept) {
            this.path = text;
        } else if (e.getSource() == cancel) {
            System.exit(0);
        } else if (e.getSource() == select) {
            createFileChooser();
        }
    }
}
