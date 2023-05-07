package com.mynba.myNBA.model;

public class Team {
	private Integer id;
	private String name;
	private String nickname;
	private String code;
	private String city;
	private String logo;
	private boolean allStar;
	private boolean nbaFranchise;
	private Object leagues;
	
	public Team() {
		
	}
	
	public Team(Integer id, String name, String nickname, String code, String city, String logo, boolean allStar,
			boolean nbaFranchise, Object leagues) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.code = code;
		this.city = city;
		this.logo = logo;
		this.allStar = allStar;
		this.nbaFranchise = nbaFranchise;
		this.leagues = leagues;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isAllStar() {
		return allStar;
	}

	public void setAllStar(boolean allStar) {
		this.allStar = allStar;
	}

	public boolean isNbaFranchise() {
		return nbaFranchise;
	}

	public void setNbaFranchise(boolean nbaFranchise) {
		this.nbaFranchise = nbaFranchise;
	}

	public Object getLeagues() {
		return leagues;
	}

	public void setLeagues(Object leagues) {
		this.leagues = leagues;
	}
}
