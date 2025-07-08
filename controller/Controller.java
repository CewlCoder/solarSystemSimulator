package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import graphics3D.Vector3D;
import view.View;

public class Controller implements KeyListener, MouseMotionListener {
    private ControllableModel model;
    private View view;

    
    public Controller(ControllableModel model, View view) {
        this.model = model;
        this.view = view;

        view.addKeyListener(this);
        view.addMouseMotionListener(this);
        view.setFocusable(true);
    }



    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_Q) {
            model.shiftCamera(new Vector3D(0, -0.05, 0));
        }
        if (event.getKeyCode() == KeyEvent.VK_E) {
            model.shiftCamera(new Vector3D(0, 0.05, 0));
        }

        if (event.getKeyCode() == KeyEvent.VK_W) {
            model.shiftCamera(new Vector3D(0, 0, -0.05));
        }
        if (event.getKeyCode() == KeyEvent.VK_A) {
            model.shiftCamera(new Vector3D(-0.05, 0, 0));
        }
        if (event.getKeyCode() == KeyEvent.VK_S) {
            model.shiftCamera(new Vector3D(0, 0, 0.05));
        }
        if (event.getKeyCode() == KeyEvent.VK_D) {
            model.shiftCamera(new Vector3D(0.05, 0, 0));
        }

        view.repaint();
    }

    @Override
    public void keyTyped(KeyEvent event) {}

    @Override
    public void keyReleased(KeyEvent event) {}



    @Override
    public void mouseMoved(MouseEvent event) {
        List<Double> screenPositions = view.renderer().projector().inverseProject(event.getX(), event.getY());

        double xScreenSpace = screenPositions.get(0);
        double yScreenSpace = screenPositions.get(1);

        double rotationX = Math.toDegrees(Math.atan(xScreenSpace));
        double rotationY = Math.toDegrees(Math.atan(yScreenSpace));

        model.rotateCamera(rotationX, rotationY);

        view.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent event) {}

}
