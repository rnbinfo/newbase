package com.rnb.demo.service.constant;

import com.rnb.newbase.exception.IRnbExceptionInfo;

public enum ExceptionInfo implements IRnbExceptionInfo {
    USER_NOT_EXISTED("100001", "user.not.existed"),
    USER_EXISTED("100002", "user.existed"),
    USER_STATUS_ABNORMAL("100003", "user.status.abnormal"),
    USER_VERIFY_CODE_UNAVAILABLE("100004", "user.verify.code.unavailable"),
    USER_VERIFY_CODE_NOT_MATCH("100005", "user.verify.code.not.match"),
    USER_INSUFFICIENT_BALANCE("100006", "user.insufficient.balacne"),
    USER_MOBILE_ID_NOT_MATCH("100007", "user.mobile.id.not.match"),
    USER_LOGIN_INFO_ERROR("100008", "user.login.info.error"),
    USER_NOT_LOGIN("100009", "user.not.login"),

    WITHDRAW_NOT_EXISTED("141001", "withdraw.not.existed"),
    WITHDRAW_STATUS_NOT_MATCH("141002", "withdraw.status.not.match"),
    WITHDRAW_BALANCE_NOT_ENOUGH("141003", "withdraw.balance.not.enough"),
    WITHDRAW_AMOUNT_TOO_LOW("141004", "withdraw.amount.too.low"),
    WITHDRAW_UNAVAILABLE("141005", "withdraw.unavailable"),
    WITHDRAW_EXCEEDS_THE_LIMIT("141006", "withdraw.exceeds.the.limit"),

    SYSTEM_PARAMETER_NOT_EXISTED("510001", "system.parameter.not.existed"),
    SYSTEM_PARAMETER_EXISTED("510002", "system.parameter.existed"),
    SYSTEM_DATA_DICTIONARY_NOT_EXISTED("510003", "system.data.dictionary.not.existed"),
    SYSTEM_DATA_DICTIONARY_EXISTED("510004", "system.data.dictionary.existed"),
    SYSTEM_DATA_DICTIONARY_LOCKED("510005", "system.data.dictionary.locked"),
    SYSTEM_DATA_DICTIONARY_TYPE_NOT_MATCH("510006", "system.data.dictionary.not.match"),


    CONFIG_ACCESSOR_EXISTED("600001", "config.accessor.existed"),
    CONFIG_ACCESSOR_NOT_EXISTED("600002", "config.accessor.not.existed"),

    CONFIG_BUSINESS_TRADE_EXISTED("601001", "config.business.trade.existed"),
    CONFIG_BUSINESS_TRADE_NOT_EXISTED("601002", "config.business.trade.not.existed"),

    CONFIG_PROFIT_EXISTED("602001", "config.profit.existed"),
    CONFIG_PROFIT_NOT_EXISTED("602002", "config.profit.not.existed"),
    CONFIG_PROFIT_ANOTHER_ACTIVATED("602003", "config.profit.another.activated"),

    MESSAGE_SMS_SENT_EXCEED_DAILY("700001", "message.sms.sent.exceed.daily"),
    MESSAGE_SMS_SENT_TOO_FREQUENTLY("700002", "message.sms.sent.too.frequently"),

    OPERATOR_NOT_EXISTED("800001", "operator.not.existed"),
    OPERATOR_EXISTED("800002", "operator.existed"),
    OPERATOR_STATUS_ABNORMAL("800003", "operator.status.abnormal"),
    OPERATOR_PASSWORD_NOT_MATCH("800004", "operator.password.not.match"),

    UPLOAD_FILE_ERROR("900000", "upload.file.error"),
    PAYMENT_ALIPAY_ERROR("910001", "payment.alipay.error");

    private String errorCode;
    private String errorMessage;

    ExceptionInfo(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
