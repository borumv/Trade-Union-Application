package backend.persist.models;

import backend.persist.entity.PersonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class PersonModel extends SlackPersonModel{

    private int id;
    private String patronymic;
    private String education;
    private Date birth;

    public static PersonModel toModel(PersonEntity personEntity){
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
