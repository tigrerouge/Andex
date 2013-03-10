package fr.umlv.andex.data;

import java.util.ArrayList;
import java.util.List;

public class Question {
		
	private String title;
	private String text;
	private long time;
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

	private byte[] image;
	
	private TypeQuestion[] typeAnswer;
	
	public TypeQuestion[] getTypeAnswer() {
		return typeAnswer;
	}
	public void setTypeAnswer(TypeQuestion[] typeAnswer) {
		this.typeAnswer = typeAnswer;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public byte[] getImageAnswer() {
		return imageAnswer;
	}
	public void setImageAnswer(byte[] imageAnswer) {
		this.imageAnswer = imageAnswer;
	}

	private byte[] imageAnswer;
	
	private List<Option> options = new ArrayList<Option>();

	public List<Option> getOptions() {
		return options;
	}
	
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
}
