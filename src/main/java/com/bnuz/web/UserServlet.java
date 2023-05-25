package main.java.com.bnuz.web;

import com.google.gson.Gson;
import main.java.com.bnuz.pojo.User;
import main.java.com.bnuz.service.UserService;
import main.java.com.bnuz.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = "/userServlet")
public class UserServlet extends BeanServlet{
    //实例化对象
    private UserService userService = new UserServiceImpl();

    /**
     * 处理登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        //获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用userService.login()登录处理业务
        User loginUser = userService.login(new User(null,username,password,null));
        //如果等于null,说明登录失败！
        if(loginUser == null){
            //把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            //跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            //登录成功
            // 保存用户登录的信息到session域中
            req.getSession().setAttribute("user",loginUser);
            //跳到成功页面login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
//        if ("admin".equals(username) && "admin".equals(password)) {
//            // 登录成功
//            Cookie cookie = new Cookie("username", username);
//            Cookie cookie1 = new Cookie("password", password);
//            // 当前Cookie一周内有效
//            cookie.setMaxAge(60 * 60 *24 * 7);
//            resp.addCookie(cookie);
//            resp.addCookie(cookie1);
//            System.out.println("登录成功");
//            req.getSession().setAttribute("user",loginUser);
//            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
//        } else {
//            // 登录失败
//            //把错误信息，和回显的表单项信息，保存到Request域中
//            req.setAttribute("msg","用户名或密码错误！");
//            req.setAttribute("username",username);
//            System.out.println("登录失败");
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
//        }
    }

    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
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

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        // 1.销毁Session 中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
        // 2.重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }
    /**
     * ajax验证用户名是否可用
     * */
    protected void ajaxExistsUsername(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        //获取请求的参数username
        String username = req.getParameter("username");
        //调用userService.existsUsername()
        boolean existsUsername = userService.existsUserName(username);
        //把返回的结果封装成map对象
        Map<String,Object> resultMap = new HashMap<>();
        //放进map对象里
        resultMap.put("existsusername",existsUsername);
        //创建一个gson对象
        Gson gson = new Gson();
        //map对象转换为string对象
        String jsonString = gson.toJson(resultMap);
        //返回给客户端
        resp.getWriter().write(jsonString);
    }
}
