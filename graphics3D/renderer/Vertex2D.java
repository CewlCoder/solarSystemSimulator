package graphics3D.renderer;

public class Vertex2D extends Vector2D {
    private double depth;

    protected Vertex2D(int x, int y, double depth) {
        super(x, y);
        this.depth = depth;
    }



    public double depth() {
        return depth;
    }
}
