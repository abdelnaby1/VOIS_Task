package DataManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class JsonFileForDDTManager {
    String jsonFilePath;
    String jsonContent;
    public JsonFileForDDTManager(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        try {
            jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
           e.printStackTrace();
           Assert.fail(e.getMessage());
        }
    }
    public List<HashMap<Object,Object>> getJsonDataToMap(){
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<Object,Object>> data = null;
        try {
            data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<Object,Object>>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        return data;
    }
}
