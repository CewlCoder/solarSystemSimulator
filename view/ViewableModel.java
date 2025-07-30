package view;

import java.util.List;

import graphics3D.ReadOnlyCamera;
import model.Planet;

public interface ViewableModel {
    /**
     * Returns a list of all the planets within the model.
     * 
     * @return a list of all planets within model
     */
    List<Planet> planets();

    /**
     * Returns a read-only version of the camera.
     * 
     * @return a read-only version of the camera
     */
    ReadOnlyCamera camera();
}
