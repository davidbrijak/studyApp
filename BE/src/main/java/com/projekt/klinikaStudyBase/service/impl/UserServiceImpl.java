package com.projekt.klinikaStudyBase.service.impl;

import com.projekt.klinikaStudyBase.data.entity.Jogosultsag;
import com.projekt.klinikaStudyBase.data.entity.User;
import com.projekt.klinikaStudyBase.dto.UserDto;
import com.projekt.klinikaStudyBase.exception.UserNotFoundException;
import com.projekt.klinikaStudyBase.repository.JogosultsagRepository;
import com.projekt.klinikaStudyBase.repository.UserRepository;
import com.projekt.klinikaStudyBase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JogosultsagRepository jogosultsagRepository;

    @Override
    public UserDto findbyId(final Long id) {
        final User entity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        return UserDto.builder()
                .id(entity.getId())
                .felhasznaloNev(entity.getFelhasznaloNev())
                .jelszo(entity.getJelszo())
                .jogosultsag(entity.getJogosultsagok())
                .build();
    }

    @Override
    public User saveUserWithJogosultsag(final UserDto newUserDto){
        User user =new User();
        user.setId(newUserDto.getId());
        user.setFelhasznaloNev(newUserDto.getFelhasznaloNev());
        user.setJelszo(newUserDto.getJelszo());
        user.setJogosultsag(newUserDto.getJogosultsag()
                .stream()
                .map(jog -> {
                    Jogosultsag jogosultsag = jog;
                    if(jogosultsag.getId() > 0){
                        jogosultsag = jogosultsagRepository.findById(jogosultsag.getId()).get();
                    }
                    jogosultsag.addUser(user);
                    return jogosultsag;
                })
                .collect(Collectors.toSet()));
        return userRepository.save(user);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            userDtoList.add(
                    UserDto.builder()
                            .id(user.getId())
                            .felhasznaloNev(user.getFelhasznaloNev())
                            .jogosultsag(jogosultsagRepository.findAllByFelhasznalok(user))
                            .jelszo(user.getJelszo()).build());
        }
        return userDtoList;
    }

    @Override
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }
}
