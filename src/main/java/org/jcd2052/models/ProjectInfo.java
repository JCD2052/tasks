package org.jcd2052.models;

import java.util.Objects;

public class ProjectInfo {
    private final String projectDeadline;
    private final String projectPercentFund;
    private final String projectName;

    public ProjectInfo(String projectDeadline, String projectPercentFund, String projectName) {
        this.projectDeadline = projectDeadline;
        this.projectPercentFund = projectPercentFund;
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDeadline() {
        return projectDeadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectInfo that = (ProjectInfo) o;
        return Objects.equals(projectDeadline, that.projectDeadline) &&
                Objects.equals(projectPercentFund, that.projectPercentFund) &&
                Objects.equals(projectName, that.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectDeadline, projectPercentFund, projectName);
    }
}
