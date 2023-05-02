package basic.sgg;

import java.util.Scanner;

///数组模拟队列
public class Lesson01 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean isLoop = true;
		char key;
		ArrayQueue queue = new ArrayQueue(3);
		while (isLoop) {
			showMenu();
			key = scanner.next().charAt(0);
			switch (key) {
			case 'a':
				System.out.println("请输入数据:");
				int num = scanner.nextInt();
				queue.add(num);
				break;
			case 'g':
				try {
					int result = queue.get();
					P.print("获取的数据是:" + result);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 's':
				queue.show();
				break;
			case 'e':
				scanner.close();
				isLoop = false;
				break;
			}
		}
	}

	private static void showMenu() {
		System.out.println("请输入：");
		System.out.println("a(add)添加一个数据：");
		System.out.println("g(get)返回一个数据：");
		System.out.println("s(show)展示队列：");
		System.out.println("e(exit)退出：");
	}

}

class ArrayQueue {
	public int[] queue;
	private int left = 0;// 取值的位置
	private int right = 0;// 添加元素位置的后一位
	private int size;

	public ArrayQueue(int queueSize) {
		super();
		queue = new int[queueSize];
		size = queueSize;
	}

	public boolean isFull() {
		return right - left == size;
	}

	public boolean isEmpty() {
		return left == right;
	}

	public void add(int num) {
		if (isFull()) {
			System.out.println("队列已满,无法添加");
			return;
		}
		int index = right % size;
		queue[index] = num;
		right++;
	}

	public int get() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空，没有数据");
		}
		int index = left % size;
		left++;
		return queue[index];
	}

	public void show() {
		if (isEmpty()) {
			System.out.println("队列为空，没有数据");
			return;
		}
//		P.printArray(queue);
		System.out.print("{ ");
		for (int i = left; i < right; i++) {
			if (i == right - 1) {
				System.out.printf("%d }\n", queue[i % size]);
				break;
			}
			System.out.printf("%d ,", queue[i % size]);
		}

	}
}