package graphics3D;

import java.util.List;

/**
 * Creates a mesh based on triangles.
 * 
 * @param triangles a list of triangles
 */
public record Mesh(List<Triangle3D> triangles) {}
