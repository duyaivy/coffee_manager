package Manage.GUI;

import Manage.BUS.KhachHangBUS;
import Manage.DTO.KhachHang;
import MyCustom.MyDialog;
import MyCustom.MyTable;
import MyCustom.TransparentPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import static Main.Main.changLNF;

public class PnQuanLyKhachHangGUI extends JPanel {

    public PnQuanLyKhachHangGUI() {
        changLNF("Windows");
        addControls();
        addEvents();
    }

    private KhachHangBUS khachHangBUS = new KhachHangBUS();

    final Color colorPanel = new Color(247, 247, 247);
    JButton btnReset;
    JTextField txtMa, txtHo, txtTen, txtTongChiTieu, txtTukhoa, txtMaxChiTieu, txtMinchiTieu;
    JComboBox<String> cmbGioiTinh;
    JButton btnThem, btnSua, btnXoa, btnTim;
    MyTable tblKhachHang;
    DefaultTableModel dtmKhachHang;

    private void addControls() {
        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        Font headerFont = new Font("Segoe UI", Font.BOLD, 18);

        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(colorPanel);
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ==================== PANEL CHÍNH ====================
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(colorPanel);

        // ==================== PANEL TIÊU ĐỀ ====================
        JPanel pnTitle = new JPanel(new BorderLayout());
        pnTitle.setBackground(colorPanel);
        
        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setToolTipText("Làm mới");
        btnReset.setPreferredSize(new Dimension(40, 40));
        
        pnTitle.add(lblTitle, BorderLayout.WEST);
        pnTitle.add(btnReset, BorderLayout.EAST);
        
        mainPanel.add(pnTitle, BorderLayout.NORTH);

        // ==================== PANEL THÔNG TIN ====================
        JPanel pnInfo = new JPanel();
        pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.Y_AXIS));
        pnInfo.setBackground(colorPanel);
        pnInfo.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));

        // Nhóm các trường thông tin
        JPanel pnFields = new JPanel(new GridLayout(3, 2, 10, 10));
        pnFields.setBackground(colorPanel);

        // Dòng 1: Mã KH và Họ đệm
        JPanel pnMa = createFieldPanel("Mã KH", txtMa = new JTextField());
        txtMa.setEditable(false);
        JPanel pnHo = createFieldPanel("Họ đệm", txtHo = new JTextField());
        pnFields.add(pnMa);
        pnFields.add(pnHo);

        // Dòng 2: Tên và Giới tính
        JPanel pnTen = createFieldPanel("Tên", txtTen = new JTextField());
        cmbGioiTinh = new JComboBox<>();
        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");
        JPanel pnGioiTinh = createFieldPanel("Giới tính", cmbGioiTinh);
        pnFields.add(pnTen);
        pnFields.add(pnGioiTinh);

        // Dòng 3: Tổng chi tiêu
        JPanel pnTongChiTieu = createFieldPanel("Tổng chi tiêu", txtTongChiTieu = new JTextField());
        txtTongChiTieu.setEditable(false);
        pnFields.add(pnTongChiTieu);
        pnFields.add(new JPanel()); // Placeholder

        pnInfo.add(pnFields);

        // ==================== PANEL NÚT CHỨC NĂNG ====================
        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        pnButton.setBackground(colorPanel);
        
        btnThem = createButton("Thêm", "image/add-icon.png");
        btnSua = createButton("Lưu", "image/Pencil-icon.png");
        btnXoa = createButton("Xoá", "image/delete-icon.png");
        
        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
        
        pnInfo.add(pnButton);
        mainPanel.add(pnInfo, BorderLayout.WEST);

        // ==================== PANEL TÌM KIẾM ====================
        JPanel pnSearch = new JPanel();
        pnSearch.setLayout(new BoxLayout(pnSearch, BoxLayout.Y_AXIS));
        pnSearch.setBackground(colorPanel);
        pnSearch.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

        // Tìm kiếm theo từ khóa
        JPanel pnTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnTimKiem.setBackground(colorPanel);
        pnTimKiem.add(new JLabel("Từ khoá:"));
        txtTukhoa = new JTextField(20);
        pnTimKiem.add(txtTukhoa);
        pnSearch.add(pnTimKiem);

        // Tìm kiếm theo khoảng chi tiêu
        JPanel pnTimGioiHan = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnTimGioiHan.setBackground(colorPanel);
        pnTimGioiHan.add(new JLabel("Chi tiêu từ:"));
        txtMinchiTieu = new JTextField(10);
        txtMinchiTieu.setHorizontalAlignment(JTextField.RIGHT);
        pnTimGioiHan.add(txtMinchiTieu);
        pnTimGioiHan.add(new JLabel("đến:"));
        txtMaxChiTieu = new JTextField(10);
        txtMaxChiTieu.setHorizontalAlignment(JTextField.RIGHT);
        pnTimGioiHan.add(txtMaxChiTieu);
        btnTim = createButton("", "image/Search-icon.png");
        btnTim.setToolTipText("Tìm kiếm");
        pnTimGioiHan.add(btnTim);
        pnSearch.add(pnTimGioiHan);

        mainPanel.add(pnSearch, BorderLayout.CENTER);

        // ==================== PANEL BẢNG DỮ LIỆU ====================
        dtmKhachHang = new DefaultTableModel();
        dtmKhachHang.addColumn("Mã KH");
        dtmKhachHang.addColumn("Họ đệm");
        dtmKhachHang.addColumn("Tên");
        dtmKhachHang.addColumn("Giới tính");
        dtmKhachHang.addColumn("Tổng chi tiêu");

        tblKhachHang = new MyTable(dtmKhachHang);
        tblKhachHang.setRowHeight(30);
        tblKhachHang.setFont(font);
        tblKhachHang.getTableHeader().setFont(headerFont);

        JScrollPane scrtblKhachHang = new JScrollPane(tblKhachHang);
        scrtblKhachHang.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
        
        mainPanel.add(scrtblKhachHang, BorderLayout.SOUTH);
        this.add(mainPanel);

        loadDataLenTableKhachHang();
    }

    private JPanel createFieldPanel(String label, Component field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(colorPanel);
        
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lbl, BorderLayout.WEST);
        
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        if (field instanceof JTextField) {
            ((JTextField)field).setPreferredSize(new Dimension(200, 30));
        } else if (field instanceof JComboBox) {
            ((JComboBox<?>)field).setPreferredSize(new Dimension(200, 30));
        }
        panel.add(field, BorderLayout.CENTER);
        
        return panel;
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(100, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    // Các phương thức còn lại giữ nguyên như cũ
    private void addEvents() {
        // Giữ nguyên toàn bộ phần addEvents() như cũ
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenTableKhachHang();
                txtMa.setText("");
                txtHo.setText("");
                txtTen.setText("");
                txtTongChiTieu.setText("");
                txtTukhoa.setText("");
                txtMinchiTieu.setText("");
                txtMaxChiTieu.setText("");
                cmbGioiTinh.setSelectedIndex(0);
            }
        });

        tblKhachHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClicktblKhachHang();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        txtTukhoa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }
        });

        txtMinchiTieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaxChiTieu.requestFocus();
            }
        });

        txtMaxChiTieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTim.doClick();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemTheoKhoang();
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhachHang();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaKhachHang();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaKhachHang();
            }
        });
    }

    private void loadDataLenTableKhachHang() {
        khachHangBUS.docDanhSach();
        ArrayList<KhachHang> dskh = khachHangBUS.getListKhachHang();
        loadDataLenTableKhachHang(dskh);
    }

    private void loadDataLenTableKhachHang(ArrayList<KhachHang> dskh) {
        dtmKhachHang.setRowCount(0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (KhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(kh.getGioiTinh());
            vec.add(dcf.format(kh.getTongChiTieu()));
            dtmKhachHang.addRow(vec);
        }
    }

    private void xuLyClicktblKhachHang() {
        int row = tblKhachHang.getSelectedRow();
        if (row > -1) {
            txtMa.setText(tblKhachHang.getValueAt(row, 0) + "");
            txtHo.setText(tblKhachHang.getValueAt(row, 1) + "");
            txtTen.setText(tblKhachHang.getValueAt(row, 2) + "");
            int index = tblKhachHang.getValueAt(row, 3).equals("Nam") ? 1 : 2;
            cmbGioiTinh.setSelectedIndex(index);
            txtTongChiTieu.setText(tblKhachHang.getValueAt(row, 4) + "");
        }
    }

    private void xuLyTimKiemTheoKhoang() {
        ArrayList<KhachHang> dskh = khachHangBUS.timKiemKhachHang(txtMinchiTieu.getText(), txtMaxChiTieu.getText());
        if (dskh == null)
            return;
        loadDataLenTableKhachHang(dskh);
    }

    private void xuLyLiveSearch() {
        ArrayList<KhachHang> dskh = khachHangBUS.timKiemKhachHang(txtTukhoa.getText());
        loadDataLenTableKhachHang(dskh);
    }

    private void xuLyThemKhachHang() {
        if (khachHangBUS.themKhachHang(txtHo.getText(), txtTen.getText(), cmbGioiTinh.getSelectedItem() + ""))
            btnReset.doClick();
    }

    private void xuLySuaKhachHang() {
        if (khachHangBUS.suaKhachHang(txtMa.getText(), txtHo.getText(), txtTen.getText(), cmbGioiTinh.getSelectedItem() + ""))
            btnReset.doClick();
    }

    private void xuLyXoaKhachHang() {
        if(khachHangBUS.xoaKhachHang(txtMa.getText()))
            btnReset.doClick();
    }
}