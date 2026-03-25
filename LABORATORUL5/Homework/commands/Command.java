package org.example.homework.commands;

import org.example.homework.repository.Catalog;

public interface Command {

    void execute(Catalog catalog) throws Exception;
}
