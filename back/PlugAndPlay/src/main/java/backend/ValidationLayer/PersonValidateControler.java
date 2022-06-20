package backend.ValidationLayer;

import backend.controllers.BaseRestController;
import backend.exceptions.PersonNotFoundException;
import backend.persist.entity.PersonEntity;
import backend.persist.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Validated
public class PersonValidateControler {
    @Autowired
    private PersonRepo personRepo;


    public PersonEntity update(@Valid PersonEntity personEntity) {
        return personRepo.save(personEntity);
    }
}



