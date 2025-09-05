import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ParserFileJson {

    public static List<Station> parsingJson(List<Path> list) throws Exception {
        List<Station> stations = new ArrayList<>();
        for (Path path : list) {
            if(path.toString().endsWith(".json")) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(new File(path.toUri()));
                Iterator<JsonNode> elements = jsonNode.elements();
                while (elements.hasNext()) {
                    JsonNode next = elements.next();
                    String stationName = next.get("station_name").asText();
                    String depth = next.get("depth").asText();
                    Station station = new Station(stationName, depth);
                    stations.add(station);
                }
            }else {
                throw new NotFoundFileJsonOrCsvException("не найдено JSON файл или имеются файлы иного формата");
            }
        }
        return stations;
    }

    public static void printJsonFile(List<Station> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ". Станция -> " + list.get(i).getStationName() +
                    " , глубина: " + list.get(i).getDepth());
        }
    }
}
