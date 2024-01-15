package com.app.agendacontact.service.impl;

import com.app.agendacontact.entitie.Contact;
import com.app.agendacontact.repository.ContactRepository;
import com.app.agendacontact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact updateContact( Long id ,Contact contact) {
        Contact contactexist = contactRepository.findById(id).orElse(null);

        if (contactexist != null){
            contactexist.setName(contact.getName());
            contactexist.setEmail(contact.getEmail());
            contactexist.setTelephone(contact.getTelephone());
            contactexist.setFirstDate(contact.getFirstDate());
            return contactRepository.save(contactexist);
        }
        return null;
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
