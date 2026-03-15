package org.example.homework;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Programmer extends Person {

    private String favoriteLanguage;

    public Programmer(String name, java.time.LocalDate birthDate, String favoriteLanguage) {
        super(name, birthDate);
        this.favoriteLanguage = favoriteLanguage;
    }
}