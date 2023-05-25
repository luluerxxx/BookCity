package main.java.com.bnuz.service.impl;

import main.java.com.bnuz.dao.UserDao;
import main.java.com.bnuz.dao.impl.UserDaoImpl;
import main.java.com.bnuz.pojo.User;
import main.java.com.bnuz.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUserName(String username) {
        if(userDao.queryUserByUsername(username) == null){
            //等于Null,说明没查到，没查到表示可用
            return false;
        }
        return true;
    }
}
