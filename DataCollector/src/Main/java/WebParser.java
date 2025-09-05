
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WebParser {
    private String url;
    WebParser(String url) {
        this.url = url;
    }

    private void getWriteFileHtmlPage(String path) throws IOException {
        Document document = Jsoup.connect(url).get();
        try (FileOutputStream textFile = new FileOutputStream(path)) {
            textFile.write(document.outerHtml().getBytes());
        }
    }

    private String parseFile(String path) throws IOException {
        getWriteFileHtmlPage(path);
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public List<Line> parseLine(String path) throws IOException {
        List<Line> list = new ArrayList<>();
        String text = parseFile(path);
        Document doc = Jsoup.parse(text);
        Elements elements = doc.select(".js-metro-line");
        for (Element element : elements) {
            String lineName = element.text();
            String lineNumber = element.attr("data-line");
            list.add(new Line(lineName, lineNumber));
        }
        return list;
    }

    public List<Station> parseStation(String path) throws IOException {
        List<Station> stations = new ArrayList<>();
        String text = parseFile(path);
        Document doc = Jsoup.parse(text);

        Elements lineElements = doc.select(".js-metro-line");
        for (Element lineElement : lineElements) {
            String lineNumber = lineElement.attr("data-line");
            String lineName = lineElement.text();
            Elements stationElements = doc.select(".js-metro-stations[data-line='" + lineNumber + "'] .single-station");
            Line line = new Line(lineName,lineNumber);
            for (Element stationElement : stationElements) {
                String stationName = stationElement.select(".name").text();
                boolean hasTransitions = !stationElement.select(".t-icon-metroln").isEmpty();
                stations.add(new Station(stationName, hasTransitions, line));
            }
        }
        return stations;
    }

    public void printLines(List<Line> lines) {
        lines.forEach(line -> System.out.println("Линия -> " + "название: " + line.getNameLine() +
                " , номер: " + line.getNumberLine()));
    }

    public void printStations(List<Station> stations) {
        stations.forEach(station -> System.out.println("Станция -> " + "название: " + station.getStationName() + " , линия "
                + station.getLine().getNumberLine()));
    }
}
