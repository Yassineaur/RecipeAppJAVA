package layers.dao;

import layers.entity.Recipe;
import layers.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tags, Integer> {


    @Query("SELECT t FROM Tags t WHERE t.tagName = :title")
    Optional<Tags> findByName(@Param("name") String tagName);

}
