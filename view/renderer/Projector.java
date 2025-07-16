package view.renderer;

import graphics3D.Triangle3D;
import graphics3D.Vector3D;

public class Projector {
    private int width;
    private int height;

    private double screenSpaceWidth;
    private double screenSpaceHeight;

    private double screenSpaceToPixelSpaceRatio;

    /**
     * Creates a projector which handles conversion world space and screen space.
     * 
     * @param width screen width
     * @param height screen height
     * @param cameraFov fov of camera
     */
    public Projector(int width, int height, double cameraFov) {
        this.width = width;
        this.height = height;

        this.screenSpaceWidth = 2 * Math.tan(Math.toRadians(cameraFov / 2));
        this.screenSpaceHeight = 2 * Math.tan(Math.toRadians(cameraFov / 2)) * height / width;

        this.screenSpaceToPixelSpaceRatio = width / screenSpaceWidth;
    }



    public boolean isWithinViewThrustum(Triangle3D triangle) {
        for (Vector3D vertex : triangle.vertices()) {
            double xMin = screenSpaceWidth / 2 * vertex.z();
            double xMax = -xMin;

            double yMin = screenSpaceHeight / 2 * vertex.z();
            double yMax = -yMin;

            if ((vertex.x() <= xMin) | (xMax <= vertex.x())) return false;
            if ((vertex.y() <= yMin) | (yMax <= vertex.y())) return false;
        }

        return true;
    }

    private Vertex2D project(Vector3D vertex) {
        double xScreenSpace = -vertex.x() / vertex.z();
        double yScreenSpace = -vertex.y() / vertex.z();

        double xPixelSpace = width / 2 + (xScreenSpace * screenSpaceToPixelSpaceRatio);
        double yPixelSpace = height / 2 - (yScreenSpace * screenSpaceToPixelSpaceRatio);

        return new Vertex2D((int) Math.round(xPixelSpace), (int) Math.round(yPixelSpace), vertex.z());
    }

    public Triangle2D toPixelSpace(Triangle3D triangle) {
        return new Triangle2D(
            project(triangle.a()), 
            project(triangle.b()), 
            project(triangle.c())
        );
    }

}
