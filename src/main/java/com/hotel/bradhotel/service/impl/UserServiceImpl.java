package com.hotel.bradhotel.service.impl;

import com.hotel.bradhotel.dao.UserDao;
import com.hotel.bradhotel.dto.UserLoginRequest;
import com.hotel.bradhotel.dto.UserRegisterRequest;
import com.hotel.bradhotel.model.User;
import com.hotel.bradhotel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
      //檢查註冊email
      User user =  userDao.getUserByEmail(userRegisterRequest.getEmail());

      if(user != null){
          log.warn("該email {} 已經被註冊", userRegisterRequest.getEmail());
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }

      //創建註冊的帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {

        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        if (user == null){
            log.warn("該email {} 未被註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword().equals(userLoginRequest.getPassword())){
            return user;
        }else {
            log.warn("該email {} 密碼不正確", userLoginRequest.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


    }
}
