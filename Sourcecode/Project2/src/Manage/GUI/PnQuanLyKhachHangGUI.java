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
        Font font = new Font("Times New Roman", Font.PLAIN, 14);
        Font headerFont = new Font("Times New Roman", Font.BOLD, 18);

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

        // ==================== PANEL THÔNG TIN VÀ TÌM KIẾM ====================
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.Y_AXIS));
        pnTop.setBackground(colorPanel);

        // ==== Panel Thông tin khách hàng ====
        JPanel pnInfo = new JPanel(new GridBagLayout());
        pnInfo.setBackground(colorPanel);
        pnInfo.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        pnInfo.setPreferredSize(new Dimension(600, 180));

        Font bigFont = new Font("Times New Roman", Font.PLAIN, 16);
      

        int row = 0;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        gbc.weightx = 0.1;
        gbc.gridx = 0; gbc.gridy = row;
        JLabel lblMaKH = new JLabel("Mã KH");
        lblMaKH.setFont(bigFont);
        pnInfo.add(lblMaKH, gbc);
        gbc.gridx = 1; gbc.weightx = 0.35;
        txtMa = new JTextField();
        txtMa.setPreferredSize(new Dimension(220, 35));
        txtMa.setFont(bigFont);
        pnInfo.add(txtMa, gbc);
        txtMa.setEditable(false);

        gbc.weightx = 0.1;
        gbc.gridx = 2;
        JLabel lblHo = new JLabel("Họ đệm");
        lblHo.setFont(bigFont);
        pnInfo.add(lblHo, gbc);
        gbc.gridx = 3; gbc.weightx = 0.2;
        txtHo = new JTextField();
        txtHo.setPreferredSize(new Dimension(120, 35));
        txtHo.setFont(bigFont);
        pnInfo.add(txtHo, gbc);

        row++;
        gbc.gridy = row; gbc.gridx = 0; gbc.weightx = 0.1;
        JLabel lblTen = new JLabel("Tên");
        lblTen.setFont(bigFont);
        pnInfo.add(lblTen, gbc);
        gbc.gridx = 1; gbc.weightx = 0.35;
        txtTen = new JTextField();
        txtTen.setPreferredSize(new Dimension(220, 35));
        txtTen.setFont(bigFont);
        pnInfo.add(txtTen, gbc);

        gbc.gridx = 2; gbc.weightx = 0.1;
        JLabel lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setFont(bigFont);
        pnInfo.add(lblGioiTinh, gbc);
        gbc.gridx = 3; gbc.weightx = 0.2;
        cmbGioiTinh = new JComboBox<>();
        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");
        cmbGioiTinh.setPreferredSize(new Dimension(100, 35));
        cmbGioiTinh.setFont(bigFont);
        pnInfo.add(cmbGioiTinh, gbc);

        row++;
        gbc.gridy = row; gbc.gridx = 0; gbc.weightx = 0.1;
        JLabel lblTongChiTieu = new JLabel("Tổng chi tiêu");
        lblTongChiTieu.setFont(bigFont);
        pnInfo.add(lblTongChiTieu, gbc);
        gbc.gridx = 1; gbc.weightx = 0.35;
        txtTongChiTieu = new JTextField();
        txtTongChiTieu.setPreferredSize(new Dimension(220, 30));
        txtTongChiTieu.setFont(bigFont);
        pnInfo.add(txtTongChiTieu, gbc);
        txtTongChiTieu.setEditable(false);

        gbc.gridx = 2; gbc.gridwidth = 2;
        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        btnThem = new JButton("Thêm");
        btnSua = new JButton(  "Sửa");
        btnXoa = new JButton("Xoá");
        btnThem.setPreferredSize(new Dimension(120, 30));
        btnSua.setPreferredSize(new Dimension(120, 30));
        btnXoa.setPreferredSize(new Dimension(120, 30));
        btnThem.setFont(bigFont);
        btnSua.setFont(bigFont);
        btnXoa.setFont(bigFont);
        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
        pnButton.setBackground(colorPanel);
        pnInfo.add(pnButton, gbc);

        // ==== Panel Tìm kiếm ====
        JPanel pnSearch = new JPanel(new GridBagLayout());
        pnSearch.setBackground(colorPanel);
        pnSearch.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        pnSearch.setPreferredSize(new Dimension(700, 130));
        GridBagConstraints gbcSearch = new GridBagConstraints();
        gbcSearch.insets = new Insets(8, 8, 8, 8);
        gbcSearch.anchor = GridBagConstraints.WEST;
        gbcSearch.fill = GridBagConstraints.HORIZONTAL;

        // Dòng từ khoá
        gbcSearch.gridx = 0; gbcSearch.gridy = 0; gbcSearch.gridwidth = 1;
        JLabel lblTuKhoa = new JLabel("Từ khoá:");
        lblTuKhoa.setFont(bigFont);
        pnSearch.add(lblTuKhoa, gbcSearch);
        gbcSearch.gridx = 1; gbcSearch.gridwidth = 3;
        txtTukhoa = new JTextField();
        txtTukhoa.setPreferredSize(new Dimension(350, 36));
        txtTukhoa.setFont(bigFont);
        pnSearch.add(txtTukhoa, gbcSearch);

        // Dòng chi tiêu từ -> đến
        gbcSearch.gridy = 1; gbcSearch.gridx = 0; gbcSearch.gridwidth = 1;
        JLabel lblChiTieuTu = new JLabel("Chi tiêu từ:");
        lblChiTieuTu.setFont(bigFont);
        pnSearch.add(lblChiTieuTu, gbcSearch);
        gbcSearch.gridx = 1;
        txtMinchiTieu = new JTextField();
        txtMinchiTieu.setPreferredSize(new Dimension(140, 36));
        txtMinchiTieu.setFont(bigFont);
        pnSearch.add(txtMinchiTieu, gbcSearch);
        gbcSearch.gridx = 2;
        JLabel lblDen = new JLabel("đến:");
        lblDen.setFont(bigFont);
        pnSearch.add(lblDen, gbcSearch);
        gbcSearch.gridx = 3;
        txtMaxChiTieu = new JTextField();
        txtMaxChiTieu.setPreferredSize(new Dimension(140, 36));
        txtMaxChiTieu.setFont(bigFont);
        pnSearch.add(txtMaxChiTieu, gbcSearch);

        // Nút tìm kiếm cùng dòng với input
        gbcSearch.gridx = 4; gbcSearch.gridy = 1; gbcSearch.gridwidth = 1;
        btnTim = createButton("", "image/Search-icon.png");
        btnTim.setToolTipText("Tìm kiếm");
        btnTim.setPreferredSize(new Dimension(100, 36));
        btnTim.setFont(bigFont);
        pnSearch.add(btnTim, gbcSearch);

        // ==== Đặt 2 panel theo chiều dọc ====
        pnInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnSearch.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnTop.add(pnInfo);
        pnTop.add(Box.createRigidArea(new Dimension(0, 10)));
        pnTop.add(pnSearch);

        mainPanel.add(pnTop, BorderLayout.CENTER);

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
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(colorPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2); // Padding nhỏ
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(lbl, gbc);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        if (field instanceof JTextField || field instanceof JComboBox) {
            field.setPreferredSize(new Dimension(200, 30));
        }

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        panel.add(field, gbc);

        return panel;
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(120, 40));
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