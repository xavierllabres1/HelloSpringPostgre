package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import com.world.hello.repository.NameRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NameService {

    private ConversionService conversionService;

    private final NameRepository repository;

    public NameService(ConversionService conversionService,
                       NameRepository repository) {
        this.conversionService = conversionService;
        this.repository = repository;
    }

    public NameView findById(Long id){

        Optional<Name> result = repository.findById(id);

        if (result.isPresent()){
            return this.conversionService.convert(result.get(), NameView.class);
        } else {
            return null;
        }

    }

    public List<NameView> findAll(){

        List<Name> names = repository.findAll() ;

        return Stream.ofNullable(names)
                .map(name -> this.conversionService.convert(name, NameView.class))
                .collect(Collectors.toList());


        /*if (names != null) {
            List<NameView> namesView = new ArrayList<>();

            for (int i = 0; i < names.size(); i++) {
                namesView.add(this.conversionService.convert(names.get(i), NameView.class));
            }
            return namesView;
        } else {
            return  null;
        }*/
    }

    public void save(NameView nameView){
        repository.save(NameConverter.converterToName(nameView));
    }

    public void delete(NameView nameView){
        repository.delete(NameConverter.converterToName(nameView));
    }

}
