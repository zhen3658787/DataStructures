package basic.sgg;

import java.util.Scanner;
import java.util.Stack;

///数组模拟栈练习
public class Lesson03 {
	public static void main(String[] args) {
//		test01();
//		test02();
//		test03();
//		test04(7);
		test05();
	}

	/// 8皇后问题模拟,递归与回溯
	static void test05() {
		Queue8 queue = new Queue8(8);
		queue.run(0);
		int count = queue.getCount();
		System.out.println("一共计算了：" + count + "次 ");
	}

	/// 迷宫路径模拟,递归与回溯
	static void test04(int n) {
		// 创建地图
		int[][] map = new int[n][n];
		// 设置地图边界为1
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (i == 0 || i == map.length - 1 || j == 0 || j == map[i].length - 1) {
					map[i][j] = 1;
				}
			}
		}
		for (int i = 0; i < 5; i++) {
			int x = N.getNum(2, n - 2);
			int y = N.getNum(2, n - 2);
			map[x][y] = 1;
			System.out.printf("[%d,%d]=1\t", x, y);
		}
		int endX = N.getNum(2, n - 2);
		int endY = N.getNum(2, n - 2);
		map[endX][endY] = 9;
		System.out.println("endX:" + endX + "\tendY:" + endY);
		// 设置路径寻址
		boolean isFind = setRoute(map, 1, 1, endX, endY);
		System.out.println("isFind:" + isFind);
		// 打印地图
		P.printMapList(map);

	}

	static boolean setRoute(int[][] map, int x, int y, int endX, int endY) {
		if (endX == x && endY == y) {
			return true;
		} else if (map[x][y] == 0) {// 路径未走过，可以尝试走
			map[x][y] = 2;
			// 上、右、下、左
			if (setRoute(map, x - 1, y, endX, endY)) {
				return true;
			} else if (setRoute(map, x, y + 1, endX, endY)) {
				return true;
			} else if (setRoute(map, x + 1, y, endX, endY)) {
				return true;
			} else if (setRoute(map, x, y - 1, endX, endY)) {
				return true;
			} else {
				map[x][y] = 3;
			}
		}
		// 1墙 2走过的路 3走不通的路
		return false;
	}

	/// 中缀表达式转后缀表达式
	static void test03() {
		String midEx = "1+((2+3)*4)-5";
//		midEx = " 0123456789a";
		String buffer = "";
		int index = 0;
		System.out.println("原表达式为：" + midEx);
		Stack<Character> stack = new Stack<Character>();
		StringBuilder target = new StringBuilder();
		int endIndex = midEx.length() - 1;
		while (index <= endIndex) {
			char c = midEx.charAt(index);
			if (isNum(c)) {
				// 是数字
				buffer += c;
				if (index == endIndex || !isNum(midEx.charAt(index + 1))) {
					target.append(buffer);
					buffer = "";
				}
			} else {
				// 不是数字，是操作符或者括号
				if (stack.isEmpty() || isLeft(c)) {
					// 栈内是否为空或者左括号，是就入栈
					stack.push(c);
				} else {
					// 右括号栈内弹出直到左括号;
					if (isRight(c)) {
						// 右括号
						while (!isLeft(stack.peek())) {
							target.append(stack.pop());
						}
						stack.pop();
					} else {
						// 比较优先级，大的入栈，小于等于，把s1弹出到target，然后重复比较优先级...
						int priority = priority(c);
						if (priority == -1)
							throw new RuntimeException("无法解析的操作符：index(" + index + ")" + c);
						while (true) {
							if (stack.isEmpty() || (priority(stack.peek()) < priority)) {// 扫描到的操作符优先级大于栈顶的，直接入栈
								stack.push(c);
								break;
							}
							target.append(stack.pop());
						}
					}
				}
			}
			index++;
		}
		while (!stack.isEmpty()) {
			target.append(stack.pop());
		}
		System.out.println(target.toString());
	}

	static boolean isRight(char c) {
		return c == ')';
	}

	static boolean isLeft(char c) {
		return c == '(';
	}

	static boolean isNum(char c) {
		if (c >= 48 && c < 58)
			return true;
		return false;
	}

	/// 计算+-*/简单数学表达式的值
	static void test02() {
		String ex = "5-3*2-6+1*2*2*2";
//		String ex = "5-3*2-6";
		ArrayStack numStack = new ArrayStack(10);
		ArrayStack operatorStack = new ArrayStack(10);
		int index = 0;
		char key;
		String buffer = "";
		while (true) {
			if (index == ex.length()) {
				break;
			}
			///////////////////////////
			// 获取元素
			key = ex.substring(index, index + 1).charAt(0);

			// 判断key是什么类型
			if (isOperator(key)) {
				handleOperator(key, index, operatorStack, numStack);
			} else {
				// 是数字
				buffer += key;
				if (index == ex.length() - 1 || isOperator(ex.substring(index + 1, index + 2).charAt(0))) {
					numStack.push(Integer.parseInt(buffer));
					buffer = "";
				}
			}
			///////////////////////////
			index++;
		}

		while (!operatorStack.isEmpty()) {
			int num1 = numStack.pop();
			int num2 = numStack.pop();
			char ope = (char) operatorStack.pop();
			numStack.push(calc(num1, num2, ope));
		}
		System.out.printf("%s 的结果是:%d", ex, numStack.pop());
	}

	private static void handleOperator(char key, int index, ArrayStack operatorStack, ArrayStack numStack) {
		// 是符号
		if (operatorStack.isEmpty()) {
			operatorStack.push(key);

		} else {
			// 栈中有符号,判断优先级
			while (!operatorStack.isEmpty() && priority(key) <= priority((char) operatorStack.peek())) {
				int num1 = numStack.pop();
				int num2 = numStack.pop();
				char ope = (char) operatorStack.pop();
				numStack.push(calc(num1, num2, ope));
			}
			operatorStack.push(key);
		}
	}

	/// 判断符号的优先级
	static int priority(char key) {
		if (key == '*' || key == '/' || key == '%') {
			return 1;
		} else if (key == '+' || key == '-') {
			return 0;
		}
		return -1;
	}

	static int calc(int num1, int num2, char oper) {
		int result = 0;
		switch (oper) {
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num2 - num1;
			break;
		case '*':
			result = num2 * num1;
			break;
		case '/':
			result = num2 / num1;
			break;
		}
		return result;
	}

	/// 判断是否是符号
	static boolean isOperator(char key) {
		return '+' == key || '-' == key || '*' == key || '/' == key;
	}

	/// 简单的栈
	static void test01() {
		ArrayStack stack = new ArrayStack(3);
		Scanner scanner = new Scanner(System.in);
		boolean isLoop = true;
		char key;
		while (isLoop) {
			showMenu();
			key = scanner.next().charAt(0);
			switch (key) {
			case 'a':
				System.out.println("请输入数据:");
				int num = scanner.nextInt();
				stack.push(num);
				break;
			case 'g':
				try {
					int result = stack.pop();
					P.print("获取的数据是:" + result);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'p':
				try {
					int result = stack.peek();
					P.print("top数据是:" + result);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 's':
				stack.show();
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
		System.out.println("p(peek)查看top数据：");
		System.out.println("s(show)展示队列：");
		System.out.println("e(exit)退出：");
	}

	static class ArrayStack {

		public int size;
		public int[] stack;
		private int top = -1;

		public ArrayStack(int stackSize) {
			super();
			size = stackSize;
			stack = new int[size];
		}

		public boolean isEmpty() {
			return top == -1;
		}

		public boolean isFull() {

			return top == size - 1;
		}

		public void push(int num) {
			if (isFull()) {
				System.out.println("栈已满,无法存入数据!");
				return;
			}
			top++;
			stack[top] = num;
		}

		public int pop() {
			if (isEmpty()) {
				throw new RuntimeException("栈已空,无法取出数据!");
			}
			int num = stack[top--];
			return num;
		}

		public int peek() {
			if (isEmpty()) {
				throw new RuntimeException("栈已空,无法取出数据!");
			}
			int num = stack[top];
			return num;
		}

		public void show() {
			if (top == -1) {
				System.out.println("[]");
				return;
			}
			int tempIndex = top;
			System.out.print("[");
			while (tempIndex >= 0) {
				if (tempIndex == 0) {
					System.out.printf(" (%d)%d ]\n", tempIndex, stack[tempIndex]);
				} else {

					System.out.printf(" (%d)%d ,", tempIndex, stack[tempIndex]);
				}
				tempIndex--;
			}

		}
	}
}
