package view;

import javax.swing.JPanel;

import rasterizer.Projector;
import rasterizer.Triangle;
import rasterizer.Vertex;

import java.awt.Graphics;

public class View extends JPanel {
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        Triangle tris = new Triangle(
            new Vertex(0.5f, 0.5f, 1),
            new Vertex(-0.5f, 0.5f, 1),
            new Vertex(-0.5f, -0.5f, 1)
        );

        drawTriangle(graphics, new Projector(this.getWidth(), this.getHeight()), tris);
    }

    private void drawTriangle(Graphics graphics, Projector projector, Triangle triangle) {
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        Vertex[] vertices = {
            projector.project(triangle.first()),
            projector.project(triangle.second()),
            projector.project(triangle.third())
        };

        for (int index = 0; index < 3; index++) {
            xPoints[index] = Math.round(vertices[index].x());
            yPoints[index] = Math.round(vertices[index].y());
        }

        graphics.drawPolygon(xPoints, yPoints, 3);
    }
}
