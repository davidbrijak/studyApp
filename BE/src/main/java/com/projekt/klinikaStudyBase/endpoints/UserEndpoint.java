package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.data.entity.User;
import com.projekt.klinikaStudyBase.dto.UserDto;
import com.projekt.klinikaStudyBase.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserEndpoint {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Felhasznalo lekerese azonosito alapjan.", notes = "Felhasznalo lekerese azonosito alapjan.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") final Long userId) {
        return ResponseEntity.ok(userService.findbyId(userId));
    }

    @Transactional
    @ApiOperation(value = "Uj felhasznalo mentese.", notes = "Uj felhasznalo mentese.")
    @PostMapping
    public ResponseEntity<User> addUserWithJogosultsag(@RequestBody final UserDto newUserDto) {
        return ResponseEntity.ok(userService.saveUserWithJogosultsag(newUserDto));
    }

    @ApiOperation(value = "Felhasznalok lekerese.", notes = "Felhasznalok lekerese.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @Transactional
    @ApiOperation(value = "Felhasznalo torlese azonosito alapjan.", notes = "Felhasznalo torlese azonosito alapjan.")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") final Long userId) {
        userService.delete(userId);
    }
}
