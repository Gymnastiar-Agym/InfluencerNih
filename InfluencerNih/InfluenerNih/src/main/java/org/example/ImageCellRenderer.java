package org.example;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import javax.swing.ImageIcon;
import java.io.File;

public class ImageCellRenderer extends JLabel implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            setIcon((ImageIcon) value);
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        return this;
    }
}
