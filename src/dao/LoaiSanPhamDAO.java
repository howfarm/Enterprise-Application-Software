package dao;


import helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LoaiSanPham;

public class LoaiSanPhamDAO {

    public void insert(LoaiSanPham model) {
        String sql = "INSERT INTO LoaiSanPham(MaK,  SoLuong) VALUES(?, ?, ?)";
        JdbcHelper.executeUpdate(sql, model.getMaK());
    }

    public void update(LoaiSanPham model) {
        String sql = "UPDATE LoaiSanPham SET MaK=?, MaKH=?, SoLuong=? WHERE MaLSP=?";
        JdbcHelper.executeUpdate(sql, model.getMaK(), model.getMaLSP());
    }

    public void delete(Integer MaLSP) {
        String sql = "DELETE FROM LoaiSanPham WHERE MaLSP=?";
        JdbcHelper.executeUpdate(sql, MaLSP);
    }

    public List<LoaiSanPham> select() {
        String sql = "SELECT * FROM LoaiSanPham";
        return select(sql);
    }

    public LoaiSanPham findById(Integer mahv) {
        String sql = "SELECT * FROM LoaiSanPham WHERE MaLSP=?";
        List<LoaiSanPham> list = select(sql, mahv);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<LoaiSanPham> select(String sql, Object... args) {
        List<LoaiSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    LoaiSanPham model = readFromResultSet(rs);
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

    private LoaiSanPham readFromResultSet(ResultSet rs) throws SQLException {
        LoaiSanPham model = new LoaiSanPham();
        model.setMaLSP(rs.getInt("MaLSP"));
        model.setMaK(rs.getInt("KH"));
        return model;
    }
}
