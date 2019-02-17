package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.GameManager;

public class CharactorSelectController implements Initializable {
	
	@FXML
	private VBox charactorSel;

	@FXML
	private Label popUpText;  //마우스를 버튼 위에 올리면 나타나는 해당 캐릭터 이름
	
	Media media;
	MediaPlayer mediaPlayer;
	GameManager gm = GameManager.getGameManager();
	private Stage primaryStage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		//bgm 반복재생
		media = new Media(getClass().getResource("/bgm/charactorSelect.mp3").toExternalForm());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setOnEndOfMedia(() -> {mediaPlayer.seek(Duration.ZERO);});

		mediaPlayer.play();
		
		int size = GameManager.getGameManager().charactorListSize();
		gm.setPlayerBSel((int)(Math.random()*size));  //플레이어B(ai) 초기화
	}
	
	//각각의 버튼위에 마우스를 올리면 나타날 이름들
	public void popUpName(MouseEvent event) {
		popUpText.setVisible(true);
		Button btn = (Button)event.getSource();
		popUpText.setText(gm.getCharactorName(Integer.parseInt(btn.getUserData().toString())));
	}
	
	public void popUpReset(MouseEvent event) {
		popUpText.setVisible(false);
		popUpText.setText("");
	}
	
	public void charactorSelect(ActionEvent event) throws IOException {
		
		//버튼의 정보를 읽어와서 그 버튼의 할당된 userData를 이용하여 플레이어의 캐릭터를 선택한다
		Button btn = (Button)event.getSource();
		gm.setPlayerASel(Integer.parseInt(btn.getUserData().toString()));
		
		//캐릭터를 선택하면 라운드 선택창이 띄워진다
		primaryStage = (Stage) charactorSel.getScene().getWindow();
		Stage dialog = new Stage(StageStyle.DECORATED);
		
		Parent root = FXMLLoader.load(getClass().getResource("RoundDialog.fxml"));
		Scene scene = new Scene(root);
		
		TextField roundText = (TextField) root.lookup("#roundText");
		Button btnOK = (Button) root.lookup("#btnOK");
		Button btnCancel = (Button) root.lookup("#btnCancel");
		
		//라운드 입력 창 이벤트 핸들링
		btnOK.setOnAction((e) -> {
			gm.setRound(Integer.parseInt(roundText.getText()));	
			changeScene();
			dialog.close();
		});
		btnCancel.setOnAction((e) -> {
			dialog.close();
		});
		
		//모달 세팅
		dialog.initModality(Modality.WINDOW_MODAL);
		//부모창 세팅
		dialog.initOwner(primaryStage);
		
		dialog.setResizable(false);
		dialog.setScene(scene);
		dialog.setTitle("라운드 선택");
		dialog.show();
	}

	public void changeScene() {

		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("BattleScene.fxml"));
	        Parent root = loader.load();
	        charactorSel.getScene().setRoot(root);
	        mediaPlayer.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
