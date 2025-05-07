package Manage.BUS;

import Manage.DAO.CTHoaDonDAO;
import Manage.DTO.CTHoaDon;
import Manage.DTO.SanPham;
import java.util.ArrayList;

public class CTHoaDonBUS {

    private ArrayList<CTHoaDon> listCTHoaDon;
    private CTHoaDonDAO ctHDDAO = new CTHoaDonDAO();
    private HoaDonBUS hdBUS = new HoaDonBUS();
    private SanPhamBUS spBUS = new SanPhamBUS();

    public CTHoaDonBUS() {
        docListCTHoaDon();
    }

    public void docListCTHoaDon() {
        this.listCTHoaDon = ctHDDAO.getListCTHoaDon();
        // Cập nhật thông tin sản phẩm cho mỗi chi tiết hóa đơn
        for (CTHoaDon cthd : listCTHoaDon) {
            SanPham sp = spBUS.getSanPham(String.valueOf(cthd.getMaSP()));
            cthd.setSanPham(sp);
        }
    }

    public ArrayList<CTHoaDon> getListCTHoaDon() {
        return listCTHoaDon;
    }

    public ArrayList<CTHoaDon> getListCTHoaDonTheoMaHD(String maHD) {
        int ma = Integer.parseInt(maHD);
        ArrayList<CTHoaDon> dsct = new ArrayList<>();

        for (CTHoaDon cthd : listCTHoaDon) {
            if (cthd.getMaHD() == ma)
                dsct.add(cthd);
        }

        return dsct;
    }

    public void addCTHoaDon(String maSP, String soLuong, String donGia, String thanhTien) {
        int ma = hdBUS.getMaHoaDonMoiNhat();

        donGia = donGia.replace(",", "");
        thanhTien = thanhTien.replace(",", "");

        CTHoaDon cthd = new CTHoaDon();

        cthd.setMaHD(ma);
        cthd.setMaSP(Integer.parseInt(maSP));
        cthd.setDonGia(Integer.parseInt(donGia));
        cthd.setSoLuong(Integer.parseInt(soLuong));
        cthd.setThanhTien(Integer.parseInt(thanhTien));
        
        // Lấy thông tin sản phẩm
        SanPham sp = spBUS.getSanPham(maSP);
        cthd.setSanPham(sp);

        ctHDDAO.addCTHoaDon(cthd);
    }
}

