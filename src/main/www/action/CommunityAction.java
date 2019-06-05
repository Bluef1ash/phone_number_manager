package www.action;

import com.alibaba.fastjson.JSON;
import exception.BusinessException;
import exception.JsonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import www.entity.Community;
import www.entity.Subcontractor;
import www.entity.Subdistrict;
import www.service.CommunityService;
import www.service.SubcontractorService;
import www.service.SubdistrictService;
import www.validator.CommunityInputValidator;
import www.validator.SubcontractorInputValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 社区控制器
 *
 * @author 廿二月的天
 */
@Controller
@RequestMapping("/community")
public class CommunityAction extends BaseAction {
    @Resource
    private CommunityService communityService;
    @Resource
    private SubcontractorService subcontractorService;
    @Resource
    private SubdistrictService subdistrictService;
    private final HttpServletRequest request;

    @Autowired
    public CommunityAction(HttpServletRequest request) {
        this.request = request;
    }

    @InitBinder
    public void initBinder(DataBinder binder) {
        String validFunction = (String) request.getSession().getAttribute("validFunction");
        if ("communityCreateOrEditHandle".equals(validFunction)) {
            binder.replaceValidators(new CommunityInputValidator(communityService, request));
        } else if ("subcontractorCreateOrEditHandle".equals(validFunction)) {
            binder.replaceValidators(new SubcontractorInputValidator(subcontractorService, request));
        }
    }

