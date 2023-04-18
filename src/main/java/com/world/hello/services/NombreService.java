package com.world.hello.services;

import com.world.hello.models.Nombre;
import com.world.hello.repository.NombreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NombreService {

    @Autowired
    private NombreRepository repository;

    public List<Nombre> findAll(){
        return repository.findAll();
    }
}
