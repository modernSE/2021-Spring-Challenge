package de.cas.challenges.coupon.model;

import java.util.Objects;

public class Vertex {
	
	public final int x;
	public final int y;
	public final int z;

	public Vertex(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public boolean isSmaller(Vertex otherVertex) {
		return x + y + z <= otherVertex.x + otherVertex.y + otherVertex.z;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		return x == other.x && y == other.y && z == other.z;
	}
	
	@Override
	public String toString() {
		return String.format("[%d,%d,%d]", x,y,z);
	}

}