package Manage.DAO;

import MyCustom.MyConnect;
import Manage.DTO.NhanVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO {

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        try {
            String sql = "SELECT * FROM NhanVien";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<NhanVien> dssv = new ArrayList<>();
            while (rs.next()) {
                NhanVien nv = new NhanVien();

                nv.setMaNV(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setChucVu(rs.getString(5));

                dssv.add(nv);
            }
            return dssv;
        } catch (SQLException e) {
        }

        return null;
    }

    public NhanVien getNhanVien(int maNV) {
        NhanVien nv = null;
        try {
            String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setChucVu(rs.getString(5));
            }
        } catch (SQLException e) {
            return null;
        }

        return nv;
    }

    public boolean updateNhanVien(NhanVien nv) {
        boolean result = false;
        try {
            String sql = "UPDATE nhanvien SET Ho=?, Ten=?, GioiTinh=?, ChucVu=? WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, nv.getHo());
            pre.setString(2, nv.getTen());
            pre.setString(3, nv.getGioiTinh());
            pre.setString(4, nv.getChucVu());
            pre.setInt(5, nv.getMaNV());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteNhanVien(int maNV) {
        boolean result = false;
        PreparedStatement pre = null;
        try {
            if (getNhanVien(maNV) == null) {
                return false;
            }
            
            String sql = "DELETE FROM nhanvien WHERE MaNV=?";
            pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                if (pre != null) {
                    pre.close();
                }
            } catch (SQLException ex) {
                // Log error if needed
            }
        }
        return result;
    }

    public boolean themNhanVien(NhanVien nv) {
        if (nv == null || nv.getHo() == null || nv.getTen() == null || 
            nv.getGioiTinh() == null || nv.getChucVu() == null) {
            return false;
        }
        
        boolean result = false;
        PreparedStatement pre = null;
        try {
            String sql = "INSERT INTO NhanVien(Ho, Ten, GioiTinh, ChucVu) " +
                    "VALUES(?, ?, ?, ?)";
            pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, nv.getHo().trim());
            pre.setString(2, nv.getTen().trim());
            pre.setString(3, nv.getGioiTinh().trim());
            pre.setString(4, nv.getChucVu().trim());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                if (pre != null) {
                    pre.close();
                }
            } catch (SQLException ex) {
                // Log error if needed
            }
        }
        return result;
    }
}