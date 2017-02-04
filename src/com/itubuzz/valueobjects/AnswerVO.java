package com.itubuzz.valueobjects;

public class AnswerVO {

	private long answer_id;
	private String answer_text;
	private long immparent_id;
	private int question_id;
	private int log_user_id;
	private String log_user_name;
	private long answer_number;
		
		
	
		public long getAnswer_number() {
			return answer_number;
		}

		public void setAnswer_number(long answer_number) {
			this.answer_number = answer_number;
		}

		public String getLog_user_name() {
			return log_user_name;
		}

		public void setLog_user_name(String log_user_name) {
			this.log_user_name = log_user_name;
		}

		public long getanswer_id() {
			return answer_id;
		}
		
		public void setanswer_id(long answer_id) {
			this.answer_id = answer_id;
		}
		
		public String getanswer_text() {
			return answer_text;
		}
		
		public void setanswer_text(String answer_text) {
			this.answer_text = answer_text;
		}

		public long getimmparent_id() {
			return immparent_id;
		}
		
		public void setimmparent_id(long immparent_id) {
			this.immparent_id = immparent_id;
		}

		public int getquestion_id() {
			return question_id;
		}
		
		public void setquestion_id(int log_user_id) {
			this.question_id = log_user_id;
		}

		public int getLog_user_id() {
			return log_user_id;
		}
		
		public void setLog_user_id(int log_user_id) {
			this.log_user_id = log_user_id;
		}

}