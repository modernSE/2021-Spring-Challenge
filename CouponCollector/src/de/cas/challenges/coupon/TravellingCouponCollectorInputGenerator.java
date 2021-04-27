package de.cas.challenges.coupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import de.cas.challenges.coupon.model.Coupon;
import de.cas.challenges.coupon.model.CouponCondition;
import de.cas.challenges.coupon.model.CouponCondition.Coordinate;
import de.cas.challenges.coupon.model.CouponCondition.CouponConditionOperator;
import de.cas.challenges.coupon.model.Edge;
import de.cas.challenges.coupon.model.Vertex;

public class TravellingCouponCollectorInputGenerator {

	private Random random;

	public TravellingCouponCollectorInput generateInput(int size, long seed) {
		TravellingCouponCollectorInput result = new TravellingCouponCollectorInput();
		random = new Random(seed);
		List<Vertex> vertices = generateVertices(size);
		generateEdges(size, result, vertices);
		findStartAndGoal(size, result);
		generateCoupons(size, result);

		System.out.println(
				String.format("generated problem instance with %d vertices, %d edges and %d coupons, start:%s, goal:%s",
						vertices.size(), result.graphEgdes.size(), result.coupons.size(), result.startVertex,
						result.goalVertex));
		System.out.println("total paths " + countPaths(result.startVertex, result.goalVertex, result.graphEgdes));
		return result;
	}

	private void generateCoupons(int size, TravellingCouponCollectorInput result) {
		long numberOfCoupons = Math.round(Math.pow(size, 4));
		for (long couponCount = 0; couponCount < numberOfCoupons; couponCount++) {
			result.coupons.add(new Coupon(random.nextInt(1000), generateConditions(size)));
		}
	}

	private CouponCondition[] generateConditions(int size) {
		int numberOfConditions = 1 + random.nextInt(5);
		CouponCondition[] conditions = new CouponCondition[numberOfConditions];
		for (int conditionCount = 0; conditionCount < numberOfConditions; conditionCount++) {
			conditions[conditionCount] = new CouponCondition(randomEnum(Coordinate.class),
					randomEnum(CouponConditionOperator.class), random.nextInt(size));
		}
		return conditions;
	}

	private <T extends Enum<?>> T randomEnum(Class<T> clazz) {
		int x = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}

	private void findStartAndGoal(int size, TravellingCouponCollectorInput result) {
		Vertex start = new Vertex(size, size, size);
		Vertex goal = new Vertex(0, 0, 0);
		for (Edge edge : result.graphEgdes) {
			if (edge.from.isSmaller(start)) {
				start = edge.from;
			}
			if (goal.isSmaller(edge.to)) {
				goal = edge.to;
			}
		}
		result.startVertex = start;
		result.goalVertex = goal;
	}

	private void generateEdges(int size, TravellingCouponCollectorInput result, List<Vertex> vertices) {
		Set<Edge> alreadyGeneratedEdges = new HashSet<Edge>();
		int numberOfEdges = Math.round(vertices.size() * (float) Math.sqrt(vertices.size()));
		while (alreadyGeneratedEdges.size() < numberOfEdges) {
			Vertex vertexFrom = randomFromVertex(vertices, size);
			Vertex vertexTo = randomLargerVertex(vertices, vertexFrom);
			Edge edge = new Edge(vertexFrom, vertexTo);
			if (!alreadyGeneratedEdges.contains(edge)) {
				alreadyGeneratedEdges.add(edge);
				result.graphEgdes.add(edge);
			}
		}
	}

	private List<Vertex> generateVertices(int size) {
		List<Vertex> vertices = new ArrayList<Vertex>();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				for (int z = 0; z < size; z++) {
					vertices.add(new Vertex(x, y, z));
				}
			}
		}
		return vertices;
	}

	private Vertex randomVertex(List<Vertex> vertices) {
		return vertices.get(random.nextInt(vertices.size()));
	}

	private Vertex randomFromVertex(List<Vertex> vertices, int sizeInOneDimension) {
		Vertex candidate = randomVertex(vertices);
		while (candidate.x == sizeInOneDimension - 1 && candidate.y == sizeInOneDimension - 1
				&& candidate.z == sizeInOneDimension - 1) {
			candidate = randomVertex(vertices);
		}
		return candidate;
	}

	private Vertex randomLargerVertex(List<Vertex> vertices, Vertex vertex) {
		Vertex candidate = randomVertex(vertices);
		while (candidate.isSmaller(vertex) || candidate.equals(vertex)) {
			candidate = randomVertex(vertices);
		}
		return candidate;
	}

	private HashMap<Vertex, Double> pathCache;

	private double countPaths(Vertex from, Vertex to, List<Edge> edges) {
		pathCache = new HashMap<Vertex, Double>();
		return countPathsRecursive(from, to, edges);
	}

	private double countPathsRecursive(Vertex from, Vertex to, List<Edge> edges) {
		if (from.equals(to)) {
			return 1;
		}
		if (pathCache.containsKey(from)) {
			return pathCache.get(from);
		}
		double total = 0;
		for (Edge e : edges) {
			if (e.from.equals(from)) {
				total += countPathsRecursive(e.to, to, edges);
			}
		}
		pathCache.put(from, total);
		return total;
	}

}
