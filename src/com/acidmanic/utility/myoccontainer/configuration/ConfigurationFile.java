/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration;

import com.acidmanic.utility.myoccontainer.ResolvationMapRecordDictionary;
import com.acidmanic.utility.myoccontainer.configuration.serialization.MapRecordSerializer;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 *
 * @author diego
 */
public class ConfigurationFile {

    private final ResolvationMapRecordDictionary dependancyMap = new ResolvationMapRecordDictionary();

    private void addLine(String line) {

        try {
            MapRecord record = new MapRecordSerializer()
                    .deserialize(line);
            this.dependancyMap.put(record);
        } catch (Exception e) {
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

    public ResolvationMapRecordDictionary getDependancyMap() {
        return this.dependancyMap.clone();
    }

    public void save(String filepath) throws Exception {
        ConfigurationFile.save(filepath, this.dependancyMap);
    }

    public static void save(String filepath, ResolvationMapRecordDictionary dependancies) throws Exception {
        StringBuilder sb = new StringBuilder();
        MapRecordSerializer serializer = new MapRecordSerializer();
        for (MapRecord record : dependancies.toList()) {
            sb.append(serializer.serialize(record)).append("\n");
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
