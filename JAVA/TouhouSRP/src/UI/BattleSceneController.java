package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Controllable;
import model.GameManager;

public class BattleSceneController implements Initializable {

	@FXML
	private BorderPane battleScene;
	@FXML
	private Button command1, command2, command3;
	@FXML
	private Label playerACmd, playerBCmd;
	@FXML
	private Label playerAName, playerBName;
	@FXML
	private Label aiLabel;
	@FXML
	private Label roundLabel, roundResultLabel;
	@FXML
	private ImageView playerAImg, playerBImg; 
	
	GameManager gm = GameManager.getGameManager();
	Media media;
	MediaPlayer mediaPlayer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		//초기 설정
		gm.init();
		//플레이어a 캐릭터의 테마곡 bgm 반복재생
		media = new Media(getClass().getResource(gm.getplayerA().getBgmUrl()).toExternalForm());
		mediaPlayer = new MediaPlayer(media);	
		mediaPlayer.setOnEndOfMedia(() -> {mediaPlayer.seek(Duration.ZERO);});
		mediaPlayer.play();
		
		//플레이가 선택한 캐릭터 이름 표시
		playerAName.setText(gm.getplayerA().getName());
		playerBName.setText(gm.getplayerB().getName());
	
		//플레이가 선택한 캐릭터 사진 표시
		playerAImg.setImage(new Image(gm.getplayerA().getImgUrl()));
		playerBImg.setImage(new Image(gm.getplayerB().getImgUrl()));
		
		roundLabel.setText(gm.getRound());

		//커맨드 버튼들에 가위 바위 보를 랜덤으로 셋팅한다
		command1.setText(gm.getCommand());
		command2.setText(gm.getCommand());
		command3.setText(gm.getCommand());
	}
	
	//매 라운드 루프 루틴
	public void loop(MouseEvent event) {

		command1.setDisable(true);
		command2.setDisable(true);
		command3.setDisable(true);
		
		int playerSkill = 0;
		
		//플레이어b(ai) 랜덤 선택
		int aiSkill = (int) (Math.random()*Controllable.SKILL_SIZE);
		switch(aiSkill) {
		case 0:
			playerBCmd.setText(gm.getplayerB().doScissors());
			break;
		case 1:
			playerBCmd.setText(gm.getplayerB().doRock());
			break;
		case 2:
			playerBCmd.setText(gm.getplayerB().doPaper());
			break;
		default:
			break;
		}
		aiLabel.setText("선택완료");
		
		//사용자가 누른 버튼의 정보를 얻어온 후 그에 맞게 UI 셋팅
		Button btn = (Button)event.getSource();
		String cmd=btn.getText();

		if(cmd.equals("가위")) {
			playerACmd.setText(gm.getplayerA().doScissors());
			playerSkill=0;
		}else if(cmd.equals("바위")) {
			playerACmd.setText(gm.getplayerA().doRock());
			playerSkill=1;
		}else if(cmd.equals("보")) {
			playerACmd.setText(gm.getplayerA().doPaper());
			playerSkill=2;
		}
		
		//결과 표시
		String result = gm.aiBattle(playerSkill, aiSkill);
		roundResultLabel.setText(result);		
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//라운드가 끝났는지 확인
				if(gm.isRoundEnd()) {
					changeScene();
				}

				Platform.runLater(()->{
					//안 끝났으면 다시 재설정
					command1.setDisable(false);
					command2.setDisable(false);
					command3.setDisable(false);
					
					command1.setText(gm.getCommand());
					command2.setText(gm.getCommand());
					command3.setText(gm.getCommand());
					
					playerACmd.setText("");
					playerBCmd.setText("");
					
					roundLabel.setText(gm.getRound());
					aiLabel.setText("선택 중");
					roundResultLabel.setText("");	
				});

			}
		});

		thread.setDaemon(true);
		thread.start();
		
	}
	
	public void changeScene() {
		try {		
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
	        Parent root = loader.load();
	        battleScene.getScene().setRoot(root);
	        mediaPlayer.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
