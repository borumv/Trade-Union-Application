package backend.persist.models;

import backend.persist.entity.ClassEducation;
import lombok.Data;

@Data
public class ClassEducationModel {
    private String name;
     public static ClassEducationModel toModel(ClassEducation classEducation){
        ClassEducationModel classEducationModel = new ClassEducationModel();
        classEducationModel.setName(classEducation.getName());
        return classEducationModel;
    }
}
