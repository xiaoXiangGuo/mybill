package gui.listener;

import gui.panel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel panel = MainPanel.instance;
        JButton b = (JButton) e.getSource();
        if (b == panel.bReport) {
            panel.workingPanel.show(ReportPanel.instance);
        } else if (b == panel.bSpend) {
            panel.workingPanel.show(SpendPanel.getInstance());
        }else if (b == panel.bRecord){
            panel.workingPanel.show(RecordPanel.getInstance());
        }else if (b == panel.bBackup){
            panel.workingPanel.show(BackupPanel.getInstance());
        }else if (b == panel.bConfig){
            panel.workingPanel.show(ConfigPanel.getInstance());
        }else if (b == panel.bCategory){
            panel.workingPanel.show(CategoryPanel.getInstance());
        }else if (b == panel.bRecover){
            panel.workingPanel.show(RecoverPanel.getInstance());
        }
    }
}
