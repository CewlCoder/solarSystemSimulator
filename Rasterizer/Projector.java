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
        float screenSpaceHeight = 1;

    
    
        float worldSpaceWidthToScreenRatio = width / screenSpaceWidth;
        float worldSpaceHeighToScreenRatio = height / screenSpaceHeight;

        float vertexDepth = vertex.z();

        float xScreenSpace = worldSpaceWidthToScreenRatio * vertex.x() / vertexDepth;
        float yScreenSpace = worldSpaceHeighToScreenRatio * vertex.y() / vertexDepth;

        float centerXScreenSpace = width/2 + xScreenSpace;
        float centerYScreenSpace = height/2 + yScreenSpace;

        return new Vertex(centerXScreenSpace, centerYScreenSpace, vertexDepth);
    }

}
