package backend.persist.repositories;

import backend.persist.entity.DocMember;
import backend.persist.entity.PersonEntity;
import backend.persist.entity.TradeUnion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DocTradeUnionRepo extends CrudRepository<DocMember,Integer> {
//    public DocMember findDocTradeUnionByPerson(PersonEntity personEntity);
    List<DocMember> findDocMembersByLeaveDateIsNull();
    Optional<DocMember> findDocMemberByMembershipCard(int num);

    List<DocMember> findByPersonId(int id);
    void deleteByPersonId(int id);
}
