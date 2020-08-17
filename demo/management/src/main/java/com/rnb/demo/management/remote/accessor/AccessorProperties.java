package com.rnb.demo.management.remote.accessor;

import com.rnb.newbase.controller.base.BaseRemoteServerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "remote.accessor")
public class AccessorProperties extends BaseRemoteServerProperties {
}
