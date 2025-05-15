package Manage.BUS;

import MyCustom.MyDialog;
import Manage.DAO.SanPhamDAO;
import Manage.DTO.SanPham;
import java.util.ArrayList;

public class SanPhamBUS {
    private ArrayList<SanPham> listSanPham = null;
    private SanPhamDAO spDAO = new SanPhamDAO();

    public SanPhamBUS() {
        docListSanPham();
    }

    public void docListSanPham() {
        listSanPham = spDAO.getListSanPham();
    }

    public ArrayList<SanPham> getListSanPham() {
        if (listSanPham == null) {
            docListSanPham();
        }
        return listSanPham;
    }

    public SanPham getSanPham(String ma) {
        if (!ma.trim().equals("")) {
            try {
                int maSP = Integer.parseInt(ma);
                for (SanPham sp : listSanPham) {
                    if (sp.getMaSP() == maSP) {
                        return sp;
                    }
                }
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }
        return null;
    }

    public ArrayList<SanPham> getSanPhamTheoTen(String ten) {
        ArrayList<SanPham> dssp = new ArrayList<>();
        String tenLower = ten.toLowerCase();
        for (SanPham sp : listSanPham) {
            if (sp.getTenSP().toLowerCase().contains(tenLower)) {
                dssp.add(sp);
            }
        }
        return dssp;
    }

    public ArrayList<SanPham> getSanPhamTheoLoai(String ma) {
        if (!ma.trim().equals("")) {
            try {
                int maLoai = Integer.parseInt(ma);
                ArrayList<SanPham> dssp = new ArrayList<>();
                for (SanPham sp : listSanPham) {
                    if (sp.getMaLoai() == maLoai) {
                        dssp.add(sp);
                    }
                }
                return dssp;
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }
        return null;
    }

    public String getAnh(String ma) {
        try {
            int maSP = Integer.parseInt(ma);
            return spDAO.getAnh(maSP);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void capNhatSoLuongSP(int ma, int soLuongMat) {
        spDAO.capNhatSoLuongSP(ma, soLuongMat);
    }

    public boolean themSanPham(String ten, String loai, String soLuong, String donViTinh, String anh, String donGia) {
        if (ten.trim().isEmpty()) {
            new MyDialog("Tên SP không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }

        if (donViTinh.trim().isEmpty()) {
            new MyDialog("Vui lòng điền đơn vị tính!", MyDialog.ERROR_DIALOG);
            return false;
        }

        try {
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            if (maLoai == 0) {
                new MyDialog("Vui lòng chọn Loại sản phẩm!", MyDialog.ERROR_DIALOG);
                return false;
            }

            int soLuongSP = Integer.parseInt(soLuong);
            int donGiaSP = Integer.parseInt(donGia.replace(",", ""));

            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            if (spDAO.themSanPham(sp)) {
                new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (NumberFormatException e) {
            new MyDialog("Nhập số hợp lệ cho Đơn giá và Số lượng!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean nhapSanPhamTuExcel(String ten, String loai, String soLuong, String donViTinh, String anh, String donGia) {
        try {
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            int donGiaSP = Integer.parseInt(donGia.replace(",", ""));

            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            return spDAO.nhapSanPhamTuExcel(sp);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean xoaSanPham(String ma) {
        if (ma.trim().isEmpty()) {
            new MyDialog("Chưa chọn sản phẩm để xoá!", MyDialog.ERROR_DIALOG);
            return false;
        }

        try {
            int maSP = Integer.parseInt(ma);
            if (spDAO.xoaSanPham(maSP)) {
                new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            }
            new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
        } catch (NumberFormatException e) {
            new MyDialog("Mã sản phẩm không hợp lệ!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean suaSanPham(String ma, String ten, String loai, String soLuong, String donViTinh, String anh, String donGia) {
        try {
            if (ma.trim().isEmpty()) {
                new MyDialog("Chưa chọn sản phẩm để sửa!", MyDialog.ERROR_DIALOG);
                return false;
            }

            if (ten.trim().isEmpty()) {
                new MyDialog("Tên SP không được để trống!", MyDialog.ERROR_DIALOG);
                return false;
            }

            if (donViTinh.trim().isEmpty()) {
                new MyDialog("Vui lòng điền Đơn vị tính!", MyDialog.ERROR_DIALOG);
                return false;
            }

            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            if (maLoai == 0) {
                new MyDialog("Vui lòng chọn Loại sản phẩm!", MyDialog.ERROR_DIALOG);
                return false;
            }

            int maSP = Integer.parseInt(ma);
            int soLuongSP = Integer.parseInt(soLuong);
            int donGiaSP = Integer.parseInt(donGia.replace(",", ""));

            SanPham sp = new SanPham();
            sp.setMaSP(maSP);
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            if (spDAO.suaSanPham(sp)) {
                new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (NumberFormatException e) {
            new MyDialog("Nhập số hợp lệ cho Đơn giá và Số lượng!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public String getTenSP(int maSP) {
        for (SanPham sp : listSanPham) {
            if (sp.getMaSP() == maSP) {
                return sp.getTenSP();
            }
        }
        return "";
    }
}