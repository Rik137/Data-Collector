import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParserFileCsv {
    public static List<Station> parsingCsv(List<Path> list) throws Exception {
        List<Station> stations = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (Path path : list) {
            if (path.toString().endsWith(".csv")) {
                String csvFile = path.toString();

                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    br.readLine();
                    String str;
                    while ((str = br.readLine()) != null) {
                        String[] data = str.split("\n");
                        for (int i = 0; i < data.length; i++) {
                            String[] part = data[i].split(",");
                            String name = part[0];
                            LocalDate date = LocalDate.parse(part[1], formatter);
                            Station station = new Station(name, date);
                            stations.add(station);
                        }
                    }
                }
            } else {
                throw new NotFoundFileJsonOrCsvException("CSV file was not found or there are files of a different format");
            }
        }
        return stations;
    }
    public static void printCsvFiles(List<Station> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ". Station -> " + list.get(i).getStationName() +
                    " , date: " + list.get(i).getDate());
        }
    }
}
