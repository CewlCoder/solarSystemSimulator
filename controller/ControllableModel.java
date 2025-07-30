package controller;

import graphics3D.Vector3D;

public interface ControllableModel {
    /**
     * Shifts the camera by offset.
     * 
     * @param offset 3D vector to offset by
     */
    void shiftCamera(Vector3D offset);

    /**
     * Rotates the camera by pitch first, then by yaw.
     * 
     * @param pitch pitch (in deg)
     * @param yaw yaw (in deg)
     */
    void rotateCamera(double pitch, double yaw);

    /** 
     * Gets the tick delay for the game.
     * 
     * @return tick delay in ms
     */
    int tickDelay();

    /**
     * Updates the objects of the game
     */
    void gameTick();

    /**
     * Gets the mouse sensitivity.
     * 
     * @return mouse sensitivity factor
     */
    double sensitivity();
}
