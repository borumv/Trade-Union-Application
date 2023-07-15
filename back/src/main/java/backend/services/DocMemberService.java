package backend.services;

import backend.exceptions.DocMemberNotFoundException;
import backend.persist.entity.DocMember;
import backend.persist.repositories.DocTradeUnionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocMemberService {

    @Autowired
    private DocTradeUnionRepo docRepo;

    public List<DocMember> getAllWhoNotFinished() {

        return docRepo.findDocMembersByLeaveDateIsNull();
    }

    public DocMember findByNum(int num) {

        return docRepo.findDocMemberByMembershipCard(num)
                .orElseThrow(() -> new DocMemberNotFoundException(num));

    }

    public List<DocMember> findByPersonId(int id) {

        return docRepo.findByPersonId(id);
    }

    public void deleteByPersonId(int id) {

        docRepo.deleteByPersonId(id);
    }
}
