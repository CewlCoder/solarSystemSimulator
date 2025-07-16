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

    /**
     * Gets the x component of this vector.
     * 
     * @return the x component of this vector
     */
    public double x() {
        return x;
    }

    /**
     * Gets the y component of this vector.
     * 
     * @return the y component of this vector
     */
    public double y() {
        return y;
    }

    /**
     * Gets the z component of this vector.
     * 
     * @return the z component of this vector
     */
    public double z() {
        return z;
    }


    /**
     * Creates a new vector, which is this vector but transformed to a system with basis vectors localX, localY and localZ.
     * 
     * @param localX x basis vector
     * @param localY y basis vector
     * @param localZ z basis vector
     * @return the transformed vector
     */
    public Vector3D toBasis(Vector3D localX, Vector3D localY, Vector3D localZ) {
        Vector3D x = localX.scale(this.x());
        Vector3D y = localY.scale(this.y());
        Vector3D z = localZ.scale(this.z());

        return x.add(y).add(z);
    }

    /**
     * Creates a new vector, which is this vector but rotated thru axis by angle.
     * 
     * @param axis the axis to rotate by (x, y and z)
     * @param angle the angle to rotate by (radians)
     * @return the rotated vector
     */
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


    /**
     * Calculates the dot product of this vector and other.
     * 
     * @param other second factor of the dot product
     * @return the dot product
     */
    public double dot(Vector3D other) {
        double sum = 0;

        sum += this.x() * other.x();
        sum += this.y() * other.y();
        sum += this.z() * other.z();

        return sum;
    }

    /**
     * Calculates the length of this vector.
     * 
     * @return the length of this vector
     */
    public double length() {
        double sum = 0;

        sum += this.x() * this.x();
        sum += this.y() * this.y();
        sum += this.z() * this.z();

        return Math.sqrt(sum);
    }

    /**
     * Creates a new vector which is this vector but scaled by factor.
     * 
     * @param factor the scaling factor
     * @return the scaled vector
     */
    public Vector3D scale(double factor) {
        double x = this.x() * factor;
        double y = this.y() * factor;
        double z = this.z() * factor;

        return new Vector3D(x, y, z);
    }

    /**
     * Creates a new vector which is other subtracted onto this vector.
     * 
     * @param other vector to subtract by
     * @return the subtracted vector
     */
    public Vector3D subtract(Vector3D other) {
        double x = this.x() - other.x();
        double y = this.y() - other.y();
        double z = this.z() - other.z();

        return new Vector3D(x, y, z);
    }

    /**
     * Creates a new vector which is other added onto this vector.
     * 
     * @param other vector to add by
     * @return the added vector
     */
    public Vector3D add(Vector3D other) {
        double x = this.x() + other.x();
        double y = this.y() + other.y();
        double z = this.z() + other.z();

        return new Vector3D(x, y, z);
    }
}
