package com.github.phonenumbermanager.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 用户角色实体
 *
 * @author 廿二月的天
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("pm_role")
public class UserRole implements Serializable {
    private Long id;
    private String name;
    private String description;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @Version
    private Integer version;
    private Long parentId;
    @TableField(exist = false)
    private Set<SystemUser> systemUsers;
    @TableField(exist = false)
    private Set<UserPrivilege> userPrivileges;
}
