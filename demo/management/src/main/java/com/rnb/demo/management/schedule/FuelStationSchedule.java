package com.rnb.demo.management.schedule;

import com.rnb.demo.entity.constants.DataDictionaryType;
import com.rnb.demo.entity.enums.fuel.FuelStationStatus;
import com.rnb.demo.entity.enums.fuel.FuelStationType;
import com.rnb.demo.entity.enums.fuel.FuelType;
import com.rnb.demo.entity.enums.remote.ChannelEnergy;
import com.rnb.demo.entity.po.fuel.FuelStation;
import com.rnb.demo.entity.po.fuel.FuelStationProvide;
import com.rnb.demo.entity.remote.tuanyou.OilPrice;
import com.rnb.demo.entity.remote.tuanyou.SyncStation;
import com.rnb.demo.management.remote.tuanyou.TuanyouRemoteService;
import com.rnb.demo.service.cache.DataDictionaryCache;
import com.rnb.demo.service.service.fuel.FuelStationProvideService;
import com.rnb.demo.service.service.fuel.FuelStationService;
import com.rnb.newbase.toolkit.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FuelStationSchedule {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DataDictionaryCache dataDictionaryCache;
    @Resource
    private TuanyouRemoteService tuanyouRemoteService;
    @Resource
    private FuelStationService fuelStationService;
    @Resource
    private FuelStationProvideService fuelStationProvideService;

    @Scheduled(cron = "0 0 */6 * * *")
    public void syncStations() {
        logger.info("Start to synchronize tuan you stations");
        String channel = dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.CHANNEL_ENERGY, ChannelEnergy.TUANYOU.getValue());
        if (StringUtil.isBlank(channel)) {
            logger.error("Synchronize stations from tuan you FAILED! Channel is null.");
            return;
        }
        fuelStationService.closeStationsByChannel(channel);
        List<SyncStation> sourceStations = tuanyouRemoteService.syncStations();
        logger.debug("Need to synchronize [{}] stations", sourceStations.size());
        for(SyncStation syncStation : sourceStations) {
            try {
                generateFuelStation(syncStation, channel);
            } catch (Exception e) {
                logger.error("GenerateFuelStation[{}] Error", syncStation);
                logger.error("Exception =>", e);
            }
        }
        logger.info("Complete to synchronize tuan you stations");
    }

    @Transactional
    public void generateFuelStation(SyncStation syncStation, String channel) {
        FuelStation condition = new FuelStation();
        condition.setChannel(channel);
        condition.setChannelStationId(syncStation.getGasId());
        FuelStation existedStation = fuelStationService.queryOneByCondition(condition);
        FuelStation updatedOrInsertedStation = null;
        //更新或新增加注站信息
        if (existedStation != null) {
            existedStation.setName(syncStation.getGasName());
            existedStation.setLogoBig(syncStation.getGasLogoBig());
            existedStation.setLogoSmall(syncStation.getGasLogoSmall());
            existedStation.setAddress(syncStation.getGasAddress());
            existedStation.setLongitude(syncStation.getGasAddressLongitude());
            existedStation.setLatitude(syncStation.getGasAddressLatitude());
            existedStation.setProvinceCode(String.valueOf(syncStation.getProvinceCode()));
            existedStation.setProvinceName(syncStation.getProvinceName());
            existedStation.setCityCode(String.valueOf(syncStation.getCityCode()));
            existedStation.setCityName(syncStation.getCityName());
            existedStation.setCountyCode(String.valueOf(syncStation.getCountyCode()));
            existedStation.setCountyName(syncStation.getCountyName());
            existedStation.setInvoice(syncStation.getIsInvoice());
            existedStation.setStatus(dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.CHANNEL_STATION_STATUS, FuelStationStatus.NORMAL.getValue()));
            updatedOrInsertedStation = fuelStationService.updateReturnObject(existedStation);
        } else {
            FuelStation newStation = new FuelStation();
            newStation.setChannel(channel);
            newStation.setType(dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.CHANNEL_STATION_TYPE, FuelStationType.OIL.getValue()));
            newStation.setBrand(convertOilBrand(syncStation.getGasType()));
            newStation.setChannelStationId(syncStation.getGasId());
            newStation.setName(syncStation.getGasName());
            newStation.setLogoBig(syncStation.getGasLogoBig());
            newStation.setLogoSmall(syncStation.getGasLogoSmall());
            newStation.setAddress(syncStation.getGasAddress());
            newStation.setLongitude(syncStation.getGasAddressLongitude());
            newStation.setLatitude(syncStation.getGasAddressLatitude());
            newStation.setProvinceCode(String.valueOf(syncStation.getProvinceCode()));
            newStation.setProvinceName(syncStation.getProvinceName());
            newStation.setCityCode(String.valueOf(syncStation.getCityCode()));
            newStation.setCityName(syncStation.getCityName());
            newStation.setCountyCode(String.valueOf(syncStation.getCountyCode()));
            newStation.setCountyName(syncStation.getCountyName());
            newStation.setInvoice(syncStation.getIsInvoice());
            newStation.setStatus(dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.CHANNEL_STATION_STATUS, FuelStationStatus.NORMAL.getValue()));
            updatedOrInsertedStation = fuelStationService.insertReturnObject(newStation);
        }
        //更新燃料信息
        fuelStationProvideService.deleteProvideByStation(updatedOrInsertedStation.getId());
        for(OilPrice oilPrice : syncStation.getOilPriceList()) {
            FuelStationProvide newFuelStationProvide = new FuelStationProvide();
            newFuelStationProvide.setStationId(updatedOrInsertedStation.getId());
            newFuelStationProvide.setType(convertOilType(oilPrice.getOilType()));
            newFuelStationProvide.setNo(String.valueOf(oilPrice.getOilNo()));
            newFuelStationProvide.setName(oilPrice.getOilName() == null ? String.valueOf(oilPrice.getOilNo()) : oilPrice.getOilName());
            newFuelStationProvide.setOfficialPrice(Double.valueOf(oilPrice.getPriceOfficial()));
            newFuelStationProvide.setStationPrice(Double.valueOf(oilPrice.getPriceGun()));
            fuelStationProvideService.insert(newFuelStationProvide);
        }
    }

    private String convertOilType(Integer oilType) {
        switch (oilType) {
            case 1:
                return dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.BASE_FUEL_TYPE, FuelType.PETROL.getValue());
            case 2:
                return dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.BASE_FUEL_TYPE, FuelType.DIESEL.getValue());
            case 3:
                return dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.BASE_FUEL_TYPE, FuelType.NATURAL.getValue());
        }
        return null;
    }

    private String convertOilBrand(Integer gasType) {
        //TODO 待完善
        return "测试品牌";
    }
}
