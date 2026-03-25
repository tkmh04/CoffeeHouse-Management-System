-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 19, 2024 lúc 05:50 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanliquantrasua`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `idHd` int(11) DEFAULT NULL,
  `idSp` int(11) DEFAULT NULL,
  `idTopping` int(11) DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `price` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `idNL` int(11) DEFAULT NULL,
  `idPN` int(11) DEFAULT NULL,
  `priceNL` int(11) DEFAULT NULL,
  `soLuong` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietquyen`
--

CREATE TABLE `chitietquyen` (
  `idQuyen` int(11) NOT NULL,
  `maCn` int(10) NOT NULL,
  `HanhDong` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietquyen`
--

INSERT INTO `chitietquyen` (`idQuyen`, `maCn`, `HanhDong`) VALUES
(1, 1, 'Sửa'),
(1, 1, 'Thêm'),
(1, 1, 'Tìm kiếm'),
(1, 1, 'Xem'),
(1, 1, 'Xoá'),
(1, 2, 'Sửa'),
(1, 2, 'Thêm'),
(1, 2, 'Tìm kiếm'),
(1, 2, 'Xem'),
(1, 2, 'Xoá'),
(1, 3, 'Sửa'),
(1, 3, 'Thêm'),
(1, 3, 'Tìm kiếm'),
(1, 3, 'Xem'),
(1, 3, 'Xoá'),
(1, 4, 'Sửa'),
(1, 4, 'Thêm'),
(1, 4, 'Tìm kiếm'),
(1, 4, 'Xem'),
(1, 4, 'Xoá'),
(1, 5, 'Sửa'),
(1, 5, 'Thêm'),
(1, 5, 'Tìm kiếm'),
(1, 5, 'Xem'),
(1, 5, 'Xoá'),
(1, 6, 'Sửa'),
(1, 6, 'Thêm'),
(1, 6, 'Tìm kiếm'),
(1, 6, 'Xem'),
(1, 6, 'Xoá'),
(1, 7, 'Sửa'),
(1, 7, 'Thêm'),
(1, 7, 'Tìm kiếm'),
(1, 7, 'Xem'),
(1, 7, 'Xoá'),
(1, 8, 'Sửa'),
(1, 8, 'Thêm'),
(1, 8, 'Tìm kiếm'),
(1, 8, 'Xem'),
(1, 8, 'Xoá');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chucnang`
--

CREATE TABLE `chucnang` (
  `maCn` int(11) NOT NULL,
  `nameChucNang` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chucnang`
--

INSERT INTO `chucnang` (`maCn`, `nameChucNang`) VALUES
(1, 'Menu'),
(2, 'Phiếu nhập'),
(3, 'Phiếu xuất'),
(4, 'Nhân viên'),
(5, 'Khách Hàng'),
(6, 'Thống kê'),
(7, 'Tài khoản'),
(8, 'Nguyên liệu'),
(9, 'Phân quyền');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `congthuc`
--

CREATE TABLE `congthuc` (
  `idSp` int(11) DEFAULT NULL,
  `idNL` int(11) DEFAULT NULL,
  `Size` varchar(2) DEFAULT NULL,
  `unit` varchar(20) DEFAULT NULL,
  `quant` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `congthuc`
--

INSERT INTO `congthuc` (`idSp`, `idNL`, `Size`, `unit`, `quant`) VALUES
(1, 20, 'M', 'ml', 200),
(1, 20, 'L', 'ml', 350),
(3, 2, 'M', 'ml', 200),
(3, 2, 'L', 'ml', 350),
(2, 1, 'M', 'ml', 200),
(2, 1, 'L', 'ml', 350),
(4, 19, 'M', 'ml', 200),
(4, 19, 'L', 'ml', 350),
(6, 21, 'M', 'ml', 200),
(6, 22, 'M', 'ml', 50),
(6, 21, 'L', 'ml', 350),
(6, 22, 'L', 'ml', 70),
(7, 21, 'M', 'ml', 200),
(7, 4, 'M', 'ml', 50),
(7, 21, 'L', 'ml', 350),
(7, 4, 'L', 'ml', 70),
(8, 21, 'M', 'ml', 200),
(8, 7, 'M', 'ml', 50),
(8, 21, 'L', 'ml', 350),
(8, 7, 'L', 'ml', 70),
(9, 21, 'M', 'ml', 200),
(9, 8, 'M', 'ml', 50),
(9, 21, 'L', 'ml', 350),
(9, 8, 'L', 'ml', 70),
(10, 11, 'M', 'trái', 1),
(10, 3, 'M', 'ml', 60),
(10, 11, 'L', 'trái', 2),
(10, 3, 'L', 'ml', 90);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giasanpham`
--

CREATE TABLE `giasanpham` (
  `idSp` int(11) DEFAULT NULL,
  `size` varchar(50) DEFAULT NULL,
  `priceSp` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `giasanpham`
--

INSERT INTO `giasanpham` (`idSp`, `size`, `priceSp`) VALUES
(1, 'M', 25000),
(1, 'L', 30000),
(2, 'M', 25000),
(2, 'L', 30000),
(3, 'M', 25000),
(3, 'L', 30000),
(4, 'M', 25000),
(4, 'L', 30000),
(6, 'M', 25000),
(6, 'L', 30000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `idHd` int(11) NOT NULL,
  `idNv` int(11) DEFAULT NULL,
  `idKhMember` int(11) DEFAULT NULL,
  `timeHd` datetime DEFAULT NULL,
  `total` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khach_hang`
--

CREATE TABLE `khach_hang` (
  `MAKH` int(11) NOT NULL,
  `TENKH` varchar(50) NOT NULL,
  `SDT` varchar(10) NOT NULL,
  `DIACHI` varchar(100) NOT NULL,
  `LOAITHANHVIEN` varchar(1) NOT NULL,
  `NGAYTHAMGIA` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='khach_hang';

--
-- Đang đổ dữ liệu cho bảng `khach_hang`
--

INSERT INTO `khach_hang` (`MAKH`, `TENKH`, `SDT`, `DIACHI`, `LOAITHANHVIEN`, `NGAYTHAMGIA`) VALUES
(1, 'Hà Trọng Nghĩa', '0921465660', 'dsakdnas', 'A', NULL),
(3, 'Bắc', '0832132441', 'jsdj', 'B', NULL),
(4, 'Nhân', '0998832993', 'jj', 'C', NULL),
(5, 'Thi', '0932421451', 'dsf', 'F', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `idtypeSp` int(11) NOT NULL,
  `nametypeSp` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`idtypeSp`, `nametypeSp`) VALUES
(1, 'Trà sữa'),
(2, 'Trà'),
(3, 'Nước ép'),
(4, 'Cà phê'),
(5, 'Đá xay');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguyenlieu`
--

CREATE TABLE `nguyenlieu` (
  `idNL` int(11) NOT NULL,
  `nameNL` varchar(50) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `quant` double DEFAULT NULL,
  `dongia` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nguyenlieu`
--

INSERT INTO `nguyenlieu` (`idNL`, `nameNL`, `unit`, `quant`, `dongia`) VALUES
(1, 'Trà sữa thường', 'ml', 3600, 5000),
(2, 'Trà sữa lài', 'ml', 1800, 5000),
(3, 'Nước đường', 'ml', 3000, 5000),
(4, 'Mứt dâu', 'ml', 3000, 5000),
(5, 'Ổi tươi', 'trái', 50, 5000),
(6, 'Mứt đường', 'ml', 3000, 5000),
(7, 'Mứt đào', 'ml', 2850, 5000),
(8, 'Mứt vải', 'ml', 3000, 5000),
(9, 'Dâu tươi', 'lát', 50, 5000),
(10, 'Vải tươi', 'trái', 50, 5000),
(11, 'Cà rốt tươi', 'trái', 50, 5000),
(12, 'Đào tươi', 'lát', 50, 5000),
(13, 'Táo tươi', 'trái', 50, 5000),
(14, 'Cam tươi', 'trái', 50, 5000),
(15, 'Sữa ', 'ml', 3000, 5000),
(16, 'Cà phê ', 'g', 10000, 5000),
(17, 'Bánh quy', 'cái', 100, 5000),
(18, 'Bột matcha', 'g', 3000, 5000),
(19, 'Trà sữa ô long', 'ml', 3000, 5000),
(20, 'Trà sữa thái', 'ml', 200, 5000),
(21, 'Trà', 'ml', 2400, 5000),
(22, 'Mứt ổi', 'ml', 3000, 5000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `idNcc` int(11) NOT NULL,
  `nameNcc` varchar(30) DEFAULT NULL,
  `phoneNumbNcc` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`idNcc`, `nameNcc`, `phoneNumbNcc`) VALUES
(1, 'Đại lý trà Ban Mai', '0392745231'),
(2, 'Công ty giải khát Lý Mẫn', '0366652271');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `idNv` int(11) NOT NULL,
  `nameNv` varchar(30) DEFAULT NULL,
  `dateOfBirthNv` varchar(50) DEFAULT NULL,
  `sexualNv` varchar(3) DEFAULT NULL,
  `phoneNumbNv` varchar(10) DEFAULT NULL,
  `addressNv` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`idNv`, `nameNv`, `dateOfBirthNv`, `sexualNv`, `phoneNumbNv`, `addressNv`) VALUES
(1, 'Hà Trọng Nghĩa', '2004-08-05', 'Nam', '0921465660', '16/9/13 Quận 12 TpHCM'),
(2, 'Nguyễn Như Hoài Bắc', '2004-05-22', 'Nam', '0359698104', '766/18 Lạc Long Quân,p9,Q.Tân Bình,tp.Hồ Chí Minh'),
(3, 'Hoàng Minh Phúc', '2004-08-19', 'Nam', '0921432132', '22 Âu Cơ,P99 ,Q.Tân Bình, TpHCM');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `idQuyen` int(11) DEFAULT NULL,
  `tenQuyen` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`idQuyen`, `tenQuyen`) VALUES
(1, 'Admin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `idPN` int(11) NOT NULL,
  `idNv` int(11) DEFAULT NULL,
  `idNcc` int(11) DEFAULT NULL,
  `timeNhap` datetime DEFAULT NULL,
  `Total` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rankmember`
--

CREATE TABLE `rankmember` (
  `idRank` int(11) NOT NULL,
  `nameRank` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `rankmember`
--

INSERT INTO `rankmember` (`idRank`, `nameRank`) VALUES
(1, 'D'),
(2, 'C'),
(3, 'B'),
(4, 'A');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `idSp` int(11) NOT NULL,
  `nameSp` varchar(30) DEFAULT NULL,
  `idtypeSp` int(11) DEFAULT NULL,
  `imageSp` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`idSp`, `nameSp`, `idtypeSp`, `imageSp`) VALUES
(1, 'Trà sữa thái', 1, '/img/trsthai.png'),
(2, 'Trà sữa truyền thống', 1, '/img/trs.png'),
(3, 'Trà sữa lài', 1, '/img/trs.png'),
(4, 'Trà sữa ô long', 1, '/img/trs.png'),
(6, 'Trà ổi', 2, '/img/trs.png'),
(7, 'Trà dâu', 2, '/img/trs.png'),
(8, 'Trà đào', 2, '/img/trs.png'),
(9, 'Trà vải', 2, '/img/trs.png'),
(10, 'Nước ép cà rốt', 3, '/img/trs.png'),
(11, 'Nước ép ổi', 3, '/img/trs.png'),
(12, 'Nước ép táo', 3, '/img/trs.png'),
(13, 'Nước ép cam', 3, '/img/trs.png'),
(14, 'Cà phê sữa', 4, '/img/trs.png'),
(15, 'Cà phê đen', 4, '/img/trs.png'),
(16, 'Bạc xỉu', 4, '/img/trs.png'),
(17, 'Cappuchino', 4, '/img/trs.png'),
(18, 'Cookies đá xay', 5, '/img/trs.png'),
(19, 'Matcha đá xay', 5, '/img/trs.png'),
(20, 'Chocolate đá xay', 5, '/img/trs.png'),
(21, 'Dâu đá xay', 5, '/img/trs.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `idAccount` int(11) NOT NULL,
  `idNv` int(11) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `passWord` varchar(30) DEFAULT NULL,
  `idQuyen` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`idAccount`, `idNv`, `userName`, `passWord`, `idQuyen`, `status`) VALUES
(1, 1, 'admin', 'admin', 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `topping`
--

CREATE TABLE `topping` (
  `idTopping` int(11) NOT NULL,
  `nameTopping` varchar(50) DEFAULT NULL,
  `priceTopping` double DEFAULT NULL,
  `quantTopping` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `topping`
--

INSERT INTO `topping` (`idTopping`, `nameTopping`, `priceTopping`, `quantTopping`) VALUES
(1, 'Trân châu trắng', 5000, 100),
(2, 'Trân châu đen', 5000, 100),
(3, 'Thạch trái cây', 3000, 100),
(4, 'Bánh flan', 8000, 100);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietquyen`
--
ALTER TABLE `chitietquyen`
  ADD PRIMARY KEY (`idQuyen`,`maCn`,`HanhDong`);

--
-- Chỉ mục cho bảng `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`maCn`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`idHd`);

--
-- Chỉ mục cho bảng `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`MAKH`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`idtypeSp`);

--
-- Chỉ mục cho bảng `nguyenlieu`
--
ALTER TABLE `nguyenlieu`
  ADD PRIMARY KEY (`idNL`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`idNcc`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`idNv`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`idPN`);

--
-- Chỉ mục cho bảng `rankmember`
--
ALTER TABLE `rankmember`
  ADD PRIMARY KEY (`idRank`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`idSp`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`idAccount`);

--
-- Chỉ mục cho bảng `topping`
--
ALTER TABLE `topping`
  ADD PRIMARY KEY (`idTopping`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietquyen`
--
ALTER TABLE `chitietquyen`
  MODIFY `idQuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `chucnang`
--
ALTER TABLE `chucnang`
  MODIFY `maCn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `idHd` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khach_hang`
--
ALTER TABLE `khach_hang`
  MODIFY `MAKH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `idtypeSp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `nguyenlieu`
--
ALTER TABLE `nguyenlieu`
  MODIFY `idNL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `idNcc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `idNv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `idPN` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `rankmember`
--
ALTER TABLE `rankmember`
  MODIFY `idRank` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `idSp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `idAccount` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `topping`
--
ALTER TABLE `topping`
  MODIFY `idTopping` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
