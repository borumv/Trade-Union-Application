package backend.persist.repositories;

import backend.persist.entity.ClassEducation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ClassEducationRepo extends CrudRepository<ClassEducation, Integer> {

//    ClassEducation findById(int educationTypeId);

    List<ClassEducation>findAll();
}
