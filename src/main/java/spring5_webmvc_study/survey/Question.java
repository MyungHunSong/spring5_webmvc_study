package spring5_webmvc_study.survey;

import java.util.List;

public class Question {
	private String title;
	private List<String> options;
	
	public Question() {}
	
	
	public Question(String title, List<String> options) {
		this.title = title;
		this.options = options;
	}
	
	public Question(String title) {
		this.title = title;
	}


	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	@Override
	public String toString() {
		return String.format("Question [title=%s, options=%s]", title, options);
	}
}
