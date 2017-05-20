package com.relevancytester.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the TestCase entity.
 */
public class TestCaseDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private ZonedDateTime createdDate;

    private Long projectId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TestCaseDTO testCaseDTO = (TestCaseDTO) o;

        if ( ! Objects.equals(id, testCaseDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TestCaseDTO{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", createdDate='" + createdDate + "'" +
            '}';
    }
}
