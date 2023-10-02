package com.hotel.bradhotel.service;

import com.hotel.bradhotel.dto.UserLoginRequest;
import com.hotel.bradhotel.dto.UserRegisterRequest;
import com.hotel.bradhotel.model.User;

public interface UserService {

   User getUserById(Integer userId);

   User getUserByEmail(UserRegisterRequest userRegisterRequest);

   Integer register(UserRegisterRequest userRegisterRequest);

   User login(UserLoginRequest userLoginRequest);

}
