package backend.persist.models;

import backend.persist.entity.PersonEntity;
import lombok.Data;

@Data
public class SlackPersonModel extends AbstractModel {
    private String firstName;
    private String lastName;

    public static SlackPersonModel toModel(PersonEntity personEntity){
        PersonModel personModel = new PersonModel();
        personModel.setFirstName(personEntity.getFirstName());
        personModel.setLastName(personEntity.getLastName());

        return personModel;

    }


}

