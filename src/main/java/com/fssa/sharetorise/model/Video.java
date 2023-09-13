package com.fssa.sharetorise.model;

public class Video {

	private String videoUrl;
	
	

	public Video(String videoUrl) {
		super();
		this.videoUrl = videoUrl;
	}

	public Video() {
		// TODO Auto-generated constructor stub
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	@Override
	public String toString() {
		return "Video [videoUrl=" + videoUrl + "]";
	}

}
