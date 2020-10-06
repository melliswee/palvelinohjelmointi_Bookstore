package hh.swd20.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@CrossOrigin
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryrepository;
	
	@GetMapping("/categorylist")
	public String categoryList(Model model) {
		model.addAttribute("categories", categoryrepository.findAll());
		return "categorylist";
	}
	
	//tehtävä 5.1
	// RESTful service to get all categories
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> categoryListRest() {	
        return (List<Category>) categoryrepository.findAll();
    }
    
    //tehtävä 5.1
	// RESTful service to get category by id
    @RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long categoryId) {	
    	return categoryrepository.findById(categoryId);
    } 
	
    @GetMapping(value = "/addCategory")
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
