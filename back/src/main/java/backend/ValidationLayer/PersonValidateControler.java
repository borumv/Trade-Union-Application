package backend.ValidationLayer;

import backend.persist.entity.PersonEntity;
import backend.persist.repositories.PersonRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PersonValidateControler {

    @Autowired
    private PersonRepo personRepo;

    /**
     * Updates a person entity.
     *
     * @param personEntity the person entity to update
     * @return the updated person entity
     */
    public PersonEntity update(@Valid PersonEntity personEntity) {

        return personRepo.save(personEntity);
    }

}



