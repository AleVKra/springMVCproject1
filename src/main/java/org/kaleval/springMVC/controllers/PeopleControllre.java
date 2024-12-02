package org.kaleval.springMVC.controllers;

import org.kaleval.springMVC.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleControllre {

    private PersonDAO personDAO;

    /**
     * способ создания конструктора лучше чем писать @Autowired
     */
    @Autowired
    public PeopleControllre(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
@GetMapping()
    public String index(Model model) {
//        Получим всех людей из DAO и передадим на отображеие в представлене
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
//        Получим одного человека по id из DAO и передадим на отображение в представление
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
}
