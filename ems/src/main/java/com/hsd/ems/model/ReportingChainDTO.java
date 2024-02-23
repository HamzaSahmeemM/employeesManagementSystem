package com.hsd.ems.model;

import java.util.List;

public class ReportingChainDTO {
	
	private Employee manager;
	private List<Employee> reportingEmployees;

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getReportingEmployees() {
		return reportingEmployees;
	}

	public void setReportingEmployees(List<Employee> reportingEmployees) {
		this.reportingEmployees = reportingEmployees;
	}

	@Override
	public String toString() {
		return "ReportingChainDTO [manager=" + manager + ", reportingEmployees=" + reportingEmployees + "]";
	}
}
