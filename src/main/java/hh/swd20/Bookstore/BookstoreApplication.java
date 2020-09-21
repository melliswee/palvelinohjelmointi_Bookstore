package hh.swd20.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	//automaattisesti luotu main-metodi
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository bookrepository) {
	return (args) -> {
	// Your code...add some demo data to db
		//Auto auto1 = new Auto("Tesla", 2016);
		//Auto auto2 = new Auto("Saab", 1980);
		Book book1 = new Book("Hitchhiker's guide to the Galaxy", "Douglas Adams", 1980, "978-051-7-54209-5", 35.00);
		Book book2 = new Book("Merenpeitto", "Olli Jalonen", 2019, "978-951-1-34512-1", 19.95);
		bookrepository.save(book1);
		bookrepository.save(book2);
		
	};
	}


}
