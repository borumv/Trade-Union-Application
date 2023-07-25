package backend.controllers;

import backend.persist.entity.WorkPlace;
import backend.services.WorkPlaceService;
import jakarta.validation.constraints.Min;
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
@CrossOrigin(origins = {"cors.allow"})
public class WorkPlaceController {

    @Autowired
    WorkPlaceService workPlaceSevice;
    Logger logger = LoggerFactory.getLogger(WorkPlaceController.class);

    /**
     * Retrieves all work places.
     *
     * @return a list of WorkPlace objects representing all work places.
     */
    @GetMapping("/all")
    public List<WorkPlace> showAll() {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: showAll", a.getName(), "WorkPlaceController");
        return workPlaceSevice.getAllWorkPlace();
    }

    /**
     * Retrieves the work place with the specified ID.
     *
     * @param workPlaceId the ID of the work place.
     * @return a WorkPlace object representing the work place.
     */
    @GetMapping("/{workPlaceId}")
    @PreAuthorize("hasAuthority('tradeunion:read')")
    public WorkPlace getById(
            @PathVariable
            @Min(value = 1, message = "{workplace.id.size.error}")
            int workPlaceId) {

        return workPlaceSevice.getById(workPlaceId);
    }

}
