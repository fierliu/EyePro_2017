package application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller implements Initializable{
	Property rb;
	public String time;
	@FXML
	private Label lbTime, lbTime1, lbTime2;
	@FXML
	private Button setColorBtn, setColorBtn2, setColorBtn3, btn, btnSetting, btnQuit;
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
	public void getTimeBtn2Handler(ActionEvent event){
		lbTime2.setText(showTime());
	}
	public void getTimeBtn1Handler(ActionEvent event){
		lbTime1.setText(showTime());
	}
	public void getTimeBtnHandler(ActionEvent event){
		setLabel(showTime());
	}
	public void btnSettingHandler(ActionEvent event) throws IOException, URISyntaxException{
//		System.out.println(get_Comp_time());
		Stage setting = new Stage();
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/application/setting.fxml"));
		Parent root = fxmlloader.load();
		setting.setScene(new Scene(root));
		SettingController controller = fxmlloader.getController();
		//传递primaryStage参数给Controller
		controller.setStage(setting);
		setting.show();
	}
	public void btnQuitHandler(ActionEvent event){
		System.exit(0);
	}
	/****************************************************/

	public String showTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String nowTime = df.format(new Date());// new Date()为获取当前系统时间
		return nowTime;
	}
	public int getVal(int val){
		return val/60000;
	}
	public String get_Time() {
        Calendar calendar = Calendar.getInstance();
        Date date = (Date) calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        time = format.format(date);
        return time;
	}
	public String get_Comp_time(){
		Calendar calendar = Calendar.getInstance();
        Date date = (Date) calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        time = format.format(date);
        return time;
	}
	public void show_count_down(){
//在主界面上显示倒计时
	}
}
