package graphics3D;

public class Vector3D {
    private double x;
    private double y;
    private double z;

    /**
     * Creates a 3D vector defined by an x, y and z coordinate.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }



    public Vector3D rotate(char axis, double angle) {
        switch (axis) {
            case 'x': {
                double yPrime = y * Math.cos(angle) - z * Math.sin(angle);
                double zPrime = y * Math.sin(angle) + z * Math.cos(angle);

                return new Vector3D(x, yPrime, zPrime);
            }
            case 'y': {
                double xPrime = x * Math.cos(angle) + z * Math.sin(angle);
                double zPrime = z * Math.cos(angle) - x * Math.sin(angle);

                return new Vector3D(xPrime, y, zPrime);
            }

            default:
                throw new IllegalArgumentException("Axis is not x or y");
        }
    }

    public Vector3D subtract(Vector3D other) {
        double x = this.x() - other.x();
        double y = this.y() - other.y();
        double z = this.z() - other.z();

        return new Vector3D(x, y, z);
    }

    public Vector3D add(Vector3D other) {
        double x = this.x() + other.x();
        double y = this.y() + other.y();
        double z = this.z() + other.z();

        return new Vector3D(x, y, z);
    }
}
