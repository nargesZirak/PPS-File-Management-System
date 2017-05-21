package com.palayeshparsian.filemanager.web.controller;

import com.palayeshparsian.filemanager.model.File;
import com.palayeshparsian.filemanager.service.CategoryService;
import com.palayeshparsian.filemanager.service.FileService;
import com.palayeshparsian.filemanager.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @Autowired
    private CategoryService categoryService;

    // Home page - index of all files
    @SuppressWarnings("unchecked")
    @RequestMapping("/files")
    public String listfiles(Model model) {
        // TODO: Get all files
        List<File> files = fileService.findAll();

        model.addAttribute("files", files);
        return "file/index";
    }

    // Single file page
    @RequestMapping("/files/{fileId}")
    public String fileDetails(@PathVariable Long fileId, Model model) {
        // TODO: Get file whose id is fileId
        File file = fileService.findById(fileId);

        model.addAttribute("file", file);
        return "file/details";
    }

    // Favorites - index of all files marked favorite
    @RequestMapping("/favorites")
    public String favorites(Model model) {
        // TODO: Get list of all files marked as favorite
        List<File> faves = new ArrayList<File>();

        model.addAttribute("files",faves);
        model.addAttribute("username","Chris Ramacciotti"); // Static username
        return "file/favorites";
    }

    // Upload a new file
    @RequestMapping(value = "/files", method = RequestMethod.POST)
    @ResponseBody
    public String addFile( File uploadedFile,@RequestParam("file") MultipartFile multiFile, RedirectAttributes redirectAttributes) {
        // TODO: Upload new file if data is valid
        System.out.println("FILE SHOULD BEEEE ADDED");
        //fileService.save(file1,multiFile);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("File Successfully uploaded",FlashMessage.Status.SUCCESS));
        // TODO: Redirect browser to new file's detail view
        return String.format("redirect:/files/");
    }


    // Form for uploading a new GIF
    @RequestMapping("/upload")
    public String formNewGif(Model model) {
        // TODO: Add model attributes needed for new GIF upload form
        System.out.println("FILE SHOULD BE ADDED");
        File uploadedFile =new File();
        if(!model.containsAttribute("file")) {
             model.addAttribute("uploadedFile",uploadedFile);
        }
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("action","/files");
        model.addAttribute("heading","Upload");
        model.addAttribute("submit","Add");
        System.out.println("HERE ADDED");

        return "file/form";
    }

    // file image data
    @RequestMapping("/files/{fileId}.png")
    @ResponseBody
    public byte[] fileImage(@PathVariable Long fileId) {
        // TODO: Return file data as byte array of the file whose id is fileId
        System.out.println("IMAGE ADDED");

        return fileService.findById(fileId).getBytes();
    }

    // Form for editing an existing file
    @RequestMapping(value = "/files/{dfileI}/edit")
    public String formEditfile(@PathVariable Long fileId, Model model) {
        // TODO: Add model attributes needed for edit form
        // TODO: Add model attributes needed for new GIF upload form
        if(!model.containsAttribute("file")) {
            model.addAttribute("file",fileService.findById(fileId));
        }
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("action",String.format("/files/%s",fileId));
        model.addAttribute("heading","Edit File");
        model.addAttribute("submit","Update");
        return "file/form";
    }

    // Update an existing file
    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.POST)
    public String updatefile() {
        // TODO: Update file if data is valid

        // TODO: Redirect browser to updated file's detail view
        return null;
    }

    // Delete an existing file
    @RequestMapping(value = "/files/{fileId}/delete", method = RequestMethod.POST)
    public String deletefile(@PathVariable Long fileId,RedirectAttributes redirectAttributes) {
        // TODO: Delete the file whose id is fileId
        File file = fileService.findById(fileId);
        fileService.delete(file);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("File successfully deleted", FlashMessage.Status.SUCCESS));
        // TODO: Redirect to app root
        return "redirect:/";
    }

    // Mark/unmark an existing file as a favorite
    @RequestMapping(value = "/files/{fileId}/favorite", method = RequestMethod.POST)
    public String toggleFavorite(@PathVariable Long fileId) {
        // TODO: With file whose id is fileId, toggle the favorite field

        // TODO: Redirect to file's detail view
        return null;
    }

    // Search results
    @RequestMapping("/search")
    public String searchResults(@RequestParam String q, Model model) {
        // TODO: Get list of files whose description contains value specified by q
        List<File> files = new ArrayList<File>();

        model.addAttribute("files",files);
        return "file/index";
    }
}
