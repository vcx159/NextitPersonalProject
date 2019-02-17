package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {
	
	@FXML
	private Button aiBattleBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		aiBattleBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("CharactorSelect.fxml"));
			        Stage stage = (Stage) aiBattleBtn.getScene().getWindow();
			        Scene scene = new Scene(loader.load());
			        stage.setScene(scene);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
}
