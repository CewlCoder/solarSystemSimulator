package graphics3D.renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle2D {
    private Vertex2D a;
    private Vertex2D b;
    private Vertex2D c;    

    protected Triangle2D(Vertex2D a, Vertex2D b, Vertex2D c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public List<Vertex2D> vertices() {
        List<Vertex2D> verticies = new ArrayList<>();

        verticies.add(a);
        verticies.add(b);
        verticies.add(c);

        return verticies;
    }



    private int getMin(int a, int b, int c) {
        int min = a;

        if (b < min) {
            min = b;
        }
        if (c < min) {
            min = c;
        }

        return min;
    }

    private int getMax(int a, int b, int c) {
        int max = a;

        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }

        return max;
    }

    public List<List<Integer>> boundingBox() {
        List<Integer> xBounds = new ArrayList<>();
        List<Integer> yBounds = new ArrayList<>();

        xBounds.add(getMin(a.x(), b.x(), c.x()));
        xBounds.add(getMax(a.x(), b.x(), c.x()));

        yBounds.add(getMin(a.y(), b.y(), c.y()));
        yBounds.add(getMax(a.y(), b.y(), c.y()));

        return Arrays.asList(xBounds, yBounds);
    }

    public boolean isWithinTriangle(Vector2D pixel) {
        Vector2D ab = b.subtract(a);
        Vector2D bc = c.subtract(b);
        Vector2D ca = a.subtract(c);

        Vector2D ap = pixel.subtract(a);
        Vector2D bp = pixel.subtract(b);
        Vector2D cp = pixel.subtract(c);

        Vector2D abn = new Vector2D(ab.y(), -ab.x());
        Vector2D bcn = new Vector2D(bc.y(), -bc.x());
        Vector2D can = new Vector2D(ca.y(), -ca.x());

        boolean isWithinAB = ap.dot(abn) > 0;
        boolean isWithinBC = bp.dot(bcn) > 0;
        boolean isWithinCA = cp.dot(can) > 0;

        return isWithinAB & isWithinBC & isWithinCA;
    }
}
