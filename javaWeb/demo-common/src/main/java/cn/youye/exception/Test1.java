package cn.youye.exception;

/**
 * Created by pc on 2016/9/13.
 */
public class Test1 {
    private int x;
    private int y;

    public Test1() {
    }

    public Test1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int show(int x,int y) throws ArithmeticException{
        return x/y;
    }
}
