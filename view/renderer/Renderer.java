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
    private BufferedImage image;
    private List<List<Double>> depthBuffer;

    private ReadOnlyCamera camera;
    private Projector projector;

    private int width;
    private int height;

    public Renderer(ReadOnlyCamera camera, int width, int height) {
        this.width = width;
        this.height = height;

        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.depthBuffer = new ArrayList<>();

        this.camera = camera;
        this.projector = new Projector(width, height, camera.fov());
    }



    public Projector projector() {
        return projector;
    }



    private Double depthBufferValue(int x, int y) {
        List<Double> depthY = depthBuffer.get(y);
        if (depthY == null) return null;
        Double depthX = depthY.get(x);
        if (depthX == null) return null;

        return depthX;
    }

    private void drawToImage(Triangle2D pixelSpaceTriangle, Color color) {
        List<List<Integer>> boundingBox = pixelSpaceTriangle.boundingBox();

        int xMin = boundingBox.get(0).get(0);
        int xMax = boundingBox.get(0).get(1);

        int yMin = boundingBox.get(1).get(0);
        int yMax = boundingBox.get(1).get(1);

        for (int y = yMin; y < yMax; y++) {
            for (int x = xMin; x < xMax; x++) {
                Vector2D pixel = new Vector2D(x, y);

                if (pixelSpaceTriangle.isWithinTriangle(pixel)) { //todo fix depthBufferValue logic in terms of when bufferDepth is null
                    /*
                    Double bufferDepth = depthBufferValue(x, y);
                    double pixelDepth;

                    if (bufferDepth == null) {
                        List<Double> yDepthBuffer = new ArrayList<>();

                        yDepthBuffer.set(x, pixelDepth);
                        depthBuffer.set(y, yDepthBuffer);

                        image.setRGB(x, y, color.getRGB());
                    } else if (bufferDepth < pixelDepth) {
                        depthBuffer.get(y).set(x, pixelDepth);
                    }
                    */

                    image.setRGB(x, y, color.getRGB());
                }
            }
        }
    }

    public void renderMesh(Mesh mesh) {
        for (Triangle3D triangle : mesh.triangles()) {
            Triangle3D cameraSpaceTriangle = camera.transformFromWorldSpaceToCameraSpace(triangle);
            if (!projector.isWithinViewThrustum(cameraSpaceTriangle)) continue;

            Triangle2D pixelSpaceTriangle = projector.projectTriangle(cameraSpaceTriangle);
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
