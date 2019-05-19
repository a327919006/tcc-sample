package com.cn.sample.api.enums;

/**
 * 订单状态
 *
 * @author Chen Nan
 */
public enum AccountBillStatusEnum {
    /**
     * 0 TYING
     */
    TYING((byte) 0),
    /**
     * 1 成功
     */
    SUCCESS((byte) 1),
    /**
     * 2 取消
     */
    CANCEL((byte) 2);

    private byte value;

    AccountBillStatusEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