    /**
     * 社区列表
     *
     * @param model 前台模型
     * @param page  分页页码
     * @return 视图页面
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String communityList(Model model, Integer page) {
        try {
            Map<String, Object> communityMap = communityService.findCommunitiesAndSubdistrict(page, null);
            model.addAttribute("communities", communityMap.get("data"));
            model.addAttribute("pageInfo", communityMap.get("pageInfo"));
            return "community/list";
        } catch (Exception e) {
            throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
        }
    }

    /**
     * 添加社区
     *
     * @param model 前台模型
     * @return 视图页面
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createCommunity(Model model) {
        try {
            List<Subdistrict> subdistricts = subdistrictService.findObjects();
            model.addAttribute("subdistricts", subdistricts);
            return "community/edit";
        } catch (Exception e) {
            throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
        }
    }

    /**
     * 编辑社区
     *
     * @param model 前台模型
     * @param id    编辑的对应编号
     * @return 视图页面
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editCommunity(Model model, Long id) {
        try {
            Community community = communityService.findCommunityAndSubdistrictById(id);
            List<Subdistrict> subdistricts = subdistrictService.findObjects();
            model.addAttribute("subdistricts", subdistricts);
            model.addAttribute("community", community);
            return "community/edit";
        } catch (Exception e) {
            throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
        }
    }

    /**
     * 添加、修改社区处理
     *
     * @param request       HTTP请求对象
     * @param model         前台模型
     * @param community     社区对象
     * @param bindingResult 错误信息对象
     * @return 视图页面
     */
    @RequestMapping(value = "/handle", method = {RequestMethod.POST, RequestMethod.PUT})
    public String communityCreateOrEditHandle(HttpServletRequest request, Model model, @Validated Community community, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            try {
                // 输出错误信息
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                model.addAttribute("messageErrors", allErrors);
                List<Subdistrict> subdistricts = subdistrictService.findObjects();
                model.addAttribute("subdistricts", subdistricts);
                return "community/edit";
            } catch (Exception e) {
                throw new BusinessException("展示社区失败！", e);
            }
        }
        if (RequestMethod.POST.toString().equals(request.getMethod())) {
            try {
                communityService.createObject(community);
            } catch (Exception e) {
                throw new BusinessException("添加社区失败！", e);
            }
        } else {
            try {
                communityService.updateObject(community);
            } catch (Exception e) {
                throw new BusinessException("修改社区失败！", e);
            }
        }
        return "redirect:/community/list";
    }

    /**
     * 使用AJAX技术通过社区编号删除社区
     *
     * @param id 对应编号
     * @return Ajax信息
     */
    @RequestMapping(value = "/ajax_delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteCommunityForAjax(Long id) {
        Map<String, Object> map = new HashMap<>(3);
        try {
            communityService.deleteObjectById(id);
            map.put("state", 1);
            map.put("message", "删除社区成功！");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException("删除社区失败！", e);
        }
    }

    /**
     * 社区分包人列表
     *
     * @param session Session对象
     * @param model   前台模型
     * @param page    分页页码
     * @return 视图页面
     */
    @RequestMapping(value = "/subcontractor/list", method = RequestMethod.GET)
    public String subcontractorList(HttpSession session, Model model, Integer page) {
        getSessionRoleId(session);
        try {
            Map<String, Object> subcontractorMap = subcontractorService.findSubcontractors(page, null, systemUser.getRoleId(), systemUser.getRoleLocationId(), communityRoleId, subdistrictRoleId, systemRoleId);
            model.addAttribute("subcontractors", subcontractorMap.get("data"));
            model.addAttribute("pageInfo", subcontractorMap.get("pageInfo"));
            return "subcontractor/list";
        } catch (Exception e) {
            throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
        }
    }

    /**
     * 添加社区分包人
     *
     * @param model 前台模型
     * @return 视图页面
     */
    @RequestMapping(value = "/subcontractor/create", method = RequestMethod.GET)
    public String createSubcontractor(Model model, HttpSession session) {
        getSessionRoleId(session);
        try {
            List<Community> communities = communityService.findCommunitiesBySystemUser(systemUser, communityRoleId, subdistrictRoleId);
            model.addAttribute("communities", JSON.toJSON(communities));
            return "subcontractor/edit";
        } catch (Exception e) {
            throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
        }
    }

    /**
     * 编辑社区分包人
     *
     * @param model 前台模型
     * @param id    编辑的对应编号
     * @return 视图页面
     */
    @RequestMapping(value = "/subcontractor/edit", method = RequestMethod.GET)
    public String editSubcontractor(HttpSession session, Model model, Long id) {
        getSessionRoleId(session);
        try {
            Subcontractor subcontractor = subcontractorService.findObject(id);
            List<Community> communities = communityService.findCommunitiesBySystemUser(systemUser, communityRoleId, subdistrictRoleId);
            model.addAttribute("communities", JSON.toJSON(communities));
            model.addAttribute("communities", communities);
            model.addAttribute("subcontractor", subcontractor);
            return "subcontractor/edit";
        } catch (Exception e) {
            throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
        }
    }

    /**
     * 添加、修改社区分包人处理
     *
     * @param request       HTTP请求对象
     * @param session       Session对象
     * @param model         前台模型
     * @param subcontractor 社区分包人对象
     * @param bindingResult 错误信息对象
     * @return 视图页面
     */
    @RequestMapping(value = "/subcontractor/handle", method = {RequestMethod.POST, RequestMethod.PUT})
    public String subcontractorCreateOrEditHandle(HttpServletRequest request, HttpSession session, Model model, @Validated Subcontractor subcontractor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            getSessionRoleId(session);
            try {
                // 输出错误信息
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                model.addAttribute("messageErrors", allErrors);
                List<Community> communities = communityService.findCommunitiesBySystemUser(systemUser, communityRoleId, subdistrictRoleId);
                model.addAttribute("communities", JSON.toJSON(communities));
                return "subcontractor/edit";
            } catch (Exception e) {
                throw new BusinessException("展示社区分包人失败！", e);
            }
        }
        if (RequestMethod.POST.toString().equals(request.getMethod())) {
            try {
                subcontractorService.createObject(subcontractor);
            } catch (Exception e) {
                throw new BusinessException("添加社区分包人失败！", e);
            }
        } else {
            try {
                subcontractorService.updateObject(subcontractor);
            } catch (Exception e) {
                throw new BusinessException("修改社区分包人失败！", e);
            }
        }
        return "redirect:/subcontractor/list";
    }

    /**
     * 使用AJAX技术通过社区分包人编号删除社区分包人
     *
     * @param id 对应编号
     * @return Ajax信息
     */
    @RequestMapping(value = "/subcontractor/ajax_delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteSubcontractorForAjax(Long id) {
        Map<String, Object> jsonMap = new HashMap<>(3);
        try {
            subcontractorService.deleteObjectById(id);
            jsonMap.put("state", 1);
            jsonMap.put("message", "删除社区分包人成功！");
            return jsonMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException("删除社区分包人失败！", e);
        }
    }

    /**
     * 使用Ajax技术通过社区编号加载社区分包人
     *
     * @param communityId 社区编号
     * @return 社区分包人对象集合
     */
    @RequestMapping(value = "/subcontractor/ajax_load", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> loadSubcontractorForAjax(Long communityId) {
        Map<String, Object> jsonMap = new HashMap<>(3);
        try {
            List<Subcontractor> subcontractors = subcontractorService.findSubcontractors(communityId);
            jsonMap.put("state", 1);
            jsonMap.put("subcontractors", subcontractors);
            return jsonMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException("加载社区分包人失败！", e);
        }
    }

    /**
     * 使用AJAX技术列出社区居委会
     *
     * @param session Session对象
     * @return Ajax消息
     */
    @RequestMapping(value = "/ajax_select", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findCommunityForAjax(HttpSession session) {
        Map<String, Object> jsonMap = new HashMap<>(3);
        getSessionRoleId(session);
        try {
            Long roleId = systemUser.getUserRole().getRoleId();
            Long roleLocationId = systemUser.getRoleLocationId();
            Set<Subdistrict> subdistricts = subdistrictService.findCommunitiesAndSubdistrictsByRole(systemRoleId, communityRoleId, subdistrictRoleId, roleId, roleLocationId);
            jsonMap.put("state", 1);
            jsonMap.put("subdistricts", subdistricts);
            return jsonMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException("查找社区失败！", e);
        }
    }
}
