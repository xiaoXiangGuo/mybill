package gui.panel;

import service.ConfigService;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends WorkingPanel {
    private static ConfigPanel instance = new ConfigPanel();

    public static ConfigPanel getInstance() {
        return instance;
    }

    private JLabel lBudget = new JLabel("本月预算（￥）");
    private JTextField tfBudget = new JTextField();
    private JLabel lBackup = new JLabel("Mysql备份目录");
    private JTextField tfBackup = new JTextField();

    private JButton bUpdate = new JButton("更新");

    private ConfigService service = new ConfigService();

    public ConfigPanel() {
        JPanel panel = new JPanel();
        int gap = 40;
        panel.setLayout(new GridLayout(4, 1, gap, gap));
        panel.add(lBudget);
        panel.add(tfBudget);
        panel.add(lBackup);
        panel.add(tfBackup);

        bUpdate.setSize(20, 10);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(bUpdate, BorderLayout.CENTER);
        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.getInstance());
    }

    @Override
    public void updateData() {
        String budget = service.get(ConfigService.budget);
        String mysqlPath = service.get(ConfigService.mysqlPath);

        tfBudget.setText(budget);
        tfBackup.setText(mysqlPath);

        tfBudget.grabFocus();

    }

    @Override
    public void addListener() {
        bUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.updateOrAdd(ConfigService.budget, tfBudget.getText().trim());
                service.updateOrAdd(ConfigService.mysqlPath, tfBackup.getText().trim());
            }
        });
    }

    public JTextField getTfBackupPath() {
        return tfBackup;
    }
}
