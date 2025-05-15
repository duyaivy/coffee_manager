-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: coffeeshop
-- ------------------------------------------------------
-- Server version	8.0.41
use coffeshop;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cthoadon`
--

DROP TABLE IF EXISTS `cthoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cthoadon` (
  `MaHD` int NOT NULL,
  `MaSP` int NOT NULL,
  `SoLuong` int NOT NULL,
  `DonGia` int NOT NULL,
  `ThanhTien` int NOT NULL,
  PRIMARY KEY (`MaHD`,`MaSP`),
  KEY `cthoadon_ibfk_1` (`MaHD`),
  KEY `fk_cthoadon_sanpham` (`MaSP`),
  CONSTRAINT `cthoadon_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cthoadon_sanpham` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cthoadon`
--

LOCK TABLES `cthoadon` WRITE;
/*!40000 ALTER TABLE `cthoadon` DISABLE KEYS */;
INSERT INTO `cthoadon` VALUES (1,1,2,20000,40000),(1,2,1,40000,40000),(1,3,1,40000,40000),(1,4,1,40000,40000),(1,7,2,10000,20000),(2,1,2,20000,40000),(2,3,1,40000,40000),(2,6,1,30000,30000),(2,7,2,10000,20000),(3,3,1,40000,40000),(3,4,1,40000,40000),(3,5,2,40000,80000),(3,7,10,10000,100000),(4,6,1,30000,30000),(4,7,1,10000,10000),(5,3,1,50000,50000),(5,6,1,20000,20000),(5,7,1,30000,30000),(5,8,5,50000,150000),(6,4,1,30000,30000),(6,7,1,30000,30000),(6,9,6,40000,120000),(6,16,3,30000,30000),(7,8,1,50000,50000),(8,5,1,50000,50000);
/*!40000 ALTER TABLE `cthoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giamgia`
--

DROP TABLE IF EXISTS `giamgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giamgia` (
  `MaGiam` int NOT NULL AUTO_INCREMENT,
  `TenGiamGia` text NOT NULL,
  `PhanTramGiam` int NOT NULL,
  `DieuKien` int NOT NULL,
  `NgayBD` date NOT NULL,
  `NgayKT` date NOT NULL,
  PRIMARY KEY (`MaGiam`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giamgia`
--

LOCK TABLES `giamgia` WRITE;
/*!40000 ALTER TABLE `giamgia` DISABLE KEYS */;
INSERT INTO `giamgia` VALUES (1,'Không giảm giá',0,0,'2025-05-05','2042-12-31'),(2,'Khách hàng thân thiết',20,0,'2025-05-05','2026-06-01');
/*!40000 ALTER TABLE `giamgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHD` int NOT NULL AUTO_INCREMENT,
  `MaKH` int NOT NULL,
  `MaNV` int NOT NULL,
  `NgayLap` date NOT NULL,
  `TongTien` int NOT NULL,
  `GhiChu` text NOT NULL,
  PRIMARY KEY (`MaHD`),
  KEY `hoadon_ibfk_1` (`MaNV`),
  KEY `hoadon_ibfk_2` (`MaKH`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1,1,1,'2025-05-09',180000,'Đã thanh toán'),(2,2,2,'2025-05-09',130000,'Đã thanh toán'),(3,3,3,'2025-05-09',260000,'Đã thanh toán'),(4,4,0,'2025-05-10',32000,'Đã thanh toán'),(5,1,0,'2025-05-10',250000,'Đã thanh toán'),(6,3,0,'2025-05-10',168000,'Đã thanh toán'),(7,3,0,'2025-05-10',40000,'Đã thanh toán'),(8,1,0,'2025-05-09',50000,'Đã thanh toán');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKH` int NOT NULL AUTO_INCREMENT,
  `Ho` varchar(255) NOT NULL,
  `Ten` varchar(255) NOT NULL,
  `GioiTinh` varchar(3) NOT NULL,
  `TongChiTieu` int NOT NULL DEFAULT '0',
  `TinhTrang` int NOT NULL,
  PRIMARY KEY (`MaKH`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'Ngô Quang','Vinh','Nam',480000,1),(2,'Đỗ Thiện','Quân','Nam',130000,1),(3,'Nguyễn Minh','Nhật','Nữ',468000,1),(4,'Nguyễn Hải','Sơn','Nam',32000,1);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai`
--

DROP TABLE IF EXISTS `loai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loai` (
  `MaLoai` int NOT NULL AUTO_INCREMENT,
  `TenLoai` text NOT NULL,
  PRIMARY KEY (`MaLoai`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai`
--

LOCK TABLES `loai` WRITE;
/*!40000 ALTER TABLE `loai` DISABLE KEYS */;
INSERT INTO `loai` VALUES (1,'Đồ uống'),(2,'Bánh'),(3,'Soft drink'),(4,'Beer');
/*!40000 ALTER TABLE `loai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNV` int NOT NULL AUTO_INCREMENT,
  `Ho` varchar(255) NOT NULL,
  `Ten` varchar(255) NOT NULL,
  `GioiTinh` varchar(3) NOT NULL,
  `ChucVu` varchar(255) NOT NULL,
  PRIMARY KEY (`MaNV`),
  KEY `chucvu_ibfk_1` (`ChucVu`),
  CONSTRAINT `chucvu_ibfk_1` FOREIGN KEY (`ChucVu`) REFERENCES `taikhoan` (`Quyen`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (0,'Admin','','','Quản trị'),(1,'Ngô Quang','Vinh','Nam','Quản lý'),(2,'Đỗ Minh','Quân','Nam','Nhân viên'),(3,'Nguyễn Hải','Sơn','Nam','Nhân viên');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phanquyen`
--

DROP TABLE IF EXISTS `phanquyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phanquyen` (
  `Quyen` varchar(255) NOT NULL,
  `QLSanPham` int NOT NULL,
  `QLNhanVien` int NOT NULL,
  `QLKhachHang` int NOT NULL,
  `ThongKe` int NOT NULL,
  PRIMARY KEY (`Quyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phanquyen`
--

LOCK TABLES `phanquyen` WRITE;
/*!40000 ALTER TABLE `phanquyen` DISABLE KEYS */;
INSERT INTO `phanquyen` VALUES ('Default',0,0,0,0),('Nhân viên',0,0,1,0),('Quản lý',0,1,1,1),('Quản trị',1,1,1,1);
/*!40000 ALTER TABLE `phanquyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `MaSP` int NOT NULL AUTO_INCREMENT,
  `TenSP` varchar(255) NOT NULL,
  `MaLoai` int NOT NULL,
  `SoLuong` int NOT NULL,
  `DonViTinh` varchar(255) NOT NULL,
  `HinhAnh` varchar(255) NOT NULL,
  `DonGia` int NOT NULL,
  PRIMARY KEY (`MaSP`),
  KEY `sanpham_ibfk_1` (`MaLoai`),
  CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loai` (`MaLoai`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Bang San Pham';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,'Almond',2,20,'Cái','Almond.png',30000),(2,'Bạc Xỉu',1,100,'Cốc','bac_xiu.png',20000),(3,'Blue Berry Smothie',1,99,'Cốc','blue_berry_smothie.png',50000),(4,'Chocolate Cake',2,19,'Cái','Chocolate.png',30000),(5,'Chocolate Cookie',1,97,'Cốc','chocolate_cookie.png',50000),(6,'Coke',3,97,'Lon','cocacola.png',20000),(7,'Coconut Cake',2,18,'Cái','coconut.png',30000),(8,'Coconut Coffee',1,93,'Cốc','coconut_coffee.png',50000),(9,'Coconut Matcha Latte',1,93,'Cốc','coconut_matcha_latte.png',40000),(10,'Cold Brew',1,97,'Chai','cold_brew.png',60000),(11,'Corona Extra',4,100,'Chai','corona_extra.png',40000),(12,'Egg Coffee',1,100,'Cốc','egg_coffe.png',70000),(13,'Espresso Double Shot',1,100,'Cốc','espresso.png',30000),(14,'Espresso Single Shot',1,100,'Cốc','espresso_single.png',15000),(15,'Fanta',3,100,'Lon','fanta.png',20000),(16,'Hanoi Beer',4,97,'Chai','hanoi_beer.png',30000),(17,'Honey Lemon Tea',1,100,'Cốc','honey_lemon_tea.png',30000),(18,'Capuchino',1,100,'Cốc','hot_capuchino.png',60000),(19,'Hot Chocolate',1,100,'Cốc','hot_chocolate.png',70000),(20,'Latte',1,100,'Cốc','hot_latte.png',60000),(21,'Mango Smoothie',1,100,'Cốc','mango_smoothie.png',50000),(22,'Matcha Green Smoothie',1,100,'Cốc','Matcha-Green-Smoothie.png',60000),(23,'Mocha',1,100,'Cốc','mocha.png',40000),(24,'Orange Grass Peach Tea',1,100,'Cốc','orange_grass_peach_tea.png',40000),(25,'Orange Mixed',1,100,'Cốc','orange_mixed.png',50000),(26,'Passion Mousse',2,20,'Cái','Passion-mousse.png',40000),(27,'Pinky Mixed',1,100,'Cốc','pinky_mixed.png',50000),(28,'Sprite',3,100,'Lon','sprite.png',20000),(29,'Tea Bags',3,100,'Cốc','tea_bags.png',40000),(30,'Tiramisu',2,19,'Cái','tiramisu.png',30000),(31,'Vietnamese Coffee',1,100,'Cốc','vietnamesecoffe.png',30000),(32,'Yellow Mixed',1,100,'Cốc','yellow_mixed.png',50000),(33,'Honey Lemon Cake',2,100,'Cái','honey_lemon_cake.png',30000);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `MaNV` int NOT NULL AUTO_INCREMENT,
  `TenDangNhap` varchar(255) NOT NULL,
  `MatKhau` varchar(255) NOT NULL,
  `Quyen` varchar(255) NOT NULL,
  `TrangThai` int NOT NULL,
  PRIMARY KEY (`MaNV`),
  KEY `taikhoan_ibfk_2` (`Quyen`),
  CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`Quyen`) REFERENCES `phanquyen` (`Quyen`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (0,'admin','admin','Quản trị',1),(1,'ql01','ql01','Quản lý',1),(2,'nv01','nv01','Nhân viên',1),(3,'nv6886','nv6886','Nhân viên',1);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-13 10:54:59
