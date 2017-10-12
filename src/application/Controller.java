package application;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller implements Initializable{

	@FXML
	private Label lbTime;
	@FXML
	private Button btn;
	@FXML
	private Button setColorBtn;
	private Stage stage;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	public void setStage(Stage stage){
		this.stage = stage;
	}
	public void setLabel(String s){

		lbTime.setText(s);
	}
	public void setColorBtnHandler(ActionEvent event) throws IOException{
        String batName = "mybat.bat"; //该bat文件保存在项目目录下，所以无需写出完整路径，如果文件不在项目目录下则需要直接写出文件路径
        runBat rBat = new runBat();
        rBat.run_bat(batName);
	}
	public void btnHandler(ActionEvent event){

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
