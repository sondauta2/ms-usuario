package com.manuelcastillo.api.usuario.ms.usuario.mapper;

import com.manuelcastillo.api.usuario.ms.usuario.dto.OutLoginDto;
import com.manuelcastillo.api.usuario.ms.usuario.dto.PhoneDto;
import com.manuelcastillo.api.usuario.ms.usuario.dto.UserDto;
import com.manuelcastillo.api.usuario.ms.usuario.entities.Phone;
import com.manuelcastillo.api.usuario.ms.usuario.entities.User;
import com.manuelcastillo.api.usuario.ms.usuario.services.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserMapperImpl implements IUserMapper {

    @Autowired
    IUser iUser;


    @Override
    public UserDto addUserAndPhone(UserDto userDto) {
        return userDto;
    }

    @Override
    public Object createUser(UserDto userDto) {

        Optional<User> userEmail = iUser.getByEmail(userDto.getEmail());

        System.out.println(userEmail.toString());

        if (userEmail.isPresent()){
            return "El email ya está registrado.  Utilice otro.";
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setIsactive(true);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        user.setCreated(now);
        user.setModified(now);

        for (PhoneDto phoneRequest : userDto.getPhones()) {
            Phone phone = new Phone();
            phone.setNumber(phoneRequest.getNumber());
            phone.setCityCode(phoneRequest.getCityCode());
            phone.setCountryCode(phoneRequest.getCountryCode());
            phone.setUser(user);
            user.getPhones().add(phone);
        }
        return iUser.create(user);
    }

    @Override
    public User delUser(UUID id) {
        Optional<User> user = iUser.getById(id);
        User userData = new User();
        if (user.isPresent()){
            userData.setId(user.get().getId());
            userData.setName(user.get().getName());
            userData.setEmail(user.get().getEmail());
            userData.setPassword(user.get().getPassword());
            userData.setCreated(user.get().getCreated());
            userData.setModified(new Date());
            userData.setIsactive(false);
            userData.setPhones(user.get().getPhones());


            return iUser.create(userData);
        } else {
            return null;
        }
    }

    @Override
    public OutLoginDto loginUser(String email, String password) {

        Optional<User> userLogin = iUser.getValidLogin(email, password);
        OutLoginDto respLogin = new OutLoginDto();
        if (userLogin.isPresent()){
            respLogin.setId(userLogin.get().getId());
            respLogin.setCreated(userLogin.get().getCreated());
            respLogin.setModified(userLogin.get().getModified());
            respLogin.setLastlogin(userLogin.get().getLastlogin());
            respLogin.setToken("klsjhdflsvne3423v%%$");
            respLogin.setIsactive(userLogin.get().getIsactive());
        }
        return respLogin;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return iUser.getById(id);
    }


}
