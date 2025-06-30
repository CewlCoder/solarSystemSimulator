package view;

import javax.swing.JPanel;

import graphics3D.Mesh;
import graphics3D.renderer.RenderTarget;

import java.awt.Graphics;

public class View extends JPanel {
    private Viewablemodel model;

    public View(Viewablemodel model) {
        this.model = model;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        RenderTarget target = new RenderTarget(model.camera(), this.getWidth(), this.getHeight());

        for (Mesh mesh : model.meshes()) {
            target.drawMesh(mesh);
        }

        graphics.drawImage(target.image(), 0, 0, null);
    }
}
