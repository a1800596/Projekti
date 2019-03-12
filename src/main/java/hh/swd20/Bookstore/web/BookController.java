package hh.swd20.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.CategoryRepository;

@Controller
public class BookController
{
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// login homma
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	
	@RequestMapping(value="/booklist", method = RequestMethod.GET)
	public String getBooks(Model model)
	{
		List<Book> books = (List<Book>) repository.findAll();
		model.addAttribute("books", books);
		
		return "booklist";
	}
	
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> booktListRest() {	
        return (List<Book>) repository.findAll();
    }    

    // RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookid) {	
    	return repository.findById(bookid);
    }       
	
	@RequestMapping(value="/newbook", method = RequestMethod.GET)
	public String getNewBookForm(Model model)
	{
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value="/savebook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute Book book)
	{
		repository.save(book);
		return "redirect:/booklist";
	}
	
	@RequestMapping(value="/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id")Long id, Model model)
	{
		repository.deleteById(id);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String editBook(@PathVariable("id")Long id, Model model)
	{
		model.addAttribute("book", repository.findById(id));
		return "editbook";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index()
	{
		return "index";
	}
	
	
}
