package basic.sgg;

///随机数类
public class N {

	/// 生成区间[min,max]
	public static int getNum(int min, int max) {
		int t = max - min;
		return (int) (Math.random() * (t + 1)) + min;
	}
}
