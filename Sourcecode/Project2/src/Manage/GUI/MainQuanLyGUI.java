package Manage.GUI;

import Manage.BUS.DangNhapBUS;
import Manage.BUS.PhanQuyenBUS;
import Manage.DTO.PhanQuyen;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainQuanLyGUI extends JFrame {

    public MainQuanLyGUI() {
        this.setTitle("Phần mềm quản lý bán hàng");
        this.setSize(1280, 900);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/ManagerUI/icon-app.png");
        this.setIconImage(icon);
        addControls();
        addEvents();
    }

    public void showWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    JLabel btnDoiMatKhau;
    JPanel pnTitle, pnMenuLeft, pnCard, pnBanHang, pnKhuyenMai, pnSanPham, pnNhanVien, pnKhachHang, pnThongKe;
    PnQuanLyBanHangGUI banHangPanel;
    PnQuanLyKhuyenMaiGUI khuyenMaiPanel;
    PnQuanLySanPhamGUI sanPhamPanel;
    PnQuanLyNhanVienGUI nhanVienPanel;
    PnQuanLyKhachHangGUI khachHangPanel;
    PnQuanLyThongKeGUI thongKePanel;

    JLabel btnClose, btnMinimize, lblBanHang, lblKhuyenMai, lblSanPham, lblNhanVien, lblKhachHang, lblThongKe;
    final Color clLeftItem = new Color(179, 163, 137);
    final Color clLeftItemHover = new Color(209, 185, 143);
    final Color clLeftItemSelected = new Color(232, 195, 115);
    ArrayList<JLabel> listMenuLeft;
    CardLayout cardMenuLeftGroup = new CardLayout();

    private void addControls() {
        int width = this.getWidth();
        int height = this.getHeight();

        Container con = getContentPane();

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());

        /*
        ============================================================
                                TITLE BAR
        ============================================================
         */
        pnTitle = new JPanel(null);
        pnTitle.setPreferredSize(new Dimension(width, 46));
        pnTitle.setBackground(new Color(159, 145, 121));

        btnDoiMatKhau = new JLabel(new ImageIcon("image/ManagerUI/icons8_gear_46px.png"));
        btnDoiMatKhau.setToolTipText("Đổi mật khẩu");
        btnDoiMatKhau.setBounds(0, 0, 46, 46);
        btnDoiMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnDoiMatKhau);

        JLabel lblTitleText = new JLabel(new ImageIcon("image/ManagerUI/title-text.png"));
        lblTitleText.setBounds(width / 2 - 428 / 2, 3, 428, 38);
        pnTitle.add(lblTitleText);

        btnMinimize = new JLabel(new ImageIcon("image/ManagerUI/btn-minimize.png"));
        btnMinimize.setBounds(width - 85, 5, 38, 35);
        btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnMinimize);

        btnClose = new JLabel(new ImageIcon("image/ManagerUI/btn-close.png"));
        btnClose.setBounds(width - 40, 5, 35, 35);
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnClose);

        pnMain.add(pnTitle, BorderLayout.NORTH);
        /*
        ============================================================
                                SIDE BAR MENU
        ============================================================
         */
        pnMenuLeft = new JPanel();
        pnMenuLeft.setPreferredSize(new Dimension(250, height - pnTitle.getHeight()));
        pnMenuLeft.setBackground(clLeftItem);
        pnMenuLeft.setLayout(new BoxLayout(pnMenuLeft, BoxLayout.Y_AXIS));

        JLabel lblAvatar = new JLabel(new ImageIcon("image/ManagerUI/avatar.png"), JLabel.CENTER);
        lblAvatar.setPreferredSize(new Dimension(250, 210));
        pnMenuLeft.add(lblAvatar);

        lblBanHang = new JLabel(new ImageIcon("image/ManagerUI/lblBanHang.png"));
        lblKhuyenMai = new JLabel(new ImageIcon("image/ManagerUI/lblKhuyenMai.png"));
        lblSanPham = new JLabel(new ImageIcon("image/ManagerUI/lblSanPham.png"));
        lblNhanVien = new JLabel(new ImageIcon("image/ManagerUI/lblNhanVien.png"));
        lblKhachHang = new JLabel(new ImageIcon("image/ManagerUI/lblKhachHang.png"));
        lblThongKe = new JLabel(new ImageIcon("image/ManagerUI/lblThongKe.png"));

        listMenuLeft = new ArrayList<>();
        listMenuLeft.add(lblBanHang);
        listMenuLeft.add(lblKhuyenMai);
        listMenuLeft.add(lblSanPham);
        listMenuLeft.add(lblNhanVien);
        listMenuLeft.add(lblKhachHang);
        listMenuLeft.add(lblThongKe);

        for (JLabel lbl : listMenuLeft) {
            lbl.setVisible(false);
            lbl.setPreferredSize(new Dimension(250, 65));
            lbl.setOpaque(true);
            lbl.setBackground(clLeftItem);
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnMenuLeft.add(lbl);
        }

        lblBanHang.setBackground(clLeftItemSelected);
        lblBanHang.setVisible(true);
        lblKhuyenMai.setVisible(true);

        pnMain.add(pnMenuLeft, BorderLayout.WEST);

        /*
        ============================================================
                                CARD PANEL           
        ============================================================
         */
        pnCard = new JPanel(cardMenuLeftGroup);

        pnBanHang = new JPanel();
        pnKhuyenMai = new JPanel();
        pnSanPham = new JPanel();
        pnNhanVien = new JPanel();
        pnKhachHang = new JPanel();
        pnThongKe = new JPanel();

        pnCard.add(pnBanHang, "1");
        pnCard.add(pnKhuyenMai, "2");
        pnCard.add(pnSanPham, "3");
        pnCard.add(pnNhanVien, "4");
        pnCard.add(pnKhachHang, "5");
        pnCard.add(pnThongKe, "6");

        //==========ADD PANEL BÁN HÀNG + KHUYẾN MÃI (Ko phân quyền)==========
        banHangPanel = new PnQuanLyBanHangGUI();
        pnBanHang.setLayout(new BorderLayout());
        pnBanHang.add(banHangPanel, BorderLayout.CENTER);

        khuyenMaiPanel = new PnQuanLyKhuyenMaiGUI();
        pnKhuyenMai.setLayout(new BorderLayout());
        pnKhuyenMai.add(khuyenMaiPanel, BorderLayout.CENTER);

        //======XỬ LÝ PHÂN QUYỀN=======
        PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        PhanQuyen quyen = phanQuyenBUS.getPhanQuyen(DangNhapBUS.quyenTK);

        if (quyen.getQlSanPham() == 1) {
            sanPhamPanel = new PnQuanLySanPhamGUI();
            pnSanPham.setLayout(new BorderLayout());
            pnSanPham.add(sanPhamPanel, BorderLayout.CENTER);
            lblSanPham.setVisible(true);
        }

        if (quyen.getQlNhanVien() == 1) {
            nhanVienPanel = new PnQuanLyNhanVienGUI();
            pnNhanVien.setLayout(new BorderLayout());
            pnNhanVien.add(nhanVienPanel, BorderLayout.CENTER);
            lblNhanVien.setVisible(true);
        }

        if (quyen.getQlKhachHang() == 1) {
            khachHangPanel = new PnQuanLyKhachHangGUI();
            pnKhachHang.setLayout(new BorderLayout());
            pnKhachHang.add(khachHangPanel, BorderLayout.CENTER);
            lblKhachHang.setVisible(true);
        }

        if (quyen.getThongKe() == 1) {
            thongKePanel = new PnQuanLyThongKeGUI();
            pnThongKe.setLayout(new BorderLayout());
            pnThongKe.add(thongKePanel, BorderLayout.CENTER);
            lblThongKe.setVisible(true);
        }
        pnMain.add(pnCard);
        /*
        ============================================================
                                CARD PANEL           
        ============================================================
         */
        con.add(pnMain);
    }

    int xMouse, yMouse;

    private void addEvents() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moverFrame(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        btnDoiMatKhau.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new DlgDoiMatKhau().setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnDoiMatKhau.setOpaque(true);
                btnDoiMatKhau.setBackground(clLeftItemHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDoiMatKhau.setOpaque(false);
                btnDoiMatKhau.setBackground(new Color(0, 0, 0, 0));
            }
        });

        btnMinimize.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thuNhoFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnMinimize.setOpaque(true);
                btnMinimize.setBackground(clLeftItemHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMinimize.setOpaque(false);
                btnMinimize.setBackground(new Color(0, 0, 0, 0));
            }
        });

        btnClose.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thoatChuongTrinh();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setOpaque(true);
                btnClose.setBackground(clLeftItemHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClose.setOpaque(false);
                btnClose.setBackground(new Color(0, 0, 0, 0));
            }
        });

        for (int i = 0; i < listMenuLeft.size(); i++) {
            final int index = i;
            listMenuLeft.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JLabel lbl : listMenuLeft) {
                        lbl.setBackground(clLeftItem);
                    }
                    listMenuLeft.get(index).setBackground(clLeftItemSelected);
                    cardMenuLeftGroup.show(pnCard, String.valueOf(index + 1));
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (listMenuLeft.get(index).getBackground() != clLeftItemSelected) {
                        listMenuLeft.get(index).setBackground(clLeftItemHover);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (listMenuLeft.get(index).getBackground() != clLeftItemSelected) {
                        listMenuLeft.get(index).setBackground(clLeftItem);
                    }
                }
            });
        }
    }

    private void moverFrame(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void thuNhoFrame() {
        this.setState(Frame.ICONIFIED);
    }

    private void thoatChuongTrinh() {
        System.exit(0);
    }
}
