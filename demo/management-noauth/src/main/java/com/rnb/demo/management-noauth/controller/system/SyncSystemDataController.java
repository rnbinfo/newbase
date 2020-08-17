package com.rnb.demo.accessor.controller.system;

import com.rnb.demo.accessor.controller.AbstractController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("system")
public class SyncSystemDataController extends AbstractController {
    @PostMapping("refreshDataDictionary")
    public void refreshDataDictionary() {
        dataDictionaryCache.refreshAllDataDictionaries();
    }

    @PostMapping("refreshParameter")
    public void refreshParameter() {
        parameterCache.refreshAllNormalParameters();
    }
}
