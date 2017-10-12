package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Main extends Application {
	public String time;
	@FXML

	public void init(){

	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/application/scene.fxml"));
		Parent root = fxmlloader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Controller controller = fxmlloader.getController();
		//传递primaryStage参数给Controller
		controller.setStage(primaryStage);
		primaryStage.getIcons().add(new Image("/application/eye.png"));
		primaryStage.setTitle("EyePro");
		//terminate the all threads when close button clicked.
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
			@Override
			public void handle(WindowEvent event){
				System.exit(0);
			}
		});
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

		Timer timer = new Timer();
        TimerTask  timerTask = new TimerTask (){
            public void run() {
//            	System.out.println(get_Time());
//            	Platform.runLater(()->showTimedDialog(300000, "5 Min."));
				if(get_Time().equals("30:00")){
					Platform.runLater(()->showTimedDialog(300000, "5 Min."));
				}else if(get_Time().equals("00:00")){
					Platform.runLater(()->showTimedDialog(600000, "10 Min."));
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
	    root.setAlignment(Pos.BOTTOM_CENTER);//显示位置
	    root.setSpacing(20);
	    root.getChildren().addAll(new Label(message), closeBtn);
	    Scene scene = new Scene(root);
	    popup.setScene(scene);
	    popup.setTitle("Rest");
	    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//	    popup.setX(primScreenBounds.getWidth()-primScreenBounds.getWidth()/9);
//	    popup.setY(primScreenBounds.getHeight()-primScreenBounds.getWidth()/10);
	    popup.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 180);
	    popup.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 130);
	    popup.setWidth(180);
	    popup.setHeight(130);
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
