package com.palayeshparsian.filemanager.web.controller;

import com.palayeshparsian.filemanager.model.Category;
import com.palayeshparsian.filemanager.service.CategoryService;
import com.palayeshparsian.filemanager.web.Color;
import com.palayeshparsian.filemanager.web.FlashMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Index of all categories
    @RequestMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "category/index";
    }

    // Single category page
    @RequestMapping("/categories/{categoryId}")
    public String category(@PathVariable Long categoryId, Model model) {
        // TODO: Get the category given by categoryId
        Category category = null;

        model.addAttribute("category", category);
        return "category/details";
    }

    // Form for adding a new category (WHEN PAEG IS LOADED (new Category()) OR REDIRECTED)
    @RequestMapping("categories/add")
    public String formNewCategory(Model model) {
        // TODO: Add model attributes needed for new form
        if(!model.containsAttribute("category")) {
            model.addAttribute("category", new Category());
        }
        model.addAttribute("colors", Color.values());
        model.addAttribute("action", String.format("/categories"));
        model.addAttribute("heading", "New Category");
        model.addAttribute("submit", "Add");

        return "category/form";
    }

    // Add a category (POST)
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String addCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        // TODO: Add category if valid data was received
        if(result.hasErrors()){
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);
            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("category",category);
            return "redirect:/categories/add";
        }
        categoryService.save(category);
        // FLashMessage will be added to model map
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Catgeory successfully added", FlashMessage.Status.SUCCESS));
        return "redirect:/categories";
    }


    // Form for editing an existing category
    @RequestMapping("categories/{categoryId}/edit")
    public String formEditCategory(@PathVariable Long categoryId, Model model) {
        // TODO: Add model attributes needed for edit form
        if(!model.containsAttribute("category")) {
            model.addAttribute("category", categoryService.findById(categoryId));
        }
        model.addAttribute("colors", Color.values());
        model.addAttribute("action", String.format("/categories/%s",categoryId));
        model.addAttribute("heading", "Edit Category");
        model.addAttribute("submit", "Update");
        return "category/form";
    }

    // Update an existing category
    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.POST)
    public String updateCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        // TODO: Update category if valid data was received
        if(result.hasErrors()){
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);
            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("category",category);
            return String.format("redirect:/categories/%s/edit",category.getId());
        }
        categoryService.save(category);
        // FLashMessage will be added to model map
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Catgeory successfully updated", FlashMessage.Status.SUCCESS));
        return "redirect:/categories";

    }


    // Delete an existing category
    @RequestMapping(value = "/categories/{categoryId}/delete", method = RequestMethod.POST)
    public String deleteCategory(@PathVariable Long categoryId, RedirectAttributes redirectAttributes) {
        // TODO: Delete category if it contains no GIFs
        Category category = categoryService.findById(categoryId);
        if(category.getFiles().size()>0){
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Unable to delete unempty categories",FlashMessage.Status.FAILURE));
            return String.format("redirect:/categories/%s/edit",categoryId);
        }
        categoryService.delete(category);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category Successfully deleted", FlashMessage.Status.SUCCESS));
        // TODO: Redirect browser to /categories
        return "redirect:/categories";
    }
}