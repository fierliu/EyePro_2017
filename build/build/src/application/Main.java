package application;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.FutureTask;

import javax.print.attribute.standard.Fidelity;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Main extends Application {
	public String time;
	public void init(){

	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		String time = null;
		Parent root = FXMLLoader.load(getClass().getResource("/application/scene.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("EyePro");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

		Timer timer = new Timer();
        TimerTask  timerTask = new TimerTask (){
            public void run() {
//            	System.out.println(get_Time());

				if(get_Time().equals("30:00")){
					Platform.runLater(()->showTimedDialog(50000, "5 Min."));
				}else if(get_Time().equals("00:00")){
					Platform.runLater(()->showTimedDialog(10000, "10 Min."));
				}

            }
        };
        timer.schedule (timerTask, 0, 1000);
	}
	public String get_Time() {
        Calendar calendar = Calendar.getInstance();
        Date date = (Date) calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        time = format.format(date);
        return time;
	}
	public void showTimedDialog(long time, String message) {
	    Stage popup = new Stage();
	    popup.setAlwaysOnTop(true);
	    popup.initModality(Modality.APPLICATION_MODAL);
	    Button closeBtn = new Button("Get it!");
	    closeBtn.setOnAction(e -> {
	        popup.close();
	    });
	    VBox root = new VBox();
	    root.setPadding(new Insets(20));
	    root.setAlignment(Pos.BASELINE_CENTER);//显示位置
	    root.setSpacing(20);
	    root.getChildren().addAll(new Label(message), closeBtn);
	    Scene scene = new Scene(root);
	    popup.setScene(scene);
	    popup.setTitle("Rest");
	    popup.show();

	    Thread thread = new Thread(() -> {
	        try {
	            Thread.sleep(time);
	            if (popup.isShowing()) {
	                Platform.runLater(() -> popup.close());
	            }
	        } catch (Exception exp) {
	            exp.printStackTrace();
	        }
	    });
	    thread.setDaemon(true);
	    thread.start();
//	    提示窗口上显示倒计时
//	    Timer timer = new Timer();
//        TimerTask  timerTask = new TimerTask (){
//            public void run() {
//            	System.out.println(get_Time());
//
//				if(get_Time().equals("30:00")){
//					Platform.runLater(()->showTimedDialog(50000, "5 Min."));
//				}
//            }
//        };
//        timer.schedule (timerTask, 0, 1000);
	}
	public static void main(String[] args) {
		launch(args);
	}

}
