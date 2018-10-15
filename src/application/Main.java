package application;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
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
	private boolean firstTime;
    private TrayIcon trayIcon;
	Property rb = new Property();
	Controller controller;
	@FXML
	public void init(){
	}
	@Override
	public void start(Stage primaryStage) throws IOException, URISyntaxException {
		createTrayIcon(primaryStage);
        firstTime = true;
        Platform.setImplicitExit(false);
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/application/scene.fxml"));
		Parent root = fxmlloader.load();
		Scene scene = new Scene(root);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		controller = fxmlloader.getController();
		//传递primaryStage参数给Controller
		controller.setStage(primaryStage);
		primaryStage.getIcons().add(new Image("/application/eye.png"));
		primaryStage.setTitle("EyePro");
		//terminate the all threads when close button clicked.
//		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
//			@Override
//			public void handle(WindowEvent event){
//				System.exit(0);
//			}
//		});
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

		Timer timer = new Timer();
        TimerTask  timerTask = new TimerTask (){
            public void run() {
//            	System.out.println(get_Time());
//            	Platform.runLater(()->showTimedDialog(300000, "5 Min."));
				if(controller.get_Time().equals("30:00")&rb.get_halfFlag().equals(1)){
					Platform.runLater(()->{
						showTimedDialog(120000, "2 Min.");
						tts("It's "+ controller.get_Comp_time() +"now.");
						});
				}else if(controller.get_Time().equals("27:40")&rb.get_halfFlag().equals(1)){
					Platform.runLater(()->{
						showTimedDialog(300000, "5 Min.");
						tts("It's "+ controller.get_Comp_time() +"now.");
							});
				}
            }
        };
        timer.schedule (timerTask, 0, 1000);


	}

	public void showTimedDialog(long time, String message) {
		//判断定时提醒是否是开的
	    Stage popup = new Stage();
	    popup.setAlwaysOnTop(true);
	    popup.setResizable(false);
	    popup.initModality(Modality.APPLICATION_MODAL);
	    popup.getIcons().add(new Image("/application/eye.png"));
	    Button closeBtn = new Button("Got it!");
	    closeBtn.setOnAction(e -> {
	        popup.close();
	    });
	    VBox root = new VBox();
	    root.setPadding(new Insets(10));
	    root.setAlignment(Pos.CENTER);//显示位置
	    root.setSpacing(10);
	    root.getChildren().addAll(new Label(message), closeBtn);
	    Scene scene = new Scene(root);
	    popup.setScene(scene);
	    popup.setTitle("Rest");
	    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	    popup.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 180);
	    popup.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 130);
	    popup.setWidth(180);
	    popup.setHeight(130);
	    popup.show();

	    Thread thread = new Thread(() -> {
	        try {
	            Thread.sleep(time);
	            if (popup.isShowing()) {
	                Platform.runLater(() -> {
	                	popup.close();
	                	tts("End the break.");
	                });
	            }
	        } catch (Exception exp) {
	            exp.printStackTrace();
	        }
	    });
	    thread.setDaemon(true);
	    thread.start();
	}
	public void createTrayIcon(final Stage stage) throws URISyntaxException {
        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            SystemTray tray = SystemTray.getSystemTray();
            // load an image
            java.awt.Image image = null;
            try {
                File pathToFile = new File("trayPic.png");
                image = ImageIO.read(pathToFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    hide(stage);
                }
            });
            // create a action listener to listen for default action executed on the tray icon
            final ActionListener closeListener = new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    System.exit(0);
                }
            };

            ActionListener showListener = new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stage.show();
//                            System.out.println("showListener!");
                        }
                    });
                }
            };
            // create a popup menu
            PopupMenu popup = new PopupMenu();

            MenuItem showItem = new MenuItem("Show");
            showItem.addActionListener(showListener);
            popup.add(showItem);

            MenuItem closeItem = new MenuItem("Close");
            closeItem.addActionListener(closeListener);
            popup.add(closeItem);
            /// ... add other items
            // construct a TrayIcon
            trayIcon = new TrayIcon(image, "EyePro", popup);
            // set the TrayIcon properties
            trayIcon.addActionListener(showListener);

            // ...
            // add the tray image
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
            // ...
        }
    }

    public void showProgramIsMinimizedMsg() {
        if (firstTime) {
            trayIcon.displayMessage("Notice",
                    "Application has been hidden in the system tray",
                    TrayIcon.MessageType.INFO);
            firstTime = false;
        }
    }

    private void hide(final Stage stage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (SystemTray.isSupported()) {
                    stage.hide();
                    showProgramIsMinimizedMsg();
                } else {
                    System.exit(0);
                }
            }
        });
    }
    public void tts(String str){
    	if(rb.get_soundFlag().equals(1)){
	    	VoiceManager vm = VoiceManager.getInstance();
	        Voice voice = vm.getVoice("kevin16");
	        voice.allocate();
	        voice.speak(str);
    	}
    }
    public void fixed_reminded(){
    	//定时提醒，当定时更改时需及时处理，否则不能立即生效
    	Timer timer = new Timer();
		int remindTime = rb.readBreak();
//    			System.out.println("flag:"+rb.readFlag());
//    			System.out.println("remindTime:"+remindTime);
	    TimerTask settedTime = new TimerTask() {
			@Override
			public void run() {
				if (rb.readFlag().equals(1)){
//    					System.out.println("11");
				Platform.runLater(()->showTimedDialog(300000, "Time's Up!"));
					}
				}
			};
			timer.schedule(settedTime, remindTime, remindTime);
}
	public static void main(String[] args) {
		launch(args);
	}

}
