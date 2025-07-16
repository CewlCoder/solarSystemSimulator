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

    @Override
    public String toString() {
        return "x: " + x + " y: " + y + " z: " + z;
    }

    public Vector3D toBasis(Vector3D localX, Vector3D localY, Vector3D localZ) {
        Vector3D x = localX.scale(this.x());
        Vector3D y = localY.scale(this.y());
        Vector3D z = localZ.scale(this.z());

        return x.add(y).add(z);
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
            case 'z': {
                double xPrime = x * Math.cos(angle) - y * Math.sin(angle);
                double yPrime = x * Math.sin(angle) + y * Math.cos(angle);

                return new Vector3D(xPrime, yPrime, z);
            }

            default:
                throw new IllegalArgumentException("Axis is not x, y or z");
        }
    }



    public double dot(Vector3D other) {
        double sum = 0;

        sum += this.x() * other.x();
        sum += this.y() * other.y();
        sum += this.z() * other.z();

        return sum;
    }

    public double length() {
        double sum = 0;

        sum += this.x() * this.x();
        sum += this.y() * this.y();
        sum += this.z() * this.z();

        return Math.sqrt(sum);
    }

    public Vector3D scale(double factor) {
        double x = this.x() * factor;
        double y = this.y() * factor;
        double z = this.z() * factor;

        return new Vector3D(x, y, z);
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
