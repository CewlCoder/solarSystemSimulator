package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.ControllableModel;
import graphics3D.Camera;
import graphics3D.Mesh;
import graphics3D.ReadOnlyCamera;
import graphics3D.Triangle3D;
import graphics3D.Vector3D;
import view.ViewableModel;

public class Model implements ControllableModel, ViewableModel {
    private List<Mesh> meshes;
    private Camera camera;

    public Model() {
        this.meshes = new ArrayList<>();
        this.camera = new Camera(95);

        Mesh cube = new Mesh(Arrays.asList(
            new Triangle3D(new Vector3D(0.5, 0.5, -1), new Vector3D(-0.5, 0.5, -1), new Vector3D(-0.5, -0.5, -1)),
            new Triangle3D(new Vector3D(0.5, 0.5, -1), new Vector3D(-0.5, -0.5, -1), new Vector3D(0.5, -0.5, -1)),

            new Triangle3D(new Vector3D(-0.5, 0.5, -1), new Vector3D(-0.5, 0.5, -2), new Vector3D(-0.5, -0.5, -1)),
            new Triangle3D(new Vector3D(-0.5, 0.5, -2), new Vector3D(-0.5, -0.5, -2), new Vector3D(-0.5, -0.5, -1)),

            new Triangle3D(new Vector3D(-0.5, 0.5, -2), new Vector3D(0.5, 0.5, -2), new Vector3D(0.5, -0.5, -2)),
            new Triangle3D(new Vector3D(-0.5, 0.5, -2), new Vector3D(0.5, -0.5, -2), new Vector3D(-0.5, -0.5, -2)),

            new Triangle3D(new Vector3D(0.5, 0.5, -2), new Vector3D(0.5, 0.5, -1), new Vector3D(0.5, -0.5, -1)),
            new Triangle3D(new Vector3D(0.5, 0.5, -2), new Vector3D(0.5, -0.5, -1), new Vector3D(0.5, -0.5, -2)),

            new Triangle3D(new Vector3D(0.5, 0.5, -2), new Vector3D(-0.5, 0.5, -2), new Vector3D(-0.5, 0.5, -1)),
            new Triangle3D(new Vector3D(0.5, 0.5, -2), new Vector3D(-0.5, 0.5, -1), new Vector3D(0.5, 0.5, -1)),

            new Triangle3D(new Vector3D(0.5, -0.5, -1), new Vector3D(-0.5, -0.5, -1), new Vector3D(-0.5, -0.5, -2)),
            new Triangle3D(new Vector3D(0.5, -0.5, -1), new Vector3D(-0.5, -0.5, -2), new Vector3D(0.5, -0.5, -2))
        ), Color.RED);

        Mesh cube2 = new Mesh(Arrays.asList(
            new Triangle3D(new Vector3D(0.5, 0.5, -1 -1), new Vector3D(-0.5, 0.5, -1 -1), new Vector3D(-0.5, -0.5, -1 -1)),
            new Triangle3D(new Vector3D(0.5, 0.5, -1 -1), new Vector3D(-0.5, -0.5, -1 -1), new Vector3D(0.5, -0.5, -1 -1)),

            new Triangle3D(new Vector3D(-0.5, 0.5, -1 -1), new Vector3D(-0.5, 0.5, -2 -1), new Vector3D(-0.5, -0.5, -1 -1)),
            new Triangle3D(new Vector3D(-0.5, 0.5, -2 -1), new Vector3D(-0.5, -0.5, -2 -1), new Vector3D(-0.5, -0.5, -1 -1)),

            new Triangle3D(new Vector3D(-0.5, 0.5, -2 -1), new Vector3D(0.5, 0.5, -2 -1), new Vector3D(0.5, -0.5, -2 -1)),
            new Triangle3D(new Vector3D(-0.5, 0.5, -2 -1), new Vector3D(0.5, -0.5, -2 -1), new Vector3D(-0.5, -0.5, -2 -1)),

            new Triangle3D(new Vector3D(0.5, 0.5, -2 -1), new Vector3D(0.5, 0.5, -1 -1), new Vector3D(0.5, -0.5, -1 -1)),
            new Triangle3D(new Vector3D(0.5, 0.5, -2 -1), new Vector3D(0.5, -0.5, -1 -1), new Vector3D(0.5, -0.5, -2 -1)),

            new Triangle3D(new Vector3D(0.5, 0.5, -2 -1), new Vector3D(-0.5, 0.5, -2 -1), new Vector3D(-0.5, 0.5, -1 -1)),
            new Triangle3D(new Vector3D(0.5, 0.5, -2 -1), new Vector3D(-0.5, 0.5, -1 -1), new Vector3D(0.5, 0.5, -1 -1)),

            new Triangle3D(new Vector3D(0.5, -0.5, -1 -1), new Vector3D(-0.5, -0.5, -1 -1), new Vector3D(-0.5, -0.5, -2 -1)),
            new Triangle3D(new Vector3D(0.5, -0.5, -1 -1), new Vector3D(-0.5, -0.5, -2 -1), new Vector3D(0.5, -0.5, -2 -1))
        ), Color.YELLOW);

        meshes.add(cube);
        meshes.add(cube2);
    }


    @Override
    public void shiftCamera(Vector3D offset) {
        camera.shift(offset);
    }

    @Override
    public void rotateCamera(double pitch, double yaw) {
        double newYaw = camera.yaw() + yaw;

        if ((-90 <= newYaw) & (newYaw <= 90)) {
            camera.rotate(pitch, yaw);
        }
    }



    @Override
    public ReadOnlyCamera camera() {
        return camera;
    }

    @Override
    public List<Mesh> meshes() {
        return meshes;
    }
}
