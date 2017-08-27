package main.dao;

import java.util.List;

import main.entity.SystemUser;
/**
 * 系统用户DAO接口
 *
 */
public interface SystemUsersDao extends BaseDao<SystemUser> {
	/**
	 * 查询所有系统用户与所属角色
	 * @return
	 * @throws Exception
	 */
	public List<SystemUser> selectSystemUsersAndRolesAll() throws Exception;
	/**
	 * 通过用户ID查询系统用户与所属角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SystemUser selectSystemUserAndRoleById(Integer id) throws Exception;
	/**
	 * 通过用户名称查询系统用户与所属角色以及权限
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public SystemUser selectSystemUserAndRoleAndPrivilegesByName(String username) throws Exception;
}