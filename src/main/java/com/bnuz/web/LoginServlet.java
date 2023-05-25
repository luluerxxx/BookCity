package main.java.com.bnuz.web;

import main.java.com.bnuz.pojo.User;
import main.java.com.bnuz.service.UserService;
import main.java.com.bnuz.service.impl.UserServiceImpl;
import main.java.com.bnuz.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "LoginServlet",urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet{
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用userService.login()登录处理业务
        User loginUser = userService.login(new User(null,username,password,null));
        //如果等于null,说明登录失败！
//        if(loginUser == null){
//            //把错误信息，和回显的表单项信息，保存到Request域中
//            req.setAttribute("msg","用户名或密码错误！");
//            req.setAttribute("username",username);
//            //跳回登录页面
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
//        }else{
//            //登录成功
//            //跳到成功页面login_success.jsp
//            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
//        }
        if ("admin".equals(username) && "admin".equals(password)) {
            // 登录成功
            Cookie cookie = new Cookie("username", username);
            Cookie cookie1 = new Cookie("password", password);
            // 当前Cookie一周内有效
            cookie.setMaxAge(60 * 60 *24 * 7);
            resp.addCookie(cookie);
            resp.addCookie(cookie1);
            System.out.println("登录成功");
            // 保存用户登录的信息到session域中
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        } else {
            // 登录失败
            //把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            System.out.println("登录失败");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }
    protected void createCookie(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        // 1.创建Cookie对象
        Cookie cookie = new Cookie("key4","value4");
        // 2.通知客户端保存cookie
        resp.addCookie(cookie);
        // 1.创建Cookie对象
        Cookie cookie1 = new Cookie("key5","value5");
        // 2.通知客户端保存cookie
        resp.addCookie(cookie);

        resp.getWriter().write("cookie创建成功");
    }
    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            // getName方法返回Cookie的key（名）
            // getValue方法返回Cookie的value（值）
            resp.getWriter().write("Cookie["+cookie.getName()+"="+cookie.getValue()+"]<br/>");
        }

        Cookie iWantCookie = CookieUtils.findCookies("key1", cookies);
        if (iWantCookie != null) {
            resp.getWriter().write("找到了需要的Cookie");
        }
    }
}
