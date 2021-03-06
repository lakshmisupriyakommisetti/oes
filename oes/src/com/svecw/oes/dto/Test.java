package com.svecw.oes.dto;

import java.util.Date;

public class Test {

	private int testId;
	private String name;
	private Date startDate;
	private Date endDate;
	private Date duration;

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDuration() {
		return duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public Test(int testId, String name, Date startDate, Date endDate, Date duration) {
		super();
		this.testId = testId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
	}

	public Test() {
		super();
	}

	@Override
	public String toString() {
		return "TestPojo [testId=" + testId + ", Name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", duration=" + duration + "]";
	}

}
