package demo;

/**
 * 线程睡眠时间测试，反比例函数
 */
public class ThreadSleepTimeDemo2 {
  public static void main(String[] args) {
    for (int level = 1; level < 100; level++) {
      long sleep = (long)(1750 / (level + 1.5));
      System.out.println(level + ":\t" + sleep + "ms");
    }
  }
}
