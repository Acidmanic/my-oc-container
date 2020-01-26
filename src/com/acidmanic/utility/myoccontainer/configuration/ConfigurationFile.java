/* 
 * Copyright (C) 2018 Mani Moayedi (acidmanic.moayedi@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acidmanic.utility.myoccontainer.configuration;

import com.acidmanic.utility.myoccontainer.configuration.data.Dependency;
import com.acidmanic.utility.myoccontainer.configuration.data.DependencySafeSaveValidator;
import com.acidmanic.utility.myoccontainer.configuration.serialization.DependencySerializer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 *
 * @author diego
 */
public class ConfigurationFile {

    private final DependencyDictionary dependancyMap = new DependencyDictionary();

    private void addLine(String line) {

        try {
            Dependency record = new DependencySerializer()
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

    public DependencyDictionary getDependancyMap() {
        return this.dependancyMap.clone();
    }

    public void save(String filepath) throws Exception {
        ConfigurationFile.save(filepath, this.dependancyMap);
    }

    public static void save(String filepath, DependencyDictionary dependancies) throws Exception {
        StringBuilder sb = new StringBuilder();
        DependencySerializer serializer = new DependencySerializer();
        DependencySafeSaveValidator validator = new DependencySafeSaveValidator();
        for (Dependency record : dependancies.toList()) {
            if (validator.isSaveSafe(record)) {
                sb.append(serializer.serialize(record)).append("\n");
            }
        }
        File f = new File(filepath);
        try {
            if (f.exists()) {
                f.delete();
            }
        } catch (Exception e) {
            throw new IOException("Unable to write to file.");
        }
        Files.write(Paths.get(filepath), sb.toString().getBytes(),
                StandardOpenOption.CREATE);
    }
}
