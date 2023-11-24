package com.manuelcastillo.api.usuario.ms.usuario.controller;

import com.manuelcastillo.api.usuario.ms.usuario.dto.LoginDto;
import com.manuelcastillo.api.usuario.ms.usuario.dto.OutLoginDto;
import com.manuelcastillo.api.usuario.ms.usuario.dto.UserDto;
import com.manuelcastillo.api.usuario.ms.usuario.entities.User;
import com.manuelcastillo.api.usuario.ms.usuario.mapper.IUserMapper;
import com.manuelcastillo.api.usuario.ms.usuario.services.IUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private static final String SECRET_KEY = "mi_clave_secreta";

    @Autowired
    IUser iUser;

    @Autowired
    IUserMapper iUserMapper;

    @Operation(summary = "Add User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User and Phones records success ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error ",
                    content = @Content)
    }
    )
    @PostMapping(value = "/createuser", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto, BindingResult result){
        if (result.hasErrors()){
            return validar(result);
        }
        Object data = iUserMapper.createUser(userDto);
        if (data instanceof User) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Email duplicado", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/deleteuser/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delUser(@PathVariable UUID id){
        User user = iUserMapper.delUser(id);
        if (user == null){
            return new ResponseEntity<>("Usuario no existe", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(iUser.create(user), HttpStatus.ACCEPTED);
        }
    }



    @GetMapping(value = "/getuser/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable UUID id){
        Optional<User> user = iUserMapper.getUserById(id);
        if (user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Usuario no existe", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        OutLoginDto resp = iUserMapper.loginUser(loginDto.getEmail(), loginDto.getPassword());
        if ( resp.getId() == null){
            return new ResponseEntity<>("Usuario no existe o password incorrecta", HttpStatus.OK);
        } else {
            String token = Jwts.builder()
                    .setSubject(loginDto.getEmail())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día de duración
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();
            OutLoginDto outLoginDto = new OutLoginDto();
            outLoginDto.setId(resp.getId());
            outLoginDto.setCreated(resp.getCreated());
            outLoginDto.setModified(resp.getModified());
            outLoginDto.setLastlogin(resp.getLastlogin());
            outLoginDto.setToken(token);
            outLoginDto.setIsactive(resp.getIsactive());
            return new ResponseEntity<>(outLoginDto, HttpStatus.OK);
        }
    };

    private boolean autenticacionExitosa(LoginDto usuario) {
        return true;
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
