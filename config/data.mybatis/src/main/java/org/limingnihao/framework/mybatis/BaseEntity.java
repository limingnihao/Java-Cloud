package org.limingnihao.framework.mybatis;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by limingnihao on 2017/5/11.
 */
public abstract class BaseEntity implements Serializable {

    /**
     * 乐观锁
     */
    protected Integer version;
    protected Integer useFlag;
    protected Integer hdelete;
    protected Timestamp createTime;
    protected Timestamp updateTime;

    public abstract String toString();

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
    }

    public Integer getHdelete() {
        return hdelete;
    }

    public void setHdelete(Integer hdelete) {
        this.hdelete = hdelete;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
