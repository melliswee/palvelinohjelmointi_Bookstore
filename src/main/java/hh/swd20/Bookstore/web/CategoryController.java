package hh.swd20.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryrepository;
	
	@GetMapping("/categorylist")
	public String categoryList(Model model) {
		model.addAttribute("categories", categoryrepository.findAll());
		return "categorylist";
	}
	
    @GetMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("category", new Category());
        return "addcategory";
    }
    
    @PostMapping(value = "/saveCategory")
    public String save(Category category){
        categoryrepository.save(category);
        return "redirect:categorylist";
    }
}
