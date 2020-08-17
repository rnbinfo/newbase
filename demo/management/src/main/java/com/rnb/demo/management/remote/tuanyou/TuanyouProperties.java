package com.rnb.demo.management.remote.tuanyou;

import com.rnb.newbase.controller.base.BaseRemoteServerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "remote.tuanyou")
public class TuanyouProperties extends BaseRemoteServerProperties {
    private String channelId;
    private String key;
    private String secret;
}
