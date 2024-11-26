package com.ccsw.tutorial.category;

import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    //     private long SEQUENCE = 1;
    //    private Map<Long, CategoryDto> categories = new HashMap<Long, CategoryDto>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Category get(Long id) {

        return this.categoryRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Category> findAll() {

        // return new ArrayList<CategoryDto>(this.categories.values());
        return (List<Category>) this.categoryRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public void save(Long id, CategoryDto dto) {
        // CategoryDto category;
        Category category;

        if (id == null) {
            // category = new CategoryDto();
            // category.setId(this.SEQUENCE++);
            category = new Category();
        } else {
            // category = this.categories.get(id);
            category = this.get(id);
        }

        category.setName(dto.getName());
        this.categoryRepository.save(category);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) throws Exception {

        // this.categories.remove(id);

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.categoryRepository.deleteById(id);
    }

}
