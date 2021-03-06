package com.tynet.app.api;



import java.util.Comparator;

import com.tynet.app.bean.TongXunLu;

public class PinyinComparator implements Comparator<TongXunLu> {

	public int compare(TongXunLu o1, TongXunLu o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
