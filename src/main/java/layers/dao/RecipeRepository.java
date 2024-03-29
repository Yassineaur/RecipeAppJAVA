package layers.dao;

import layers.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

    @Query("SELECT r FROM Recipe r WHERE r.title = :title")
    Optional<Recipe> findByTitle(@Param("title") String title);

}
