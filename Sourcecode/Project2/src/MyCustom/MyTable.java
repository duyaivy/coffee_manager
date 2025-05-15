package MyCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class MyTable extends JTable {
    private final Color DARK_GREEN = new Color(0x09A603);
    private final Color MEDIUM_GREEN = new Color(0x078C03);
    private final Color GOLD = new Color(0xF2B705);
    private final Color LIGHT_YELLOW = new Color(0xF2D479);
    private final Color DARK_GOLD = new Color(0xD98E04);
    private final Color MACARON = new Color(0xc8b4ba);
    private final Color LIGHT_GREEN = new Color(0xc1cd97);
    private final Color PINK = new Color(0xe18d96);
    private final Color LIGHT_GOLD = new Color(0xF3DDB3);
    private final Color GRAY = new Color(0x909090);

    public MyTable() {
        
        //======CUSTOM TABLE=======
        this.setFocusable(false);
        this.setIntercellSpacing(new Dimension(0, 0));
        this.setRowHeight(25);
        this.setSelectionBackground(PINK);
        this.setSelectionForeground(Color.white);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        JTableHeader header = this.getTableHeader();
        header.setBackground(MEDIUM_GREEN);
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