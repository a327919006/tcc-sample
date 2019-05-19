package com.cn.sample.api.enums;

/**
 * 订单状态
 *
 * @author Chen Nan
 */
public enum OrderStatusEnum {
    /**
     * 0 待支付
     */
    WAIT((byte) 0),
    /**
     * 1 TCC-try
     */
    TYING((byte) 1),
    /**
     * 2 成功
     */
    SUCCESS((byte) 2);

    private byte value;

    OrderStatusEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
