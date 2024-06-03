package galeriacasasubasta;

import java.awt.Color;

public class ModeloGrafico {

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValues() {
        return values;
    }

    public void setValues(double values) {
        this.values = values;
    }

    public ModeloGrafico(Color color, String name, double values) {
        this.color = color;
        this.name = name;
        this.values = values;
    }

    public ModeloGrafico() {
    }

    private Color color;
    private String name;
    private double values;
}
