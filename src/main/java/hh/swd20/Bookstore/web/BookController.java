package hh.swd20.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.CategoryRepository;

@CrossOrigin
@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookrepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@GetMapping("/index")
	public String HelloBooks(Model model) {
		String msg = "Hello books!";
		model.addAttribute("message",msg);
		return "index";
	}
	
	//tehtävä c3.2
	@GetMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", bookrepository.findAll());
		return "booklist";
	}
	
	//tehtävä 5.1
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookrepository.findAll();
    }
    
    //tehtävä 5.1
	// RESTful service to get book by id
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookrepository.findById(bookId);
    } 
	
	//tehtävä c3.3
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }
    
  //tehtävä c3.3
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        bookrepository.save(book);
        return "redirect:booklist";
    }
    
  //tehtävä c3.3
  //tehtävä c6.1 preauthorisointi, näin ei voi poistaa tietokannasta tavallisena käyttäjänä rivejä tietämällä/arvaamalla id:n
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookrepository.deleteById(bookId);
        return "redirect:../booklist";
    }
    
    //tehtävä c.3.4
    //annetaan editoitavan kirjan id path variablena ja etsitään se repositorysta
    // ja liitetään modeliin, sitten siirrytään editoimissivulle
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
    	model.addAttribute("book", bookrepository.findById(bookId));
    	model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }    

    
    //tehtävä c3.4 --> turha koska voi vain laittaa editbook-templatessa th:actioniksi ../save jolloin käyttää ylempää save- metodia
    /*
    @PostMapping(value = "/edit/save")
    public String saveEdit(Book book){
        bookrepository.save(book);
        return "redirect:booklist";
    }*/
    
    
}
