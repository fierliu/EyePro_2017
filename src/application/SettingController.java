package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingController implements Initializable{
	@FXML
	private RadioButton rb_sharp, rb_half, rb_sound, rb_fixed;
	@FXML
	private ChoiceBox<Integer> choice;
	@FXML
	private TextField tf;
	@FXML
	private Button btnOk;
	@FXML
	private RadioButton remind;
	@FXML
	Stage Setting;
	Property rb;
	Controller ct = new Controller();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rb = new Property();
		int flag = rb.readFlag();
		int breakTime = rb.readBreak();
		if(breakTime/60000==30|breakTime/60000==45|breakTime/60000==60|breakTime/60000==75){
			choice.setValue(breakTime/60000);
		}
		else choice.setValue(null);
//		if(flag==0) remind.setSelected(false);
//		else remind.setSelected(true);
		choice.setItems(FXCollections.observableArrayList(30, 45, 60, 75));
		choice.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number>
		ov, Number old_val, Number new_val) -> {try {
			if(flag==1) remindSetting(new_val.intValue());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}});
//		setting stage initialize;
		if(rb.get_sharpFlag().equals(1)) rb_sharp.setSelected(true);
		else rb_sharp.setSelected(false);
		if(rb.get_halfFlag().equals(1)) rb_half.setSelected(true);
		else rb_half.setSelected(false);
		if(rb.get_soundFlag().equals(1)) rb_sound.setSelected(true);
		else rb_sound.setSelected(false);
		Integer brk = rb.readBreak()/60000;
		tf.setText(brk.toString());
	}
	public void setStage(Stage stage){
		Setting = stage;
	}
	public void rb_sharp_handler(ActionEvent event){
		if(rb_sharp.isSelected()) rb.set_sharpFlag("1");
		else rb.set_sharpFlag("0");
	}
	public void rb_half_handler(ActionEvent event){
		if(rb_half.isSelected()) rb.set_halfFlag("1");
		else rb.set_halfFlag("0");
	}
	public void rb_fixed_handler(ActionEvent event){
		if(rb_fixed.isSelected()) rb.updateFlag("1");
		else rb.updateFlag("0");
	}
	public void rb_sound_handler(ActionEvent event){
		if(rb_sound.isSelected()) rb.set_soundFlag("1");
		else rb.set_soundFlag("0");
	}
	public void btnOk_handler(ActionEvent event){
//		判断输入的是否是数字
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		if(pattern.matcher(tf.getText()).matches()& !tf.getText().equals("")){
			Integer break_time = Integer.valueOf(tf.getText())*60000;
//			System.out.println(break_time);
			rb = new Property();
			rb.updateBreak(break_time.toString());
			choice.setValue(null);
			Setting.close();
		}
		else{
			showInfo("请输入正整数!");
		}
	}
//	-------------------------------------------------------
	public void remindSetting(int val) throws FileNotFoundException, IOException{
		rb = new Property();
		if (val==0) rb.updateBreak("1800000");
		else if(val==1) rb.updateBreak("2700000");
		else if(val==2) rb.updateBreak("3600000");
		else if(val==3) rb.updateBreak("4500000");
	}
	public void showInfo(String content){
		Platform.runLater(() ->{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("INFO");
			alert.setHeaderText("");
			alert.setContentText(content);
			alert.initOwner(null);
			alert.show();
			}
		);
	}
}
