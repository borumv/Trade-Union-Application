package backend.persist.repositories;

import backend.persist.entity.PersonEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepo extends PagingAndSortingRepository<PersonEntity,Integer> {

    void delete(PersonEntity personEntity);
    PersonEntity save(PersonEntity personEntity);
    Optional<PersonEntity> findById(int id);
    void deleteById(int id);

    List<PersonEntity> findAll();
    List<PersonEntity> findByFirstNameStartsWith(String firstNameStart, Pageable pageable);
    List<PersonEntity> findByFirstNameStartsWith(String firstNameStart);
    List <PersonEntity> findAllByEducation(String education);

    @Query(value = "SELECT\n" +
            "\tperson_main.* \n" +
            "FROM\n" +
            "\tperson_main\n" +
            "\tINNER JOIN doc_member ON person_main.\"id\" = doc_member.person_id \n" +
            "WHERE\n" +
            "\t( doc_member.completed IS NOT NULL AND doc_member.org_id = ?1 ) \n" +
            "ORDER BY\n" +
            "\tdoc_member.created",countQuery = "SELECT count(person_main.*) \n" +
            "FROM\n" +
            "\tperson_main\n" +
            "\tINNER JOIN doc_member ON person_main.\"id\" = doc_member.person_id \n" +
            "WHERE\n" +
            "\t( doc_member.completed IS NOT NULL AND doc_member.org_id = ?1 )",  nativeQuery = true )
    List<PersonEntity>getAllActiveMembers(int unionId, Pageable pageable);

    @Query(value = "SELECT\n" +
            "\tperson_main.* \n" +
            "FROM\n" +
            "\tperson_main\n" +
            "\tINNER JOIN doc_member ON person_main.\"id\" = doc_member.person_id \n" +
            "WHERE\n" +
            "\t( doc_member.completed IS NOT NULL AND doc_member.org_id = ?1 ) \n" +
            "ORDER BY\n" +
            "\tdoc_member.created",  nativeQuery = true )
    List<PersonEntity>getAllActiveWithoutPageable(int unionId);

}
