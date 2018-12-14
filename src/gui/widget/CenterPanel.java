package gui.widget;

import gui.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends WorkingPanel {

    private JComponent c;//显示的组件
    private double rate;//拉伸比例
    private boolean strech;//是否拉伸

    public CenterPanel(double rate, boolean strech) {
        this.setLayout(null);
        this.rate = rate;
        this.strech = strech;
    }

    public CenterPanel(double rate) {
        this(rate, true);
    }

    @Override
    public void repaint() {
        System.out.println("调用repaint");
        if (null != c) {
            Dimension panelSize = getSize();
            Dimension componentSize = c.getSize();
            if (strech) {
                c.setSize((int) (panelSize.width * rate), (int) (panelSize.height * rate));
            } else {
                c.setSize(componentSize.width, componentSize.height);
            }
            c.setLocation((panelSize.width - c.getSize().width) / 2, (panelSize.height - c.getSize().height) / 2);
        }
        super.repaint();
    }

    public void show(JComponent p) {
        System.out.println("调用show");
        c = p;
        Component[] components = getComponents();
        for (Component component : components) {
            remove(component);
        }
        add(c);

        if (p instanceof WorkingPanel)
            ((WorkingPanel) p).updateData();
        super.updateUI();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(200, 200);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(0.85, false);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        JButton b = new JButton("abc");
        b.setSize(50, 60);
        cp.show(b);
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {

    }
}
