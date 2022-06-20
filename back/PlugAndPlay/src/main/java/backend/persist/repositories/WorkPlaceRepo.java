package backend.persist.repositories;

import backend.persist.entity.PersonEntity;
import backend.persist.entity.WorkPlace;
import org.hibernate.jdbc.Work;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkPlaceRepo extends CrudRepository<WorkPlace, Integer> {

    public List<WorkPlace> findAll();


}
