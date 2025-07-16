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

    private BufferedImage image;
    private List<List<Double>> depthBuffer;

    private ReadOnlyCamera camera;
    private Projector projector;

    public Renderer(ReadOnlyCamera camera, int width, int height) {
        this.width = width;
        this.height = height;

        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.depthBuffer = new ArrayList<>();

        this.camera = camera;
        this.projector = new Projector(width, height, camera.fov());
    }



    private void drawColumn(int column, int xMinTriangleBound, int xMaxTriangleBound, Color color) {
        for (int x = xMinTriangleBound; x < xMaxTriangleBound; x++) {
            image.setRGB(x, column, color.getRGB());
        }
    }

    private void drawToImage(Triangle2D pixelSpaceTriangle, Color color) {
        int yMinBound = pixelSpaceTriangle.a().y();
        int yMaxBound = pixelSpaceTriangle.c().y();

        for (int y = yMaxBound; y > yMinBound; y--) {
            List<Integer> triangleBounds = pixelSpaceTriangle.xTriangleBounds(y);

            int xMinTriangleBound = triangleBounds.get(0);
            int xMaxTriangleBound = triangleBounds.get(1);

            drawColumn(y, xMinTriangleBound, xMaxTriangleBound, color);
        }
    }

    public void renderMesh(Mesh mesh) {
        for (Triangle3D triangle : mesh.triangles()) {
            Triangle3D cameraSpaceTriangle = camera.toCameraSpace(triangle);
            if (!projector.isWithinViewThrustum(cameraSpaceTriangle)) continue;

            Triangle2D pixelSpaceTriangle = projector.toPixelSpace(cameraSpaceTriangle);
            drawToImage(pixelSpaceTriangle, mesh.color());
        }
    }



    public void clearImage() {
        Graphics graphics = image.getGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
        graphics.dispose();
    }

    public void displayImage(Graphics graphics) {
        graphics.drawImage(image, 0, 0, null);
    }
}
