package com.gustavo.lojamongo.service;

import com.gustavo.lojamongo.dto.UserDTO;
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

    public User insert(User user){
        return userRepository.insert(user);
    }
    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User user){
       User newUser = findById(user.getId());
       updateData(newUser, user);
       return userRepository.save(newUser);
    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

}
