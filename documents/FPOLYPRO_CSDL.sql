CREATE DATABASE QLHangLuuNiem
GO
USE QLHangLuuNiem
GO
drop table NhanVien
CREATE TABLE NhanVien(
	MaNV nvarchar(50) NOT NULL,
	MatKhau nvarchar(50) NOT NULL,
	HoTen nvarchar(50) NOT NULL,
	VaiTro bit NOT NULL DEFAULT 0,
	PRIMARY KEY(MaNV)
)
GO
CREATE TABLE SanPham(
	MaSP nchar(5) NOT NULL,
	TenSP nvarchar(50) NOT NULL,
	GiaTien float NOT NULL DEFAULT 0,
	SoLuong int NOT NULL DEFAULT 30,
	Hinh nvarchar(50) NOT NULL DEFAULT 'chuyen-de.png',
	MoTa nvarchar(255) NOT NULL,
	PRIMARY KEY(MaSP),
	UNIQUE(TenSP),
	CHECK(GiaTien >= 0 AND SoLuong > 0)
)
GO
Drop table KhachHang
CREATE TABLE KhachHang(
	MaKH nchar(7) NOT NULL,
	HoTen nvarchar(50) NOT NULL,
	NgaySinh date NOT NULL,
	GioiTinh bit NOT NULL DEFAULT 0,
	DienThoai nvarchar(50) NOT NULL,
	Email nvarchar(50) NOT NULL,
	GhiChu nvarchar(max) NULL,
	MaNV nvarchar(50) NOT NULL,
	NgayMua date NOT NULL DEFAULT getdate(),
	PRIMARY KEY(MaKH),
	FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON UPDATE CASCADE
)
GO
CREATE TABLE Kho(
	MaK int IDENTITY(1,1) NOT NULL,
	MaSP nchar(5) NOT NULL,
	GiaTien float NOT NULL DEFAULT 0,
	SoLuong int NOT NULL DEFAULT 0,
	NgayXuat date NOT NULL,
	GhiChu nvarchar(50) NULL,
	MaNV nvarchar(50) NOT NULL,
	NgayNhap date NOT NULL DEFAULT getdate(),
	PRIMARY KEY(MaK),
	CHECK(GiaTien >= 0 AND SoLuong > 0),
	FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP) ON UPDATE CASCADE,
	FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON UPDATE CASCADE
)
GO
CREATE TABLE LoaiSanPham(
	MaLSP int IDENTITY(1,1) NOT NULL,
	MaK int NOT NULL,
	MaKH nchar(7) NOT NULL,
	SoLuong float NOT NULL,
	PRIMARY KEY(MaLSP),
	UNIQUE(MaK, MaKH),
	FOREIGN KEY (MaK) REFERENCES Kho(MaK) ON DELETE CASCADE,
	FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH) ON UPDATE CASCADE
)
--viết thủ tục lưu trữ
--Bảng nhân viên
--insert
CREATE PROC	sp_insert_nhanvien
	@manv nvarchar(50),
	@matKhau nvarchar(50),
	@hoTen nvarchar (50),
	@vaiTro bit
AS
	insert into NhanVien values (@manv, @matKhau, @hoTen, @vaiTro)
	
--update
CREATE PROC	sp_update_nhanvien
	@manv nvarchar(50),
	@matKhau nvarchar(50),
	@hoTen nvarchar (50),
	@vaiTro bit
AS
	update nhanVien set
		hoTen= @hoTen,
		matKhau= @matKhau,
		vaiTro= @vaiTro
	where manv= @manv
--delete 
CREATE PROC sp_delete_nhanvien
	@manv nvarchar(50)
AS
Delete from nhanvien where manv like @manv
--getALL
CREATE proc sp_getAll_nhanvien
as
	select * from nhanVien
--search by mã nhân viên
CREATE PROC sp_findbyid_nhanvien
	@manv nvarchar(50)
as select*from nhanVien where manv like @manv
--search by tên nhân viên
CREATE PROC sp_findbyTen_nhanvien
	@hoTen nvarchar(50)
As 
	Select*from nhanVien where hoTen like @hoTen
	
--Bảng Chuyên đề
--insert
CREATE PROC	sp_insert_sanpham
	@maSP nchar(5),
	@tenSP nvarchar(50),
	@giaTien float,
	@soLuong int,
	@hinh nvarchar(50),
	@moTa nvarchar(255)
