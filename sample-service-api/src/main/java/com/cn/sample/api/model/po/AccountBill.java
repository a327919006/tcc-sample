package com.cn.sample.api.model.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountBill implements Serializable {
    private String billId;

    private String orderId;

    private BigDecimal oldMoney;

    private BigDecimal changeMoney;

    private BigDecimal newMoney;

    private Byte status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(BigDecimal oldMoney) {
        this.oldMoney = oldMoney;
    }

    public BigDecimal getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(BigDecimal changeMoney) {
        this.changeMoney = changeMoney;
    }

    public BigDecimal getNewMoney() {
        return newMoney;
    }

    public void setNewMoney(BigDecimal newMoney) {
        this.newMoney = newMoney;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}