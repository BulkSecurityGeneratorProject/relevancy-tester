package com.relevancytester.service.dto;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class ExtractorDTO {

    private Long id;

    @NotNull
    private String name;

    private ZonedDateTime createdDate;

    private Long projectId;

    private String script;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
