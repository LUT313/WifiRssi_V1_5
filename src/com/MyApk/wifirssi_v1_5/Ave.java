package com.MyApk.wifirssi_v1_5;

import java.util.Arrays;

public class Ave {
	// ȡ��ֵ
		public static int average(int[] a) {
			Arrays.sort(a);// sort����
			exchange(a);// ����
			int ave = 0;
			int temp = 30;
			int S = 0;
			for (int i = 0; i < 30; i++) {
				if (a[i] == -200) {
					temp = temp - 1;
					continue;
				}
				S += a[i];
			}
			ave = S / temp;
			return ave;
		}

		// ����
		public static void exchange(int a[]) {
			int x = a.length / 2;
			for (int i = 0; i < x; i++)
				swap(a, i, (a.length - i - 1));
		}

		// ����
		public static void swap(int a[], int x, int y) {
			int tmp = a[x];
			a[x] = a[y];
			a[y] = tmp;
		}

}
