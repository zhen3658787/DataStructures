package basic.sgg;

import java.util.Arrays;
import java.util.Date;

public class Lesson04 {

	public static void main(String[] args) {
		test00();
//		Sort sort = new Sort();
//		test01(sort, 100000);
//		test02(sort, 100000);
	}

	/// 冒泡排序
	static void test01(Sort sort, int n) {
//		n = 100000;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = N.getNum(10, 100);
		}
		Date date = new Date();
//		System.out.println("冒泡排序前：\n" + Arrays.toString(arr));

		sort.bubbleSort(arr);
		long useTime = new Date().getTime() - date.getTime();
		System.out.printf("冒泡排序用时(%d)：%d\n", n, useTime);
//		System.out.println("冒泡排序后：\n" + Arrays.toString(arr));
	}

	/// 选择排序
	static void test02(Sort sort, int n) {
//		n = 100000;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = N.getNum(10, 100);
		}
		Date date = new Date();

		sort.selectSort(arr);
		long useTime = new Date().getTime() - date.getTime();
		System.out.printf("选择排序用时(%d)：%d\n", n, useTime);
	}

	/// 简单插入排序
	static void test03(int[] arr, Sort sort, int n) {
//		n = 100000;
//		int[] arr = new int[n];
//		for (int i = 0; i < n; i++) {
//			arr[i] = N.getNum(10, 100);
//		}
		Date date = new Date();

		sort.insertSort(arr);
		long useTime = new Date().getTime() - date.getTime();
		System.out.printf("简单插入排序1用时(%d)：%d\n", n, useTime);
	}

	/// 简单插入排序2
	static void test03_2(int[] arr, Sort sort, int n) {
//		n = 100000;
//		int[] arr = new int[n];
//		for (int i = 0; i < n; i++) {
//			arr[i] = N.getNum(10, 100);
//		}
		Date date = new Date();

		sort.insertSort2(arr);
		long useTime = new Date().getTime() - date.getTime();
		System.out.printf("简单插入排序2用时(%d)：%d\n", n, useTime);
	}

	/// 希尔排序
	static void test04(int[] arr, Sort sort, int n) {
//		n = 100000;
//		int[] arr = new int[n];
//		for (int i = 0; i < n; i++) {
//			arr[i] = N.getNum(10, 100);
//		}
		Date date = new Date();

		sort.shellSort(arr);
		long useTime = new Date().getTime() - date.getTime();
		System.out.printf("希尔排序用时(%d)：%d\n", n, useTime);
	}

	/// 希尔排序
	static void test04_2(int[] arr, Sort sort, int n) {
//		n = 100000;
//		int[] arr = new int[n];
//		for (int i = 0; i < n; i++) {
//			arr[i] = N.getNum(10, 100);
//		}
		Date date = new Date();

		sort.shellSort2(arr);
		long useTime = new Date().getTime() - date.getTime();
		System.out.printf("希尔排序2用时(%d)：%d\n", n, useTime);
	}

	/// 对数器验算
	static void test00() {
		int count = 100000;
//		count = 1;
		Sort sort = new Sort();
		for (int j = 1; j <= count; j++) {
			int size = N.getNum(1, 5000000);
//			size = 3;
			int[] array = new int[size];
			for (int i = 0; i < size; i++) {
				array[i] = N.getNum(-size, size);
			}
//			array = new int[] { 2, 3, -2 };
			int[] copyOf1 = Arrays.copyOf(array, size);
			int[] copyOf2 = Arrays.copyOf(array, size);
//			Arrays.sort(copyOf1);
//			test03(copyOf1, sort, size);
//			test03_2(copyOf2, sort, size);
			test04(copyOf1, sort, size);
			test04_2(copyOf2, sort, size);
			for (int i = 0; i < size; i++) {
				if (copyOf1[i] != copyOf2[i]) {
					System.out.printf("原始数据：%s\n", Arrays.toString(array));
					System.out.printf("排序正确：%s\n", Arrays.toString(copyOf1));
					System.out.printf("排序错误：%s\n", Arrays.toString(copyOf2));
					return;
				}
			}
			System.out.printf("第%d次排序验证完毕.\n", j);
		}
	}

}
