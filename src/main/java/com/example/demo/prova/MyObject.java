package com.example.demo.prova;

public class MyObject {

	private Long jobId;
	private Long testId;
	
	
	public MyObject(Long jobId, Long testId) {
		this.jobId = jobId;
		this.testId = testId;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public Long getTestId() {
		return testId;
	}
	public void setTestId(Long testId) {
		this.testId = testId;
	}
	
	
}
