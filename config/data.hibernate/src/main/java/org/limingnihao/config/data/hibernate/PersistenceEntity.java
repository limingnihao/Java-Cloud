package org.limingnihao.config.data.hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class PersistenceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "use_flag", columnDefinition = "int(11) default 1 COMMENT '1弃用，0禁用'")
    private Integer useFlag = 1;

    @Column(name = "h_delete", columnDefinition = "int(11) default 0 COMMENT '1删除，0正常'")
    private Integer hdelete = 0;

    @Version
    @Column(name = "h_version", columnDefinition = "int(11) default 0 COMMENT '更新版本号'")
    private Integer hversion;

    @Column(name = "h_create_time", columnDefinition = "datetime COMMENT '创建时间'")
    private Timestamp createTime;

    @Column(name = "h_update_time", columnDefinition = "datetime COMMENT '更新时间'")
    private Timestamp updateTime;

    @PrePersist
    protected void prePersist() {
        this.setCreateTime(new Timestamp(System.currentTimeMillis()));
    }

    @PreUpdate
    protected void preUpdate() {
        this.setUpdateTime(new Timestamp(System.currentTimeMillis()));
    }

    public Integer getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
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

    public Integer getHdelete() {
        return hdelete;
    }

    public void setHdelete(Integer hdelete) {
        this.hdelete = hdelete;
    }

    public Integer getHversion() {
        return hversion;
    }

    public void setHversion(Integer hversion) {
        this.hversion = hversion;
    }
}
