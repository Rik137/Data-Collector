import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String url = "https://skillbox-java.github.io/";
    private static final String path = "DataCollector/src/Main/resources/stations-data";
    private static final String path1 = "File.json";
    private static final String path3 = "File1.json";
    private static final String path2 = "Data.html";
    public static void main(String[] args) throws Exception {
        System.out.println("Task No1 Parsync Web Pages");
        WebParser webPageParsing = new WebParser(url);
        List<Line> lines = webPageParsing.parseLine(path2);
        webPageParsing.printLines(lines);
        List<Station> stations = webPageParsing.parseStation(path2);
        webPageParsing.printStations(stations);
        System.out.println("Task No2 File search");
        System.out.println(FolderSearch.walkAllFiles(path));
        System.out.println(FolderSearch.walkAndFindFilesCSV(path));
        System.out.println(FolderSearch.walkAndFindFilesJSON(path));
        System.out.println("Task No3 Parsing JSON Files");
        List<Station> stationsFromJsonFiles = ParserFileJson.parsingJson(FolderSearch.walkAndFindFilesJSON(path));
        ParserFileJson.printJsonFile(stationsFromJsonFiles);
        System.out.println("зTask No4 Parsing Csv Files");
        List<Station> stationsFromCsvFiles = ParserFileCsv.parsingCsv(FolderSearch.walkAndFindFilesCSV(path));
        ParserFileCsv.printCsvFiles(stationsFromCsvFiles);
        CreatesAndWritesToDisk createsAndWritesToDisk = new CreatesAndWritesToDisk();
        List<Station> newStation = createsAndWritesToDisk.getNewStation(stations,stationsFromJsonFiles,stationsFromCsvFiles);
        createsAndWritesToDisk.writeStationsToFile(path1, newStation);
        createsAndWritesToDisk.writeStationsToFile1(path3, newStation);

    }
}
