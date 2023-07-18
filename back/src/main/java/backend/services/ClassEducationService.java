package backend.services;

import backend.exceptions.ClassEducationNotFoundException;
import backend.persist.entity.ClassEducation;
import backend.persist.entity.PersonEntity;
import backend.persist.repositories.ClassEducationRepo;
import backend.persist.repositories.PersonRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for ClassEducation-related operations
 *
 * @author Boris Vlasevsky
 */
@Service
public class ClassEducationService {

    @Autowired
    ClassEducationRepo classEducationRepo;

    @Autowired
    PersonRepo personRepo;

    @PersistenceContext
    private EntityManager em;

    /**
     * Retrieves a list of PersonEntity objects representing all individuals who have taken the specified type of education.
     *
     * @param typeEducationId the ID of the type of education
     * @return a list of PersonEntity objects
     */

    public List<PersonEntity> getAllWhoTakeThisTypeEducation(int typeEducationId) {

        String nameEducation = classEducationRepo.findById(typeEducationId).get().getName();
        Query q = em.createNativeQuery("SELECT\n" +
                                               "\tperson_main.*\n" +
                                               "FROM\n" +
                                               "\tperson_main\n" +
                                               "WHERE\n" +
                                               "\tperson_main.education = ?1", "PersonMapping");
        q.setParameter(1, nameEducation);
        return q.getResultList();
    }

    /**
     * Retrieves a list of all ClassEducation objects.
     *
     * @return a list of all ClassEducation objects
     */
    public List<ClassEducation> getAllType() {

        return classEducationRepo.findAll();
    }

    /**
     * Retrieves a ClassEducation object by the specified ID.
     *
     * @param id the ID of the ClassEducation object to retrieve
     * @return the retrieved ClassEducation object
     * @throws ClassEducationNotFoundException if no ClassEducation object is found with the specified ID
     */
    public ClassEducation findById(int id) {

        return classEducationRepo.findById(id)
                .orElseThrow(() -> new ClassEducationNotFoundException(id));
    }
}
