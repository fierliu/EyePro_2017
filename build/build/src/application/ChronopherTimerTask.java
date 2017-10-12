package application;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ChronopherTimerTask extends java.util.TimerTask {

    Calendar cal = new GregorianCalendar();
    Date date = null;

    public ChronopherTimerTask() {

    }

    @Override
    public void run() {
        try {
            date = new Date();
            cal.setTime(date);
            //if (cal.get(Calendar.SECOND) % 5 < 3) {
            if (cal.get(Calendar.MINUTE) == 0 && cal.get(Calendar.SECOND) < 3) {
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            this.cancel();
        }
    }

    @Override
    public boolean cancel() {
        try {
            cal = null;
            date = null;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return super.cancel();
    }

    private void AlterChronopherLiteResource() {

    }
}

