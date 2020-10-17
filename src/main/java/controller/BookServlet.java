package controller;

import entity.BookEntity;
import service.IBookService;
import service.impl.BookServiceImpl;
import utils.Page;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet("/BookServlet")
public class BookServlet extends FatherServlet {
    /**
     * 添加书籍字段的方法
     *
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    private String doAddField(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        运用了父类的反射方法
        BookEntity entity = requestMap(BookEntity.class, req);
        IBookService service = new BookServiceImpl();
        int result = service.add(entity);
        if (result > 0) {
            return "forward:BookServlet?method=doShowAll&curr=1";
        }
        return "";
    }

    /**
     * 删除的Servlet
     *
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */

    private String doChangeStatus(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        IBookService service = new BookServiceImpl();
        int result = service.delete(id);
        if (result > 0) {
            return "redirect:BookServlet?method=doShowAll&curr=1";
        }
        return "";
    }

    /**
     * 编辑提交的Servlet
     *
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    private String doEdit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        运用了父类的反射方法
        BookEntity entity = requestMap(BookEntity.class, req);
        System.out.println(entity);
        IBookService service = new BookServiceImpl();
        int result = service.updateById(entity);
        System.out.println(entity+"%%%%%%%%%%%");
        if (result > 0) {
            return "redirect:BookServlet?method=doShowAll&curr=1";
        }
        return "";
    }

    /**
     * 点击编辑后展示详情页面的servlet
     *
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    private String doShowDetail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        IBookService service = new BookServiceImpl();
        BookEntity entity = service.queryDetail(id);
        req.setAttribute("detail", entity);
        return "forward:add.jsp";
    }

    /**
     * 展示所有信息的servlet
     *
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    private String doShowAll(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int curr = Integer.parseInt(req.getParameter("curr"));
//        设置分页
        Page page = new Page();
        page.setCurrentPage(curr);
        IBookService service = new BookServiceImpl();
        ArrayList<BookEntity> list = service.queryAll(page);
        req.setAttribute("list", list);
        req.setAttribute("page", page);
        return "forward:index.jsp";
    }
}
