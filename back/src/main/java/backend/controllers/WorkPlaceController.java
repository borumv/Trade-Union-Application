package backend.controllers;

import backend.persist.entity.WorkPlace;
import backend.services.WorkPlaceService;
import io.swagger.annotations.ApiImplicitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/union/workplace")
@CrossOrigin(origins = {"http://localhost:3000"})
public class WorkPlaceController {

    @Autowired
    WorkPlaceService workPlaceSevice;
    Logger logger = LoggerFactory.getLogger(WorkPlaceController.class);

    @GetMapping("/all")
    public List<WorkPlace> showAll(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: showAll",  a.getName(), "WorkPlaceController");
        return workPlaceSevice.getAllWorkPlace();
    }

    @GetMapping("/{workPlaceId}")
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    public WorkPlace getById(@PathVariable int workPlaceId){
        return workPlaceSevice.getById(workPlaceId);
    }

}
