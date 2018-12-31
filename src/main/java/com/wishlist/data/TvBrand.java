package com.wishlist.data;

import java.util.Random;

public enum TvBrand {
	Xiomi, Samsung, LG, VU;

	public static TvBrand getRandomTv() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
