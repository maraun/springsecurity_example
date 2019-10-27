package kz.bitlab.StartSpringR.controllers;

import kz.bitlab.StartSpringR.models.Category;
import kz.bitlab.StartSpringR.models.Item;
import kz.bitlab.StartSpringR.repositories.CategoryRepository;
import kz.bitlab.StartSpringR.repositories.ItemRepository;
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
@RequestMapping("items")
public class ItemController {

    private ItemRepository itemRepository;
    private CategoryService categoryService;
    private CategoryService categoryServiceSalem;

    @Autowired
    public ItemController(ItemRepository itemRepository,
                          @Qualifier("alem") CategoryService categoryService,
                          @Qualifier("salem") CategoryService categoryServiceSalem
    ) {
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
        this.categoryServiceSalem = categoryServiceSalem;
    }

    @GetMapping("/categories/{categoryId}")
    public String index(@PathVariable Long categoryId, Model model) {
        Optional<Category> categoryOpt = categoryService.findById(categoryId);
        if (categoryOpt.isPresent()) {
            model.addAttribute("items", itemRepository.findAllByCategory_Id(categoryId));
            model.addAttribute("category", categoryOpt.get());
            return "items/index";
        } else {
            return "redirect:/";
        }
    }


    @PostMapping("/categories/{categoryId}")
    public String store(@PathVariable Long categoryId, @RequestParam String name, @RequestParam Double price) {
        Optional<Category> categoryOpt = categoryService.findById(categoryId);
        if (categoryOpt.isPresent()) {
            itemRepository.save(Item.builder().category(categoryOpt.get()).name(name).price(price).build());
            return "redirect:/items/categories/" + categoryId;
        } else {
            return "redirect:/";
        }
    }
}
