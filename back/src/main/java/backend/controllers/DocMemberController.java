package backend.controllers;

import backend.persist.entity.DocMember;
import backend.services.DocMemberService;
import io.swagger.annotations.ApiImplicitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/union/docmember")
@CrossOrigin(origins = {"http://localhost:3000"})
public class DocMemberController {

    @Autowired
    private DocMemberService docMemberService;
    Logger logger = LoggerFactory.getLogger(DocMemberController.class);

    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/all")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    public List<DocMember> findDocMembersByLeaveDateIsNull(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: findDocMembersByLeaveDateIsNull",  a.getName(), "DocMemberController");
        return docMemberService.getAllWhoNotFinished();
    }

    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/{num}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    public DocMember findByNum(@PathVariable @Min(1) int num){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: findByNum",  a.getName(), "DocMemberController");
        return docMemberService.findByNum(num);
    }
}
