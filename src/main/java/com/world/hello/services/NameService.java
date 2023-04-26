package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.repository.NameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NameService {

    private final NameRepository repository;

    public NameService(NameRepository repository) {
        this.repository = repository;
    }

    public Name findById(Long id){
        Optional<Name> result = repository.findById(id);
        System.out.println(result);
        return result.orElse(null);
    }

    public List<Name> findAll(){
        return repository.findAll();
    }

    public void save(Name name){
        repository.save(name);
    }

    public void delete(Name name){
        repository.delete(name);
    }

}
