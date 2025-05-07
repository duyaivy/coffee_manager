package Manage.DAO;

import Manage.DTO.PhanQuyen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import MyCustom.MyConnect;

public class PhanQuyenDAO {

    private Connection conn = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;

    public PhanQuyenDAO() {
        conn = MyConnect.conn;
    }

    public ArrayList<PhanQuyen> getListQuyen() {
        ArrayList<PhanQuyen> dspq = new ArrayList<>();
        String sql = "SELECT * FROM phanquyen";
        try {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                PhanQuyen phanQuyen = new PhanQuyen();
                phanQuyen.setQuyen(rs.getString(1));
                phanQuyen.setQlSanPham(rs.getInt(2));
                phanQuyen.setQlNhanVien(rs.getInt(3));
                phanQuyen.setQlKhachHang(rs.getInt(4));
                phanQuyen.setThongKe(rs.getInt(5));
                dspq.add(phanQuyen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dspq;
    }

    public PhanQuyen getQuyen(String quyen) {
        PhanQuyen phanQuyen = null;
        String sql = "SELECT * FROM phanquyen WHERE Quyen=?";
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, quyen);
            rs = pre.executeQuery();
            if (rs.next()) {
                phanQuyen = new PhanQuyen();
                phanQuyen.setQuyen(rs.getString(1));
                phanQuyen.setQlSanPham(rs.getInt(2));
                phanQuyen.setQlNhanVien(rs.getInt(3));
                phanQuyen.setQlKhachHang(rs.getInt(4));
                phanQuyen.setThongKe(rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phanQuyen;
    }

    public boolean suaQuyen(PhanQuyen phanQuyen) {
        String sql = "UPDATE phanquyen SET QLSanPham=?,QLNhanVien=?,QLKhachHang=?,ThongKe=? WHERE Quyen=?";
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, phanQuyen.getQlSanPham());
            pre.setInt(2, phanQuyen.getQlNhanVien());
            pre.setInt(3, phanQuyen.getQlKhachHang());
            pre.setInt(4, phanQuyen.getThongKe());
            pre.setString(5, phanQuyen.getQuyen());
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean themQuyen(PhanQuyen phanQuyen) {
        String sql = "INSERT INTO phanquyen(Quyen,QLSanPham,QLNhanVien,QLKhachHang,ThongKe) VALUES(?,?,?,?,?)";
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, phanQuyen.getQuyen());
            pre.setInt(2, phanQuyen.getQlSanPham());
            pre.setInt(3, phanQuyen.getQlNhanVien());
            pre.setInt(4, phanQuyen.getQlKhachHang());
            pre.setInt(5, phanQuyen.getThongKe());
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaQuyen(String quyen) {
        String sql = "DELETE FROM phanquyen WHERE Quyen=?";
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, quyen);
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
