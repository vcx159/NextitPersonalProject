package model;

public class TouhouCharactor extends Info implements Controllable {
	
	String scissors, rock, paper;  //해당 캐릭터 특유의 가위 바위 보
	String imgUrl;  //해당 캐릭터의 사진
	String bgmUrl;
	
	public TouhouCharactor() {}
	public TouhouCharactor(int id, String name, String scissors, String rock, String paper, String imgUrl, String bgmUrl) {
		super(id, name);
		this.scissors = scissors;
		this.rock = rock;
		this.paper = paper;
		this.imgUrl = "/image/"+imgUrl;
		this.bgmUrl = "/bgm/"+bgmUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getBgmUrl() {
		return bgmUrl;
	}
	public void setBgmUrl(String bgmUrl) {
		this.bgmUrl = bgmUrl;
	}
	
	@Override
	public String doScissors() {
		return scissors;
	}

	@Override
	public String doRock() {	
		return rock;
	}

	@Override
	public String doPaper() {
		return paper;
	}

}
