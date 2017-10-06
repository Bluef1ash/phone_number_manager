package main.dao;

import main.entity.UserPrivilege;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户权限DAO接口
 */
public interface UserPrivilegesDao extends BaseDao<UserPrivilege> {
    /**
     * 通过角色ID查询
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<UserPrivilege> selectPrivilegesByRoleId(Integer roleId) throws Exception;

    /**
     * 通过角色是否在导航栏显示查询
     *
     * @param isDisplay
     * @return
     * @throws Exception
     */
    public List<UserPrivilege> selectPrivilegesByIsDisplay(Integer isDisplay) throws Exception;

    /**
     * 通过角色是否在导航栏和拥有的权限显示查询
     *
     * @param map
     * @return
     * @throws Exception
     */
    public List<UserPrivilege> selectPrivilegesByIsDisplayAndConstraintAuth(Map<String, Object> map) throws Exception;

    /**
     * 通过角色父级ID查询
     *
     * @param higherPrivilege
     * @param isDisplay
     * @return
     * @throws Exception
     */
    public List<UserPrivilege> selectPrivilegesByHigherPrivilegeAndIsDisplay(@Param("higherPrivilege") Integer higherPrivilege, @Param("isDisplay") Integer isDisplay) throws Exception;

    /**
     * 通过角色父级ID查询
     *
     * @param higherPrivilege
     * @return
     * @throws Exception
     */
    public List<UserPrivilege> selectPrivilegesByHigherPrivilege(Integer higherPrivilege) throws Exception;
}
