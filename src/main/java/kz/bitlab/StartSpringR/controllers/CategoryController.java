package kz.bitlab.StartSpringR.controllers;

import kz.bitlab.StartSpringR.models.Category;
import kz.bitlab.StartSpringR.models.User;
import kz.bitlab.StartSpringR.repositories.CategoryRepository;
import kz.bitlab.StartSpringR.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Assylkhan
 * on 9.09.2019
 * @project StartSpringR
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    @Qualifier("admin")
    private User admin;

    @Autowired
    @Qualifier("client")
    private User client;


    private CategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("alem") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/index";
    }

    @PostMapping
    public String store(@RequestParam String name) {
        categoryService.save(Category.builder().name(name).build());
        return "redirect:/categories";
    }

    @PostMapping("/delete/{categoryId}")
    public String delete(@PathVariable Long categoryId) {
        categoryService.deleteById(categoryId);
        return "redirect:/categories";
    }

}
