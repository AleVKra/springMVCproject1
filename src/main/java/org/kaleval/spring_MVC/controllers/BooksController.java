package org.kaleval.spring_MVC.controllers;

import org.kaleval.spring_MVC.dao.BookDAO;
import org.kaleval.spring_MVC.dao.PersonDAO;
import org.kaleval.spring_MVC.models.Book;
import org.kaleval.spring_MVC.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person) {

        //  если книга пренадлежит какому то человеку, то показать этого человека
        model.addAttribute("book", bookDAO.show(id));

        // получить владельца книги
        Optional<Person> bookOwner = bookDAO.getBookOwner(id);
// если книга свободна оказываем выпадающий список
        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book
            , BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    //    освобождает книгу при нажатии на кнопку
    @PatchMapping("/{id}/realease")
    public String release(@PathVariable("id") int id) {
//        у selectPerson назначено только поле id, остальные поля -null
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    //  назначает книгу при нажатии на кнопку
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectPerson) {
//        у selectPerson назначено только поле id, остальные поля -null
        bookDAO.assign(id, selectPerson);
        return "redirect:/books/" + id;
    }
}
