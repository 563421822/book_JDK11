package service.impl;

import dao.IDao;
import dao.impl.DaoImpl;
import entity.BookEntity;
import service.IBookService;
import utils.Page;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookServiceImpl implements IBookService {
    //    成员变量
    final IDao dao = new DaoImpl();

    @Override
    public ArrayList<BookEntity> queryAll(Page page) throws SQLException {
        page.setPageSize(5);
//        从数据库中查询总数量
        page.setTotalCount(dao.queryCount());
//        计算能显示的最大页数
        page.setTotalPage((int) Math.ceil((double) page.getTotalCount() / page.getPageSize()));
//计算起始显示记录数
        page.setStart((page.getCurrentPage() - 1) * page.getPageSize());
        return dao.queryAll(page);
    }

    @Override
    public int add(BookEntity entity) throws SQLException {
        return dao.add(entity);
    }

    @Override
    public BookEntity queryDetail(String id) throws SQLException {
        return dao.queryById(id);
    }

    @Override
    public int delete(String id) throws SQLException {
        return dao.changeStatus(id);
    }

    @Override
    public int updateById(BookEntity entity) throws SQLException {
        return dao.updateById(entity);
    }
}
