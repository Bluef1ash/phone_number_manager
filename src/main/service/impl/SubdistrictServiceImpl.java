package main.service.impl;

import org.springframework.stereotype.Service;

import main.entity.Subdistrict;
import main.service.SubdistrictService;

import java.util.HashSet;
import java.util.Set;

/**
 * 街道业务实现
 */
@Service("subdistrictService")
public class SubdistrictServiceImpl extends BaseServiceImpl<Subdistrict> implements SubdistrictService {
    @Override
    public Set<Subdistrict> findCommunitiesAndSubdistrictsByRole(Integer roleId, Integer roleLocationId) throws Exception {
        Set<Subdistrict> subdistricts = null;
        switch (roleId) {
            case 3: // 社区角色
                subdistricts = new HashSet<Subdistrict>();
                Subdistrict subdistrict = subdistrictsDao.selectSubdistrictAndCommunityByCommunityId(roleLocationId);
                subdistricts.add(subdistrict);
                break;
            case 2: // 街道角色
                subdistricts = subdistrictsDao.selectSubdistrictAndCommunityBySubdistrictId(roleLocationId);
                break;
            default: // 管理员角色
                subdistricts = subdistrictsDao.selectSubdistrictsAndCommunitiesAll();
                break;
        }
        return subdistricts;
    }
}