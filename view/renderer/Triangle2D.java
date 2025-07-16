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

    /**
     * Calculates the vertical bounding box of this triangle.
     * 
     * @return the vertical bounding box (min, max)
     */
    public List<Integer> verticalBoundingBox() {
        return Arrays.asList(a.y(), c.y());
    }



    private int xAtYIntersection(int y, Vector2D edgeStart, Vector2D edgeVector) {
        double scalingFactor = (double) edgeVector.x() / (double) edgeVector.y();

        return (int) Math.round(edgeStart.x() + (y - edgeStart.y()) * scalingFactor);
    }

    private boolean isIntersectingWithEdge(int y, Vector2D edgeStart, Vector2D edgeVector) {
        int yMin = edgeStart.y();
        int yMax = edgeStart.y() + edgeVector.y();

        return ((yMin <= y) & (y <= yMax));
    }

    /**
     * Calculates the x value of the two intersections by a line at y thru this triangle.
     * 
     * @param y the function value of the line
     * @return the two intersection points (min, max)
     */
    public List<Integer> lineIntersections(int y) {
        List<Integer> intersections = new ArrayList<>();

        if (isIntersectingWithEdge(y, a, ab)) intersections.add(xAtYIntersection(y, a, ab));
        if (isIntersectingWithEdge(y, a, ac)) intersections.add(xAtYIntersection(y, a, ac));
        if (isIntersectingWithEdge(y, b, bc)) intersections.add(xAtYIntersection(y, b, bc));

        int xMinIntersection = intersections.get(0);
        int xMaxIntersection = intersections.get(1);

        if (xMaxIntersection < xMinIntersection) {
            int oldMax = xMinIntersection;

            xMinIntersection = xMaxIntersection;
            xMaxIntersection = oldMax;
        }

        return Arrays.asList(xMinIntersection, xMaxIntersection);
    }
}
