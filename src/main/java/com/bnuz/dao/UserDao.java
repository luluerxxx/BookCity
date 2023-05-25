package main.java.com.bnuz.dao;

import main.java.com.bnuz.pojo.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * return 如果返回Null ,水命没有这个用户，反之亦然
     */
    public User queryUserByUsername(String username);
    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * return 如果返回Null ,水命没有这个用户，反之亦然
     */
    public User queryUserByUsernameAndPassword(String username,String password);
    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
     */
    public int saveUser(User user);
}
