package org.example.homework;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Designer extends Person {

    private String tool;

    public Designer(String name, java.time.LocalDate birthDate, String tool) {
        super(name, birthDate);
        this.tool = tool;
    }
}