AS
	insert into SanPham values (@maSP,@tenSP,@giaTien,@soLuong,@hinh,@moTa)
	
--update
CREATE PROC	sp_update_sanpham
	@maSP nchar(5),
	@tenSP nvarchar(50),
	@giaTien float,
	@soLuong int,
	@hinh nvarchar(50),
	@moTa nvarchar(255)
AS
	update SanPham set
		TenSP= @tenSP,
		GiaTien= @giaTien,
		SoLuong=@soLuong,
		Hinh=@hinh,
		MoTa=@moTa
	where MaSP= @maSP
--delete 
CREATE PROC sp_delete_sanpham
	@masp nvarchar(5)
AS
Delete from SanPham where MaSP like @masp
--getALL
CREATE proc sp_getAll_sanpham
as
	select * from SanPham
--search by mã chuyên đề 
CREATE PROC sp_findbyid_sanpham
	@masp nvarchar(5)
as select * from SanPham where MaSP like @masp
--search by tên chuyên đề 
CREATE PROC sp_findbyTen_sanpham
	@tensp nvarchar(50)
As 
	Select*from SanPham where TenSP like @tensp
	
--Bảng Người học
--insert
CREATE PROC	sp_insert_khachhang
	@MaKH nchar(7),
	@HoTen nvarchar(50),
	@NgaySinh date,
	@GioiTinh bit,
	@DienThoai nvarchar(50),
	@Email nvarchar(50),
	@GhiChu nvarchar(max),
	@MaNV nvarchar(50),
	@NgayMua date
AS
	insert into KhachHang values (@MaKH,@HoTen,@NgaySinh,@GioiTinh,@DienThoai,@Email,@GhiChu,@MaNV,@NgayMua)
	
--update
CREATE PROC	sp_update_khachhang
	@MaKH nchar(7),
	@HoTen nvarchar(50),
	@NgaySinh date,
	@GioiTinh bit,
	@DienThoai nvarchar(50),
	@Email nvarchar(50),
	@GhiChu nvarchar(max),
	@MaNV nvarchar(50),
	@NgayMua date
AS
	update KhachHang set
		MaKH=@MaKH,
		HoTen=@HoTen,
		NgaySinh=@NgaySinh,
		GioiTinh=@GioiTinh,
		DienThoai=@DienThoai,
		Email=@Email,
		GhiChu=@GhiChu,
		MaNV=@MaNV,
		NgayMua=@NgayMua
	where MaKH = @MaKH
--delete 
CREATE PROC sp_delete_khachhang
	@makh nchar(7)
AS
Delete from KhachHang where MaKH like @makh
--getALL
CREATE proc sp_getAll_khachhang
as
	select * from KhachHang
--search by mã người học	
CREATE PROC sp_findbyid_khachhang
	@makh nchar(7)
as select * from KhachHang where MaKH like @makh
--search by tên người học 
CREATE PROC sp_findbyTen_khachhang
	@hoTen nvarchar(50)
As 
	Select*from KhachHang where HoTen like @hoTen	
	
--Bảng Học viên
--insert
CREATE PROC	sp_insert_loaisanpham
	@MaLSP int,
	@MaK nchar(7),
	@SoLuong float
AS
	insert into LoaiSanPham values (@MaLSP,@MaK,@SoLuong)
	
--update
CREATE PROC	sp_update_loaisanpham
	@MaLSP int,
	@MaK nchar(7),
	@SoLuong float
AS
	update LoaiSanPham set
		MaK = @MaK,
		SoLuong = @SoLuong
	where MaK = @MaK
--delete 
CREATE PROC sp_delete_loaisanpham
	@maKH nchar(7)
AS
Delete from LoaiSanPham where MaKH like @maKH
--getALL
CREATE proc sp_getAll_loaisanpham
as
	select * from LoaiSanPham
--search by mã học viên 
CREATE PROC sp_findbyid_loaisanpham
	@maLSP nchar(7)
as select * from LoaiSanPham where MaLSP like @maLSP

--search by mã người học	
CREATE PROC sp_findbyid_loaisanphamLSP
	@maKH nchar(7)
as select * from LoaiSanPham where @maKH like @maKH


