package basic.sgg;

///排序
public class Sort {

	/// 基数排序(桶排序)，不支持负数
	public void radixSort(int[] array) {
		if (array == null || array.length < 2)
			return;

		// 寻找最大数，确定最大数的位数
		int maxNum = array[0];
		for (int i = 1; i < array.length; i++) {
			if (maxNum < array[i]) {
				maxNum = array[i];
			}
		}
		int maxNumLength = String.valueOf(maxNum).length();

		// 桶数组，10个桶，每个桶最多length个数
		int[][] bucket = new int[10][array.length];

		// 第N个桶装了多少数
		int[] bucketCount = new int[10];
		int num = 0;
//		P.printArray(array);
		// 使用桶装数，进行排序
		for (int k = 0, base = 1; k < maxNumLength; k++, base *= 10) {// 读取位数
			for (int i = 0; i < array.length; i++) {
				num = array[i] / base % 10; // 个位数
				bucket[num][bucketCount[num]] = array[i];// 第N个桶的 指针位置装 array[i]
				bucketCount[num]++;// 第N个桶位置数量+1
			}
			num = 0;
			for (int j = 0; j < bucketCount.length; j++) {
				if (bucketCount[j] == 0)
					continue;
				// 第j个桶内有数据存放
				for (int x = 0; x < bucketCount[j]; x++) {
					array[num++] = bucket[j][x];
				}
				bucketCount[j] = 0;
			}
		}
	}

	/*
	 * arr[5,8,1] mid=1 left=0,right=2
	 */
	/// left:0 mid:0 right:1
	/// left:0 mid:1 right:2
	/// left:3 mid:3 right:4
	/// left:0 mid:2 right:4
	public void mergeSort(int[] array, int left, int right, int[] temp) {
		if (left >= right)
			return;
		int mid = (left + right) / 2;
		mergeSort(array, left, mid, temp);
		mergeSort(array, mid + 1, right, temp);
//		System.out.println("left:" + left + "\tmid:" + mid + "\tright:" + right);
		merge(array, left, mid, right, temp);
	}

	private void merge(int[] array, int left, int mid, int right, int[] temp) {
		int i = left;
		int j = mid + 1;
		int t = 0;
		while (i <= mid && j <= right) {
			if (array[i] > array[j]) {
				temp[t] = array[j];
				t++;
				j++;
			} else {
				temp[t] = array[i];
				t++;
				i++;
			}
		}
		while (i <= mid) {
			temp[t] = array[i];
			t++;
			i++;
		}
		while (j <= right) {
			temp[t] = array[j];
			t++;
			j++;
		}
		t = 0;
		int tempLeft = left;
		while (tempLeft <= right) {
			array[tempLeft] = temp[t];
			tempLeft++;
			t++;
		}
	}

	/// 冒泡排序
	public void bubbleSort(int[] array) {
		if (array == null || array.length < 2)
			return;
		boolean isSwap = false;
		int end = array.length - 1;
		for (int i = 0; i < end; i++) {
			isSwap = false;
			for (int j = 0; j < end - i; j++) {
				if (array[j] > array[j + 1]) {
					swap(array, j, j + 1);
					isSwap = true;
				}
			}
//			System.out.printf("第%d次排序:%s\n", i + 1, Arrays.toString(arr));
			if (!isSwap)
				break;
		}
	}

	/// 选择排序
	public void selectSort(int[] array) {
		if (array == null || array.length < 2)
			return;
		int end = array.length - 1;
		for (int i = 0; i < end; i++) {
			int minIndex = i;
			for (int j = i + 1; j <= end; j++) {
				if (array[minIndex] > array[j]) {
					minIndex = j;
				}
			}
			if (i != minIndex)
				swap(array, i, minIndex);
		}
	}

	/// 插入排序
	public void insertSort(int[] array) {
		if (array == null || array.length < 2)
			return;
		int end = array.length - 1;
		for (int i = 1; i <= end; i++) {
			for (int j = i - 1; j >= 0 && array[j] > array[j + 1]; j--) {
				swap(array, j + 1, j);
			}
		}
	}

	/// 插入排序2
	public void insertSort2(int[] array) {
		if (array == null || array.length < 2)
			return;
		int end = array.length - 1;
		int insertValue = 0;
		int insertIndex = 0;
		for (int i = 1; i <= end; i++) {
			insertValue = array[i];
			insertIndex = i - 1;
			if (array[insertIndex] > insertValue) {
				while (insertIndex >= 0 && array[insertIndex] > insertValue) {
					array[insertIndex + 1] = array[insertIndex];
					insertIndex--;
				}
				array[insertIndex + 1] = insertValue;
			}
		}
	}

	/// 希尔排序
	public void shellSort(int[] array) {
		if (array == null || array.length < 2)
			return;
		int end = array.length - 1;
		for (int step = array.length / 2; step >= 1; step /= 2) {
			for (int i = step; i <= end; i++) {
				for (int j = i - step; j >= 0 && array[j] > array[j + step]; j -= step) {
					swap(array, j, j + step);
				}
			}
		}
	}

	/// 希尔排序2
	public void shellSort2(int[] array) {
		if (array == null || array.length < 2)
			return;
		int end = array.length - 1;
		int insertValue = 0;
		int insertIndex = 0;
		for (int step = array.length / 2; step >= 1; step /= 2) {
			for (int i = step; i <= end; i++) {
				insertIndex = i - step;
				insertValue = array[i];
				if (array[insertIndex] > insertValue) {
					while (insertIndex >= 0 && array[insertIndex] > insertValue) {
						array[insertIndex + step] = array[insertIndex];
						insertIndex -= step;
					}
					array[insertIndex + step] = insertValue;
				}
			}
		}
	}

	/// 快排
	public void quickSort(int[] array, int left, int right) {
		if (left >= right || array == null || array.length < 2)
			return;
		int l = left, r = right;
		int midValue = array[(l + r) / 2];
		while (l < r) {
			// 3,-3,1
			while (array[l] < midValue) {
				l++;
			}
			while (array[r] > midValue) {
				r--;
			}

			if (l >= r)
				break;

			swap(array, l, r);
			if (array[l] == midValue) {
				r--;
			} else if (array[r] == midValue) {
				l++;
			}
		}
		if (left < r - 1) {
			quickSort(array, left, r - 1);
		}
		if (l + 1 < right) {
			quickSort(array, l + 1, right);
		}
	}

	public void quickSort2(int[] array, int left, int right) {
		if (left >= right || array == null || array.length < 2)
			return;
		int l = left, r = right;
		int midValue = array[left];
		while (l < r) {
			// -4, 3, 4, 0
			while (l < r && array[r] > midValue) {
				r--;
			}
			if (l < r) {
				array[l] = array[r];
				l++;
			}

			while (l < r && array[l] < midValue) {
				l++;
			}
			if (l < r) {
				array[r] = array[l];
				r--;
			}
			if (l >= r) {
				array[l] = midValue;
				break;
			}
		}
		if (left < r - 1) {
			quickSort(array, left, r - 1);
		}
		if (l + 1 < right) {
			quickSort(array, l + 1, right);
		}
	}

	private void swap(int[] arr, int i, int j) {
		if (i == j)
			return;
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
