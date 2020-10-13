package app.repos;

import app.entities.ItemAvs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryAvs extends CrudRepository<ItemAvs, String> {

    List<ItemAvs> findAll();

    Optional<ItemAvs> findByItem(String item);
    
}
