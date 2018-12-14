package gui.panel;

import com.mysql.jdbc.StringUtils;
import entity.Category;
import gui.model.CategoryTableModel;
import service.CategoryService;
import utils.GUIUtil;
import utils.StringUtil;

import javax.lang.model.element.VariableElement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CategoryPanel extends WorkingPanel {
    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.getInstance());
    }

    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");

    private CategoryService cService = new CategoryService();
    private CategoryTableModel ctm = new CategoryTableModel(cService.list());
    private JTable t = new JTable(ctm);

    private static CategoryPanel instance = new CategoryPanel();

    public static CategoryPanel getInstance() {
        return instance;
    }

    public CategoryPanel() {
        setLayout(new BorderLayout());
        JScrollPane sp = new JScrollPane(t);
        add(sp, BorderLayout.CENTER);
        add(south(), BorderLayout.SOUTH);
        addListener();
    }

    private Component south() {
        JPanel panel = new JPanel();
        panel.add(bAdd);
        panel.add(bEdit);
        panel.add(bDelete);

        return panel;
    }

    @Override
    public void updateData() {
        ctm.setData(cService.list());
        t.updateUI();
        System.out.println("categoryPanel选择的model->" + t.getSelectionModel());
        System.out.println("categoryPanel的model->" + t.getModel());
        t.getSelectionModel().setSelectionInterval(0, 0);
    }

    @Override
    public void addListener() {
        bAdd.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
    }

    private ActionListener listener = e -> {
        if (e.getSource() == bAdd) {
            String name = JOptionPane.showInputDialog(null, "请输入添加的分类名称", "添加分类", JOptionPane.PLAIN_MESSAGE);
            if (checkInput(name, "分类名称不能为空")) return;
            if (!cService.add(name)) {
                JOptionPane.showMessageDialog(null, "添加失败");
            }
        } else if (e.getSource() == bEdit) {
            Category category = getSelectedCategory();
            String name = (String) JOptionPane.showInputDialog(this, null, "修改分类", JOptionPane.PLAIN_MESSAGE,
                    null,null,category.getName());
            if (checkInput(name, "修改分类不能为空")) return;
            category.setName(name);
            if (!cService.update(category)) {
                JOptionPane.showMessageDialog(this, "修改失败");
            }
        } else if (e.getSource() == bDelete) {
            Category category = getSelectedCategory();
            if (category.getRecordNumber() > 0) {
                JOptionPane.showMessageDialog(this, "本分类下有消费记录存在，不能删除");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(this, "确定要删除?"))
                return;
            if (!cService.delete(category.getName())) {
                JOptionPane.showMessageDialog(this, "删除失败");
            }
        }
        updateData();
    };

    private boolean checkInput(String name, String message) {
        if (name == null) {
            return true;
        } else if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, message);
            return true;
        }
        return false;
    }

    public Category getSelectedCategory() {
        int index = t.getSelectedRow();
        return ctm.getData().get(index);
    }
}
