package basic.sgg;

///排序
public class Sort {

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

	private void swap(int[] arr, int i, int j) {
		if (i == j)
			return;
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
