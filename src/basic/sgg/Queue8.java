package basic.sgg;

///8皇后模拟
public class Queue8 {

	int max;
	int[] map;
	int count = 1;

	Queue8(int max) {
		this.max = max;
		map = new int[max];
	}

	public int getCount() {
		return count;
	}

	public void run(int n) {
		if (n == max) {// n=8,说明0-7的皇后已经摆放完毕
			print();
			return;
		}
		for (int i = 1; i <= max; i++) {// 计算列的位置
			count++;
			map[n] = i;
			if (!isClash(n))
				run(n + 1);
		}
	}

	/// 当前皇后摆放位置与前面的皇后位置是否冲突
	private boolean isClash(int n) {
		for (int i = 0; i < n; i++) {
			// 垂直列是否冲突||斜列是否冲突
			if (map[i] == map[n] || Math.abs(map[n] - map[i]) == Math.abs(n - i))
				return true;
		}
		return false;
	}

	public void print() {
		for (int i = 0; i < max; i++) {
			System.out.printf("%d ", map[i]);
		}
		System.out.println();
	}
}
