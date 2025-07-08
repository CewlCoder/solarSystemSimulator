package view.renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle2D {
    private Vertex2D a;
    private Vertex2D b;
    private Vertex2D c;

    private Vector2D ab;
    private Vector2D ac;
    private Vector2D bc;

    protected Triangle2D(Vertex2D a, Vertex2D b, Vertex2D c) {
        List<Vertex2D> ySorted = sortVerticiesBasedOnY(a, b, c);

        this.a = ySorted.get(0);
        this.b = ySorted.get(1);
        this.c = ySorted.get(2);

        this.ab = this.b.subtract(this.a);
        this.ac = this.c.subtract(this.a);
        this.bc = this.c.subtract(this.b);
    }

    private List<Vertex2D> sortVerticiesBasedOnY(Vertex2D a, Vertex2D b, Vertex2D c) {
        List<Vertex2D> vertecies = Arrays.asList(a, b, c);
        vertecies.sort((vertex1, vertex2) -> Integer.compare(vertex1.y(), vertex2.y()));

        return vertecies;
    }

    public Vertex2D a() {
        return a;
    }

    public Vertex2D c() {
        return c;
    }



    private int xBasedOnYWithinEdge(int y, Vector2D edgeStart, Vector2D edgeVector) {
        double scalingFactor = (double) edgeVector.x() / (double) edgeVector.y();

        return (int) Math.round(edgeStart.x() + (y - edgeStart.y()) * scalingFactor);
    }

    private boolean isCollidingWithEdge(int y, Vector2D edgeStart, Vector2D edgeVector) {
        int yMin = edgeStart.y();
        int yMax = edgeStart.y() + edgeVector.y();

        return ((yMin <= y) & (y <= yMax));
    }

    public List<Integer> xTriangleBounds(int y) {
        List<Integer> bounds = new ArrayList<>();

        if (isCollidingWithEdge(y, a, ab)) bounds.add(xBasedOnYWithinEdge(y, a, ab));
        if (isCollidingWithEdge(y, a, ac)) bounds.add(xBasedOnYWithinEdge(y, a, ac));
        if (isCollidingWithEdge(y, b, bc)) bounds.add(xBasedOnYWithinEdge(y, b, bc));

        int xMinTriangleBound = bounds.get(0);
        int xMaxTriangleBound = bounds.get(1);

        if (xMaxTriangleBound < xMinTriangleBound) {
            int oldMax = xMinTriangleBound;

            xMinTriangleBound = xMaxTriangleBound;
            xMaxTriangleBound = oldMax;
        }

        return Arrays.asList(xMinTriangleBound, xMaxTriangleBound);
    }
}
