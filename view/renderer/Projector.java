package view.renderer;

import graphics3D.Triangle3D;
import graphics3D.Vector3D;

public class Projector {
    private int width;
    private int height;

    private final double FARCLIP = -500;
    private final double NEARCLIP = -5;

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


    /**
     * Checks if a given 3D triangle is within the view thrustum.
     * 
     * @param triangle the triangle to check
     * @return if the triangle is within the view thrustum
     */
    public boolean isWithinViewThrustum(Triangle3D triangle) {
        for (Vector3D vertex : triangle.vertices()) {
            if ((NEARCLIP <= vertex.z()) | (vertex.z() <= FARCLIP)) return false;

            double xMin = screenSpaceWidth / 2 * vertex.z();
            double xMax = -xMin;

            double yMin = screenSpaceHeight / 2 * vertex.z();
            double yMax = -yMin;

            if ((vertex.x() <= xMin) | (xMax <= vertex.x())) return false;
            if ((vertex.y() <= yMin) | (yMax <= vertex.y())) return false;
        }

        return true;
    }

    private ProjectedVector project(Vector3D vertex) {
        double xScreenSpace = -vertex.x() / vertex.z();
        double yScreenSpace = -vertex.y() / vertex.z();

        double xPixelSpace = width / 2 + (xScreenSpace * screenSpaceToPixelSpaceRatio);
        double yPixelSpace = height / 2 - (yScreenSpace * screenSpaceToPixelSpaceRatio);

        return new ProjectedVector((int) Math.round(xPixelSpace), (int) Math.round(yPixelSpace), vertex.z());
    }

    /**
     * Creates a triangle2D object which is transformed from 3D space to pixel space.
     * 
     * @param triangle the 3D triangle to transform
     * @return the transformed 2D triangle
     */
    public ProjectedTriangle toPixelSpace(Triangle3D triangle) {
        return new ProjectedTriangle(
            project(triangle.a()), 
            project(triangle.b()), 
            project(triangle.c())
        );
    }

}
