package graphics3D;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Mesh {
    private List<Triangle3D> triangles;
    private Color color;

    public Mesh(List<Triangle3D> triangles, Color color) {
        this.triangles = triangles;
        this.color = color;
    }

    public List<Triangle3D> triangles() {
        return triangles;
    }

    public Color color() {
        return color;
    }



    public void shift(Vector3D offset) {
        List<Triangle3D> newTriangles = new ArrayList<>();

        for (Triangle3D triangle : triangles) {
            Vector3D a = triangle.a().add(offset);
            Vector3D b = triangle.b().add(offset);
            Vector3D c = triangle.c().add(offset);

            newTriangles.add(new Triangle3D(a, b, c));
        }

        triangles = newTriangles;
    }
}
