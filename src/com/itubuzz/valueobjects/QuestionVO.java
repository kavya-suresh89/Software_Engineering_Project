package com.itubuzz.valueobjects;

public class QuestionVO {

	private int question_id;
	private String question_text;
	private int log_user_id;
	private String log_user_name;
		
		
		
		public String getLog_user_name() {
			return log_user_name;
		}

		public void setLog_user_name(String log_user_name) {
			this.log_user_name = log_user_name;
		}

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

		public int getLog_user_id() {
			return log_user_id;
		}
		
		public void setLog_user_id(int log_user_id) {
			this.log_user_id = log_user_id;
		}
		
}