package dao;

import helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Kho;

public class KhoDAO {

    public void insert(Kho model) {
        String sql = "INSERT INTO Kho (MaSP, GiaTien, SoLuong, NgayXuat, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql, model.getMaSP(), model.getGiaTien(), model.getSoLuong(), model.getNgayXuat(), model.getGhiChu(), model.getMaNV());
    }

    public void update(Kho model) {
        String sql = "UPDATE Kho SET MaSP=?, GiaTien=?, SoLuong=?, NgayXuat=?, GhiChu=?, MaNV=? WHERE MaK=?";
        JdbcHelper.executeUpdate(sql, model.getMaSP(), model.getGiaTien(), model.getSoLuong(), model.getNgayXuat(), model.getGhiChu(), model.getMaNV(), model.getMaK());
    }

    public void delete(Integer MaK) {
        String sql = "DELETE FROM Kho WHERE MaK=?";
        JdbcHelper.executeUpdate(sql, MaK);
    }

    public List<Kho> select() {
        String sql = "SELECT * FROM Kho";
        return select(sql);
    }

    public Kho findById(Integer makh) {
        String sql = "SELECT * FROM Kho WHERE MaK=?";
        List<Kho> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<Kho> select(String sql, Object... args) {
        List<Kho> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Kho model = readFromResultSet(rs);
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

    private Kho readFromResultSet(ResultSet rs) throws SQLException {
        Kho model = new Kho();
        model.setMaK(rs.getInt("MaK"));
        model.setGiaTien(rs.getDouble("GiaTien"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setNgayXuat(rs.getDate("NgayXuat"));
        model.setGhiChu(rs.getString("GhiChu"));
        model.setMaNV(rs.getString("MaNV"));
        model.setNgayNhap(rs.getDate("NgayNhap"));
        model.setMaSP(rs.getString("MaSP"));
        return model;
    }
}
