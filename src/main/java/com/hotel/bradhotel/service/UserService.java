package com.hotel.bradhotel.service;

import com.hotel.bradhotel.dto.UserRegisterRequest;
import com.hotel.bradhotel.model.User;

public interface UserService {

   User getUserById(Integer userId);

   Integer register(UserRegisterRequest userRegisterRequest);



}
