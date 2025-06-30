package graphics3D.renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graphics3D.Triangle3D;
import graphics3D.Vector3D;

public class Camera {
    private Vector3D position;

    private double pitch;
    private double yaw;

    public Camera() {
        this.position = new Vector3D(0, 0, 0);

        this.pitch = 0;
        this.yaw = 0;
    }



    public void rotate(double pitch, double yaw) {
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public void shift(Vector3D triangle) {
        position = position.add(triangle);
    }

    public Triangle3D transformTriangle(Triangle3D triangle) {
        List<Vector3D> vertecies = Arrays.asList(triangle.a(), triangle.b(), triangle.c());
        List<Vector3D> translatedVertecies = new ArrayList<>();

        for (Vector3D vector : vertecies) {
            Vector3D roatedY = vector.rotate('y', pitch);
            Vector3D rotatedYX = roatedY.rotate('x', yaw);

            Vector3D translated = rotatedYX.subtract(position);
            if (translated.z() < 0) {return null;}

            translatedVertecies.add(translated);
        }

        return new Triangle3D(translatedVertecies.get(0), translatedVertecies.get(1), translatedVertecies.get(2));
    }
}
