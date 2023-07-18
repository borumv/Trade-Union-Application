package backend.persist.models;

import backend.persist.entity.PersonEntity;
import lombok.Data;

/**
 * Model class representing a SlackPerson.
 * <p>
 * This class extends AbstractModel and includes attributes for the first name and last name.
 *
 * @author Boris Vlasevsky
 */
@Data
public class SlackPersonModel extends AbstractModel {

    private String firstName;
    private String lastName;

    /**
     * Converts a PersonEntity object to a SlackPersonModel object.
     *
     * @param personEntity the PersonEntity object to be converted
     * @return the corresponding SlackPersonModel object
     */
    public static SlackPersonModel toModel(PersonEntity personEntity) {

        SlackPersonModel slackPersonModel = new SlackPersonModel();
        slackPersonModel.setFirstName(personEntity.getFirstName());
        slackPersonModel.setLastName(personEntity.getLastName());
        return slackPersonModel;
    }

}
