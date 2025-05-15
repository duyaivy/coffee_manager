package Manage.DTO;

/**
 * Class đại diện cho một sản phẩm trong hệ thống
 */
public class SanPham {
    private int maSP;
    private String tenSP;
    private int maLoai;
    private int soLuong;
    private String donViTinh;
    private String hinhAnh;
    private int donGia;

    /**
     * Khởi tạo một sản phẩm mới với các giá trị mặc định
     */
    public SanPham() {
        this.maSP = 0;
        this.tenSP = "";
        this.maLoai = 0;
        this.soLuong = 0;
        this.donViTinh = "";
        this.hinhAnh = "";
        this.donGia = 0;
    }

    /**
     * Khởi tạo một sản phẩm mới với các giá trị được chỉ định
     * @param maSP Mã sản phẩm
     * @param tenSP Tên sản phẩm
     * @param maLoai Mã loại sản phẩm
     * @param soLuong Số lượng
     * @param donViTinh Đơn vị tính
     * @param hinhAnh Đường dẫn hình ảnh
     * @param donGia Đơn giá
     */
    public SanPham(int maSP, String tenSP, int maLoai, int soLuong, String donViTinh, String hinhAnh, int donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
        this.hinhAnh = hinhAnh;
        this.donGia = donGia;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        if (maSP < 0) {
            throw new IllegalArgumentException("Mã sản phẩm không được âm");
        }
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        if (tenSP == null || tenSP.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống");
        }
        this.tenSP = tenSP.trim();
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        if (maLoai < 0) {
            throw new IllegalArgumentException("Mã loại không được âm");
        }
        this.maLoai = maLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            throw new IllegalArgumentException("Số lượng không được âm");
        }
        this.soLuong = soLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        if (donViTinh == null || donViTinh.trim().isEmpty()) {
            throw new IllegalArgumentException("Đơn vị tính không được để trống");
        }
        this.donViTinh = donViTinh.trim();
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh != null ? hinhAnh.trim() : "";
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        if (donGia < 0) {
            throw new IllegalArgumentException("Đơn giá không được âm");
        }
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP=" + maSP +
                ", tenSP='" + tenSP + '\'' +
                ", maLoai=" + maLoai +
                ", soLuong=" + soLuong +
                ", donViTinh='" + donViTinh + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", donGia=" + donGia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SanPham sanPham = (SanPham) o;
        return maSP == sanPham.maSP;
    }

    @Override
    public int hashCode() {
        return maSP;
    }
}
