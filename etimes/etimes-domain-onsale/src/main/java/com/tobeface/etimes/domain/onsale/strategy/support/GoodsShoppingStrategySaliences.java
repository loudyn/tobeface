package com.tobeface.etimes.domain.onsale.strategy.support;

/**
 * 
 * @author loudyn
 * 
 */
enum GoodsShoppingStrategySaliences {

	DEFAULT(-1),
	MINUS_CASH(1),
	DISCOUNT(2),
	GIVE_GIFTS(4);

	private final int salience;

	private GoodsShoppingStrategySaliences(int salience) {
		this.salience = salience;
	}

	public int getSalience() {
		return salience;
	}
}
