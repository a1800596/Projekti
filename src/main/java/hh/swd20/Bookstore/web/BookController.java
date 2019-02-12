package hh.swd20.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;

@Controller
public class BookController
{
	@Autowired
	private BookRepository repository;
	
	@RequestMapping(value="/booklist", method = RequestMethod.GET)
	public String getBooks(Model model)
	{
		List<Book> books = (List<Book>) repository.findAll();
		model.addAttribute("books", books);
		
		return "booklist";
	}
	
	@RequestMapping(value="/newbook", method = RequestMethod.GET)
	public String getNewBookForm(Model model)
	{
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@RequestMapping(value="/newbook", method = RequestMethod.POST)
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
