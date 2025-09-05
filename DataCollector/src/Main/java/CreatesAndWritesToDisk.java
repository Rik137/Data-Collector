
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreatesAndWritesToDisk {

    public List<Station> getNewStation(List<Station> list1, List<Station> list2, List<Station> list3) {
        List<Station> newListStations = new ArrayList<>();
        list1.forEach(st1 -> {
            list2.stream().filter(st2 -> st1.getStationName().equals(st2.getStationName())).forEach(st2 -> updateStationFields(st1, st2));
            list3.stream().filter(st3 -> st1.getStationName().equals(st3.getStationName())).forEach(st3 -> updateStationFields(st1, st3));
            newListStations.add(st1);
        });
        return newListStations;
    }

    private void updateStationFields(Station st1, Station st2) {
        if (st1.getDepth() == null) st1.setDepth(st2.getDepth());
        if (st1.getDate() == null) st1.setDate(st2.getDate());
        if (st1.getTransitions() == null) st1.setTransitions(st2.getTransitions());
        if (st1.getLine() == null) st1.setLine(st2.getLine());
    }

    public void writeStationsToFile(String fileName, List<Station> stations) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ArrayNode stationsArray = mapper.createArrayNode();
        stations.forEach(station -> stationsArray.add(createStation(mapper, station.getStationName(),
                station.getLine().getNumberLine(), station.getDate(), station.getDepth(), station.getTransitions())));
        root.set("stations", stationsArray);
        writeToFile(fileName, mapper, root);
    }

    public void writeStationsToFile1(String fileName, List<Station> stations) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        stations.forEach(station -> root.set(station.getLine().getNumberLine(), createStation(mapper, station.getLine().getNumberLine(), station.getStationName())));
        writeToFile(fileName, mapper, root);
    }

    private void writeToFile(String fileName, ObjectMapper mapper, ObjectNode root) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ObjectNode createStation(ObjectMapper mapper, String name, String numberLine, LocalDate date,
                                     String depth, boolean transitions) {
        ObjectNode station = mapper.createObjectNode();
        station.put("name", name);
        station.put("line", numberLine);
        station.put("date", String.valueOf(date));
        station.put("depth", depth);
        station.put("hasConnection", transitions);
        return station;
    }

    private ObjectNode createStation(ObjectMapper mapper, String numberName, String stationName) {
        ObjectNode station = mapper.createObjectNode();
        station.put("namberName", numberName);
        station.put("Stations", stationName);
        return station;
    }
}

