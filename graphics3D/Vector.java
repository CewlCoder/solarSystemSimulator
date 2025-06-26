package graphics3D;

import java.util.ArrayList;
import java.util.List;

public class Vector {
    private List<Double> components;

    /**
     * Creates a vector defined by an x and y coordinate.
     * 
     * @param x x coordinate
     * @param y y coordinate
     */
    public Vector(double x, double y) {
        components = new ArrayList<>();

        components.add(x);
        components.add(y);
    }

    /**
     * Creates a vector defined by an x, y and z coordinate.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Vector(double x, double y, double z) {
        components = new ArrayList<>();

        components.add(x);
        components.add(y);
        components.add(z);
    }



    public double angleBetween(Vector other) {
        float dotProduct = this.dot(other);

        return Math.acos(dotProduct / (this.length() * other.length()));
    }

    public float dot(Vector other) {
        float sum = 0;

        sum += this.x() * other.x();
        sum += this.y() * other.y();

        if (this.dimension() == 3 & other.dimension() == 3) {
            sum += this.z() * other.z();
        }

        return sum;
    }

    public double length() {
        float sum = 0;

        for (double component : components) {
            sum += component * component;
        }

        return Math.sqrt(sum);
    }



    public Vector subtract(Vector other) {
        double x = this.x() - other.x();
        double y = this.y() - other.y();

        if (this.dimension() == 3 & other.dimension() == 3) {
            double z = this.z() - other.z();

            return new Vector(x, y, z);
        }

        return new Vector(x, y);
    }

    public Vector add(Vector other) {
        double x = this.x() + other.x();
        double y = this.y() + other.y();

        if (this.dimension() == 3 & other.dimension() == 3) {
            double z = this.z() + other.z();

            return new Vector(x, y, z);
        }

        return new Vector(x, y);
    }

    public Vector scale(double scale) {
        double x = this.x() * scale;
        double y = this.y() * scale;

        if (this.dimension() == 3) {
            double z = this.z() * scale;

            return new Vector(x, y, z);
        }

        return new Vector(x, y);
    }



    public int dimension() {
        return components.size();
    }

    public double x() {
        return components.get(0);
    }

    public double y() {
        return components.get(1);
    }

    public double z() {
        if (this.dimension() == 2) {
            throw new IllegalArgumentException("Cannot get z component of 2D vector");
        }

        return components.get(2);
    }

}
