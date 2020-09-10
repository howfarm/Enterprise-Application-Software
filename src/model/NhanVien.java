package model;

public class NhanVien {

    private String maNV;
    private String matKhau;
    private String hoTen;
    private boolean vaiTro = false;

    public NhanVien() {
    }

    public NhanVien(String maNV, String matKhau, String hoTen) {
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
    }

 @Override
    public String toString() {
        return this.hoTen;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }    
}