package backend.persist.repositories;

import backend.persist.entity.PersonEntity;
import backend.persist.entity.WorkPlace;
import org.hibernate.jdbc.Work;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WorkPlaceRepo extends CrudRepository<WorkPlace, Integer> {

    List<WorkPlace> findAll();


}
