package gui.panel;

import entity.Category;
import entity.Record;
import gui.filter.MoneyDocument;
import gui.model.CategoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import service.RecordService;
import utils.ColorUtil;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordPanel extends WorkingPanel {
    private static RecordPanel instance = new RecordPanel();
    private JLabel lSpend = new JLabel("花费（￥）");
    private JLabel lCategory = new JLabel("分类");
    private JLabel lRemark = new JLabel("备注");
    private JLabel lDate = new JLabel("日期");

    //输入框
    private JTextField tfSpend = new JTextField(new MoneyDocument(Integer.MAX_VALUE), "0", 0);

    private RecordService rService = new RecordService();
    private CategoryService cService = new CategoryService();

    private CategoryComboBoxModel<Category> comboBoxModel = new CategoryComboBoxModel<Category>(cService.list());
    private JComboBox<Category> cbCategory = new JComboBox<>(comboBoxModel);

    private JTextField tfRemark = new JTextField();

    private JXDatePicker dp = new JXDatePicker(new Date());
    private JButton bRecord;

    public static RecordPanel getInstance() {
        return instance;
    }

    public RecordPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lSpend, lCategory, lRemark, lDate);
        setLayout(new BorderLayout());
        add(center(), BorderLayout.NORTH);
        add(south(), BorderLayout.SOUTH);
        addListener();
    }

    private Component center() {
        JPanel panel = new JPanel();
        int gap = 40;
        panel.setLayout(new GridLayout(4, 2, gap, gap));
        panel.add(lSpend);
        panel.add(tfSpend);
        panel.add(lCategory);
        panel.add(cbCategory);
        panel.add(lRemark);
        panel.add(tfRemark);
        panel.add(lDate);
        panel.add(dp);
        return panel;
    }

    private Component south() {
        bRecord = new JButton("记一笔");
        bRecord.setPreferredSize(new Dimension(50, 20));
        GUIUtil.setColor(ColorUtil.blueColor, bRecord);
        return bRecord;
    }

    public static void main(String[] args) {
        RecordPanel instance = RecordPanel.getInstance();
        GUIUtil.showPanel(instance);
    }

    @Override
    public void updateData() {
        comboBoxModel.setData(cService.list());
        cbCategory.updateUI();
        tfSpend.grabFocus();
    }

    @Override
    public void addListener() {
        bRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Record record = new Record();
                Category item = (Category) cbCategory.getSelectedItem();
                record.setCid(item.getId());
                record.setSpend(Integer.parseInt(tfSpend.getText()));
                record.setComment(tfRemark.getText());

                record.setDate(dp.getDate());
                boolean add = rService.add(record);
                System.out.println("添加状态:" + add);
            }
        });
    }
}
