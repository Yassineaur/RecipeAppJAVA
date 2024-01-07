package layers.service;

import layers.dao.RecipeRepository;
import layers.dao.TagRepository;
import layers.entity.Recipe;
import layers.entity.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagsServiceImpl implements TagsService{

    private TagRepository tagRepository;

    @Autowired
    public TagsServiceImpl(TagRepository theTagRepository) {
        tagRepository = theTagRepository;
    }

    @Override
    public List<Tags> findAll() {
        return null;
    }

    @Override
    public Tags findById(int theId) {
        return null;
    }

    @Override
    public Tags findByName(String tagName) {
        Optional<Tags> result = tagRepository.findByName(tagName);
        Tags tag = null;
        if(result.isPresent()){
            tag = result.get();
        }else{
            throw  new RuntimeException("Did not find the recipe");
        }
        return tag;
    }

    @Override
    public void save(Tags theTag) {

    }

    @Override
    public void update(Tags theTag) {

    }

    @Override
    public void deleteById(int theId) {

    }
}
