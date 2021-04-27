package de.cas.challenges.coupon.model;

import java.util.Objects;

public class CouponCondition {
	
	public enum Coordinate {
		X, Y, Z
	}

	public enum CouponConditionOperator {
		EQUALS("="), LESS("<"), LESS_OR_EQUALS("<="), MORE(">"), MORE_OR_EQUALS(">=");
		
		public String symbol;
		private CouponConditionOperator(String symbol) {
			this.symbol = symbol;
		}
	}


	public final Coordinate coordinate;
	public final CouponConditionOperator operator;
	public final int value;

	public CouponCondition(Coordinate coordinate, CouponConditionOperator operator, int value) {
		this.coordinate = coordinate;
		this.operator = operator;
		this.value = value;
	}
	
	public CouponCondition(String condition) {
		String[] parts = condition.split(" ");
		this.coordinate = Coordinate.valueOf(parts[0]);
		CouponConditionOperator operator = null;
		for (CouponConditionOperator op : CouponConditionOperator.values()) {
			if (op.symbol.equals(parts[1])) {
				operator = op;
			}
		}
		this.operator = operator;
		this.value = Integer.parseInt(parts[2]);
	}

	public boolean isSatisfied(int x, int y, int z) {
		int relevantCoordinate = coordinate == Coordinate.X ? x : coordinate == Coordinate.Y ? y : z;
		switch (operator) {
		case EQUALS:
			return relevantCoordinate == value;
		case LESS:
			return relevantCoordinate < value;
		case LESS_OR_EQUALS:
			return relevantCoordinate <= value;
		case MORE:
			return relevantCoordinate > value;
		case MORE_OR_EQUALS:
			return relevantCoordinate >= value;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordinate, operator, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CouponCondition other = (CouponCondition) obj;
		return coordinate == other.coordinate && operator == other.operator && value == other.value;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %d", coordinate, operator.symbol, value);
	}

}