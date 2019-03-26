package hh.swd20.Bookstore;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;





@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void findByName() {
        List<Category> categories = repository.findByName("Joulu");
        
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Joulu");
    }
    
    @Test
    public void createNewCategory() {
    
    	Category category = new Category();
    	repository.save(category);
    	assertThat(category.getCategoryid()).isNotNull();
    }    
    
    @Test
    public void DeleteCategory() {
    
    	Category category1 = new Category();
    	repository.save(category1);
    	Long categoryId = category1.getCategoryid();
    	repository.deleteById(categoryId);
    	assertThat(repository.findById(categoryId).equals(null));
    	
    	
    }  

}