package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.ControllableModel;
import graphics3D.Camera;
import graphics3D.ReadOnlyCamera;
import graphics3D.Vector3D;
import view.ViewableModel;

public class Model implements ControllableModel, ViewableModel {
    private Camera camera;

    private List<Planet> gravtiyAffectedPlanets;
    private List<Planet> gravtiyProducingPlanets;

    public Model() {
        this.camera = new Camera(110);

        this.gravtiyAffectedPlanets = new ArrayList<>();
        this.gravtiyProducingPlanets = new ArrayList<>();

        Planet planet = Planet.newPlanet(new Vector3D(0, 0, 0), 0.5, Color.RED);
        planet.setMass(1_000_000_000);
        gravtiyProducingPlanets.add(planet);

        for (int i = 0; i < 2000; i++) {
            Random rand = new Random();

            int min = -25;
            int max = 25;

            double x = rand.nextDouble(max - min) + min;
            double y = rand.nextDouble(max - min) + min;
            double z = rand.nextDouble(max - min) + min;

            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);

            Planet bigPlanet = planet;
            Planet smallPlanet = Planet.newPlanet(new Vector3D(x, y, 0), 0.1, new Color(r, g, b));

            Vector3D bigPlanetToSmallPlanet = bigPlanet.distanceToOther(smallPlanet);

            Vector3D randomVector = new Vector3D(rand.nextInt(), rand.nextInt(), rand.nextInt());
            //Vector3D randomVelocityDirection = bigPlanetToSmallPlanet.cross(randomVector).normalize();
            Vector3D randomVelocityDirection = new Vector3D(-bigPlanetToSmallPlanet.y(), bigPlanetToSmallPlanet.x(), 0).normalize();

            Vector3D initialVelocity = randomVelocityDirection.scale(Math.sqrt(PhysicsConstants.BIG_G * bigPlanet.mass() / bigPlanetToSmallPlanet.length()));

            smallPlanet.applyVelocity(initialVelocity);
            gravtiyAffectedPlanets.add(smallPlanet);
        }
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

        if ((pitch != 0) | (yaw != 0)) {
            camera.rotate(pitch, yaw);
        }
    }


    private void applyGravityFromAllProducers(Planet affectedPlanet) {
        for (Planet planet : gravtiyProducingPlanets) {
            affectedPlanet.applyGravity(planet);
            affectedPlanet.updatePosition();
        }
    }

    @Override
    public void gameTick() {
        for (Planet affected : gravtiyAffectedPlanets) {
            applyGravityFromAllProducers(affected);
        }
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
    public List<Planet> planets() {
        List<Planet> planets = new ArrayList<>(gravtiyAffectedPlanets);
        planets.addAll(gravtiyProducingPlanets);

        return planets;
    }
}
