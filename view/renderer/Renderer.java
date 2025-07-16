package view.renderer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import graphics3D.Mesh;
import graphics3D.ReadOnlyCamera;
import graphics3D.Triangle3D;

public class Renderer {
    private int width;
    private int height;

    private BufferedImage pixelBuffer;
    private List<List<Double>> depthBuffer;

    private ReadOnlyCamera camera;
    private Projector projector;

    public Renderer(ReadOnlyCamera camera, int width, int height) {
        this.width = width;
        this.height = height;

        this.pixelBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.depthBuffer = new ArrayList<>();

        this.camera = camera;
        this.projector = new Projector(width, height, camera.fov());
    }



    private void drawColumn(int column, int xMinTriangleBound, int xMaxTriangleBound, Color color) {
        for (int x = xMinTriangleBound; x < xMaxTriangleBound; x++) {
            pixelBuffer.setRGB(x, column, color.getRGB());
        }
    }

    private void drawToPixelBuffer(Triangle2D pixelSpaceTriangle, Color color) {
        List<Integer> verticalTriangleBoundingBox = pixelSpaceTriangle.verticalBoundingBox();

        int yMinBound = verticalTriangleBoundingBox.get(0);
        int yMaxBound = verticalTriangleBoundingBox.get(1);

        for (int y = yMaxBound; y > yMinBound; y--) {
            List<Integer> Intersections = pixelSpaceTriangle.lineIntersections(y);

            int xMinIntersection = Intersections.get(0);
            int xMaxIntersection = Intersections.get(1);

            drawColumn(y, xMinIntersection, xMaxIntersection, color);
        }
    }

    /**
     * Renders the given mesh to the image.
     * 
     * @param mesh the mesh to render
     */
    public void renderMesh(Mesh mesh) {
        for (Triangle3D triangle : mesh.triangles()) {
            Triangle3D cameraSpaceTriangle = camera.toCameraSpace(triangle);
            if (!projector.isWithinViewThrustum(cameraSpaceTriangle)) continue;

            Triangle2D pixelSpaceTriangle = projector.toPixelSpace(cameraSpaceTriangle);
            drawToPixelBuffer(pixelSpaceTriangle, mesh.color());
        }
    }


    /**
     * Clears the image.
     */
    public void clearImage() {
        Graphics graphics = pixelBuffer.getGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
        graphics.dispose();
    }

    /**
     * Displays the image onto the given graphics object.
     * 
     * @param graphics the graphic to display the image onto
     */
    public void displayImage(Graphics graphics) {
        graphics.drawImage(pixelBuffer, 0, 0, null);
    }
}
