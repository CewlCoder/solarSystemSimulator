package graphics3D;

import java.util.ArrayList;
import java.util.List;

public class Camera implements ReadOnlyCamera {
    private Vector3D position;

    private double fov;
    private double pitch;
    private double yaw;

    public Camera(double fov) {
        this.position = new Vector3D(0, 0, 0);
    
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
        return Math.toDegrees(pitch);
    }

    @Override
    public double yaw() {
        return Math.toDegrees(yaw);
    }




    public void rotate(double pitch, double yaw) {
        this.pitch = Math.toRadians(pitch);
        this.yaw = Math.toRadians(yaw);
    }

    public void shift(Vector3D offset) {
        Vector3D rotatedX = offset.rotate('x', -yaw);
        Vector3D rotatedXY = rotatedX.rotate('y', pitch);

        position = position.add(rotatedXY);
    }

    @Override
    public Triangle3D transformFromWorldSpaceToCameraSpace(Triangle3D triangle) {
        List<Vector3D> translatedVertecies = new ArrayList<>();

        for (Vector3D vertex : triangle.vertices()) {
            Vector3D translated = vertex.subtract(position);
            Vector3D rotatedY = translated.rotate('y', -pitch);
            Vector3D rotatedYX = rotatedY.rotate('x', yaw);

            translatedVertecies.add(rotatedYX);
        }

        return new Triangle3D(translatedVertecies.get(0), translatedVertecies.get(1), translatedVertecies.get(2));
    }
}
