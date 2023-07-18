package backend.persist.models;

import backend.persist.entity.PersonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Model class representing a Person.
 * <p>
 * This class extends SlackPersonModel and includes additional attributes specific to a PersonEntity.
 *
 * @author Boris Vlasevsky
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PersonModel extends SlackPersonModel {

    private int id;
    private String patronymic;
    private String education;
    private Date birth;

    /**
     * Converts a PersonEntity object to a PersonModel object.
     *
     * @param personEntity the PersonEntity object to be converted
     * @return the corresponding PersonModel object
     */
    public static PersonModel toModel(PersonEntity personEntity) {

        PersonModel personModel = new PersonModel();
        personModel.setEducation(personEntity.getEducation());
        personModel.setId(personEntity.getId());
        personModel.setFirstName(personEntity.getFirstName());
        personModel.setLastName(personEntity.getLastName());
        personModel.setPatronymic(personEntity.getPatronymic());
        personModel.setBirth(personEntity.getBirth());
        return personModel;
    }
}


