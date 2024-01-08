package layers.service;

import layers.entity.Comment;
import layers.entity.Recipe;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment findById(int theId);


    void save(Comment theComment);

    void update(Comment theComment);

    void deleteById(int theId);
}
