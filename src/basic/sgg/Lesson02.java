package basic.sgg;

///单链表增删改查练习
public class Lesson02 {

	public static void main(String[] args) {
		SingleLinkedList list = new Lesson02.SingleLinkedList();
//		test1(list);
//		test2(list,N.getNum(5, 10),N.getNum(10, 20));
//		test3(list);
//		test4(list);
//		test5(list);
//		test6(list);
//		test7(list);
	}

	/// 约瑟夫环模拟
	static void test7(SingleLinkedList list) {
		// 生成节点
		for (int i = 1; i < N.getNum(2, 20); i++) {
			list.add(new ListNode(i));
		}
		P.printLinkedList(list.getHead());
		// 组成环
		list.loop(0);
		int n = N.getNum(1, 5);
		ListNode head = list.getHead();
		// 获得第一个节点的前一个节点
		ListNode pre = head;
		while (pre.next != head) {
			pre = pre.next;
		}
		int current = 1;
		System.out.println("n=" + n);
		// 模拟报数，报到的节点退出链表
		while (head.next != head) {
			if (current == n) {// 当前节点报到数为n
				System.out.print(head.val + " ");
				head = head.next;
				pre.next = head;
				current = 1;
				continue;
			}
			current++;
			head = head.next;
			pre = pre.next;
		}
		System.out.print(head.val + " ");
	}

	/// 是否是环结构
	static void test6(SingleLinkedList list) {
		// 生成节点
		test2(list, 0, 5);
		// 组成环
		list.loop(3);
		System.out.println("链表是否是环结构？" + list.isLoop());

	}

	/// 删除倒数第n个节点
	static void test5(SingleLinkedList list) {
		test2(list, 0, N.getNum(1, 5));// 先生成链表
		int size = list.size();
		int n = N.getNum(1, size);
		System.out.println("链表长度：" + size + "\t n:" + n);
		P.printLinkedList(list.removeLastIndex(n));
	}

	/// 倒序排列链表
	static void test4(SingleLinkedList list) {
		test2(list, N.getNum(5, 10), N.getNum(10, 20));// 先生成链表
		ListNode head = list.reverse();
		System.out.println("-------------------------------------------");
		P.printLinkedList(head);
		System.out.println("-------------------------------------------");
		P.printLinkedList(list.getHead());
	}

	/// 查找指定的node
	static void test3(SingleLinkedList list) {
		ListNode node = new ListNode(N.getNum(1, 25));
		System.out.println("Node：" + node.val);
		test2(list, N.getNum(5, 10), N.getNum(10, 20));
		System.out.println(list.has(node));
	}

	/// 按节点val顺序添加
	static void test2(SingleLinkedList list, int min, int max) {
		System.out.println("count:" + (max - min));
		for (int i = min; i < max; i++) {
			list.addByOrder(new ListNode(N.getNum(1, 25)));
		}
		P.printLinkedList(list.getHead());
	}

	/// 添加到节点末尾
	static void test1(SingleLinkedList list) {
		int min = N.getNum(5, 10);
		int max = N.getNum(10, 20);

		for (int i = min; i < max; i++) {
			list.add(new ListNode(i));
		}
		P.printLinkedList(list.getHead());
	}

	static class SingleLinkedList {

		private ListNode head = new ListNode(-1);

		public ListNode getHead() {
			return head.next;
		}

		/// 添加新节点到末尾
		public ListNode add(ListNode node) {
			ListNode temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = node;
			return head;
		}

		/// 按元素大小添加
		public ListNode addByOrder(ListNode node) {
			ListNode temp = head;
			boolean isAdd = false;
			while (temp.next != null) {
				if (temp.next.val > node.val) {
					node.next = temp.next;
					temp.next = node;
					isAdd = true;
					break;
				}
				temp = temp.next;
			}
			if (!isAdd) {
				temp.next = node;
			}
			return head;
		}

		/// 查找节点中是否有val一样的节点
		public boolean has(ListNode node) {
			if (node == null)
				return false;
			ListNode temp = head.next;
			while (temp != null) {
				if (temp.val == node.val) {
					return true;
				}
				temp = temp.next;
			}
			return false;
		}

		/// 返回指定位置的元素，没有则返回null
		public ListNode index(int index) {
			ListNode temp = head.next;
			int count = 0;
			while (temp != null) {
				if (count == index) {
					return temp;
				}
				count++;
				temp = temp.next;
			}
			return null;
		}

		/// 反转链表
		public ListNode reverse() {
			if (head.next == null)
				return null;
			ListNode temp, pre = null, next = null;
			temp = head.next;
			while (temp != null) {
				next = temp.next;
				temp.next = pre;
				pre = temp;
				temp = next;
			}
			head.next = pre;
			return pre;
		}

		public int size() {
			int count = 0;
			ListNode temp = head.next;
			while (temp != null) {
				count++;
				temp = temp.next;
			}

			return count;
		}

		/// 删除倒数第n个节点
		public ListNode removeLastIndex(int n) {
			if (head.next == null)
				return null;
			ListNode left = head, right = head;
			int space = 0;
			while (right != null) {
				if (space <= n) {
					space++;
					right = right.next;
					continue;
				}
				left = left.next;
				right = right.next;
			}
			left.next = left.next.next;
			return head.next;
		}

		/// 组成环,末尾节点指向index个节点
		public void loop(int index) {
			ListNode temp = head.next;
			ListNode loopNode = index(index);
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = loopNode;
		}

		public boolean isLoop() {
			ListNode oldHead = head.next;
			ListNode newHead = reverse();
			if (oldHead == newHead)
				return true;
			return false;
		}
	}
}
