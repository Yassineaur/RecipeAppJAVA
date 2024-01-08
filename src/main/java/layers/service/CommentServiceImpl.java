package layers.service;

import layers.dao.CommentRepository;
import layers.dao.RecipeRepository;
import layers.entity.Comment;
import layers.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.CommentEvent;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository theCommentRepository) {
        commentRepository = theCommentRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(int theId) {
        Optional<Comment> result = commentRepository.findById(theId);
        Comment comment = null;
        if(result.isPresent()){
            comment = result.get();
            return comment;
        }else{
            return comment;
        }
    }


    @Override
    public void save(Comment theComment) {
        commentRepository.save(theComment);
    }

    @Override
    public void update(Comment theComment) {
        commentRepository.save(theComment);
    }

    @Override
    public void deleteById(int theId) {

    }
}
