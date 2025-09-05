
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FolderSearch {
    public static List<Path> walkAndFindFilesJSON(String path) throws IOException {
        List<Path> list = new ArrayList<>();
        list.addAll(Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(file -> file.toString().endsWith(".json"))
                .collect(Collectors.toList()));
        return list;
    }

    public static List<Path> walkAndFindFilesCSV(String path) throws IOException {
        List<Path> list = new ArrayList<>();
        list.addAll(Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(file -> file.toString().endsWith(".csv"))
                .collect(Collectors.toList()));
        return list;
    }

    public static List<Path> walkAllFiles(String path) throws IOException {
        ArrayList<Path> list = new ArrayList<>();
        list.addAll(walkAndFindFilesJSON(path));
        list.addAll(walkAndFindFilesCSV(path));
        return list;
    }
}
