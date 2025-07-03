package me.ciruu.abyss.util;

public class Timer {
    private long Field243 = -1L;
    private long Field242 = 0;
    public Timer class22;

    public boolean get10_0(double d) {
        return this.longTime(System.nanoTime() - this.Field243) >= (long)(d * 10.0);
    }

    public Timer() {
        class22 = this;
    }
    public void reset() {
        if (Field243 > this.Field243) {
            this.Field243 = System.nanoTime();
        }
    }

    public long onNanoTimemField1() {
        return this.longTime(System.nanoTime() - this.Field243);
    }

    public final boolean booleanTimeSys(long l) {
        return System.currentTimeMillis() - this.Field242 >= l;
    }

    public final boolean booleanTime(long l) {
        return this.Field242 <= l / 1000000L;
    }

    public long longTime(long l) {
        return l / 1000000L;
    }
}
