package com.wishlist.data;

import java.util.Random;

public enum Mobile {
	Xiomi, Samsung, LG, Nokia;

	public static Mobile getRandomMobile() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
