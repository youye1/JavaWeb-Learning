package cn.youye.exception;

/**
 * Created by pc on 2016/9/13.
 */
public class Test2 extends Test1 {
    private int z;

    public Test2(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public int show(int x, int y) {
        try {
            return x / y;
        }catch (ArithmeticException e){
            System.out.println("捕获runtimeException");
            e.printStackTrace();
        }
        return 0;
    }
}
