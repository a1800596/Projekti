package hh.swd20.Bookstore;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;
import hh.swd20.Bookstore.domain.User;
import hh.swd20.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Runo"));
			crepository.save(new Category("Joulu"));
			crepository.save(new Category("Dekkari"));
			
			
			repository.save(new Book("Kirjoittaja", "Kirjayks", 1997, "01010", 30, crepository.findByName("Runo").get(0)));
			repository.save(new Book("Kirjoittajakaks", "Kirjakaks", 1998, "01011", 30, crepository.findByName("Joulu").get(0)));
			
			
			// Luodaan käyttäjät user ja admin
			User user1 = new User("user", "matti@hotmail.com","$2a$11$RUUlirrSqnywfctNzJ.4QeLkaSvxTdrCQBuBkVnKBSkuYiZGBOQo2" ,"USER");
			User user2 = new User("admin", "seppo@gmail.com", "$2a$09$lPyPdmK7uxt4.1viDkbpSepsDDTBt/4MdfJrg20NSuH81qp0XB97u", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}

