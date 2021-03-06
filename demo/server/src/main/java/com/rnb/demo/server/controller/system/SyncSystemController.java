package com.rnb.demo.server.controller.system;

import com.rnb.demo.server.controller.AbstractController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("manage")
public class SyncSystemController extends AbstractController {

    @PostMapping("refreshDataDictionary")
    public void refreshDataDictionary() {
        dataDictionaryCache.refreshAllDataDictionaries();
    }

    @PostMapping("refreshParameter")
    public void refreshParameter() {
        parameterCache.refreshAllNormalParameters();
    }
}
