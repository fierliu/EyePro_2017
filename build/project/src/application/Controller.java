package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller implements Initializable{

	@FXML
	private Label lbTime;
	@FXML
	private Button btn;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	public void setLabel(String s){

		lbTime.setText(s);
	}
	public void btnHandler(ActionEvent event){
		f_alert_informationDialog("header", "message");
	}
	public void getTimeBtnHandler(ActionEvent event){
		setLabel(showTime());
	}

	public void f_alert_informationDialog(String p_header, String p_message){
        Alert _alert = new Alert(Alert.AlertType.INFORMATION);
        _alert.setTitle("Rest");
        _alert.setHeaderText(p_header);
        _alert.setContentText(p_message);
//        _alert.initOwner(d_stage);
        _alert.setX(900);
        _alert.setY(500);
        _alert.show();
    }
	public String showTime(){

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String nowTime = df.format(new Date());// new Date()为获取当前系统时间
		return nowTime;

	}

}
