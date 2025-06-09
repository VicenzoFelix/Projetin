package views;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;

public class ButtonEditor extends AbstractCellEditor
        implements TableCellEditor {
    protected JButton button;
    private String label;
    private boolean clicked;
    private int row;
    private JTable table;
    private ButtonClickListener listener;

    public interface ButtonClickListener {
        void onButtonClick(int row);
    }

    public ButtonEditor(JTable table, ButtonClickListener listener) {
        this.table = table;
        this.listener = listener;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> {
            fireEditingStopped();
            listener.onButtonClick(row);
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.row = row;
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    public Object getCellEditorValue() {
        return label;
    }

    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }

    public boolean isCellEditable(EventObject e) {
        return true;
    }
}