--Bảng Khóa học	
--insert
CREATE PROC	sp_insert_kho
	@MaSP nchar(5),
	@GiaTien float,
	@SoLuong int,
	@NgayXuat date,
	@GhiChu nvarchar(50),
	@MaNV nvarchar(50),
	@NgayNhap date
AS
	insert into Kho values (@MaSP,@GiaTien,@SoLuong,@NgayXuat,@GhiChu,@MaNV,@NgayNhap)
	
--update
CREATE PROC	sp_update_kho
	@MaK int,
	@MaSP nchar(5),
	@GiaTien float,
	@SoLuong int,
	@NgayXuat date,
	@GhiChu nvarchar(50),
	@MaNV nvarchar(50),
	@NgayNhap date
AS
	update Kho set
		MaSP=@MaSP,
		GiaTien=@GiaTien,
		SoLuong=@SoLuong,
		NgayXuat=@NgayXuat,
		GhiChu=@GhiChu,
		MaNV=@MaNV,
		NgayNhap=@NgayNhap
	where MaK = @MaK
--delete 
CREATE PROC sp_delete_kho
	@mak int
AS
Delete from Kho where MaK like @mak
--getALL
CREATE proc sp_getAll_kho
as
	select * from Kho
--search by mã khóa học 
CREATE PROC sp_findbyid_kho
	@mak int
as select * from Kho where MaK like @mak
--search by mã chuyên đề	
CREATE PROC sp_findbyMa_sanpham
	@MaSanPham nvarchar(50)
As 
	Select*from Kho where MaSP like @MaSanPham
	
---Các thủ tục thống kế
--Bảng điểm theo khóa học
CREATE PROC sp_bangthongkeByKho
	@maK nchar(7)
AS
	Select C.maKH, hoTen, SoLuong from
		Kho A JOIN LoaiSanPham B on A.MaK= B.MaK
		JOIN KhachHang C on B.MaKH = C.MaKH
	where A.MaK = @maK
--Tổng hợp điểm theo từng chuyên đề
CREATE PROC sp_TongHopTheoSanPham
AS
	Select A.MaSP, A.TenSP, AVG(SoLuong) As TongSL
	from SanPham A join Kho B on A.MaSP = B.MaSP
	Join LoaiSanPham C on B.MaK = C.MaK
	Group by A.MaSP,TenSP
--Số người học từng năm
CREATE PROC sp_ThongKeKhachHang
	AS 
BEGIN
	SELECT
		YEAR(NgayMua) Nam,
		COUNT(*) SoLuong,
		MIN(NgayMua) DauTien,
		MAX(NgayMua) CuoiCung
		FROM KhachHang
		GROUP BY YEAR(NgayMua)
END
--Doanh thu theo chuyên đề
CREATE PROC sp_ThongKeDoanhThu(@Year INT)
	AS 
BEGIN
	SELECT
		TenSP SanPham,
		COUNT(DISTINCT k.MaK) SoK,
		COUNT(lsp.MaLSP) SoLSP,
		SUM(k.GiaTien) DoanhThu,
		MIN(k.GiaTien) ThapNhat,
		MAX(k.GiaTien) CaoNhat,
		AVG(k.GiaTien) TrungBinh
	FROM Kho k
	JOIN LoaiSanPham hv ON k.MaK=lsp.MaLSP
	JOIN SanPham sp ON sp.MaSP=k.MaSP
	WHERE YEAR(NgayXuat) = @Year
	GROUP BY TenSP
END
--Học viên theo chuyên đề
CREATE PROC sp_ThongKeSoLuong
	AS 
BEGIN
	SELECT
		TenSP SanPham,
		COUNT(MaLSP) SoLoaiSanPham,
		MIN(SoLuong) ThapNhat,
		MAX(SoLuong) CaoNhat,
		AVG(SoLuong) TrungBinh
	FROM Kho k
	JOIN LoaiSanPham lsp ON k.MaK=lsp.MaK
	JOIN SanPham sp ON sp.MaSP=k.MaSP
	GROUP BY TenSP
END
--Bảng điểm
CREATE PROC sp_SoLuong(@MaK INT)
	AS 
BEGIN
	SELECT
		kh.MaKH,
		kh.HoTen,
		kh.SoLuong
	FROM LoaiSanPham lsp
	JOIN KhachHang kh ON kh.MaKH=lsp.MaKH
	WHERE lsp.MaK = @MaK
	ORDER BY lsp.SoLuong DESC
END

