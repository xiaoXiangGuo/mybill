package gui;

import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final MainFrame instance = new MainFrame();

    public static MainFrame getInstance() {
        return instance;
    }

    public MainFrame() throws HeadlessException {
        this.setSize(500, 450);
        this.setTitle("我的消费账单");
        ImageIcon imageIcon = new ImageIcon(ImageUtil.getImage("icon.png"));
        this.setIconImage(imageIcon.getImage());
        this.setContentPane(MainPanel.instance);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SpendPanel sp = SpendPanel.getInstance();
        JButton jButton = new JButton();
        sp.add(jButton);

    }
}
