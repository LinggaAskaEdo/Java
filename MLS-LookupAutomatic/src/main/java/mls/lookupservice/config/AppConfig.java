package mls.lookupservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Lingga on 23/03/17.
 */

@Component
public class AppConfig
{
    @Value("${path.download}")
    public String pathDownload;

    @Value("${pattern.diff}")
    public String patternDiff;

    @Value("${pattern.full}")
    public String patternFull;

    @Value("${format.file.compress}")
    public String formatFileCompress;

    @Value("${format.file.ori}")
    public String formatFileOri;

    @Value("${url.source}")
    public String urlSource;

    @Value("${counter.file.name}")
    public String counterFileName;

    @Value("${counter.file.prefix}")
    public String counterFilePrefix;

    @Value("${counter.file.min}")
    public String counterFileMin;

    @Value("${counter.file.max}")
    public String counterFileMax;

    @Value("${counter.file.length}")
    public int counterFileLength;

    @Value("${counter.file.time.different}")
    public int counterFileTimeDifferent;

    @Value("${marker.file.name}")
    public String markerFileName;

    @Value("${threshold.limit.day}")
    public int thresholdLimitDay;

    @Value("${setting.proxy.enable}")
    public boolean settingProxyEnable;

    @Value("${setting.proxy.host}")
    public String settingProxyHost;

    @Value("${setting.proxy.port}")
    public String settingProxyPort;

    @Value("${compress.buffer.size}")
    public int compressBufferSize;

    @Value("${response.code.not.exist}")
    public int responseCodeNotExist;

    @Override
    public String toString()
    {
        return "AppConfig{" + "pathDownload='" + pathDownload + '\'' +
                ", patternDiff='" + patternDiff + '\'' +
                ", patternFull='" + patternFull + '\'' +
                ", formatFileCompress='" + formatFileCompress + '\'' +
                ", formatFileOri='" + formatFileOri + '\'' +
                ", urlSource='" + urlSource + '\'' +
                ", counterFileName='" + counterFileName + '\'' +
                ", counterFilePrefix='" + counterFilePrefix + '\'' +
                ", counterFileMin='" + counterFileMin + '\'' +
                ", counterFileMax='" + counterFileMax + '\'' +
                ", counterFileLength=" + counterFileLength +
                ", counterFileTimeDifferent=" + counterFileTimeDifferent +
                ", markerFileName='" + markerFileName + '\'' +
                ", thresholdLimitDay=" + thresholdLimitDay +
                ", settingProxyEnable=" + settingProxyEnable +
                ", settingProxyHost='" + settingProxyHost + '\'' +
                ", settingProxyPort='" + settingProxyPort + '\'' +
                ", compressBufferSize=" + compressBufferSize +
                ", responseCodeNotExist=" + responseCodeNotExist +
                '}';
    }
}