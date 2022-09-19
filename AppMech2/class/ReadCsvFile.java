import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadCsvFile {
    private static final List<Integer> treeID = new ArrayList<Integer>();
    private static final List<Integer> treeUT = new ArrayList<Integer>();
    private static final List<Double> latitude = new ArrayList<Double>();
    private static final List<Double> longitude = new ArrayList<Double>();
    private static final List<String> treePopularName = new ArrayList<String>();

    public static List<Integer> get_TreeID() {
        return treeID;
    }
    public static List<Integer> get_TreeUT() {
        return treeUT;
    }
    public static List<String> get_TreePopularName() {
        return treePopularName;
    }
    public static List<Double> get_Latitude() {
        return latitude;
    }
    public static List<Double> get_Longitude() {
        return longitude;
    }
    public static void readCsv(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] valuesOfLine = line.split(";");
                treeID.add(Integer.valueOf(valuesOfLine[0]));
                treeUT.add(Integer.valueOf(valuesOfLine[2]));

                treePopularName.add(valuesOfLine[5]);

                latitude.add(Double.valueOf(valuesOfLine[13].replace(',', '.')));
                longitude.add(Double.valueOf(valuesOfLine[14].replace(',', '.')));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
