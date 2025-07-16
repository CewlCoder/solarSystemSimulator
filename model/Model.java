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
        this.camera = new Camera(110);

        Mesh flat = new Mesh(Arrays.asList(
            new Triangle3D(new Vector3D(0.5, 0.5, -1).scale(5), new Vector3D(-0.5, 0.5, -1).scale(5), new Vector3D(-0.5, -0.5, -1).scale(5)),
            new Triangle3D(new Vector3D(0.5, 0.5, -1).scale(5), new Vector3D(-0.5, -0.5, -1).scale(5), new Vector3D(0.5, -0.5, -1).scale(5))
        ), Color.BLUE);

        Mesh cube2 = new Mesh(Arrays.asList(
            new Triangle3D(new Vector3D(0.5, 0.5 -1, -1 -1), new Vector3D(-0.5, 0.5 -1, -1 -1), new Vector3D(-0.5, -0.5 -1, -1 -1)),
            new Triangle3D(new Vector3D(0.5, 0.5 -1, -1 -1), new Vector3D(-0.5, -0.5 -1, -1 -1), new Vector3D(0.5, -0.5 -1, -1 -1)),

            new Triangle3D(new Vector3D(-0.5, 0.5 -1, -1 -1), new Vector3D(-0.5, 0.5 -1, -2 -1), new Vector3D(-0.5, -0.5 -1, -1 -1)),
            new Triangle3D(new Vector3D(-0.5, 0.5 -1, -2 -1), new Vector3D(-0.5, -0.5 -1, -2 -1), new Vector3D(-0.5, -0.5 -1, -1 -1)),

            new Triangle3D(new Vector3D(-0.5, 0.5 -1, -2 -1), new Vector3D(0.5, 0.5 -1, -2 -1), new Vector3D(0.5, -0.5 -1, -2 -1)),
            new Triangle3D(new Vector3D(-0.5, 0.5 -1, -2 -1), new Vector3D(0.5, -0.5 -1, -2 -1), new Vector3D(-0.5, -0.5 -1, -2 -1)),

            new Triangle3D(new Vector3D(0.5, 0.5 -1, -2 -1), new Vector3D(0.5, 0.5 -1, -1 -1), new Vector3D(0.5, -0.5 -1, -1 -1)),
            new Triangle3D(new Vector3D(0.5, 0.5 -1, -2 -1), new Vector3D(0.5, -0.5 -1, -1 -1), new Vector3D(0.5, -0.5 -1, -2 -1)),

            new Triangle3D(new Vector3D(0.5, 0.5 -1, -2 -1), new Vector3D(-0.5, 0.5 -1, -2 -1), new Vector3D(-0.5, 0.5 -1, -1 -1)),
            new Triangle3D(new Vector3D(0.5, 0.5 -1, -2 -1), new Vector3D(-0.5, 0.5 -1, -1 -1), new Vector3D(0.5, 0.5 -1, -1 -1)),

            new Triangle3D(new Vector3D(0.5, -0.5 -1, -1 -1), new Vector3D(-0.5, -0.5 -1, -1 -1), new Vector3D(-0.5, -0.5 -1, -2 -1)),
            new Triangle3D(new Vector3D(0.5, -0.5 -1, -1 -1), new Vector3D(-0.5, -0.5 -1, -2 -1), new Vector3D(0.5, -0.5 -1, -2 -1))
        ), Color.GREEN);

        meshes.add(flat);
        meshes.add(cube2);
    }


    @Override
    public void shiftCamera(Vector3D offset) {
        if ((offset.x() != 0) | (offset.y() != 0) | (offset.z() != 0)) {
            camera.shift(offset);
        }
    }

    @Override
    public void rotateCamera(double pitch, double yaw) {
        double newYaw = camera.yaw() - yaw;

        if ((newYaw <= Math.toRadians(-90)) | (Math.toRadians(90) <= newYaw)) {
            yaw = 0;
        }

        camera.rotate(pitch, yaw);
    }



    @Override
    public int tickDelay() {
        return 4;
    }

    @Override
    public double sensitivity() {
        return 0.001;
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
