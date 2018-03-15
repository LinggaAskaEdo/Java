package json;

import java.util.List;

public class IsmscNode
{
    private String host;
    private String hostOperator;
    private List<String> methods;
    private List<String> highAccuracyMethods;
    private Object additionalInfo;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHostOperator() {
        return hostOperator;
    }

    public void setHostOperator(String hostOperator) {
        this.hostOperator = hostOperator;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public List<String> getHighAccuracyMethods() {
        return highAccuracyMethods;
    }

    public void setHighAccuracyMethods(List<String> highAccuracyMethods) {
        this.highAccuracyMethods = highAccuracyMethods;
    }

    public Object getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Object additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "IsmscNode{" +
                "host='" + host + '\'' +
                ", hostOperator='" + hostOperator + '\'' +
                ", methods=" + methods +
                ", highAccuracyMethods=" + highAccuracyMethods +
                ", additionalInfo=" + additionalInfo +
                '}';
    }
}
