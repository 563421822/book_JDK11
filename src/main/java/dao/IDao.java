package dao;

import entity.BookEntity;
import utils.Page;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDao {
    ArrayList<BookEntity> queryAll(Page page) throws SQLException;

    int add(BookEntity entity) throws SQLException;


    BookEntity queryById(String id) throws SQLException;

    int changeStatus(String id) throws SQLException;

    int updateById(BookEntity entity) throws SQLException;

    int queryCount() throws SQLException;

}
