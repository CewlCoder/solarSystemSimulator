package view.renderer;

public class Vector2D {
    protected int x;
    protected int y;

    protected Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x component of this vector.
     * 
     * @return the x component of this vector
     */
    public int x() {
        return x;
    }

    /**
     * Gets the y component of this vector.
     * 
     * @return the y component of this vector
     */
    public int y() {
        return y;
    }


    /**
     * Creates a new vector which is other subtracted onto this vector.
     * 
     * @param other vector to subtract by
     * @return the subtracted vector
     */
    public Vector2D subtract(Vector2D other) {
        int x = this.x() - other.x();
        int y = this.y() - other.y();

        return new Vector2D(x, y);
    }
}
