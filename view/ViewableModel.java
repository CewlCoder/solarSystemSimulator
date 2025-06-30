package view;

import java.util.List;

import graphics3D.Mesh;
import graphics3D.renderer.Camera;

public interface Viewablemodel {
    /**
     * Returns a list of all the meshes within the model.
     */
    List<Mesh> meshes();

    /**
     * Returns the camera object within the model.
     */
    Camera camera();
}
