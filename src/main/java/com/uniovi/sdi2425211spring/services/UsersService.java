package com.uniovi.sdi2425211spring.services;

import java.util.*;

import com.uniovi.sdi2425211spring.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniovi.sdi2425211spring.entities.*;
import com.uniovi.sdi2425211spring.services.UsersService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersService(UsersRepository usersRepository, BCryptPasswordEncoder
            bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void init() {
    }
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }
    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public void updateUser(User user) {
        usersRepository.save(user);
    }

    public User getUserByDni(String dni) {
        return usersRepository.findByDni(dni);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
