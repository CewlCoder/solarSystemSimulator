package view.renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle2D {
    private Vertex2D a;
    private Vertex2D b;
    private Vertex2D c;

    private Vector2D ab;
    private Vector2D bc;
    private Vector2D ca;

    protected Triangle2D(Vertex2D a, Vertex2D b, Vertex2D c) {
        Vector2D abTest = b.subtract(a);
        Vector2D acTest = c.subtract(a);

        this.a = a;

        if (abTest.isOnCounterClockSide(acTest)) {
            this.b = b;
            this.c = c;
        } else {
            this.b = c;
            this.c = b;
        }        

        this.ab = this.b.subtract(this.a);
        this.bc = this.c.subtract(this.b);
        this.ca = this.a.subtract(this.c);
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

    public List<List<Integer>> boundingBox() {
        List<Integer> xBounds = new ArrayList<>();
        List<Integer> yBounds = new ArrayList<>();

        xBounds.add(getMin(a.x(), b.x(), c.x()));
        xBounds.add(getMax(a.x(), b.x(), c.x()));

        yBounds.add(getMin(a.y(), b.y(), c.y()));
        yBounds.add(getMax(a.y(), b.y(), c.y()));

        return Arrays.asList(xBounds, yBounds);
    }

    public boolean isWithinTriangle(Vector2D pixel) {
        Vector2D ap = pixel.subtract(a);
        Vector2D bp = pixel.subtract(b);
        Vector2D cp = pixel.subtract(c);

        boolean isWithinAB = ab.isOnCounterClockSide(ap);
        boolean isWithinBC = bc.isOnCounterClockSide(bp);
        boolean isWithinCA = ca.isOnCounterClockSide(cp);

        return isWithinAB & isWithinBC & isWithinCA;
    }
}
