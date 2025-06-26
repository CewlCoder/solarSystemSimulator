package graphics3D.renderer;

import graphics3D.Triangle;
import graphics3D.Vector;

public class Projector {
    private int width;
    private int height;

    /**
     * Creates a projector which handles world space and screen space.
     * 
     * @param width screen width
     * @param height screen height
     */
    public Projector(int width, int height) {
        this.width = width;
        this.height = height;
    }



    private Vector project(Vector vertex) {
        float screenSpaceWidth = 5;
        float screenSpaceToPixelRatio = width / screenSpaceWidth;

        double xScreenSpace = vertex.x() / vertex.z();
        double yScreenSpace = vertex.y() / vertex.z();

        double xPixelSpace = width / 2 + (screenSpaceToPixelRatio * xScreenSpace);
        double yPixelSpace = (height - screenSpaceToPixelRatio * yScreenSpace) - height / 2;

        return new Vector(Math.round(xPixelSpace), Math.round(yPixelSpace), vertex.z());
    }

    protected Triangle projectTriangle(Triangle triangle) {
        Vector projectedA = project(triangle.a());
        Vector projectedB = project(triangle.b());
        Vector projectedC = project(triangle.c());

        return new Triangle(projectedA, projectedB, projectedC);
    }

}
