package backend.persist.models;

import backend.persist.entity.ClassEducation;
import lombok.Data;

/**
 * Model class representing a ClassEducation.
 * <p>
 * This class is used to transfer data between different layers of the application.
 *
 * @author Boris Vlasevsky
 */
@Data
public class ClassEducationModel {

    private String name;

    /**
     * Converts a ClassEducation entity object to a ClassEducationModel object.
     *
     * @param classEducation the ClassEducation object to be converted
     * @return the corresponding ClassEducationModel object
     */
    public static ClassEducationModel toModel(ClassEducation classEducation) {

        ClassEducationModel classEducationModel = new ClassEducationModel();
        classEducationModel.setName(classEducation.getName());
        return classEducationModel;
    }
}