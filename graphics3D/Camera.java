package graphics3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Camera implements ReadOnlyCamera {
    private Vector3D position;

    private double pitch;
    private double yaw;

    public Camera() {
        this.position = new Vector3D(0, 0, 0);

        this.pitch = 0;
        this.yaw = 0;
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
        Vector3D rotatedX = offset.rotate('x', yaw);
        Vector3D rotatedXY = rotatedX.rotate('y', -pitch);

        position = position.add(rotatedXY);
    }

    @Override
    public Triangle3D transformFromWorldSpaceToCameraSpace(Triangle3D triangle) {
        List<Vector3D> vertecies = Arrays.asList(triangle.a(), triangle.b(), triangle.c());
        List<Vector3D> translatedVertecies = new ArrayList<>();

        for (Vector3D vector : vertecies) {
            Vector3D translated = vector.subtract(position);
            Vector3D rotatedY = translated.rotate('y', pitch);
            Vector3D rotatedYX = rotatedY.rotate('x', -yaw);
            if (rotatedYX.z() > 0) return null;

            translatedVertecies.add(rotatedYX);
        }

        return new Triangle3D(translatedVertecies.get(0), translatedVertecies.get(1), translatedVertecies.get(2));
    }
}
