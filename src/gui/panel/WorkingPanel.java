package gui.panel;

import utils.GUIUtil;

import javax.swing.*;

public abstract class WorkingPanel extends JPanel {
    static{
        GUIUtil.useLNF();
    }
    public abstract void updateData();
    public abstract void addListener();
}
