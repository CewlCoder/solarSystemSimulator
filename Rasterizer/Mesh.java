package rasterizer;

import java.util.ArrayList;

public class Mesh {
    private ArrayList<Triangle> triangles;

    /**
     * Creates a mesh based on triangles.
     * 
     * @param triangles a list of triangles
     */
    public Mesh(ArrayList<Triangle> triangles) {
        this.triangles = triangles;
    }



    /**
     * Returs a list of all the triangles of which this mesh is based on.
     * 
     * @return a list of tirangles within this mesh
     */
    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

}
