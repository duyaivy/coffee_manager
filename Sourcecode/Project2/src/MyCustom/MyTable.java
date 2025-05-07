package MyCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class MyTable extends JTable {

    public MyTable() {
        //======CUSTOM TABLE=======
        this.setFocusable(false);
        this.setIntercellSpacing(new Dimension(0, 0));
        this.setRowHeight(25);
        this.setSelectionBackground(new Color(222, 184, 135));
        this.setSelectionForeground(Color.white);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        JTableHeader header = this.getTableHeader();
        header.setBackground(new Color(159, 145, 121));
        header.setFont(new Font("Times New Roman", Font.BOLD, 16));
        header.setOpaque(false);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //======/CUSTOM TABLE/=======
    }

    public MyTable(DefaultTableModel dtm) {
        this();
        this.setModel(dtm);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        //SORT HEADER TABLE
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtm);
        this.setRowSorter(sorter);
    }
}