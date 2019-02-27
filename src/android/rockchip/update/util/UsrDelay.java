package android.rockchip.update.util;
public class UsrDelay{

    public static void delayMs(long ms){

        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}