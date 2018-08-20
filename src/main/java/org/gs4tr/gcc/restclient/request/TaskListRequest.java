package org.gs4tr.gcc.restclient.request;

import org.gs4tr.gcc.restclient.model.TaskStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskListRequest extends PageableRequest {

    @JsonProperty("state")
    private String[] statuses;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("job_id")
    private Long jobId;
    @JsonProperty("unique_identifiers")
    private String[] uniqueIdentifiers;
    @JsonProperty("node_ids")
    private String[] nodeIds;

    public TaskListRequest() {

    }

    public TaskListRequest(TaskStatus[] statuses, String locale, Long jobId, String[] uniqueIdentifiers,
	    String[] nodeIds) {
	setTaskStatuses(statuses);
	this.locale = locale;
	this.jobId = jobId;
	this.uniqueIdentifiers = uniqueIdentifiers;
	this.nodeIds = nodeIds;
    }

    @JsonProperty("state")
    public String[] getTaskStatuses() {
	return statuses;
    }

    public void setTaskStatuses(String[] taskStatuses) {
	this.statuses = taskStatuses;
    }

    public void setTaskStatuses(TaskStatus[] taskStatuses) {
	if (taskStatuses != null && taskStatuses.length > 0) {
	    this.statuses = new String[taskStatuses.length];
	    int i = 0;
	    for (TaskStatus taskStatus : taskStatuses) {
		this.statuses[i++] = taskStatus.text();
	    }
	} else {
	    this.statuses = null;
	}
    }

    public String getLocale() {
	return locale;
    }

    public void setLocale(String locale) {
	this.locale = locale;
    }

    public Long getJobId() {
	return jobId;
    }

    public void setJobId(Long jobId) {
	this.jobId = jobId;
    }

    public String[] getUniqueIdentifiers() {
	return uniqueIdentifiers;
    }

    public void setUniqueIdentifiers(String[] uniqueIdentifiers) {
	this.uniqueIdentifiers = uniqueIdentifiers;
    }

    public String[] getNodeIds() {
	return nodeIds;
    }

    public void setNodeIds(String[] nodeIds) {
	this.nodeIds = nodeIds;
    }

}