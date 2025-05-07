package Manage.BUS;

import MyCustom.MyDialog;
import Manage.DAO.PhanQuyenDAO;
import Manage.DTO.PhanQuyen;
import java.util.ArrayList;

public class PhanQuyenBUS {

    private ArrayList<PhanQuyen> listPhanQuyen;
    private PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();

    public PhanQuyenBUS() {
        docListPhanQuyen();
    }

    public void docListPhanQuyen() {
        this.listPhanQuyen = phanQuyenDAO.getListQuyen();
    }

    public ArrayList<PhanQuyen> getListPhanQuyen() {
        return listPhanQuyen;
    }

    public PhanQuyen getPhanQuyen(String quyen) {
        for (PhanQuyen pq : listPhanQuyen) {
            if (pq.getQuyen().equals(quyen)) {
                return pq;
            }
        }
        return null;
    }

    public boolean suaQuyen(String tenQuyen, int sanPham, int nhanVien, int khachHang, int thongKe) {
        PhanQuyen phanQuyen = new PhanQuyen(tenQuyen, sanPham, nhanVien, khachHang, thongKe);
        boolean flag = phanQuyenDAO.suaQuyen(phanQuyen);
        if (flag) {
            docListPhanQuyen();
        }
        return flag;
    }

    public boolean themQuyen(String tenQuyen, int sanPham, int nhanVien, int khachHang, int thongKe) {
        PhanQuyen phanQuyen = new PhanQuyen(tenQuyen, sanPham, nhanVien, khachHang, thongKe);
        boolean flag = phanQuyenDAO.themQuyen(phanQuyen);
        if (flag) {
            docListPhanQuyen();
        }
        return flag;
    }

    public boolean xoaQuyen(String tenQuyen) {
        boolean flag = phanQuyenDAO.xoaQuyen(tenQuyen);
        if (flag) {
            docListPhanQuyen();
        }
        return flag;
    }
}
