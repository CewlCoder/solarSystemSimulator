package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import graphics3D.Mesh;
import graphics3D.Triangle3D;
import graphics3D.Vector3D;

public class Planet extends Mesh {
    private Vector3D position;
    private Vector3D velocity;
    private double mass;

    private Planet(List<Triangle3D> triangles, Vector3D centerPoint, Color color) {
        super(triangles, color);

        this.position = centerPoint;
        this.velocity = new Vector3D(0, 0, 0);
        this.mass = 1;
    }

    public static Planet newPlanet(Vector3D centerPoint, double radius, Color color) {
        double halfSize = radius / 2;

        Vector3D bottomLowerLeft = centerPoint.add(new Vector3D(-halfSize, -halfSize, halfSize));
        Vector3D bottomLowerRight = centerPoint.add(new Vector3D(halfSize, -halfSize, halfSize));

        Vector3D bottomUpperLeft = centerPoint.subtract(new Vector3D(halfSize, halfSize, halfSize));
        Vector3D bottomUpperRight = centerPoint.add(new Vector3D(halfSize, -halfSize, -halfSize));

        Vector3D topLowerLeft = centerPoint.add(new Vector3D(-halfSize, halfSize, halfSize));
        Vector3D topLowerRight = centerPoint.add(new Vector3D(halfSize, halfSize, halfSize));

        Vector3D topUpperLeft = centerPoint.add(new Vector3D(-halfSize, halfSize, -halfSize));
        Vector3D topUpperRight = centerPoint.add(new Vector3D(halfSize, halfSize, -halfSize));

        List<Triangle3D> triangles = Arrays.asList(
            //bottom face
            new Triangle3D(bottomLowerLeft, bottomLowerRight, bottomUpperRight),
            new Triangle3D(bottomLowerLeft, bottomUpperLeft, bottomUpperRight),

            //top face
            new Triangle3D(topLowerLeft, topLowerRight, topUpperRight),
            new Triangle3D(topLowerLeft, topUpperLeft, topUpperRight),

            //near face
            new Triangle3D(bottomLowerLeft, bottomLowerRight, topLowerRight),
            new Triangle3D(bottomLowerLeft, topLowerLeft, topLowerRight),

            //far face
            new Triangle3D(bottomUpperLeft, bottomUpperRight, topUpperRight),
            new Triangle3D(bottomUpperLeft, topUpperLeft, topUpperRight),

            //left face
            new Triangle3D(bottomUpperLeft, bottomLowerLeft, topLowerLeft),
            new Triangle3D(bottomUpperLeft, topUpperLeft, topLowerLeft),

            //right face
            new Triangle3D(bottomUpperRight, bottomLowerRight, topLowerRight),
            new Triangle3D(topLowerRight, topUpperRight, bottomUpperRight)
        );

        return new Planet(triangles, centerPoint, color);
    }

    public Vector3D position() {
        return position;
    }

    public Vector3D distanceToOther(Planet other) {
        return other.position().subtract(position);
    }

    public double mass() {
        return mass;
    }

    public void setMass(double newMass) {
        mass = newMass;
    }



    public void applyVelocity(Vector3D offset) {
        velocity = velocity.add(offset);
    }

    public void applyGravity(Planet other) {
        Vector3D distanceToOther = distanceToOther(other);
        Vector3D acceleration = distanceToOther.scale(PhysicsConstants.BIG_G * other.mass() / Math.pow(distanceToOther.length(), 3));

        velocity = velocity.add(acceleration);
    }

    public void updatePosition() {
        position = position.add(velocity);
        shift(velocity);
    }
}
