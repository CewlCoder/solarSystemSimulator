package controller;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import graphics3D.Vector3D;
import view.View;

public class Controller implements KeyListener, MouseMotionListener {
    private ControllableModel model;
    private View view;
    private Timer timer;
    
    private Robot robot;
    private Set<Integer> pressedKeys;
    private HashMap<Integer, Vector3D> movementMap;

    public Controller(ControllableModel model, View view) {
        this.model = model;
        this.view = view;
        this.timer = new Timer(model.tickDelay(), this::gameTick);
        
        try {
            this.robot = new Robot();
        } catch (Throwable error) {
            System.err.println("Cannot create robot.");
        }

        this.pressedKeys = new HashSet<>();
        this.movementMap = new HashMap<>();

        movementMap.put(KeyEvent.VK_Q, new Vector3D(0, -0.05, 0));
        movementMap.put(KeyEvent.VK_E, new Vector3D(0, 0.05, 0));

        movementMap.put(KeyEvent.VK_W, new Vector3D(0, 0, 0.05));
        movementMap.put(KeyEvent.VK_A, new Vector3D(-0.05, 0, 0));
        movementMap.put(KeyEvent.VK_S, new Vector3D(0, 0, -0.05));
        movementMap.put(KeyEvent.VK_D, new Vector3D(0.05, 0, 0));

        timer.start();
        view.addKeyListener(this);
        view.addMouseMotionListener(this);
        view.setFocusable(true);
    }

    private void gameTick(ActionEvent event) {
        updateMovement();
        view.repaint();
    }



    private void updateMovement() {
        Vector3D movement = new Vector3D(0, 0, 0);

        for (int key : movementMap.keySet()) {
            if (pressedKeys.contains(key)) {
                movement = movement.add(movementMap.get(key));
            }
        }

        model.shiftCamera(movement);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        pressedKeys.add(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        pressedKeys.remove(event.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent event) {}



    private void centerMouse() {
        Point center = new Point(view.getWidth() / 2, view.getHeight() / 2);
        SwingUtilities.convertPointToScreen(center, view);

        robot.mouseMove(center.x, center.y);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        double xDelta = (view.getWidth() / 2.0 - event.getX()) / view.getWidth();
        double yDelta = (view.getHeight() / 2.0 - event.getY()) / view.getWidth();

        model.rotateCamera(xDelta * model.sensitivity(), yDelta * model.sensitivity());
        centerMouse();
    }

    @Override
    public void mouseDragged(MouseEvent event) {}

}
