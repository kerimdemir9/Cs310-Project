package com.mynba.myNBA.response;

public class NewsResponse {
	private Object news;
	
	public NewsResponse(Object news) {
		this.news = news;
	}
	
	public Object getNews() {
		return news;
	}
}
