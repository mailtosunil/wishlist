package com.wishlist.data;

import java.util.Random;

public enum Shoe {

	Nike, Reebok, Adidas, Fila;

	public static Shoe getRandomShoe() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

}
