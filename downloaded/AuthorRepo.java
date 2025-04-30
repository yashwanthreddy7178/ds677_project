package tech.yashtiwari.spring5webapp.repo;

import org.springframework.data.repository.CrudRepository;
import tech.yashtiwari.spring5webapp.model.Author;

public interface AuthorRepo extends CrudRepository<Author, Long>  /*TODO*/ {

}
