package main.java.com.bnuz.web;

import main.java.com.bnuz.pojo.User;
import main.java.com.bnuz.service.UserService;
import main.java.com.bnuz.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "RegistServlet",urlPatterns = "/registServlet")
public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除Session中的验证码
        //req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String code = req.getParameter("code");
        // 获取用户名
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        System.out.println("yanchengma :"+code);

        if (token != null && token.equalsIgnoreCase(code)) {
            //3.检查 用户名是否可用
            if(userService.existsUserName(username)){
                System.out.println("用户名[" + username + "]已存在！");
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //可用，调用Service保存到数据库
                 userService.registUser(new User(null,username,password,email));
                //跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        } else {
            System.out.println("请不要重复提交表单");
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("email = " + email);
        System.out.println("验证码 code = " + code);
        //2.检查:验证码是否正确 （写死，要求验证码为:abcde)
        if("abcde".equalsIgnoreCase(code)){
            //3.检查 用户名是否可用
            if(userService.existsUserName(username)){
                System.out.println("用户名[" + username + "]已存在！");
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);

            }else {
                //可用，调用Service保存到数据库
                userService.registUser(new User(null,username,password,email));
                //跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }
}
