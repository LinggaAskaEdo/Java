package com.main.java.invoice.project.pojo;

public class MasterEvent
{
    private Integer masterEventId;
    private String agentEvent;
    private String name;
    private String address;
    private String noNpwp;
    private String information;

    public Integer getMasterEventId() {
        return masterEventId;
    }

    public void setMasterEventId(Integer masterEventId) {
        this.masterEventId = masterEventId;
    }

    public String getAgentEvent() {
        return agentEvent;
    }

    public void setAgentEvent(String agentEvent) {
        this.agentEvent = agentEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNoNpwp() {
        return noNpwp;
    }

    public void setNoNpwp(String noNpwp) {
        this.noNpwp = noNpwp;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MasterEvent{");
        sb.append("masterEventId=").append(masterEventId);
        sb.append(", agentEvent='").append(agentEvent).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", noNpwp='").append(noNpwp).append('\'');
        sb.append(", information='").append(information).append('\'');
        sb.append('}');
        return sb.toString();
    }
}