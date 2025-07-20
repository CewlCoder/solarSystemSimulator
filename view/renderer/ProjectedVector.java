package view.renderer;

import graphics3D.Vector3D;

public class ProjectedVector implements Comparable<ProjectedVector> {
    private int x;
    private int y;
    private double depth;

    protected ProjectedVector(int x, int y, double depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    /**
     * Gets the x component of this vector.
     * 
     * @return the x component of this vector
     */
    public int x() {
        return x;
    }

    /**
     * Gets the y component of this vector.
     * 
     * @return the y component of this vector
     */
    public int y() {
        return y;
    }

    /**
     * Gets the depth of this vector.
     * 
     * @return the depth of this vector
     */
    public double depth() {
        return depth;
    }



    /**
     * Compares this vector's y value with the other vector's y value.
     * 
     * @return the difference between the y values
     */
    public int compareTo(ProjectedVector other) {
        return y - other.y();
    }

    /**
     * Creates a new vector which is other subtracted onto this vector.
     * 
     * @param other vector to subtract by
     * @return the subtracted vector
     */
    public ProjectedVector subtract(ProjectedVector other) {
        int x = this.x() - other.x();
        int y = this.y() - other.y();
        double depth = this.depth() - other.depth();

        return new ProjectedVector(x, y, depth);
    }

    /**
     * Calculates the cross product between this vector and other.
     * 
     * @param other second factor of the cross product
     * @return the cross product
     */
    public Vector3D cross(ProjectedVector other) {
        double x = this.y * other.depth() - this.depth * other.y();
        double y = this.depth * other.x() - this.x() * other.depth();
        double depth = this.x * other.y() - this.y * other.x();

        return new Vector3D(x, y, depth);
    }
}
