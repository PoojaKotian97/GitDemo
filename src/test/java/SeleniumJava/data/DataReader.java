package SeleniumJava.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

		// read json to string
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "//src//test//java//SeleniumJava//data//PurchaseOrder.json"), StandardCharsets.UTF_8);// 2nd argument is encoding format

		// string to HashMap Jakson DataBind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				}); // convert to List of Hash map i.e {map},{map}
		return data;
	}

}
