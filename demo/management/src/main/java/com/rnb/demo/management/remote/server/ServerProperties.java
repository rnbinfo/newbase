package com.rnb.demo.management.remote.server;

import com.rnb.newbase.controller.base.BaseRemoteServerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "remote.server")
public class ServerProperties extends BaseRemoteServerProperties {
}
