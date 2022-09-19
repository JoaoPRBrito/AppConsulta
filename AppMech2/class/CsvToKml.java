import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvToKml {
    public static void convert(String csvFilePath) {

        String initXML = "<?xml version=\"%s\" encoding=\"%s\"?>\n";
        String initKML = "<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\">\n";
        String initDocument = "    <Document id=\"%d\">\n";
        String initPlacemark = "        <Placemark id=\"%d\">\n";
        String initAndCloneName = "            <name>%s</name>\n";  // X
        String initPointID = "            <Point id=\"%d\">\n";
        String writeCoordinates = "                <coordinates>%s,%s,0.0</coordinates>\n";  // (Y, X)
        String closePointID = "            </Point>\n";
        String closePlacemark = "        </Placemark>\n";
        String closeDocument = "    </Document>\n";
        String closeKML = "</kml>\n";

        try {
            FileWriter fileWriter = new FileWriter("generated.kml");

            ReadCsvFile.readCsv(csvFilePath);

            List<Double> latitude  = ReadCsvFile.get_Latitude();
            List<Double> longitude = ReadCsvFile.get_Longitude();


            fileWriter.write(String.format(initXML, "1.0", "UTF-8"));
            fileWriter.write(initKML);
            fileWriter.write(String.format(initDocument, 1));


            fileWriter.write("        <Placemark id=\"3\">\n");
            fileWriter.write("            <name>x</name>\n");
            fileWriter.write("            <Point id=\"2\">\n");
            fileWriter.write("                <coordinates>y,x,0.0</coordinates>\n");

            fileWriter.write(closePointID);
            fileWriter.write(closePlacemark);


            int pointID = 0;
            int placemark = 0;
            int index = 0;
            int count = 2;

            int pointIDCount = 0;
            int placemarkCount = 0;

            for (int i=0; i < latitude.size(); i++) {

                fileWriter.write(String.format(initPlacemark, (i + 5 + index)));  // 5,
                fileWriter.write(String.format(initAndCloneName, latitude.get(i).toString()));

                fileWriter.write(String.format(initPointID, (i + 4 + index)));  // 4
                fileWriter.write(String.format(writeCoordinates, longitude.get(i).toString(), latitude.get(i).toString()));
                fileWriter.write(closePointID);
                fileWriter.write(closePlacemark);

                index += 1;
            }

            fileWriter.write(closeDocument);
            fileWriter.write(closeKML);

            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

