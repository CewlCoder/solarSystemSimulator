package view;

import javax.swing.JPanel;

import graphics3D.Mesh;
import view.renderer.Renderer;

import java.awt.Graphics;

public class View extends JPanel {
    private ViewableModel model;

    private Renderer renderer;
    
    private int oldWidth = -1;
    private int oldHeight = -1;

    public View(ViewableModel model) {
        this.model = model;
    }



    public Renderer renderer() {
        return renderer;
    }



    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        int width = this.getWidth();
        int height = this.getHeight();

        if ((width != oldWidth) | (height != oldHeight)) {
            this.renderer = new Renderer(width, height);

            this.oldWidth = width;
            this.oldHeight = height;
        }

        renderer.clearImage();

        for (Mesh mesh : model.meshes()) {
            renderer.renderMesh(model.camera(), mesh);
        }

        renderer.displayImage(graphics);
    }

}
