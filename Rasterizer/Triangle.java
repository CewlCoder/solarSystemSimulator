package rasterizer;

/**
 * Creates a triangle defined by the vertices first, second and third.
 * 
 * @param first first vertex of triangle
 * @param second second vertex of triangle
 * @param third third vertex of triangle
 */
public record Triangle(Vertex first, Vertex second, Vertex third) {}
