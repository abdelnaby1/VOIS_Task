package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesReader {
    private static String path = "src/main/resources/";

    public static void loadProperties(){
        Properties properties = new Properties();
        Collection<File> files;
        files = FileUtils.listFiles(new File(path),new String[]{"properties"},true);
        files.forEach(file -> {
            try{
                properties.load(new FileInputStream(file));
            } catch (FileNotFoundException e) {
               e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            properties.putAll(System.getProperties());
            System.getProperties().putAll(properties);
        });

    }
}
