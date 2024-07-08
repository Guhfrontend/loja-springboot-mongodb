package com.gustavo.lojamongo.service;

import com.gustavo.lojamongo.dto.UserDTO;
import com.gustavo.lojamongo.model.Post;
import com.gustavo.lojamongo.model.User;
import com.gustavo.lojamongo.repository.PostRepository;
import com.gustavo.lojamongo.repository.UserRepository;
import com.gustavo.lojamongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(String id){
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitle(text);
    }

    public List<Post> fullsearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24*60*60*1000);
        return postRepository.fullSearch(text, minDate, maxDate);

    }

}
