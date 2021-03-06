package com.github.phonenumbermanager.dao;

import com.github.phonenumbermanager.entity.Community;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 社区DAO接口
 *
 * @author 廿二月的天
 */
public interface CommunityDao extends BaseDao<Community> {
    /**
     * 通过主键ID查询社区和所属街道
     *
     * @param id 社区编号
     * @return 一个社区信息和所属街道信息
     * @throws DataAccessException 数据库操作异常
     */
    Community selectAndSubdistrictById(Serializable id) throws DataAccessException;

    /**
     * 查询所有社区和所属街道
     *
     * @return 全部查询的社区和所属街道的信息
     * @throws DataAccessException 数据库操作异常
     */
    List<Community> selectAndSubdistrictAll() throws DataAccessException;

    /**
     * 通过所属街道编号关联查询社区
     *
     * @param subdistrictId 所属街道编号
     * @return 所属街道的所有社区
     * @throws DataAccessException 数据库操作异常
     */
    List<Community> selectCorrelationBySubdistrictId(Serializable subdistrictId) throws DataAccessException;

    /**
     * 通过所属街道编号查询社区
     *
     * @param subdistrictId 所属街道编号
     * @return 所属街道的所有社区
     * @throws DataAccessException 数据库操作异常
     */
    List<Community> selectBySubdistrictId(Serializable subdistrictId) throws DataAccessException;

    /**
     * 通过社区名称查询社区编号
     *
     * @param name 社区名称
     * @return 社区编号
     * @throws DataAccessException 数据库操作异常
     */
    Serializable selectIdByName(String name) throws DataAccessException;

    /**
     * 通过所属街道名称查询社区
     *
     * @param subdistrictName 所属街道名称
     * @return 所属街道的所有社区
     * @throws DataAccessException 数据库操作异常
     */
    List<Community> selectBySubdistrictName(String subdistrictName) throws DataAccessException;

    /**
     * 通过社区编号查询指标
     *
     * @param id 社区编号
     * @return 社区指标
     * @throws DataAccessException 数据库操作异常
     */
    Long selectActualNumberById(Serializable id) throws DataAccessException;

    /**
     * 通过所属街道编号求和指标
     *
     * @param subdistrictId 所属街道编号
     * @return 指标的和
     * @throws DataAccessException 数据库操作异常
     */
    Long sumActualNumberBySubdistrictId(Serializable subdistrictId) throws DataAccessException;

    /**
     * 求和所有社区指标
     *
     * @return 所有社区指标之和
     * @throws DataAccessException 数据库操作异常
     */
    Long sumActualNumber() throws DataAccessException;

    /**
     * 通过所属街道编号统计各个社区
     *
     * @param subdistrictId 所属街道编号
     * @return 统计社区个数
     * @throws DataAccessException 数据库操作异常
     */
    List<Community> countBySubdistrictId(Serializable subdistrictId) throws DataAccessException;

    /**
     * 关联查询所有社区与街道
     *
     * @return 社区与街道对象集合
     * @throws DataAccessException 数据库连接异常
     */
    List<Community> selectCorrelationSubdistrictsAll() throws DataAccessException;

    /**
     * 通过社区编号关联查询
     *
     * @param id 社区编号
     * @return 查询到的关联社区对象
     * @throws DataAccessException 数据库连接异常
     */
    Community selectCorrelationById(Serializable id) throws DataAccessException;

    /**
     * 通过系统用户类型批量更新提交状态
     *
     * @param data                   用户数据
     * @param changeType             更改类型
     * @param systemCompanyType      系统级角色编号
     * @param communityCompanyType   社区级角色编号
     * @param subdistrictCompanyType 街道级角色编号
     * @return 更新数量
     * @throws DataAccessException 数据库连接异常
     */
    long updateSubmitted(@Param("data") List<Map<String, Object>> data, @Param("changeType") Integer changeType, @Param("systemCompanyType") Serializable systemCompanyType, @Param("communityCompanyType") Serializable communityCompanyType, @Param("subdistrictCompanyType") Serializable subdistrictCompanyType) throws DataAccessException;

    /**
     * 通过编号与提交类别查询是否已锁定
     *
     * @param submitType 提交类别
     * @param id         社区编号
     * @return 是否已经提报
     * @throws DataAccessException 数据库连接异常
     */
    Boolean selectSubmittedById(Integer submitType, Serializable id) throws DataAccessException;
}
