package rasterizer;

public class Projector {
    private float width;
    private float height;

    /**
     * Creates a projector which handles world space and screen space.
     * 
     * @param width screen width
     * @param height screen height
     */
    public Projector(float width, float height) {
        this.width = width;
        this.height = height;
    }



    /**
     * Projects a vertex in world space to screen space.
     * 
     * @param vertex specified vertex
     * @return the projected vertex (screen_x, screen_y, world_z)
     */
    public Vertex project(Vertex vertex) {
        float screenSpaceWidth = 5;
        float worldSpaceToScreenRatio = width / screenSpaceWidth;

        float xScreenSpace = worldSpaceToScreenRatio * vertex.x() / vertex.z();
        float yScreenSpace = height - (worldSpaceToScreenRatio * vertex.y() / vertex.z());

        float centerXScreenSpace = width/2 + xScreenSpace;
        float centerYScreenSpace = yScreenSpace - height/2;

        return new Vertex(centerXScreenSpace, centerYScreenSpace, vertex.z());
    }

}
