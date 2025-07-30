package view.renderer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import graphics3D.Mesh;
import graphics3D.ReadOnlyCamera;
import graphics3D.Triangle3D;

public class Renderer {
    private int width;
    private int height;

    private BufferedImage pixelBuffer;
    private double[][] depthBuffer;

    private ReadOnlyCamera camera;
    private Projector projector;

    public Renderer(ReadOnlyCamera camera, int width, int height) {
        this.width = width;
        this.height = height;

        this.pixelBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.depthBuffer = new double[height][width];

        clearDepthBuffer();

        this.camera = camera;
        this.projector = new Projector(width, height, camera.fov());
    }



    private void drawColumn(ProjectedTriangle pixelSpaceTriangle, int y, Color color) {
        int[] Intersections = pixelSpaceTriangle.lineIntersections(y);

        int xMinIntersection = Intersections[0];
        int xMaxIntersection = Intersections[1];

        //This should be xMinIntersection + 1, but looks better with the additional pixels being drawn over (for our case).
        for (int x = xMinIntersection; x < xMaxIntersection; x++) {
            double depth = pixelSpaceTriangle.depth(x, y);

            if (depthBuffer[y][x] <= depth) {
                pixelBuffer.setRGB(x, y, color.getRGB());
                depthBuffer[y][x] = depth;
            }
        }
    }

    private void drawToPixelBuffer(ProjectedTriangle pixelSpaceTriangle, Color color) {
        int[] verticalTriangleBoundingBox = pixelSpaceTriangle.verticalBoundingBox();

        int yMinBound = verticalTriangleBoundingBox[0];
        int yMaxBound = verticalTriangleBoundingBox[1];

        for (int y = yMinBound + 1; y < yMaxBound; y++) {
            drawColumn(pixelSpaceTriangle, y, color);
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

            ProjectedTriangle pixelSpaceTriangle = projector.toPixelSpace(cameraSpaceTriangle);
            drawToPixelBuffer(pixelSpaceTriangle, mesh.color());
        }
    }



    private void clearDepthBuffer() {
        for (int y = 0; y < height; y++) {
            Arrays.fill(depthBuffer[y], Double.NEGATIVE_INFINITY);
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

        clearDepthBuffer();
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
