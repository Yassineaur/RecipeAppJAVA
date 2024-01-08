package layers.dao;

import layers.entity.Comment;
import layers.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
