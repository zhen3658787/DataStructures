package basic.sgg;

///快捷打印类
public class P {

	public static void printArray(int[] array) {
		if (array == null) {
			System.out.println("array==null");
			return;
		}
		System.out.print("{");
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				System.out.println(" " + array[i] + " }");
				break;
			}
			System.out.print(" " + array[i] + " ,");
		}
	}

	public static void print(Object obj) {
		System.out.println(obj);
	}

	public static void printLinkedList(ListNode head) {
		if (head == null) {
			System.out.println("节点为null...");
			return;
		}
		int index = 0, count = 1;
		while (head.next != null) {
			if ((count & 3) == 0) {
				System.out.printf("[(%d)%d] >> \n", index, head.val);
			} else {
				System.out.printf("[(%d)%d] >> ", index, head.val);
			}
			head = head.next;
			index++;
			count++;
		}
		System.out.printf("[(%d)%d]\n", index, head.val);
	}

	public static void printMapList(int[][] map) {
		for (int i = 0; i < map.length; i++) {

			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
