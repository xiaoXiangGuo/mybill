package gui.model;

import entity.Category;
import org.jdesktop.swingx.combobox.ListComboBoxModel;

import java.util.List;

public class CategoryComboBoxModel<E> extends ListComboBoxModel<E> {
    public CategoryComboBoxModel(List<E> list) {
        super(list);
    }

    @Override
    public E getElementAt(int index) {
        return super.getElementAt(index);
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data.clear();
        this.data.addAll(data);
    }
}
