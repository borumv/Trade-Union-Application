package backend.persist.repositories;

import backend.persist.entity.DocMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DocTradeUnionRepo extends CrudRepository<DocMember, Integer> {

    /**
     * Retrieves a list of DocMember objects with a null leave date.
     *
     * @return a list of DocMember objects with a null leave date
     */
    List<DocMember> findDocMembersByLeaveDateIsNull();

    /**
     * Retrieves an Optional of DocMember object by membership card number.
     *
     * @param num the membership card number
     * @return an Optional of DocMember object
     */
    Optional<DocMember> findDocMemberByMembershipCard(int num);

    /**
     * Retrieves a list of DocMember objects by person ID.
     *
     * @param id the ID of the person
     * @return a list of DocMember objects
     */
    List<DocMember> findByPersonId(int id);

    /**
     * Deletes DocMember objects based on the person ID.
     *
     * @param id the ID of the person
     */
    void deleteByPersonId(int id);
}
