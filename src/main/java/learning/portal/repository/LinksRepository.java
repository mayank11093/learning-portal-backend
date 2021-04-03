package learning.portal.repository;

import learning.portal.model.Links;
import learning.portal.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface LinksRepository extends CrudRepository<Links,String> {
}
