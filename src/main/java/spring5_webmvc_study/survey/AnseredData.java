package spring5_webmvc_study.survey;

import java.util.List;

public class AnseredData {
	private List<String> responses;
	private Respondent res;
	
	
	
	public AnseredData() {}
	public List<String> getResponses() {
		return responses;
	}
	public void setResponses(List<String> responses) {
		this.responses = responses;
	}
	public Respondent getRes() {
		return res;
	}
	public void setRes(Respondent res) {
		this.res = res;
	}
	@Override
	public String toString() {
		return String.format("AnseredData [responses=%s, res=%s]", responses, res);
	}
	
	
}
