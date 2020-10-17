package dao.impl;

import dao.IDao;
import entity.BookEntity;
import utils.DBUtils;
import utils.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoImpl implements IDao {
    /**
     * 展示所有数据
     *
     * @param page
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<BookEntity> queryAll(Page page) throws SQLException {
        String sql = "SELECT id, bookName, price, author FROM t_books WHERE flag=1 LIMIT ?,?";
        ResultSet query = DBUtils.query(sql, page.getStart(), page.getPageSize());
        ArrayList<BookEntity> list = new ArrayList<>();
        while (query.next()) {
            BookEntity entity = saveDataToList(query);
            list.add(entity);
        }
        return list;
    }

    /**
     * 将数据存在对象,返回对象
     *
     * @param query
     * @return
     * @throws SQLException
     */
    private BookEntity saveDataToList(ResultSet query) throws SQLException {
        BookEntity entity = new BookEntity();
        entity.setId(query.getInt("id"));
        entity.setBookName(query.getString("bookName"));
        entity.setPrice(query.getFloat("price"));
        entity.setAuthor(query.getString("author"));
        return entity;
    }

    /**
     * 添加记录的功能
     *
     * @param entity
     * @return
     * @throws SQLException
     */
    @Override
    public int add(BookEntity entity) throws SQLException {
        String sql = "INSERT INTO t_books (bookName, price, author) VALUES (?,?,?)";
        return DBUtils.update(sql, entity.getBookName(), entity.getPrice(), entity.getAuthor());
    }

    /**
     * 靠id查询展示数据的方法
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public BookEntity queryById(String id) throws SQLException {
        String sql = "SELECT id, bookName, price, author FROM t_books WHERE id =?";
        ResultSet query = DBUtils.query(sql, id);
        if (query.next()) {
            return saveDataToList(query);
        }
        return null;
    }

    /**
     * 改变记录的状态的方法
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int changeStatus(String id) throws SQLException {
        String sql = "UPDATE t_books SET flag = 0 WHERE id =?";
        return DBUtils.update(sql, id);

    }

    /**
     * 更新记录的方法
     *
     * @param entity
     * @return
     * @throws SQLException
     */
    @Override
    public int updateById(BookEntity entity) throws SQLException {
        String sql = "UPDATE t_books SET bookName =?, price =?, author =? WHERE id =?";
        return DBUtils.update(sql, entity.getBookName(), entity.getPrice(), entity.getAuthor(), entity.getId());
    }

    /**
     * 查询总数的方法
     *
     * @return
     * @throws SQLException
     */
    @Override
    public int queryCount() throws SQLException {
        String sql = "SELECT count(id) FROM t_books WHERE flag = 1";
        ResultSet query = DBUtils.query(sql);
        if (query.next()) {
            return query.getInt("count(id)");
        }
        return 0;
    }
}
