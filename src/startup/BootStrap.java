package startup;

import gui.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import utils.GUIUtil;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class BootStrap {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        GUIUtil.useLNF();//主题
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.getInstance().setVisible(true);
                MainPanel.instance.workingPanel.show(SpendPanel.getInstance());
            }
        });
    }
}
