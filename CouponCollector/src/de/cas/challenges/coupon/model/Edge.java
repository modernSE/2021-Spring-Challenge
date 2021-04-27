package de.cas.challenges.coupon.model;

import java.util.Objects;

public class Edge {
	public final Vertex from;
	public final Vertex to;

	public Edge(Vertex from, Vertex to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		return Objects.equals(from, other.from) && Objects.equals(to, other.to);
	}

	@Override
	public String toString() {
		return String.format("%s -> %s", from.toString(), to.toString());
	}

}