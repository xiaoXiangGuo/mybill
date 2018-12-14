package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CategoryTableModel extends AbstractTableModel {
    String[] columnNames = new String[]{"分类名称", "消费次数"};
    private List<Category> cs;


    public CategoryTableModel(List<Category> cs) {
        this.cs = cs;
    }

    @Override
    public int getRowCount() {
        return cs.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        // TODO Auto-generated method stub
        return columnNames[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category h = cs.get(rowIndex);
        if (0 == columnIndex) {
            System.out.println("横数为:"+rowIndex+"----列数为0");
            return h.getName();
        }
        if (1 == columnIndex) {
            System.out.println("横数为:"+rowIndex+"----列数为1");
            return h.getRecordNumber()+"";
        }

        return null;
        /*switch (columnIndex) {
            case 0:
                return cs.get(rowIndex).getName();
            case 1:
                return cs.get(rowIndex).getRecordNumber();
            default:
                return null;
        }*/
    }

    public List<Category> getData() {
        return cs;
    }

    public void setData(List<Category> cs) {
        this.cs = cs;
    }
}
