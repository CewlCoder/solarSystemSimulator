package graphics3D.renderer;

import java.util.ArrayList;
import java.util.List;

import graphics3D.Mesh;
import graphics3D.Triangle3D;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class RenderTarget {
    private BufferedImage image;
    private List<List<Double>> depthBuffer;

    private Camera camera;
    private Projector projector;

    /**
     * Creates render target which handles drawing meshes onto a 2d canvas.
     * 
     * @param width width of canvas
     * @param height height of canvas
     */
    public RenderTarget(Camera camera, int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.depthBuffer = new ArrayList<>();

        this.camera = camera;
        this.projector = new Projector(width, height);
    }



    public void drawMesh(Mesh mesh) {
        for (Triangle3D triangle : mesh.triangles()) {
            Triangle3D cameraSpaceTriangle = camera.transformTriangle(triangle);
            if (cameraSpaceTriangle == null) {continue;}

            Triangle2D pixelSpaceTriangle = projector.projectTriangle(cameraSpaceTriangle);
            if (pixelSpaceTriangle == null) {continue;}

            List<List<Integer>> boundingBox = pixelSpaceTriangle.boundingBox();

            int xMin = boundingBox.get(0).get(0);
            int xMax = boundingBox.get(0).get(1);

            int yMin = boundingBox.get(1).get(0);
            int yMax = boundingBox.get(1).get(1);

            for (int y = yMin; y < yMax; y++) {
                for (int x = xMin; x < xMax; x++) {
                    Vector2D pixel = new Vector2D(x, y);

                    if (pixelSpaceTriangle.isWithinTriangle(pixel)) {
                        image.setRGB(x, y, Color.RED.getRGB());
                    }
                }
            }
        }
    }



    public BufferedImage image() {
        return image;
    }
}
