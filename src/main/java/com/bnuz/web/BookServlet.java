package main.java.com.bnuz.web;

import main.java.com.bnuz.pojo.Book;
import main.java.com.bnuz.pojo.Page;
import main.java.com.bnuz.service.BookService;
import main.java.com.bnuz.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "BookServlet",urlPatterns = "/manager/bookServlet")
public class BookServlet extends BeanServlet {
    BookService bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /**BookServlet程序中添加list方法*/
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //1.通过BookService查询全部图书

        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到request域中
        req.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**BookServlet程序中添加add方法*/
    protected void add(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        //1.获取请求的参数==》封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3.跳到图书列表页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**BookServlet程序中添加delete方法*/
    protected void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        //1.获取请求的参数id,图书编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        // 2.调用BookService.deleteBookById()删除图书
        bookService.deleteBookById(id);
        //3.重定向回到图书列表管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**BookServlet程序中添加getBook方法*/
    protected void getBook(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        //1.获取请求的参数id,图书编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        // 2.调用BookService.queryBookId查询图书
        Book book = bookService.queryBookById(id);
        //3.保存图书到Request域中
        req.setAttribute("book",book);
        //4.请求转发到pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    /**BookServlet程序中添加update方法*/
    protected void update(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        //1.获取请求的参数==封装成为book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        // 2.调用BookService.update()修改图书
        bookService.updateBook(book);
        //3.重定向回到图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        //1.获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo,pageSize):Page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
