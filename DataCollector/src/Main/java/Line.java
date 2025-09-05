import java.util.Objects;

public class Line {
    private String nameLine;
    private String numberLine;

    public Line(String name, String number) {
        this.nameLine = name;
        this.numberLine = number;

    }

    public String getNumberLine() {
        return numberLine;
    }

    public String getNameLine() {
        return nameLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(nameLine, line.nameLine) && Objects.equals(numberLine, line.numberLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameLine, numberLine);
    }

    @Override
    public String toString() {
        return "nameLine=" + nameLine +
                ", numberLine=" + numberLine;
    }
}
