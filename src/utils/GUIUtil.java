package utils;

import gui.widget.CenterPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIUtil {

    /**
     * 使用主题
     */
    public static void useLNF() {
        try {
            UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void setImageIcon(JButton b, String fillName, String tip) {
        ImageIcon icon = new ImageIcon(ImageUtil.getImage(fillName));
        b.setIcon(icon);
        b.setPreferredSize(new Dimension(60, 80));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);

    }

    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }

    public static void showPanel(JPanel p) {
        showPanel(p, 0.85);
    }

    public static void showPanel(JPanel p, double strechRate) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        CenterPanel cp = new CenterPanel(strechRate);
//        cp.setBackground(ColorUtil.backgroundColor);
        frame.setContentPane(cp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        cp.show(p);
    }

    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + " 不能为空");
            tf.grabFocus();
            return false;
        }
        return true;

    }

    public static int getInt(JTextField tf) {
        return Integer.parseInt(tf.getText());
    }

    public static boolean checkNumber(JTextField tf, String input) {
        if (!checkEmpty(tf, input))
            return false;
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, input + " 需要是整数");
            tf.grabFocus();
            return false;
        }
    }

    public static boolean checkZero(JTextField tf, String input) {
        if (!checkNumber(tf, input))
            return false;
        String text = tf.getText().trim();

        if (0 == Integer.parseInt(text)) {
            JOptionPane.showMessageDialog(null, input + " 不能为零");
            tf.grabFocus();
            return false;
        }
        return true;
    }
}
