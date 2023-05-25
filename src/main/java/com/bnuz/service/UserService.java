package main.java.com.bnuz.service;

import main.java.com.bnuz.pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param ruser
     */
    public void registUser(User ruser);
    /**
     * 登录
     * @param user
     * @return 如果返回Null，说明登录失败，返回有值，是登录成功
     */
    public User login(User user);
    /**
     * 检查 用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUserName(String username);
}
