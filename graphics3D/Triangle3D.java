package graphics3D;

import java.util.Arrays;
import java.util.List;

public class Triangle3D {
    private Vector3D a;
    private Vector3D b;
    private Vector3D c;

    /**
     * Creates a 3D triangle defined by the vertices a, b and c.
     * 
     * @param a first vertex of triangle
     * @param b second vertex of triangle
     * @param c third vertex of triangle
     */
    public Triangle3D(Vector3D a, Vector3D b, Vector3D c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    /**
     * A list of this triangle's vertices.
     * 
     * @return this triangle's vertices
     */
    public List<Vector3D> vertices() {
        return Arrays.asList(a, b, c);
    }

    /**
     * Gets the first vertex of this triangle.
     * 
     * @return the first vertex
     */
    public Vector3D a() {
        return a;
    }

    /**
     * Gets the second vertex of this triangle.
     * 
     * @return the second vertex
     */
    public Vector3D b() {
        return b;
    }

     /**
     * Gets the third vertex of this triangle.
     * 
     * @return the third vertex
     */
    public Vector3D c() {
        return c;
    }
}
