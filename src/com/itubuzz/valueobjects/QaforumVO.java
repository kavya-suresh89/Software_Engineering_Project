package com.itubuzz.valueobjects;

public class QaforumVO {

	private int question_id;
	private String question_text;
	private String answer_id;
	private int log_user_id;
		
		public int getquestion_id() {
			return question_id;
		}
		
		public void setquestion_id(int question_id) {
			this.question_id = question_id;
		}
		
		public String getquestion_text() {
			return question_text;
		}
		
		public void setquestion_text(String question_text) {
			this.question_text = question_text;
		}

		public String getanswer_id() {
			return answer_id;
		}
		
		public void setanswer_id(String answer_id) {
			this.answer_id = answer_id;
		}

		public int getLog_user_id() {
			return log_user_id;
		}
		
		public void setLog_user_id(int log_user_id) {
			this.log_user_id = log_user_id;
		}
}