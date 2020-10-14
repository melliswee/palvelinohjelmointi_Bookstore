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
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	//automaattisesti luotu main-metodi
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository bookrepository, CategoryRepository categoryrepository, UserRepository userrepository) {
	return (args) -> {
	// Your code...add some demo data to db
		
		User user1 = new User("user", "$2a$04$KFeHewQhVXuybwKFWPTkxeLtNw8GWJR8LWcFEoi/NtJN2weBRQmCS", "USER"); //salasana
		User user2 = new User("admin", "$2a$04$HxMYQJQmJq1UVD7QpPGw2ePAj89OvTA3/AodO5jG863XxGpFXhVvq", "ADMIN"); //password
		userrepository.save(user1);
		userrepository.save(user2);
		
		Category cat1 = new Category("Scifi");
		Category cat2 = new Category("Fantasy");
		Category cat3 = new Category("Historical fiction");
		
		categoryrepository.save(cat1);
		categoryrepository.save(cat2);
		categoryrepository.save(cat3);

		Book book1 = new Book("Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1980, "978-051-7-54209-5", 35.00, cat1);
		Book book2 = new Book("Merenpeitto", "Olli Jalonen", 2019, "978-951-1-34512-1", 19.95, cat3);
		bookrepository.save(book1);
		bookrepository.save(book2);
		

		  log.info("listataan kaikki kirjat");

		  for(Book book : bookrepository.findAll()) {
			log.info(book.toString());

		}
		  
		  log.info("listataan kaikki kategoriat");

		  for(Category category : categoryrepository.findAll()) {
			log.info(category.toString());

		}
		
	};
	}


}
