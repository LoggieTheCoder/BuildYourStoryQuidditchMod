package fr.loggie.bys.entities.obj.part;


public class Vertex {
    public float x, y, z;

    public Vertex(float x, float y) {
        this(x, y, 0F);
    }

    public Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return x + "/" + y + "/" + z;
    }
}
