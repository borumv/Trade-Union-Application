package backend.services;

import backend.exceptions.ClassEducationNotFoundException;
import backend.persist.entity.ClassEducation;
import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import backend.persist.models.ClassEducationModel;
import backend.persist.models.PersonModel;
import backend.persist.repositories.ClassEducationRepo;
import backend.persist.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassEducationService {

    @Autowired
    ClassEducationRepo classEducationRepo;

    @Autowired
    PersonRepo personRepo;

    @PersistenceContext
    private EntityManager em;

    public List<PersonEntity> getAllWhoTakeThisTypeEducation(int typeEducationId){
        String nameEducation = classEducationRepo.findById(typeEducationId).get().getName();
             Query q = em.createNativeQuery("SELECT\n" +
                     "\tperson_main.*\n" +
                     "FROM\n" +
                     "\tperson_main\n" +
                     "WHERE\n" +
                     "\tperson_main.education = ?1", "PersonMapping");
            q.setParameter(1,nameEducation);
            return q.getResultList();
        }

    public List<ClassEducation> getAllType(){
        return classEducationRepo.findAll();
    }

    public ClassEducation findById(int id){
        return classEducationRepo.findById(id)
                .orElseThrow(()-> new ClassEducationNotFoundException(id));
    }
}
