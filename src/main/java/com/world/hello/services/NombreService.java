package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.repository.NombreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NombreService {

    @Autowired
    private NombreRepository repository;

    public Name findByID(long id){
        Optional<Name> result = repository.findById(id);
        return result.orElse(null);
    }
    public List<Name> findAll(){

        return repository.findAll();
    }

    public void save(Name nombre){
        repository.save(nombre);
    }

    public void delete(Name nombre){

        repository.delete(nombre);
    }

}
