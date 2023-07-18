package backend.services;

import backend.exceptions.DocMemberNotFoundException;
import backend.persist.entity.DocMember;
import backend.persist.repositories.DocTradeUnionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for DocMember-related operations.
 *
 * @author Boris Vlasevsky
 */
@Service
public class DocMemberService {

    @Autowired
    private DocTradeUnionRepo docRepo;

    /**
     * Retrieves a list of DocMember objects representing all members who have not finished their membership.
     *
     * @return a list of DocMember objects
     */
    public List<DocMember> getAllWhoNotFinished() {

        return docRepo.findDocMembersByLeaveDateIsNull();
    }

    /**
     * Retrieves a DocMember object by the specified membership card number.
     *
     * @param num the membership card number
     * @return the retrieved DocMember object
     * @throws DocMemberNotFoundException if no DocMember object is found with the specified membership card number
     */
    public DocMember findByNum(int num) throws DocMemberNotFoundException {

        return docRepo.findDocMemberByMembershipCard(num)
                .orElseThrow(() -> new DocMemberNotFoundException(num));
    }

    /**
     * Retrieves a list of DocMember objects associated with the specified person ID.
     *
     * @param id the person ID
     * @return a list of DocMember objects
     */
    public List<DocMember> findByPersonId(int id) {

        return docRepo.findByPersonId(id);
    }

    /**
     * Deletes DocMember objects associated with the specified person ID.
     *
     * @param id the person ID
     */
    public void deleteByPersonId(int id) {

        docRepo.deleteByPersonId(id);
    }
}
