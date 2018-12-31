package com.wishlist.data;

import java.util.Random;

public enum Headphone {

	Bose, Samsung, Boat, Sony;

	public static Headphone getRandomHeadphone() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

}
