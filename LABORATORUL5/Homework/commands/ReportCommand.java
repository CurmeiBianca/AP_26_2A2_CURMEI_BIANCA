package org.example.homework.commands;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.homework.repository.Catalog;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class ReportCommand implements Command {

    private String outputPath;

    @Override
    public void execute(Catalog catalog) throws Exception {

        // Configuram motorul FreeMarker
        Configuration config = new Configuration(new Version("2.3.32"));

        config.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "templates");

        config.setDefaultEncoding("UTF-8");

        // Incarcam template-ul "catalog.ftl"
        Template template = config.getTemplate("catalog.ftl");

        Map<String, Object> data = new HashMap<>();

        data.put("resources", catalog.getAllResources());

        try (FileWriter writer = new FileWriter(outputPath)) {
            template.process(data, writer); // genereaza HTML-ul final
        }

        // Deschidem automat raportul in rowser-ul implicit al sistemului
        Desktop.getDesktop().open(new File(outputPath));

        System.out.println("Raport generat: " + outputPath);
    }
}
