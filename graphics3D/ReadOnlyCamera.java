package graphics3D;

public interface ReadOnlyCamera {
    /**
     * Returns the fov of the camera.
     * 
     * @return fov of camera
     */
    double fov();

    /**
     * Returns the pitch of the camera.
     * 
     * @return pitch of camera
     */
    double pitch();

    /**
     * Returns the yaw of the camera.
     * 
     * @return yaw of camera
     */
    double yaw();

    /**
     * Creates a new triangle3D object which is transformed from world space to camera space.
     * 
     * @param triangle the 3D triangle to transform
     * @return the transformed triangle
     */
    Triangle3D toCameraSpace(Triangle3D triangle);
}
