package com.projekt.klinikaStudyBase.service;

import com.projekt.klinikaStudyBase.data.entity.User;
import com.projekt.klinikaStudyBase.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findbyId(Long id);

    User saveUserWithJogosultsag(UserDto newUserDto);

    List<UserDto> findAll();

    void delete(Long id);


}
