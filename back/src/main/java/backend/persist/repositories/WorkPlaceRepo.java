package backend.persist.repositories;

import backend.persist.entity.WorkPlace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkPlaceRepo extends CrudRepository<WorkPlace, Integer> {

    /**
     * Retrieves a list of all WorkPlace objects.
     *
     * @return a list of all WorkPlace objects
     */

    List<WorkPlace> findAll();

}
