package model;
import java.util.Vector;

//싱글톤 클래스
//이 게임의 모든 컨트롤러들과 상호작용한다
public class GameManager {
	
	//캐릭터 리스트(DB라 가정)
	private Vector<TouhouCharactor> charactors = new Vector<TouhouCharactor>();	
	
	private int startRound, finishRound;  //라운드
	private int aWin, bWin, aDraw;  //승 패 무
	private int aLose, bLose, bDraw;  
	private int playerASel, playerBSel;  //플레이어 a , b 가 정한 캐릭터
	
	private GameManager() {
		charactors.add(new TouhouCharactor(0, "하쿠레이 레이무", "영부[몽상봉인]","신기[팔방용살진]","보구[음양비조정]", "reimu.png", "reimu.mp3"));
		charactors.add(new TouhouCharactor(1, "키리사메 마리사", "연부[마스터 스파크]","혜성[블레이징 스타]","성부[새틀라이트 일루전]", "marisa.png", "marisa.mp3"));
		charactors.add(new TouhouCharactor(2, "코치야 사나에", "비술[잊혀진 제사의식]","기적[객성이 너무도 밝은 밤]","개해[모세의 기적]", "sanae.png", "sanae.mp3"));
		charactors.add(new TouhouCharactor(3, "히나나위 텐시", "비상[비상비비상의 검]","천기[비상천촉]","[전 인류의 비상천]", "tenshi.png", "tenshi.mp3"));
		charactors.add(new TouhouCharactor(4, "홍 메이링", "격부[대붕권]","채화[홍색태극권]","치격[대붕추격권]", "meiring.png", "meiring.mp3"));
		charactors.add(new TouhouCharactor(5, "레이우지 우츠호", "폭부[메가 플레어]","핵부[크리핑 선]","[서브터레이니언 선]", "utsuho.png", "utsuho.mp3"));
		charactors.add(new TouhouCharactor(6, "앨리스 마가트로이드", "주부[상하이 인형]","주저[봉래인형]","홍부[네덜란드 인형]", "alice.png", "alice.mp3"));
		charactors.add(new TouhouCharactor(7, "레밀리아 스칼렛", "운명[미저러블 페이트]","신창[스피어 더 궁니르]","[밀레니엄의 흡혈귀]", "remilia.png", "remilia.mp3"));
		charactors.add(new TouhouCharactor(8, "콘파쿠 요우무", "단명검[명상참]","전생검[원심유전참]","공관검[육근청정참]", "youmu.png", "youmu.mp3"));
		charactors.add(new TouhouCharactor(9, "이자요이 사쿠야", "은부[실버 바운드]","시계[루나 다이얼]","[사쿠야의 세계]", "sakuya.png", "sakuya.mp3"));
	}
	
	private static class GameManagerHolder {
		public static final GameManager INSTANCE = new GameManager();
	}
	
	public static GameManager getGameManager() {
		return GameManagerHolder.INSTANCE;
	}

	//초기화 메서드
	public void init() {		
		startRound = 1;
		aWin=bWin=aDraw=0;
		aLose=bLose=bDraw=0;
	}
	
	//캐릭터 목록 사이즈 반환
	public int charactorListSize() {
		return charactors.size();
	}
	
	//캐릭터 선택 메서드
	public void setPlayerASel(int playerASel) {
		this.playerASel = playerASel;
	}
	
	public void setPlayerBSel(int playerBSel) {
		this.playerBSel = playerBSel;
	}
	
	//캐릭터 정보 얻어오기 메서드
	public TouhouCharactor getplayerA() {
		return charactors.get(playerASel);
	}
	
	public TouhouCharactor getplayerB() {
		return charactors.get(playerBSel);
	}
	
	public String getCharactorName(int sel) {
		return charactors.get(sel).getName();
	}
	
	//라운드 정보
	public String getRound() {
		return startRound + "/" + finishRound + " 라운드";
	}
	
	public void setRound(int finsishRound) {
		this.finishRound = finsishRound;
	}
	
	public boolean isRoundEnd() {
		return startRound>finishRound;
	}
	
	//승패 정보 전달
	public int getaWin() {
		return aWin;
	}
	public int getaLose() {
		return aLose;
	}
	public int getaDraw() {
		return aDraw;
	}
	
	//가위 바위 보 랜덤 돌리기
	public String getCommand() {
		
		int i = (int) (Math.random()*Controllable.SKILL_SIZE);
		String command = null;
		
		switch(i) {
		case 0:
			command = "가위";
			break;
		case 1:
			command = "바위";
			break;
		case 2:
			command = "보";
			break;
		default:
			break;
		}
		
		return command;
	}
	
	//가위 바위 보 로직
	public String aiBattle(int playerA, int playerB) {
		
		String result;
		
		if(playerA == playerB) {
			result="무승부";
			aDraw++; bDraw++;
		} else {
			if((playerA-playerB) == -2 || (playerA-playerB) == 1) {
				result = getplayerA().getName() + " 승리";
				aWin++; bLose++;
			}else {
				result = getplayerB().getName() + " 승리";
				bWin++; aLose++;
			}
		}
		
		startRound++;
		
		return result;
	}
}
