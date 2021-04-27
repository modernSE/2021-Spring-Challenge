package de.cas.challenges.coupon;

import java.util.ArrayList;
import java.util.List;

import de.cas.challenges.coupon.model.Coupon;
import de.cas.challenges.coupon.model.Edge;
import de.cas.challenges.coupon.model.Vertex;

public class TravellingCouponCollectorInput {

	public final List<Edge> graphEgdes = new ArrayList<Edge>();
	public final List<Coupon> coupons = new ArrayList<Coupon>();
	public Vertex startVertex;
	public Vertex goalVertex;

}
