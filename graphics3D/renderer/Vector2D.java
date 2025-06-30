package graphics3D.renderer;

public class Vector2D {
    private int x;
    private int y;

    protected Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public double dot(Vector2D other) {
        double sum = 0;

        sum += this.x() * other.x();
        sum += this.y() * other.y();

        return sum;
    }

    public Vector2D subtract(Vector2D other) {
        int x = this.x() - other.x();
        int y = this.y() - other.y();

        return new Vector2D(x, y);
    }



    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

}
