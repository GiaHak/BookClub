package com.gia.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gia.bookclub.models.Book;
import com.gia.bookclub.models.LoginUser;
import com.gia.bookclub.models.User;
import com.gia.bookclub.service.BookService;
import com.gia.bookclub.service.UserService;



@Controller
public class HomeController {
  
     @Autowired
    private UserService userServ;
    private BookService bookServ;
    
    public HomeController(UserService userServ, BookService bookServ) {
    	this.userServ = userServ;
    	this.bookServ = bookServ;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
         BindingResult result,  Model model, HttpSession session) {
       userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
    	User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/home";
    }
    
    @RequestMapping("/home")
    public String home(Model model, HttpSession session) {
    	if (session.getAttribute("user_id") == null) {
    		return "redirect:/";}
    
		List<Book> books = bookServ.allBook();
		Long userId = (Long) session.getAttribute("user_id");
		User user = userServ.oneUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("books", books);
		return "home.jsp";
    	
    }
    @RequestMapping("/add")
	public String add(@ModelAttribute("book") Book book, Model model, HttpSession session) {
    	if (session.getAttribute("user_id") == null) {
    		return "redirect:/";}
    
    	Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
    	return"add.jsp";
	}
    
    @RequestMapping(value = "/createBook", method=RequestMethod.POST)
    public String CreatedBy(@Valid @ModelAttribute("book") Book book, BindingResult result) {
    	if(result.hasErrors()) {
    		return "add.jsp";
    	
    	}
    	else {
    		bookServ.createBook(book);
    		return "redirect:/home";
    	}
    }
    
    @RequestMapping("/oneBook/{id}")
    public String oneBook(@PathVariable("id") Long id,
    									Model model,
    									HttpSession session) {
    	
    	if (session.getAttribute("user_id") == null) {
    		return "redirect:/";}
    	
    	User user = userServ.oneUser((Long) session.getAttribute("user_id"));
    	model.addAttribute("user", user);
    	model.addAttribute("book", bookServ.oneBook(id));
    	return "onebook.jsp";
    	
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long id, HttpSession session, Model model) {
    	if (session.getAttribute("user_id") == null) {
    		return "redirect:/";}
    	
    	Book book = bookServ.oneBook(id);
    	model.addAttribute("book", book);
    	Long userId = (Long) session.getAttribute("user_id");
    	User user = userServ.oneUser(userId);
    	model.addAttribute("user", user);
    	return "edit.jsp";
}
    
    @RequestMapping(value = "/editBook/{id}", method=RequestMethod.PUT)
    public String Edit(@Valid @ModelAttribute("book") Book book, BindingResult result) {
    	if(result.hasErrors()) {
    		return "edit.jsp";
    	}
    	else {
    		bookServ.updateBook(book);
    		return "redirect:/home";
    	}
    }
    @RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable("id") Long id) {
		bookServ.deleteBook(id);
		return "redirect:/home";
	}
}
