package com.rnb.demo.service.cache;

import com.rnb.demo.entity.constants.ParameterType;
import com.rnb.demo.entity.po.system.SystemParameter;
import com.rnb.demo.service.service.system.SystemParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Lazy(false)
public class ParameterCache implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<SystemParameter> parameters;

    @Resource
    private SystemParameterService parameterService;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Start to initial system parameters");
        refreshAllNormalParameters();
    }

    /**
     * 刷新缓存参数
     */
    public void refreshAllNormalParameters() {
        SystemParameter condition = new SystemParameter();
        List<SystemParameter> parameters = parameterService.queryListByCondition(condition);
        logger.info("Refresh all normal parameters[{}]", parameters.size());
        this.parameters = parameters;
    }

    /**
     * 从缓存中查找指定类型的所有参数
     * @param type
     * @return
     */
    public List<SystemParameter> findByType(ParameterType type) {
        List<SystemParameter> parametersAsType = new ArrayList<>();
        for(SystemParameter parameter : parameters) {
            if (type.equals(parameter.getType())) {
                parametersAsType.add(parameter);
            }
        }
        return parametersAsType;
    }

    /**
     * 从缓存中查找指定类型和键对应的参数
     * @param type
     * @param code
     * @return
     */
    public SystemParameter findByTypeCode(ParameterType type, String code) {
        for(SystemParameter parameter : parameters) {
            if (type.equals(parameter.getType()) && code.equals(parameter.getCode())) {
                return parameter;
            }
        }
        return null;
    }

    /**
     * 根据类型和键，查找对应的值
     * @param type
     * @param code
     * @returnkey
     */
    public String findKeyByTypeCode(ParameterType type, String code) {
        SystemParameter parameter = findByTypeCode(type, code);
        if (parameter != null) {
            return parameter.getKey();
        }
        return null;
    }

    /**
     * 更新参数缓存
     * @param parameter
     */
    public void updateParameter(SystemParameter parameter) {
        logger.debug("Update system parameter from cache, parameter[{}]", parameter);
        // 从list中删除原数据
        deleteParameter(parameter);
        // 如果状态为正常，则将新数据回入list
        parameters.add(parameter);
    }

    /**
     * 新增参数缓存
     * @param parameter
     */
    public void createParameter(SystemParameter parameter) {
        logger.debug("Create system parameter from cache, parameter[{}]", parameter);
        parameters.add(parameter);
    }

    /**
     * 删除参数缓存
     * @param parameter
     */
    private void deleteParameter(SystemParameter parameter) {
        logger.debug("Delete system parameter from cache, parameter[{}]", parameter);
        for(SystemParameter parameterByList : parameters) {
            if (parameterByList.getType().equals(parameter.getType())) {
                if (parameterByList.getKey().equals(parameter.getKey())) {
                    logger.debug("Find system parameter need to be deleted, cachedParameter[{}]", parameterByList);
                    parameters.remove(parameterByList);
                    break;
                }
            }
        }
    }

}
