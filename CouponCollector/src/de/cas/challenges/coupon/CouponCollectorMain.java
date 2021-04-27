package de.cas.challenges.coupon;

import de.cas.challenges.utilities.Stopwatch;

public class CouponCollectorMain {

	public TravellingCouponCollectorInput input;

	public static void main(String[] args) {
		
		TravellingCouponCollectorInputGenerator generator = new TravellingCouponCollectorInputGenerator();
		TravellingCouponCollectorSolver solver = new TravellingCouponCollectorSolver();

		int benchmarkSize = 10;
		int experimentCount = 10;
		float totalTime = 0;

		for (int experimentId = 0; experimentId < experimentCount; experimentId++) {
			TravellingCouponCollectorInput input = generator.generateInput(benchmarkSize, experimentId);
			Stopwatch timer = new Stopwatch();
			long result = solver.polynomial_solve(input);
			float elapsedSeconds = timer.elapsedSeconds();
			totalTime += elapsedSeconds;
			System.out.println(String.format("The result of experiment %d is %d it took %.3f seconds to compute",
					experimentId, result, elapsedSeconds));
		}

		System.out.println(
				String.format("Experiment finished average runtime was %.3f seconds.", totalTime / experimentCount));
	}

}
