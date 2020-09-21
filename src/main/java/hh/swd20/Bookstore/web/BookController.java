package hh.swd20.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookrepository;
	
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
	
	//tehtävä c3.3
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        return "addbook";
    }
    
  //tehtävä c3.3
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        bookrepository.save(book);
        return "redirect:booklist";
    }
    
  //tehtävä c3.3
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookrepository.deleteById(bookId);
        return "redirect:../booklist";
    }     

}
