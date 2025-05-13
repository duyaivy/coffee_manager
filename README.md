# 🎓 ĐẠI HỌC ĐÀ NẴNG - TRƯỜNG ĐẠI HỌC BÁCH KHOA  
## KHOA CÔNG NGHỆ THÔNG TIN  
### ĐỒ ÁN MÔN LẬP TRÌNH JAVA
### Đề tài: Ứng Dụng Quản Lý Quán Cà Phê

## 💡 Giới Thiệu
**Coffee Manager** là một phần mềm quản lý quán cà phê được phát triển bằng **Java** sử dụng **NetBeans IDE**. Ứng dụng được xây dựng theo mô hình 3 lớp (GUI - BUS - DAO), cung cấp các chức năng hỗ trợ bán hàng, quản lý sản phẩm, hóa đơn, nhân viên và khách hàng một cách dễ dàng và hiệu quả.
## 🛠️ Công Nghệ Sử Dụng

- **Ngôn ngữ:** Java
- **IDE:** NetBeans
- **Giao diện người dùng:** Java Swing.
- **Cơ sở dữ liệu:** MySQL hoặc SQL Server
- **Mô hình:** 3 lớp (GUI - BUS - DAO)

## 🧱 Cấu Trúc Thư Mục
- Thư mục Main: Lớp khởi động chính, entry point của ứng dụng
- Thư mục Manage.BUS: Lớp xử lý nghiệp vụ (Business Logic Layer)
- Thư mục Manage.DAO: Lớp truy xuất dữ liệu (Data Access Layer)
- Thư mục Manage.DTO: Lớp định nghĩa đối tượng dữ liệu (Data Transfer Object)
- Thư mục Manage.GUI: Lớp giao diện người dùng.
- Thư mục MyCustom: Các cấu hình dùng chung, tiện ích, constant, enum,...
- Thư mục Libs: Thư viện ngoài chương trình sử dụng trong dự án.

## 📌 Chức Năng Chính

### 1. Bán Hàng

- Hiển thị danh sách sản phẩm (mã SP, tên, giá, tồn kho, đơn vị)
- Lọc theo loại sản phẩm
- Thêm sản phẩm vào giỏ hàng
- Tính tổng tiền
- Xuất hoá đơn thanh toán

### 2. Quản Lý Sản Phẩm

- Thêm / sửa / xoá sản phẩm
- Liên kết sản phẩm với loại
- Cập nhật tồn kho

### 3. Quản Lý Hóa Đơn

- Tạo hóa đơn khi thanh toán
- Lưu thông tin hoá đơn và chi tiết từng mặt hàng
- Tìm kiếm hoá đơn theo thời gian hoặc nhân viên

### 4. Quản Lý Nhân Viên

- Quản lý tài khoản đăng nhập
- Phân quyền cho nhân viên: thu ngân, quản lý

### 5. Quản Lý Khách Hàng

- Thêm / sửa / xoá thông tin khách
- Theo dõi lịch sử mua hàng
- Theo dõi tổng thanh toán
### 6. Quản Lý Khuyến mãi

- Thêm / sửa / xoá thông tin về chương trình khuyến mãi 

### 7. Thống Kê & Báo Cáo

- Thống kê doanh thu theo ngày, tháng
- Thống kê số lượng sản phẩm đã bán
  In biểu đồ báo cáo theo tháng / năm

## ▶️ Hướng Dẫn Chạy Dự Án

1. Mở project bằng **NetBeans IDE**
2. Tạo database bằng file script coffeeshop.sql có sẵn bằng MySql Workbench
3. Cấu hình chuỗi kết nối CSDL trong file kết nối
4. Nhấn `Run` để chạy ứng dụng



## 🗃️ Cơ Sở Dữ Liệu Cấu Trúc Cơ Bản: 
- sanpham (MaSP INT, TenSP VARCHAR, MaLoai INT, SoLuong INT, DonViTinh VARCHAR, HinhAnh VARCHAR, DonGia INT)
  → FK MaLoai → loai

- loai (MaLoai INT, TenLoai TEXT)

- hoadon (MaHD INT, MaKH INT, MaNV INT, NgayLap DATE, TongTien INT, GhiChu TEXT)
  → FK MaKH → khachhang, MaNV → nhanvien

- cthoadon (MaHD INT, MaSP INT, SoLuong INT, DonGia INT, ThanhTien INT)
  → FK MaHD → hoadon, MaSP → sanpham

- khachhang (MaKH INT, Ho VARCHAR, Ten VARCHAR, GioiTinh VARCHAR, TongChiTieu INT, TinhTrang INT)

- nhanvien (MaNV INT, Ho VARCHAR, Ten VARCHAR, GioiTinh VARCHAR(3), ChucVu VARCHAR)

- taikhoan (MaNV INT, TenDangNhap VARCHAR, MatKhau VARCHAR, Quyen VARCHAR, TrangThai INT)
  → PK đồng thời là FK đến nhanvien.MaNV, Quyen → phanquyen

- phanquyen (Quyen VARCHAR, QLSanPham INT, QLNhanVien INT, QLKhachHang INT, ThongKe INT)

- giamgia (MaGiam INT, TenGiamGia TEXT, PhanTramGiam INT, DieuKien TEXT, NgayBD DATE, NgayKT DATE)
  
## 🎯 Kết quả đạt được

- Giao diện trang bán hàng
  
- Giao diện trang quản lý sản phẩm
  
- Giao diện trang quản lý nhân viên
  
- Giao diện trang quản lý khuyến mãi
  
- Giao diện trang quản lý phân quyền


