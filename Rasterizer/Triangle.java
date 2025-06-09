package rasterizer;

import java.util.Vector;

/**
 * Creates a triangle defined by the vectors first, second and third.
 * 
 * @param first first vector of triangle
 * @param second second vector of triangle
 * @param third third vector of triangle
 */
public record Triangle(Vector<Float> first, Vector<Float> second, Vector<Float> third) {};
