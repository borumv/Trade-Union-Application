package backend.persist.repositories;

import backend.persist.entity.PersonEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepo extends PagingAndSortingRepository<PersonEntity, Integer> {

    /**
     * Deletes the specified PersonEntity object.
     *
     * @param personEntity the PersonEntity object to be deleted
     */
    void delete(PersonEntity personEntity);

    /**
     * Saves the specified PersonEntity object.
     *
     * @param personEntity the PersonEntity object to be saved
     * @return the saved PersonEntity object
     */
    PersonEntity save(PersonEntity personEntity);

    /**
     * Retrieves an Optional of PersonEntity object by the specified ID.
     *
     * @param id the ID of the person
     * @return an Optional of PersonEntity object
     */
    Optional<PersonEntity> findById(int id);

    /**
     * Deletes a PersonEntity object by the specified ID.
     *
     * @param id the ID of the person to be deleted
     */
    void deleteById(int id);

    /**
     * Retrieves a list of all PersonEntity objects.
     *
     * @return a list of all PersonEntity objects
     */
    List<PersonEntity> findAll();

    /**
     * Retrieves a list of PersonEntity objects whose first name starts with the specified string.
     *
     * @param firstNameStart the starting string of the first name
     * @param pageable       the pageable object for pagination
     * @return a list of PersonEntity objects matching the search criteria
     */
    List<PersonEntity> findByFirstNameStartsWith(String firstNameStart, Pageable pageable);

    /**
     * Retrieves a list of PersonEntity objects whose first name starts with the specified string.
     *
     * @param firstNameStart the starting string of the first name
     * @return a list of PersonEntity objects matching the search criteria
     */
    List<PersonEntity> findByFirstNameStartsWith(String firstNameStart);

    /**
     * Retrieves a list of PersonEntity objects with the specified education.
     *
     * @param education the education to filter by
     * @return a list of PersonEntity objects with the specified education
     */
    List<PersonEntity> findAllByEducation(String education);

    /**
     * Retrieves a list of active members for a specific trade union, using pagination.
     *
     * @param unionId  the ID of the trade union
     * @param pageable the pageable object for pagination
     * @return a list of active members for the specified trade union
     */
    @Query(value = "SELECT\n" +
            "\tperson_main.* \n" +
            "FROM\n" +
            "\tperson_main\n" +
            "\tINNER JOIN doc_member ON person_main.\"id\" = doc_member.person_id \n" +
            "WHERE\n" +
            "\t( doc_member.completed IS NOT NULL AND doc_member.org_id = ?1 ) \n" +
            "ORDER BY\n" +
            "\tdoc_member.created", countQuery = "SELECT count(person_main.*) \n" +
            "FROM\n" +
            "\tperson_main\n" +
            "\tINNER JOIN doc_member ON person_main.\"id\" = doc_member.person_id \n" +
            "WHERE\n" +
            "\t( doc_member.completed IS NOT NULL AND doc_member.org_id = ?1 )", nativeQuery = true
    )
    List<PersonEntity> getAllActiveMembers(int unionId, Pageable pageable);

    /**
     * Retrieves a list of active members for a specific trade union without pagination.
     *
     * @param unionId the ID of the trade union
     * @return a list of active members for the specified trade union
     */
    @Query(value = "SELECT\n" +
            "\tperson_main.* \n" +
            "FROM\n" +
            "\tperson_main\n" +
            "\tINNER JOIN doc_member ON person_main.\"id\" = doc_member.person_id \n" +
            "WHERE\n" +
            "\t( doc_member.completed IS NOT NULL AND doc_member.org_id = ?1 ) \n" +
            "ORDER BY\n" +
            "\tdoc_member.created", nativeQuery = true
    )
    List<PersonEntity> getAllActiveWithoutPageable(int unionId);

}
