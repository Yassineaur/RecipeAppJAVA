package layers.service;

import layers.entity.Recipe;
import layers.entity.Tags;

import java.util.List;

public interface TagsService {

    List<Tags> findAll();

    Tags findById(int theId);

    Tags findByName(String tagName);

    void save(Tags theTag);

    void update(Tags theTag);

    void deleteById(int theId);

}
