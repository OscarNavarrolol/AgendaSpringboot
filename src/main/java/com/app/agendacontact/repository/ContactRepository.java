package com.app.agendacontact.repository;

import com.app.agendacontact.entitie.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {

}
