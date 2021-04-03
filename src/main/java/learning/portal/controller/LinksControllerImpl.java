package learning.portal.controller;

import learning.portal.model.Links;
import learning.portal.model.User;
import learning.portal.repository.LinksRepository;
import learning.portal.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Log4j2
@Component
public class LinksControllerImpl implements LinksController{

    @Autowired
    LinksRepository linksRepository;

    @Override
    public Collection<Links> getAllLinks() {
        List<Links> linksList = new ArrayList<>();
        linksRepository.findAll().forEach(linksList::add);
         return  linksList;
    }

    @Override
    public boolean addLink(@RequestBody Links links) {
        try{
            links.setId(UUID.randomUUID().toString());
            linksRepository.save(links);
            return true;
        }
        catch (Exception e){
            log.error(e);
            return false;
        }

    }

    @Override
    public boolean updateLinksByID(@PathVariable String id, @RequestBody Links links) {
        Optional<Links> link = linksRepository.findById(id);
        if(link.isPresent()) {
            if(link.get().getId().equals(links.getId())){
                try{
                    linksRepository.save(links);
                    return true;
                }
                catch (Exception e){
                    log.error(e);
                    return false;
                }
            }
            else {
                log.info("Incorrect ID");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByID(@PathVariable String id) {
        if(linksRepository.findById(id).isPresent()) {
            try{
                linksRepository.deleteById(id);
                return true;
            }
            catch (Exception e){
                log.error(e);
                return false;
            }
        }
        return false;
    }
}
