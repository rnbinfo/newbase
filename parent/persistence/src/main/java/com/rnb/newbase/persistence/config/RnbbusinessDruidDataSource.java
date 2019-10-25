package com.rnb.newbase.persistence.config;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;

public class RnbbusinessDruidDataSource extends DruidDataSource {
    @Value("${security.db.propertyKey}")
    private String key;
    @Value("${security.db.isCrypted}")
    private Boolean isCrypted;
    @Override
    public void setPassword(String cryptedPassword) {
        String password = cryptedPassword;
        if (isCrypted) {
            try {
                password = ConfigTools.decrypt(key, cryptedPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.setPassword(password);
    }
}
