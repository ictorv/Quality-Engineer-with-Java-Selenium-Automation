package utilities;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DataReader {

    private final ExcelUtilityClass excel;

    public DataReader(String filePath, String sheetName) {
        this.excel = new ExcelUtilityClass(filePath, sheetName);
    }

    // ✅ Reads one row of Excel as key-value pairs
    public HashMap<String, String> getRowData(int rowIndex) {
        HashMap<String, String> rowData = new HashMap<>();
        int colCount = excel.getColumnCount();

        for (int colIndex = 0; colIndex < colCount; colIndex++) {
            String key = excel.getCellData(0, colIndex);
            String value = excel.getCellData(rowIndex, colIndex);
            rowData.put(key.trim(), value.trim());
        }

        return rowData;
    }

    public void close() {
        excel.closeWorkbook();
    }

    // ✅ Retrieves search terms from JSON array (used in TC006)
    public List<String> getJsonSearchTerms(String jsonPath, String arrayKey) {
        List<String> terms = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(jsonPath));
            JSONArray jsonArray = (JSONArray) jsonObject.get(arrayKey);

            for (Object obj : jsonArray) {
                terms.add(obj.toString().trim());
            }

        } catch (Exception e) {
            System.err.println("Failed to parse JSON from " + jsonPath + ": " + e.getMessage());
        }

        return terms;
    }

    // ✅ Retrieves XML service data as List of HashMaps (used in TC010)
    public List<HashMap<String, String>> getXmlServiceData(String xmlPath) {
        List<HashMap<String, String>> dataList = new ArrayList<>();

        try {
            File xmlFile = new File(xmlPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element service = (Element) doc.getElementsByTagName("Service").item(0);

            HashMap<String, String> row = new HashMap<>();
            row.put("Type", service.getElementsByTagName("Type").item(0).getTextContent().trim());
            row.put("MobileNumber", service.getElementsByTagName("MobileNumber").item(0).getTextContent().trim());
            row.put("Plan", service.getElementsByTagName("Plan").item(0).getTextContent().trim());

            dataList.add(row);

        } catch (Exception e) {
            System.err.println("Failed to parse XML: " + e.getMessage());
        }

        return dataList;
    }
}
