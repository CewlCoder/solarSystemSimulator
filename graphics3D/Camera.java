package graphics3D;

public class Camera implements ReadOnlyCamera {
    private Vector3D position;

    private Vector3D originalXAxis;
    private Vector3D originalYAxis;
    private Vector3D originalZAxis;

    private double fov;
    private double pitch;
    private double yaw;

    /**
     * Creates a camera that's looking towards -Z.
     * 
     * @param fov the fov of the camera
     */
    public Camera(double fov) {
        this.position = new Vector3D(0, 0, 50);

        this.originalXAxis = new Vector3D(1, 0, 0);
        this.originalYAxis = new Vector3D(0, 1, 0);
        this.originalZAxis = new Vector3D(0, 0, -1);

        this.fov = fov;
        this.pitch = 0;
        this.yaw = 0;
    }

    @Override
    public double fov() {
        return fov;
    }

    @Override
    public double pitch() {
        return pitch;
    }

    @Override
    public double yaw() {
        return yaw;
    }


    /**
     * Adds to the camera's pitch and yaw.
     * 
     * @param pitch change in pitch (radians)
     * @param yaw change in yaw (radians)
     */
    public void rotate(double pitch, double yaw) {
        this.pitch -= pitch;
        this.yaw -= yaw;
    }

    /**
     * Shifts the cameras position by offset, along the camera's basis vectors.
     * 
     * @param offset 3D vector to shift the cameras position by
     */
    public void shift(Vector3D offset) {
        Vector3D localXAxis = originalXAxis.rotate('x', yaw).rotate('y', pitch);
        Vector3D localYAxis = originalYAxis.rotate('x', yaw).rotate('y', pitch);
        Vector3D localZAxis = originalZAxis.rotate('x', yaw).rotate('y', pitch);

        position = position.add(offset.toBasis(localXAxis, localYAxis, localZAxis));
    }

    private Vector3D transform(Vector3D vertex) {
        Vector3D translated = vertex.subtract(position);
        Vector3D inverseRotataed = translated.rotate('y', -pitch).rotate('x', -yaw);

        return inverseRotataed;
    }

    @Override
    public Triangle3D toCameraSpace(Triangle3D triangle) {
        return new Triangle3D(
            transform(triangle.a()),
            transform(triangle.b()),
            transform(triangle.c())
        );
    }
}
