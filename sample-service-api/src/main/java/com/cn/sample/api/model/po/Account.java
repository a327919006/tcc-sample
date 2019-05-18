package com.cn.sample.api.model.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account implements Serializable {
    private String accountId;

    private BigDecimal money;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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