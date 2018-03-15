package telco;

import java.util.Date;

public class SftpSetup
{
    private int setupId;
    private String name;
    private String mcc;
    private String mnc;
    private String iphost;
    private int port;
    private String username;
    private String password;
    private String localDir;
    private String remoteDir;
    private String inputFileExt;
    private String outputFileExt;
    private boolean decoding;
    private boolean extract;
    private int vendorId;
    private String radioTech;
    private String protocol;
    private String decompressMethod;
    private String traceReference;
    private String locationIntrogateUrl;
    private Date created;

    public int getSetupId() {
        return setupId;
    }

    public void setSetupId(int setupId) {
        this.setupId = setupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getIphost() {
        return iphost;
    }

    public void setIphost(String iphost) {
        this.iphost = iphost;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocalDir() {
        return localDir;
    }

    public void setLocalDir(String localDir) {
        this.localDir = localDir;
    }

    public String getRemoteDir() {
        return remoteDir;
    }

    public void setRemoteDir(String remoteDir) {
        this.remoteDir = remoteDir;
    }

    public String getInputFileExt() {
        return inputFileExt;
    }

    public void setInputFileExt(String inputFileExt) {
        this.inputFileExt = inputFileExt;
    }

    public String getOutputFileExt() {
        return outputFileExt;
    }

    public void setOutputFileExt(String outputFileExt) {
        this.outputFileExt = outputFileExt;
    }

    public boolean isDecoding() {
        return decoding;
    }

    public void setDecoding(boolean decoding) {
        this.decoding = decoding;
    }

    public boolean isExtract() {
        return extract;
    }

    public void setExtract(boolean extract) {
        this.extract = extract;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getRadioTech() {
        return radioTech;
    }

    public void setRadioTech(String radioTech) {
        this.radioTech = radioTech;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getDecompressMethod() {
        return decompressMethod;
    }

    public void setDecompressMethod(String decompressMethod) {
        this.decompressMethod = decompressMethod;
    }

    public String getTraceReference() {
        return traceReference;
    }

    public void setTraceReference(String traceReference) {
        this.traceReference = traceReference;
    }

    public String getLocationIntrogateUrl() {
        return locationIntrogateUrl;
    }

    public void setLocationIntrogateUrl(String locationIntrogateUrl) {
        this.locationIntrogateUrl = locationIntrogateUrl;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SftpSetup{");
        sb.append("setupId=").append(setupId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", mcc='").append(mcc).append('\'');
        sb.append(", mnc='").append(mnc).append('\'');
        sb.append(", iphost='").append(iphost).append('\'');
        sb.append(", port=").append(port);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", localDir='").append(localDir).append('\'');
        sb.append(", remoteDir='").append(remoteDir).append('\'');
        sb.append(", inputFileExt='").append(inputFileExt).append('\'');
        sb.append(", outputFileExt='").append(outputFileExt).append('\'');
        sb.append(", decoding=").append(decoding);
        sb.append(", extract=").append(extract);
        sb.append(", vendorId=").append(vendorId);
        sb.append(", radioTech='").append(radioTech).append('\'');
        sb.append(", protocol='").append(protocol).append('\'');
        sb.append(", decompressMethod='").append(decompressMethod).append('\'');
        sb.append(", traceReference='").append(traceReference).append('\'');
        sb.append(", locationIntrogateUrl='").append(locationIntrogateUrl).append('\'');
        sb.append(", created=").append(created);
        sb.append('}');
        return sb.toString();
    }
}