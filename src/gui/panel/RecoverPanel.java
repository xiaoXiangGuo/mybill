package gui.panel;

import service.ConfigService;
import utils.DBUtil;
import utils.MysqlUtil;
import utils.StringUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RecoverPanel extends WorkingPanel {
    private static RecoverPanel instance = new RecoverPanel();
    private final JButton bRecover;

    private ConfigService cService = new ConfigService();

    public static RecoverPanel getInstance() {
        return instance;
    }

    public RecoverPanel() {
        bRecover = new JButton("恢复");
        add(bRecover);
        addListener();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        bRecover.addActionListener(e -> {
            String mysqlPath = cService.get(ConfigService.mysqlPath);
            if (StringUtil.isEmpty(mysqlPath)) {
                JOptionPane.showMessageDialog(RecoverPanel.this, "恢复前请先配置mysql路劲");
                MainPanel.instance.workingPanel.show(ConfigPanel.getInstance());
                ConfigPanel.getInstance().getTfBackupPath().grabFocus();
                return;
            }
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new File("hutubill.sql"));
            fc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().endsWith(".sql");
                }

                @Override
                public String getDescription() {
                    return ".sql";
                }
            });
            int openDialog = fc.showOpenDialog(RecoverPanel.this);
            File file = fc.getSelectedFile();
            if (openDialog == JFileChooser.APPROVE_OPTION) {
                try {
                    MysqlUtil.recover(mysqlPath, file.getAbsolutePath());
                    JOptionPane.showMessageDialog(RecoverPanel.this, "恢复成功");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(RecoverPanel.this, "恢复失败,错误:\r\n" + e1.getMessage());
                }
            }
        });
    }
}
