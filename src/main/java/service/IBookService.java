package service;

import entity.BookEntity;
import utils.Page;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBookService {
    ArrayList<BookEntity> queryAll(Page page) throws SQLException;

    int add(BookEntity entity) throws SQLException;

    BookEntity queryDetail(String id) throws SQLException;

    int delete(String id) throws SQLException;

    int updateById(BookEntity bookEntity) throws SQLException;
}
