import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class Station {
    private String stationName;
    private String depth;
    private LocalDate date;
    private Boolean transitions;

    private Line line;

    public Station(String stationName, String depth, LocalDate date, Boolean transitions, Line line) {
        this.stationName = stationName;
        this.depth = depth;
        this.date = date;
        this.transitions = transitions;
        this.line = line;
    }

    public Station(String stationName, Boolean transitions, Line line) {
        this(stationName, null, null, transitions,line);
    }

    public Station(String stationName, LocalDate date) {
        this(stationName, null, date, null, null);
    }
    public Station(String stationName, String depth){
        this(stationName, depth, null, null,null);
    }

    public String getStationName() {
        return stationName;
    }

    public String getDepth() {
        return depth;
    }

    public LocalDate getDate() {
        return date;
    }

    public Boolean getTransitions() {
        return transitions;
    }



    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTransitions(Boolean transitions) {
        this.transitions = transitions;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(stationName, station.stationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName);
    }

    @Override
    public String toString() {
        return  "name=" + stationName +
                ", depth=" + depth +
                ", date=" + date +
                ", transitions=" + transitions +
                ", numberLine=" + line;
    }
}
