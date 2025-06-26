package view;

import javax.swing.JPanel;

import graphics3D.Mesh;
import graphics3D.Triangle;
import graphics3D.Vector;
import graphics3D.renderer.RenderTarget;

import java.awt.Graphics;
import java.util.Arrays;

public class View extends JPanel {
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Mesh cube = new Mesh(Arrays.asList(
            new Triangle(new Vector(0.5, 0.5, 1), new Vector(-0.5, 0.5, 1), new Vector(-0.5, -0.5, 1)),
            new Triangle(new Vector(0.5, 0.5, 1), new Vector(-0.5, -0.5, 1), new Vector(0.5, -0.5, 1))

            //new Triangle(new Vertex(0.5f, 0.5f, 1), new Vertex(-0.5f, -0.5f, 1), new Vertex(0.5f, -0.5f, 1))
        ));

        RenderTarget target = new RenderTarget(this.getWidth(), this.getHeight());

        target.drawMesh(cube);

        graphics.drawImage(target.image(), 0, 0, null);
    }
}
