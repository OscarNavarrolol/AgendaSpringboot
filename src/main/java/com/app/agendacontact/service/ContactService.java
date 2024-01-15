package com.app.agendacontact.service;

import com.app.agendacontact.entitie.Contact;

import java.util.List;

public interface ContactService {
    public List<Contact> getContacts();

    public Contact saveContact(Contact contact);

    public Contact getContactById(Long id);

    public Contact updateContact(Long id,Contact contact);

    public void deleteContact(Long id);

}
