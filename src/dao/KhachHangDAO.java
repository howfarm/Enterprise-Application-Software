package dao;


import helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;

public class KhachHangDAO {

    public void insert(KhachHang model) {
        String sql = "INSERT INTO KhachHang (MaKH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql, model.getMaKH(), model.getHoTen(), model.getNgaySinh(), model.isGioiTinh(), model.getDienThoai(), model.getEmail(), model.getGhiChu(), model.getMaNV());
    }

    public void update(KhachHang model) {
        String sql = "UPDATE KhachHang SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=? WHERE MaKH=?";
        JdbcHelper.executeUpdate(sql, model.getHoTen(), model.getNgaySinh(), model.isGioiTinh(), model.getDienThoai(), model.getEmail(), model.getGhiChu(), model.getMaNV(), model.getMaKH());
    }

    public void delete(String id) {
        String sql = "DELETE FROM KhachHang WHERE MaKH=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<KhachHang> select() {
        String sql = "SELECT * FROM KhachHang";
        return select(sql);
    }

    public List<KhachHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KhachHang WHERE HoTen LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public List<KhachHang> selectByCourse(Integer makh) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH NOT IN (SELECT MaKH FROM HocVien WHERE MaKH=?)";
        return select(sql, makh);
    }

    public KhachHang findById(String manh) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH=?";
        List<KhachHang> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<KhachHang> select(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    KhachHang model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private KhachHang readFromResultSet(ResultSet rs) throws SQLException {
        KhachHang model = new KhachHang();
        model.setMaKH(rs.getString("MaKH"));
        model.setHoTen(rs.getString("HoTen"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setDienThoai(rs.getString("DienThoai"));
        model.setEmail(rs.getString("Email"));
        model.setGhiChu(rs.getString("GhiChu"));
        model.setMaNV(rs.getString("MaNV"));
        model.setNgayMua(rs.getDate("NgayMua"));
        return model;
    }
}
