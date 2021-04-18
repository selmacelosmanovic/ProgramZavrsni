package ba.unsa.etf.rpr;

import java.util.Objects;

public class Metric {
    private String name;
    private double value;

    public Metric(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Metric(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metric metric = (Metric) o;
        return name.equals(metric.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
