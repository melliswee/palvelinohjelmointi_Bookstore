package hh.swd20.Bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
	
	@RequestMapping("/index")
	public String HelloBooks() {
		return "Hello books!"; //Tästä on hyvä jatkaa!
	}

}
