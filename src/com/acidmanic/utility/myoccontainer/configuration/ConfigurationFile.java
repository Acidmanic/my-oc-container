/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration;

import com.acidmanic.utility.myoccontainer.DependancyDictionary;
import com.acidmanic.utility.myoccontainer.TaggedClass;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author diego
 */
public class ConfigurationFile {

    private final DependancyDictionary dependancyMap = new DependancyDictionary();

    private void addLine(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length == 3) {
            Class tfrom;
            Class tto;
            String tag;
            try {
                tfrom = Class.forName(parts[0]);
                tag = parts[1].trim();
                tto = Class.forName(parts[2]);
                this.dependancyMap.put(new TaggedClass(tag, tfrom), tto);
            } catch (Exception e) {
            }
        }
    }

    private void laodFile(String path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                addLine(line);
            }
        } catch (Exception e) {
        }
    }

    public ConfigurationFile(String filePath) {
        this.laodFile(filePath);
    }

    public DependancyDictionary getDependancyMap() {
        return this.dependancyMap.clone();
    }

    public void save(String filepath) throws Exception {
        ConfigurationFile.save(filepath, this.dependancyMap);
    }

    public static void save(String filepath, DependancyDictionary dependancies) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (TaggedClass key : dependancies.keySet()) {
            Class value = dependancies.get(key);
            sb.append(key.getType().getName()).append("\t\t")
                    .append(key.getTag()).append("\t\t")
                    .append(value.getName()).append("\n");
        }
        File f = new File(filepath);
        try {
            if (f.exists()) {
                f.delete();
            }
        } catch (Exception e) {
            throw new Exception("Unable to write to file.");
        }
        Files.write(Paths.get(filepath), sb.toString().getBytes(),
                StandardOpenOption.CREATE);
    }
}
