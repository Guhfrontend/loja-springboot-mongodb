package com.gustavo.lojamongo.service;

import com.gustavo.lojamongo.model.User;
import com.gustavo.lojamongo.repository.UserRepository;
import com.gustavo.lojamongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
