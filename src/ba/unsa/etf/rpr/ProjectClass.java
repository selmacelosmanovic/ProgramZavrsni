package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Objects;

public class ProjectClass {
    private String name;
    private String path;
    private ArrayList<Metric> metrics = new ArrayList<>();


    public ProjectClass(String name, String path, ArrayList<Metric> metrics) {
        this.name = name;
        this.path = path;
        this.metrics = metrics;
    }

    public ProjectClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(ArrayList<Metric> metrics) {
        this.metrics = metrics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectClass that = (ProjectClass) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public ProjectClass(String name) {
        this.name = name;
    }
}
