package backend.services;

import backend.exceptions.WorkPlaceNotFoundException;
import backend.persist.entity.PersonEntity;
import backend.persist.entity.WorkPlace;
import backend.persist.repositories.WorkPlaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkPlaceService {
    @Autowired
    WorkPlaceRepo workPlaceRepo;
    public List<WorkPlace> getAllWorkPlace(){
        return workPlaceRepo.findAll();
    }
    public WorkPlace getById(int id){
        return workPlaceRepo.findById(id).orElseThrow(()->new WorkPlaceNotFoundException(id));
    }

//    public PersonEntity getWorkPeople(int placeId){
//        WorkPlace workPlace = workPlaceRepo.findById(placeId);
//        return workPlace.getPerson();
//    }
}
