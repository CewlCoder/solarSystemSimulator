package graphics3D.renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graphics3D.Mesh;
import graphics3D.Triangle;
import graphics3D.Vector;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class RenderTarget {
    private BufferedImage image;
    private List<List<Double>> depthBuffer;

    private Projector projector;

    public RenderTarget(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.depthBuffer = new ArrayList<>();
        this.projector = new Projector(width, height);
    }


    private int getMin(int a, int b, int c) {
        int min = a;

        if (b < min) {
            min = b;
        }
        if (c < min) {
            min = c;
        }

        return min;
    }

    private int getMax(int a, int b, int c) {
        int max = a;

        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }

        return max;
    }

    private List<List<Integer>> boundingBoxOfProjectedTriangle(Triangle projectedTriangle) {
        Vector a = projectedTriangle.a();
        Vector b = projectedTriangle.b();
        Vector c = projectedTriangle.c();

        List<Integer> xBounds = new ArrayList<>();
        List<Integer> yBounds = new ArrayList<>();

        xBounds.add(getMin((int) a.x(), (int) b.x(), (int) c.x()));
        xBounds.add(getMax((int) a.x(), (int) b.x(), (int) c.x()));

        yBounds.add(getMin((int) a.y(), (int) b.y(), (int) c.y()));
        yBounds.add(getMax((int) a.y(), (int) b.y(), (int) c.y()));

        return Arrays.asList(xBounds, yBounds);
    }

    private boolean isWithinProjectedTriangle(Triangle projectedTriangle, Vector pixel) {
        Vector a = projectedTriangle.a();
        Vector b = projectedTriangle.b();
        Vector c = projectedTriangle.c();

        Vector ab = b.subtract(a);
        Vector bc = c.subtract(b);
        Vector ca = a.subtract(c);

        Vector ap = pixel.subtract(a);
        Vector bp = pixel.subtract(b);
        Vector cp = pixel.subtract(c);

        double aAngle = ab.angleBetween(ca.scale(-1));
        double bAngle = bc.angleBetween(ab.scale(-1));
        double cAngle = ca.angleBetween(bc.scale(-1));

        double apAngle = ab.angleBetween(ap);
        double bpAngle = bc.angleBetween(bp);
        double cpAngle = ca.angleBetween(cp);

        boolean isWithinA = apAngle <= aAngle;
        boolean isWithinB = bpAngle <= bAngle;
        boolean isWithinC = cpAngle <= cAngle;

        return isWithinA & isWithinB & isWithinC;
    }

    public void drawMesh(Mesh mesh) {
        for (Triangle triangle : mesh.triangles()) {
            Triangle projectedTriangle = projector.projectTriangle(triangle);
            List<List<Integer>> boundingBox = boundingBoxOfProjectedTriangle(projectedTriangle);

            int xMin = boundingBox.get(0).get(0);
            int xMax = boundingBox.get(0).get(1);

            int yMin = boundingBox.get(1).get(0);
            int yMax = boundingBox.get(1).get(1);

            for (int y = yMin; y < yMax; y++) {
                for (int x = xMin; x < xMax; x++) {
                    Vector pixel = new Vector(x, y);

                    if (isWithinProjectedTriangle(projectedTriangle, pixel)) {
                        image.setRGB(x, y, Color.BLUE.getRGB());
                    }
                }
            }
        }
    }



    public BufferedImage image() {
        return image;
    }
}
