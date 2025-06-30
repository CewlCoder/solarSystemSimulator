package graphics3D.renderer;

import graphics3D.Triangle3D;
import graphics3D.Vector3D;

public class Projector {
    private int width;
    private int height;

    /**
     * Creates a projector which handles conversion world space and screen space.
     * 
     * @param width screen width
     * @param height screen height
     */
    public Projector(int width, int height) {
        this.width = width;
        this.height = height;
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
        float screenSpaceWidth = 5;
        float screenSpaceToPixelRatio = width / screenSpaceWidth;

        double xScreenSpace = vertex.x() / vertex.z();
        double yScreenSpace = vertex.y() / vertex.z();

        double xPixelSpace = width / 2 + (screenSpaceToPixelRatio * xScreenSpace);
        double yPixelSpace = (height - screenSpaceToPixelRatio * yScreenSpace) - height / 2;

        if (!isValidPixelSpaceCoordinates(xPixelSpace, yPixelSpace)) {
            return null;
        }

        return new Vertex2D((int) Math.round(xPixelSpace), (int) Math.round(yPixelSpace), vertex.z());
    }

    protected Triangle2D projectTriangle(Triangle3D triangle) {
        Vertex2D projectedA = project(triangle.a());
        if (projectedA == null) {return null;}

        Vertex2D projectedB = project(triangle.b());
        if (projectedB == null) {return null;}

        Vertex2D projectedC = project(triangle.c());
        if (projectedC == null) {return null;}

        return new Triangle2D(projectedA, projectedB, projectedC);
    }

}
