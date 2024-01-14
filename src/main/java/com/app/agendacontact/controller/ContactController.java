package com.app.agendacontact.controller;


import com.app.agendacontact.entitie.Contact;
import com.app.agendacontact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/agenda","/"})
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String PageInitial(){
        return "form_tot_contacts";
    }

    @GetMapping("/new")
    public String newContact(Model model){
        model.addAttribute("newcontact", new Contact());
        return "form_new_contact";
    }

    @PostMapping("/new")
    public String crearContact(@ModelAttribute("newcontact") Contact contact, RedirectAttributes redirect) {
        contactService.saveContact(contact);
        redirect.addFlashAttribute("msggood", "Contact was added successfully");
        return "redirect:/agenda";
    }

    /*
    @PostMapping("/new")
    public String crearContact(@ModelAttribute ("newcontact") Contact contact){
        Contact contactNew = contactService.getContactById(id);
        contactNew.setId(id);
        contactNew.setName(contact.getName());
        contactNew.setEmail(contact.getEmail());
        contactNew.setTelephone(contact.getTelephone());
        contactService.saveContact(contactNew);
        return "redirect:/";
    } */

}
