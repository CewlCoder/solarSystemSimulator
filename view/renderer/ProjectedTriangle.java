package view.renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graphics3D.Vector3D;

public class ProjectedTriangle {
    private ProjectedVector a;
    private ProjectedVector b;
    private ProjectedVector c;

    private ProjectedVector ab;
    private ProjectedVector ac;
    private ProjectedVector bc;

    private Vector3D normal;

    protected ProjectedTriangle(ProjectedVector a, ProjectedVector b, ProjectedVector c) {
        ProjectedVector[] ySorted = sortVerticiesBasedOnY(a, b, c);

        this.a = ySorted[0];
        this.b = ySorted[1];
        this.c = ySorted[2];

        this.ab = this.b.subtract(this.a);
        this.ac = this.c.subtract(this.a);
        this.bc = this.c.subtract(this.b);

        this.normal = this.ab.cross(this.ac);
    }

    private ProjectedVector[] sortVerticiesBasedOnY(ProjectedVector a, ProjectedVector b, ProjectedVector c) {
        ProjectedVector[] vertecies = new ProjectedVector[] {a, b, c};
        Arrays.sort(vertecies);

        return vertecies;
    }

    /**
     * Calculates the vertical bounding box of this triangle.
     * 
     * @return the vertical bounding box (min, max)
     */
    public int[] verticalBoundingBox() {
        return new int[] {a.y(), c.y()};
    }



    private int xAtYIntersection(int y, ProjectedVector edgeStart, ProjectedVector edgeVector) {
        double scalingFactor = (double) edgeVector.x() / (double) edgeVector.y();

        return (int) Math.round(edgeStart.x() + (y - edgeStart.y()) * scalingFactor);
    }

    private boolean isIntersectingWithEdge(int y, ProjectedVector edgeStart, ProjectedVector edgeVector) {
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
    public int[] lineIntersections(int y) {
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

        return new int[] {xMinIntersection, xMaxIntersection};
    }

    /**
     * Calculates the depth within this triangle based on given x and y coordinates.
     * 
     * @param x x coordinate within this triangle
     * @param y y cooridnate within this triangle
     * @return z cooridnate within this trangle based on x and y
     */
    public double depth(int x, int y) {
        double xPoint = x - a.x();
        double yPoint = y - a.y();

        return (normal.z() * a.depth() - normal.x() * xPoint - normal.y() * yPoint) / normal.z();
    }
}
