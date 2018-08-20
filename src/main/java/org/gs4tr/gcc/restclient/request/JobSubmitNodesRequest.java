package org.gs4tr.gcc.restclient.request;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gs4tr.gcc.restclient.model.GCNode;
import org.gs4tr.gcc.restclient.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JobSubmitNodesRequest extends GCRequest {
    @JsonProperty("job_name")
    private String jobName;
    @JsonProperty("due_date")
    private Date dueDate;
    @JsonProperty("instructions")
    private String instructions;
    @JsonProperty("source_locale")
    private String sourceLocale;
    @JsonProperty("target_locale")
    private String[] targetLocales;
    @JsonProperty("tasks_list")
    private List<GCNode> nodesList;
    @JsonProperty("submitter")
    private String submitter;
    @JsonProperty("callback_url")
    private String callbackUrl;
    @JsonProperty("attributes")
    private Map<String, Object> attributes;
    @JsonProperty("config")
    private String config;

    public JobSubmitNodesRequest(String jobName, Date dueDate, String sourceLocale, String[] targetLocales,
	    List<GCNode> nodes) {
	if (StringUtils.isNullOrEmpty(jobName)) {
	    throw new IllegalArgumentException("Job Name is required");
	}
	if (dueDate.compareTo(new Date()) < 0) {
	    throw new IllegalArgumentException("DueDate must be after current date");
	}
	if (StringUtils.isNullOrEmpty(sourceLocale)) {
	    throw new IllegalArgumentException("Source Locale is required");
	}
	if (targetLocales == null || targetLocales.length <= 0) {
	    throw new IllegalArgumentException("At least one target locale is required");
	}
	if (nodes == null || nodes.size() <= 0) {
	    throw new IllegalArgumentException("At least one Node is required");
	}
	this.jobName = jobName;
	this.dueDate = dueDate;
	this.sourceLocale = sourceLocale;
	this.targetLocales = targetLocales;
	this.nodesList = nodes;
    }

    public JobSubmitNodesRequest(String jobName, Date dueDate, String sourceLocale, String[] targetLocales,
	    List<GCNode> nodes, String instructions, String submitter, Map<String, Object> attributes, String config) {
	this(jobName, dueDate, sourceLocale, targetLocales, nodes);
	this.instructions = instructions;
	this.submitter = submitter;
	this.attributes = attributes;
	this.config = config;

    }

    public Map<String, Object> getParameters() {
	Map<String, Object> parameters = new HashMap<String, Object>();
	parameters.putAll(super.getParameters());
	parameters.put("job_name", this.jobName);
	parameters.put("due_date", this.dueDate);
	parameters.put("source_lang", this.sourceLocale);
	ObjectMapper objectMapper = new ObjectMapper();
	try {
	    parameters.put("target_locale", objectMapper.writeValueAsString(targetLocales));
	    parameters.put("nodes_list", objectMapper.writeValueAsString(nodesList));
	} catch (JsonProcessingException e) {
	    e.printStackTrace();
	}

	if (!StringUtils.isNullOrEmpty(this.instructions)) {
	    parameters.put("instructions", this.instructions);
	}
	if (!StringUtils.isNullOrEmpty(this.submitter)) {
	    parameters.put("submitter", this.submitter);
	}
	if (!StringUtils.isNullOrEmpty(this.callbackUrl)) {
	    parameters.put("callback_url", this.callbackUrl);
	}
	if (this.attributes != null && this.attributes.size() > 0) {
	    try {
		parameters.put("attributes", objectMapper.writeValueAsString(this.attributes));
	    } catch (JsonProcessingException e) {
		e.printStackTrace();
	    }
	}
	if (!StringUtils.isNullOrEmpty(this.config)) {
	    parameters.put("config", this.config);
	}

	return parameters;
    }

    public String getJobName() {
	return jobName;
    }

    public void setJobName(String jobName) {
	this.jobName = jobName;
    }

    public Date getDueDate() {
	return dueDate;
    }

    public void setDueDate(Date dueDate) {
	this.dueDate = dueDate;
    }

    public String getInstructions() {
	return instructions;
    }

    public void setInstructions(String instructions) {
	this.instructions = instructions;
    }

    public String getSourceLocale() {
	return sourceLocale;
    }

    public void setSourceLocale(String sourceLocale) {
	this.sourceLocale = sourceLocale;
    }

    public String[] getTargetLocales() {
	return targetLocales;
    }

    public void setTargetLocales(String[] targetLocales) {
	this.targetLocales = targetLocales;
    }

    public List<GCNode> getNodesList() {
	return nodesList;
    }

    public void setNodesList(List<GCNode> nodesList) {
	this.nodesList = nodesList;
    }

    public String getSubmitter() {
	return submitter;
    }

    public void setSubmitter(String submitter) {
	this.submitter = submitter;
    }

    public String getCallbackUrl() {
	return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
	this.callbackUrl = callbackUrl;
    }

    public Map<String, Object> getAttributes() {
	return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
	this.attributes = attributes;
    }

    public String getConfig() {
	return config;
    }

    public void setConfig(String config) {
	this.config = config;
    }

}
