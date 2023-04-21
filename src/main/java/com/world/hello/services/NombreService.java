package com.world.hello.services;

import com.world.hello.models.Nombre;
import com.world.hello.repository.NombreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NombreService {

    @Autowired
    private NombreRepository repository;

    public Nombre findByID(long id){
        Optional<Nombre> result = repository.findById(id);
        return result.orElse(null);
    }
    public List<Nombre> findAll(){

        return repository.findAll();
    }

    public void save(Nombre nombre){
        repository.save(nombre);
    }

    public void delete(Nombre nombre){

        repository.delete(nombre);
    }

}
