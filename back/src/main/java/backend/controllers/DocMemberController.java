
package backend.controllers;

import backend.persist.entity.DocMember;
import backend.services.DocMemberService;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The DocMemberController class handles requests related to document members.
 * It provides endpoints for retrieving document member information.
 *
 * @author Boris Vlasevsky
 */
@RestController
@RequestMapping("/api/union/docmember")
@CrossOrigin(origins = {"http://localhost:3000"})
public class DocMemberController {

    @Autowired
    private DocMemberService docMemberService;
    Logger logger = LoggerFactory.getLogger(DocMemberController.class);

    /**
     * Retrieves a list of document members who have not finished their membership.
     *
     * @return a list of DocMember objects representing the active document members.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/all")
    public List<DocMember> findDocMembersByLeaveDateIsNull() {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: findDocMembersByLeaveDateIsNull", a.getName(), "DocMemberController");
        return docMemberService.getAllWhoNotFinished();
    }

    /**
     * Retrieves the document member with the specified document number.
     *
     * @param num the document number of the member.
     * @return a DocMember object representing the document member.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/{num}")
    public DocMember findByNum(
            @PathVariable
            @Min(1) int num) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: findByNum", a.getName(), "DocMemberController");
        return docMemberService.findByNum(num);
    }

}
