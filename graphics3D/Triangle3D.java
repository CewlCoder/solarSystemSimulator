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


    public List<Vector3D> vertices() {
        return Arrays.asList(a, b, c);
    }

    public Vector3D a() {
        return a;
    }

    public Vector3D b() {
        return b;
    }

    public Vector3D c() {
        return c;
    }
}
