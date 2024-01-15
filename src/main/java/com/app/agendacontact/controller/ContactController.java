package com.app.agendacontact.controller;


import com.app.agendacontact.entitie.Contact;
import com.app.agendacontact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/diary","/"})
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String PageInitial( Model model){
        model.addAttribute("contacts",contactService.getContacts());
        return "form_tot_contacts";
    }

    @GetMapping("/new")
    public String newContact(Model model){
        model.addAttribute("newcontact", new Contact());
        return "form_new_contact";
    }

    @PostMapping("/new")
    public String createContact(@ModelAttribute("newcontact") @Validated Contact contact,  BindingResult bindingResult, RedirectAttributes redirect, Model model) {
        if (bindingResult.hasErrors()){
            //pasar nuevo form
            model.addAttribute("contact",contact);
            return "form_new_contact";
        }
        contactService.saveContact(contact);
        redirect.addFlashAttribute("msggood", "Contact was added successfully");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editContact(@PathVariable Long id, Model model) {
        model.addAttribute("newcontact", contactService.getContactById(id));
        return "form_act_contact";
    }

    @PostMapping("/edit/{id}")
    public String updateContact(@ModelAttribute("newcontact") @Validated Contact contact, BindingResult bindingResult, @PathVariable Long id,RedirectAttributes redirect,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contact",contact);
            return "form_act_contact";
        }
        Contact contactExist = contactService.getContactById(id);
        contactExist.setId(id);
        contactExist.setName(contact.getName());
        contactExist.setEmail(contact.getEmail());
        contactExist.setTelephone(contact.getTelephone());
        contactExist.setFirstDate(contact.getFirstDate());
        contactService.updateContact(id, contactExist);
        redirect.addFlashAttribute("msggood", "Contact was updated successfully");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact (@PathVariable Long id ,RedirectAttributes redirect) {
        contactService.deleteContact(id);
        redirect.addFlashAttribute("delete", "Contact was Delete successfully");
        return "redirect:/";
    }

}
