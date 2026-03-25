package org.example.homework.commands;

import lombok.NoArgsConstructor;

import org.example.homework.repository.Catalog;

@NoArgsConstructor
public class ListCommand implements Command {

    @Override
    public void execute(Catalog catalog) {

        catalog.getAllResources().forEach(System.out::println);
    }
}
