package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.GameManager;

public class ResultController implements Initializable {

	@FXML
	Label win, lose, draw;
	@FXML
	Button btnEnd, btnRestart;
	
	GameManager gm = GameManager.getGameManager();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		win.setText(String.valueOf(gm.getaWin()));
		lose.setText(String.valueOf(gm.getaLose()));
		draw.setText(String.valueOf(gm.getaDraw()));
		
		//끝내기 버튼
		btnEnd.setOnAction((event)->{
			Platform.exit();
		});
		
		//캐릭터 선택창 부터 다시시작
		btnRestart.setOnAction((event)->{
			try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharactorSelect.fxml"));
		        Stage stage = (Stage) btnRestart.getScene().getWindow();
		        Scene scene = new Scene(loader.load());
		        stage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
