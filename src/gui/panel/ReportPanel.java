package gui.panel;

import entity.Record;
import service.ReportService;
import utils.ChartUtil;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportPanel extends WorkingPanel {
    public static ReportPanel instance = new ReportPanel();

    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }

    JLabel label = new JLabel();
    public ReportPanel() {
        add(label);
    }

    @Override
    public void updateData() {
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 350, 250);
        ImageIcon icon = new ImageIcon(i);
        label.setIcon(icon);
    }

    @Override
    public void addListener() {

    }
}
