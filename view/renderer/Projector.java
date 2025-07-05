package view.renderer;

import java.util.Arrays;
import java.util.List;

import graphics3D.Triangle3D;
import graphics3D.Vector3D;

public class Projector {
    private int width;
    private int height;

    private double screenSpaceWidth;
    private double screenSpaceToPixelSpaceRatio;

    /**
     * Creates a projector which handles conversion world space and screen space.
     * 
     * @param width screen width
     * @param height screen height
     */
    public Projector(int width, int height) {
        this.width = width;
        this.height = height;

        this.screenSpaceWidth = 2 * Math.tan(Math.toRadians(90 / 2));
        this.screenSpaceToPixelSpaceRatio = width / screenSpaceWidth;
    }



    private boolean isValidPixelSpaceCoordinates(double xPixelSpace, double yPixelPsace) {
        if ((xPixelSpace < 0) | (xPixelSpace > width)) {
            return false;
        }
        if ((yPixelPsace < 0) | (yPixelPsace > height)) {
            return false;
        }

        return true;
    }

    private Vertex2D project(Vector3D vertex) {
        double xScreenSpace = - vertex.x() / vertex.z();
        double yScreenSpace = - vertex.y() / vertex.z();

        double xPixelSpace = width / 2 + (xScreenSpace * screenSpaceToPixelSpaceRatio);
        double yPixelSpace = height / 2 - (yScreenSpace * screenSpaceToPixelSpaceRatio);

        if (!isValidPixelSpaceCoordinates(xPixelSpace, yPixelSpace)) return null;

        return new Vertex2D((int) Math.round(xPixelSpace), (int) Math.round(yPixelSpace), vertex.z());
    }

    public List<Double> inverseProject(int x, int y) {
        double xScreenSpace = (x - width / 2) / screenSpaceToPixelSpaceRatio;
        double yScreenSpace = (height / 2 - y) / screenSpaceToPixelSpaceRatio;

        return Arrays.asList(xScreenSpace, yScreenSpace);
    }

    public Triangle2D projectTriangle(Triangle3D triangle) {
        Vertex2D projectedA = project(triangle.a());
        if (projectedA == null) return null;

        Vertex2D projectedB = project(triangle.b());
        if (projectedB == null) return null;

        Vertex2D projectedC = project(triangle.c());
        if (projectedC == null) return null;

        return new Triangle2D(projectedA, projectedB, projectedC);
    }

}
