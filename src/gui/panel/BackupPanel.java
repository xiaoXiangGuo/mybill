package gui.panel;

import service.ConfigService;
import utils.MysqlUtil;
import utils.StringUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class BackupPanel extends WorkingPanel {
    private static BackupPanel instance = new BackupPanel();
    private final JButton bBackup;
    private ConfigService cService = new ConfigService();

    public static BackupPanel getInstance() {
        return instance;
    }

    public BackupPanel() {
        bBackup = new JButton("备份");
        add(bBackup);
        addListener();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        bBackup.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mysqlPath = cService.get(ConfigService.mysqlPath);
                if (StringUtil.isEmpty(mysqlPath)) {
                    JOptionPane.showMessageDialog(BackupPanel.this, "备份前请事先配置mysql的路径");
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
                int dialog = fc.showSaveDialog(BackupPanel.this);
                File file = fc.getSelectedFile();
                if (dialog == JFileChooser.APPROVE_OPTION){
                    if (!file.getName().endsWith(".sql")){
                        file = new File(file.getParent(),file.getName()+".sql");
                    }
                    try {
                        MysqlUtil.backup(mysqlPath,file.getAbsolutePath());
                        JOptionPane.showMessageDialog(BackupPanel.this,"备份成功,备份文件位于:\r\n"+file.getAbsolutePath());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(BackupPanel.this,"备份失败,错误原因:\r\n"+e1.getMessage());
                    }
                }

            }
        });
    }
}
