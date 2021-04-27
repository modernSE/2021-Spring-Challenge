package de.cas.challenges.coupon;

import java.util.HashMap;
import java.util.Map;

import de.cas.challenges.coupon.model.Coupon;
import de.cas.challenges.coupon.model.Edge;
import de.cas.challenges.coupon.model.Vertex;
import de.cas.challenges.utilities.Stopwatch;

public class TravellingCouponCollectorSolver {

	public long trivial_solve(TravellingCouponCollectorInput input) {
		Stopwatch stopwatch = new Stopwatch();
		return trivialSolveRecursive(input.startVertex, input, stopwatch);
	}

	// This solution has exponential runtime, it is included only for educational purposes because of its simplicity
	private int trivialSolveRecursive(Vertex from, TravellingCouponCollectorInput input, Stopwatch stopwatch) {
		int bestCouponValue = findBestCoupon(from, input);
		if (from.equals(input.goalVertex)) {
			return bestCouponValue;
		}
		int bestSolution = 0;
		for (Edge edge : input.graphEgdes) {
			if (edge.from == from) {
				int solution = trivialSolveRecursive(edge.to, input, stopwatch);
				if (solution > bestSolution) {
					bestSolution = solution;
				}
			}
		}
		return bestSolution + bestCouponValue;
	}

	public long polynomial_solve(TravellingCouponCollectorInput input) {
		Map<Vertex, Integer> resultCache = new HashMap<Vertex, Integer>();
		return polynomialSolveRecursive(input.startVertex, input, resultCache);
	}

	private int polynomialSolveRecursive(Vertex from, TravellingCouponCollectorInput input,
			Map<Vertex, Integer> resultCache) {
		if (resultCache.containsKey(from)) {
			return resultCache.get(from);
		}
		int bestCouponValue = findBestCoupon(from, input);
		if (from.equals(input.goalVertex)) {
			resultCache.put(from, bestCouponValue);
			return bestCouponValue;
		}
		int bestSolution = 0;
		for (Edge edge : input.graphEgdes) {
			if (edge.from == from) {
				int solution = polynomialSolveRecursive(edge.to, input, resultCache);
				if (solution > bestSolution) {
					bestSolution = solution;
				}
			}
		}
		resultCache.put(from, bestSolution + bestCouponValue);
		return bestSolution + bestCouponValue;
	}
	
	private int findBestCoupon(Vertex from, TravellingCouponCollectorInput input) {
		int bestCouponValue = 0;
		for (Coupon coupon : input.coupons) {
			if (coupon.isConditionsSatisfied(from.x, from.y, from.z)) {
				if (coupon.value > bestCouponValue) {
					bestCouponValue = coupon.value;
				}
			}
		}
		return bestCouponValue;
	}


}
