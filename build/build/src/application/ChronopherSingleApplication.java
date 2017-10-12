package application;

import java.util.Timer;

public class ChronopherSingleApplication {

    public static void main(String[] args) {
        Timer alterClockTimer = null;
        ChronopherTimerTask chronopherTimerTask = null;
        try {
            alterClockTimer = new Timer("ChronopherSingleApplication");
            chronopherTimerTask = new ChronopherTimerTask();
            alterClockTimer.scheduleAtFixedRate(chronopherTimerTask, 1000, 3000);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            try {
                chronopherTimerTask.cancel();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                chronopherTimerTask = null;
                alterClockTimer = null;
            }
        }
    }
}

