package com.rnb.demo.persistence.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generator {
    public static void main(String[] args) throws Exception{
        MybatisConfig config = new MybatisConfig();
        DefaultShellCallback callback = new DefaultShellCallback(config.isOverwrite());
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config.getConfig(), callback, config.getWarnings());
        myBatisGenerator.generate(null);
    }
}