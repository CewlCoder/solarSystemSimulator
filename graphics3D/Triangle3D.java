package graphics3D;

/**
 * Creates a 3D triangle defined by the vertices a, b and c.
 * 
 * @param a first vertex of triangle
 * @param b second vertex of triangle
 * @param c third vertex of triangle
 */
public record Triangle3D(Vector3D a, Vector3D b, Vector3D c) {}
