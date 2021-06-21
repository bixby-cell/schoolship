package com.school.schoolship.service;

import com.school.schoolship.exception.MallException;
import com.school.schoolship.model.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author Bixby
 * @create 2021-06-18
 */
public interface UserService {

    void register(String userName, String password) throws MallException;

    User login(String userName, String password) throws MallException;

    void updateUserInformation(User user) throws MallException;

    boolean checkAdminRole(User user);
}
