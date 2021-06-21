package com.school.schoolship.service.impl;

import com.school.schoolship.exception.MallException;
import com.school.schoolship.exception.MallExceptionEnum;
import com.school.schoolship.model.dao.UserMapper;
import com.school.schoolship.model.pojo.User;
import com.school.schoolship.service.UserService;
import com.school.schoolship.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.BufferPoolMXBean;
import java.security.NoSuchAlgorithmException;

/**
 * @author Bixby
 * @create 2021-06-18
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void register(String userName, String password) throws MallException {
        User result = userMapper.selectByName(userName);
        if (result != null){
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }
        User user = new User();
        user.setUsername(userName);
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int count = userMapper.insertSelective(user);
        if (count == 0){
            throw new MallException(MallExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public User login(String userName, String password) throws MallException{
        String md5Password = null;
        try {
            md5Password = MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userMapper.selectLogin(userName,md5Password);
        if (user == null){
            throw new MallException(MallExceptionEnum.WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void updateUserInformation(User user) throws MallException {
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 1){
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public boolean checkAdminRole(User user) {
        return user.getRole().equals(2);
    }

}
