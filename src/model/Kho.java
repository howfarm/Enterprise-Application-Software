package model;

import helper.DateHelper;
import java.util.Date;

public class Kho {

    private int maK;
    private String maSP;
    private double giaTien;
    private int soLuong;
    private Date ngayXuat;
    private String ghiChu;
    private String maNV;
    private Date ngayNhap = DateHelper.now();

    public Kho() {
    }

    public Kho(int maK, String maSP, double giaTien, int soLuong, Date ngayXuat, String ghiChu, String maNV) {
        this.maK = maK;
        this.maSP = maSP;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.ngayXuat = ngayXuat;
        this.ghiChu = ghiChu;
        this.maNV = maNV;
    }

    @Override
    public String toString() {
        return "Kho{" + "maSP=" + maSP + ", ngayXuat=" + ngayXuat + '}';
    }

    public int getMaK() {
        return maK;
    }

    public void setMaK(int maK) {
        this.maK = maK;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

   
}
