package com.hotel.bradhotel.dao;

import com.hotel.bradhotel.dto.UserRegisterRequest;
import com.hotel.bradhotel.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
