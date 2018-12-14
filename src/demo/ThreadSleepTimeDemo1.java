package demo;

/**
 * 线程睡眠时间测试，一次函数
 */
public class ThreadSleepTimeDemo1 {
  public static void main(String[] args) {
    for(int level = 1; level <= 100; level++) {
      long sleep = (-40 * level + 740);
      sleep = sleep < 100 ? 100 : sleep;
      System.out.println(level + ":\t" + sleep);
    }
  }
}
