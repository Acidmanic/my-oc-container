/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration;

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

    private final HashMap<Class, Class> dependancyMap = new HashMap<>();

    private void addLine(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length == 2) {
            Class tfrom;
            Class tto;
            try {
                tfrom = Class.forName(parts[0]);
                tto = Class.forName(parts[1]);
            } catch (Exception e) {
                return;
            }
            this.dependancyMap.put(tfrom, tto);
        }
    }

    
    private void laodFile(String path){
        List<String>  lines ;
        try {
            lines = Files.readAllLines(Paths.get(path));
            for(String line:lines){
                addLine(line);
            }
        } catch (Exception e) {
        }
    }

    public ConfigurationFile(String filePath) {
        this.laodFile(filePath);
    }
    
    public HashMap<Class,Class> getDependancyMap(){
        return (HashMap<Class,Class>)this.dependancyMap.clone();
    }
    
    public void save(String filepath) throws Exception{
        ConfigurationFile.save(filepath, this.dependancyMap);
    }
    
    public static void save(String filepath,HashMap<Class,Class> dependancies) throws Exception{
        StringBuilder sb = new StringBuilder();
        for (Class key:dependancies.keySet()){
            Class value = dependancies.get(key);
            sb.append(key.getName()).append("\t\t")
                    .append(value.getName()).append("\n");
        }
        File f = new File(filepath);
        try {
            if(f.exists()){
            f.delete();
        }
        } catch (Exception e) {
            throw new Exception("Unable to write to file.");
        }
        Files.write(Paths.get(filepath), sb.toString().getBytes(), 
                StandardOpenOption.CREATE);
    }
}
