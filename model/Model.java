package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.Controllablemodel;
import graphics3D.Mesh;
import graphics3D.Triangle3D;
import graphics3D.Vector3D;
import graphics3D.renderer.Camera;
import view.Viewablemodel;

public class Model implements Controllablemodel, Viewablemodel {
    private List<Mesh> meshes;
    private Camera camera;

    public Model() {
        this.meshes = new ArrayList<>();
        this.camera = new Camera();

        camera.rotate(Math.toRadians(0), 0);

        Mesh cube = new Mesh(Arrays.asList(
            new Triangle3D(new Vector3D(0.5, 0.5, 1), new Vector3D(-0.5, 0.5, 1), new Vector3D(-0.5, -0.5, 1))
            //new Triangle3D(new Vector3D(0.5, 0.5, 1), new Vector3D(-0.5, -0.5, 1), new Vector3D(0.5, -0.5, 1))

            //new Triangle(new Vertex(0.5f, 0.5f, 1), new Vertex(-0.5f, -0.5f, 1), new Vertex(0.5f, -0.5f, 1))
        ));

        meshes.add(cube);
    }



    public List<Mesh> meshes() {
        return meshes;
    }

    public Camera camera() {
        return camera;
    }
}
