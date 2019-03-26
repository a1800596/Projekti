package hh.swd20.Bookstore;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;





@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void findByTitle() {
        List<Book> books = repository.findByTitle("Kirjoittaja");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Kirjayks");
    }
    
    @Test
    public void createNewBook() {
    
    	Book book = new Book();
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
    
    @Test
    public void DeleteBook() {
    
    	Book book1 = new Book();
    	repository.save(book1);
    	Long bookId = book1.getId();
    	repository.deleteById(bookId);
    	assertThat(repository.findById(bookId).equals(null));
    	
    	
    }  

}