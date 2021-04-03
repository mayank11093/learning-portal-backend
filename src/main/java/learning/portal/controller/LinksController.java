package learning.portal.controller;

import learning.portal.model.Links;
import learning.portal.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/links")
@CrossOrigin("*")
public interface LinksController {
    @GetMapping
    Collection<Links> getAllLinks();

    @PostMapping
    boolean addLink(Links links);

    @PutMapping("/{id}")
    boolean updateLinksByID(String id, Links links);

    @DeleteMapping("/{id}")
    boolean deleteByID(String id);

}
