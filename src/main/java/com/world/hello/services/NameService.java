package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import com.world.hello.repository.NameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NameService {

    private final NameRepository repository;

    public NameService(NameRepository repository) {
        this.repository = repository;
    }

    public NameView findById(Long id){

        Optional<Name> result = repository.findById(id);

        if (result.isPresent()){
            return NameConverter.converterToNameView(result.get());
        } else {
            return null;
        }

    }

    public List<NameView> findAll(){

        List<Name> names = repository.findAll();

        if (names != null) {
            List<NameView> namesView = new ArrayList<>();

            for (int i = 0; i < names.size(); i++) {
                namesView.add(NameConverter.converterToNameView(names.get(i)));
            }
            return namesView;
        } else {
            return  null;
        }
    }

    public void save(NameView nameView){
        repository.save(NameConverter.converterToName(nameView));
    }

    public void delete(NameView nameView){
        repository.delete(NameConverter.converterToName(nameView));
    }

}
