package com.app.agendacontact.entitie;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Contact")

public class Contact {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Please type the name")
    String name;
    @NotEmpty(message = "Please type the Email")
    @Email
    String email;
    @NotBlank(message = "Please type the telephone")
    String telephone;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @NotNull(message = "Please chose the firstdate")
    LocalDate firstDate;
    LocalDateTime registrationDate;

    @PrePersist
    public void assignRegisDate() {
        registrationDate = LocalDateTime.now();
    }
}
