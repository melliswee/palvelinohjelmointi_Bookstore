package hh.swd20.Bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
	
	@RequestMapping("/index")
	public String HelloBooks(Model model) {
		String msg = "Hello books!";
		model.addAttribute("message",msg);
		return "index";
	}

}
