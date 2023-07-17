package backend.services;

import backend.exceptions.WorkPlaceNotFoundException;
import backend.persist.entity.WorkPlace;
import backend.persist.repositories.WorkPlaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkPlaceService {

    @Autowired
    WorkPlaceRepo workPlaceRepo;

    /**
     * Retrieves all work places.
     *
     * @return the list of all work places
     */
    public List<WorkPlace> getAllWorkPlace() {

        return workPlaceRepo.findAll();
    }

    /**
     * Retrieves a work place by ID.
     *
     * @param id the ID of the work place to retrieve
     * @return the found work place
     * @throws WorkPlaceNotFoundException if no work place is found with the specified ID
     */
    public WorkPlace getById(int id) throws WorkPlaceNotFoundException {

        return workPlaceRepo.findById(id).orElseThrow(() -> new WorkPlaceNotFoundException(id));
    }

}
