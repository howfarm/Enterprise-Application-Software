package dao;

import helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SanPham;

public class SanPhamDAO {

    public void insert(SanPham model) {
        String sql = "INSERT INTO SanPham (MaSP, TenSP, GiaTien, SoLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql, model.getMaSP(), model.getTenSP(), model.getGiaTien(), model.getSoLuong(), model.getHinh(), model.getMoTa());
    }

    public void update(SanPham model) {
        String sql = "UPDATE SanPham SET TenSP=?, GiaTien=?, SoLuong=?, Hinh=?, MoTa=? WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql, model.getTenSP(), model.getGiaTien(), model.getSoLuong(), model.getHinh(), model.getMoTa(), model.getMaSP());
    }

    public void delete(String MaSP) {
        String sql = "DELETE FROM SanPham WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql, MaSP);
    }

    public List<SanPham> select() {
        String sql = "SELECT * FROM SanPham";
        return select(sql);
    }

    public SanPham findById(String MaSP) {
        String sql = "SELECT * FROM SanPham WHERE MaSP=?";
        List<SanPham> list = select(sql, MaSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<SanPham> select(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    SanPham model = readFromResultSet(rs);
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

    private SanPham readFromResultSet(ResultSet rs) throws SQLException {
        SanPham model = new SanPham();
        model.setMaSP(rs.getString("MaSP"));
        model.setHinh(rs.getString("Hinh"));
        model.setGiaTien(rs.getDouble("GiaTien"));
        model.setMoTa(rs.getString("MoTa"));
        model.setTenSP(rs.getString("TenSP"));
        model.setSoLuong(rs.getInt("SoLuong"));
        return model;
    }
}
