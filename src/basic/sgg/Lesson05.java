package basic.sgg;

import java.util.Arrays;

///归并排序和基数排序
public class Lesson05 {
	public static void main(String[] args) {
		test00();
	}

	static void test01(int[] array) {
//		int[] array = new int[] { 5, 8, 1, 0, 10 };
//		P.printArray(array);
		int[] temp = new int[array.length];
		Sort sort = new Sort();
		sort.mergeSort(array, 0, array.length - 1, temp);
//		P.printArray(array);
	}

	static void test02(int[] array) {
//		P.printArray(array);
		Sort sort = new Sort();
		sort.radixSort(array);
//		P.printArray(array);
	}

	/// 对数器验算
	static void test00() {
		int count = 100000;
//		count = 1;
		Sort sort = new Sort();
		for (int j = 1; j <= count; j++) {
			int size = N.getNum(1, 1000);
//			size = 4;
			int[] array = new int[size];
			for (int i = 0; i < size; i++) {
//				array[i] = N.getNum(-size, size);
				array[i] = N.getNum(0, size);
			}
//			array = new int[] { -4, 3, 4, 0 };
			int[] copyOf1 = Arrays.copyOf(array, size);
			int[] copyOf2 = Arrays.copyOf(array, size);
			Arrays.sort(copyOf1);
//			test01(copyOf2);
			test02(copyOf2);
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
