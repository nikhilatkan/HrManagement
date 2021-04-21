package com.hrmanagement.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrmanagement.dao.HrDao;
import com.hrmanagement.entities.HrEntity;

@Service
public class UserService {

    @Autowired
    private HrDao dao;

    public void addUser(String username, String name, String password) {

        String encryptedPass = getEncodedString(username + password);
        HrEntity user = new HrEntity();
        user.setName(name);
        user.setPassword(encryptedPass);
        user.setUsername(username);
        dao.addUser(user);
    }

    private String getEncodedString(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public boolean authenticator(String username, String password) {

        HrEntity user = dao.getUser(username);
        String encryptedPass = user.getPassword();
        String decrpytedPass = getDecodedString(encryptedPass);
        if (user.getUsername().equals(username) && decrpytedPass.equals(username + password)) {
            return true;
        } else {
            return false;
        }

    }

    private String getDecodedString(String password) {

        return new String(Base64.getMimeDecoder().decode(password));
    }

}
