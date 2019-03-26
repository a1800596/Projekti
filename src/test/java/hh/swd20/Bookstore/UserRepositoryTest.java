package hh.swd20.Bookstore;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.Bookstore.domain.User;
import hh.swd20.Bookstore.domain.UserRepository;





@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findByUsername() {
        User user = repository.findByUsername("admin");
        
        assertThat(user).isNotNull();
        
    }
    
    @Test
    public void createNewUser() {
    
    	//User user = new User();
    	User user = new User("Heppuhopo", "matti@hotmail.com","$2a$11$RUUlirrSqnywfctNzJ.4QeLkaSvxTdrCQBuBkVnKBSkuYiZGBOQo2" ,"USER");
    	repository.save(user);
    	assertThat(user.getId()).isNotNull();
    }    
    
    @Test
    public void DeleteUser() {
    
    	User user1 = new User("Heppuhopo", "matti@hotmail.com","$2a$11$RUUlirrSqnywfctNzJ.4QeLkaSvxTdrCQBuBkVnKBSkuYiZGBOQo2" ,"USER");
    	repository.save(user1);
    	Long userId = user1.getId();
    	repository.deleteById(userId);
    	assertThat(repository.findById(userId).equals(null));
    	
    	
    }  

}