package Manage.DAO;

import MyCustom.MyConnect;
import Manage.DTO.SanPham;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SanPhamDAO {
    private static final String SELECT_ALL = "SELECT * FROM SanPham";
    private static final String SELECT_BY_ID = "SELECT * FROM SanPham WHERE MaSP=?";
    private static final String SELECT_BY_CATEGORY = "SELECT * FROM SanPham WHERE MaLoai=?";
    private static final String SELECT_IMAGE = "SELECT HinhAnh FROM SanPham WHERE MaSP=?";
    private static final String UPDATE_QUANTITY = "UPDATE SanPham SET SoLuong=? WHERE MaSP=?";
    private static final String INSERT = "INSERT INTO SanPham(TenSP, MaLoai, SoLuong, DonViTinh, HinhAnh, DonGia) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM SanPham WHERE MaSP=?";
    private static final String UPDATE = "UPDATE SanPham SET TenSP=?, MaLoai=?, SoLuong=?, DonViTinh=?, HinhAnh=?, DonGia=? WHERE MaSP=?";

    public ArrayList<SanPham> getListSanPham() {
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                dssp.add(mapResultSetToSanPham(rs));
            }
            return dssp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SanPham getSanPham(int ma) {
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(SELECT_BY_ID);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return mapResultSetToSanPham(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<SanPham> getSanPhamTheoLoai(int maLoai) {
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(SELECT_BY_CATEGORY);
            pre.setInt(1, maLoai);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                dssp.add(mapResultSetToSanPham(rs));
            }
            return dssp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAnh(int ma) {
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(SELECT_IMAGE);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString("HinhAnh");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void capNhatSoLuongSP(int ma, int soLuongMat) {
        SanPham sp = getSanPham(ma);
        if (sp != null) {
            int soLuong = sp.getSoLuong();
            sp.setSoLuong(soLuong + soLuongMat);
            try {
                PreparedStatement pre = MyConnect.conn.prepareStatement(UPDATE_QUANTITY);
                pre.setInt(1, sp.getSoLuong());
                pre.setInt(2, ma);
                pre.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean themSanPham(SanPham sp) {
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(INSERT);
            setSanPhamParameters(pre, sp);
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean nhapSanPhamTuExcel(SanPham sp) {
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(INSERT);
            setSanPhamParameters(pre, sp);
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaSanPham(int maSP) {
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(DELETE);
            pre.setInt(1, maSP);
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean suaSanPham(SanPham sp) {
        try {
            PreparedStatement pre = MyConnect.conn.prepareStatement(UPDATE);
            setSanPhamParameters(pre, sp);
            pre.setInt(7, sp.getMaSP());
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private SanPham mapResultSetToSanPham(ResultSet rs) throws SQLException {
        SanPham sp = new SanPham();
        sp.setMaSP(rs.getInt(1));
        sp.setTenSP(rs.getString(2));
        sp.setMaLoai(rs.getInt(3));
        sp.setSoLuong(rs.getInt(4));
        sp.setDonViTinh(rs.getString(5));
        sp.setHinhAnh(rs.getString(6));
        sp.setDonGia(rs.getInt(7));
        return sp;
    }

    private void setSanPhamParameters(PreparedStatement pre, SanPham sp) throws SQLException {
        pre.setString(1, sp.getTenSP());
        pre.setInt(2, sp.getMaLoai());
        pre.setInt(3, sp.getSoLuong());
        pre.setString(4, sp.getDonViTinh());
        pre.setString(5, sp.getHinhAnh());
        pre.setInt(6, sp.getDonGia());
    }
}
