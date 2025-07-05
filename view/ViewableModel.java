package view;

import java.util.List;

import graphics3D.Mesh;
import graphics3D.ReadOnlyCamera;

public interface ViewableModel {
    /**
     * Returns a list of all the meshes within the model.
     * 
     * @return a list of all meshes within model
     */
    List<Mesh> meshes();

    /**
     * Returns a read-only version of the camera.
     * 
     * @return a read-only version of the camera
     */
    ReadOnlyCamera camera();
}
