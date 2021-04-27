package de.cas.challenges.coupon.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Coupon {

	public final int value;
	public final List<CouponCondition> conditions;

	public Coupon(int value, CouponCondition... conditions) {
		this.value = value;
		this.conditions = Arrays.asList(conditions);
	}

	public boolean isConditionsSatisfied(int x, int y, int z) {
		for (CouponCondition condition : conditions) {
			if (!condition.isSatisfied(x, y, z)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return value + ": " + conditions.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(conditions, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		return Objects.equals(conditions, other.conditions) && value == other.value;
	}

}