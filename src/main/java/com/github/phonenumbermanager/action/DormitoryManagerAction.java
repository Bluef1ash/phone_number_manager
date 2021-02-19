package com.github.phonenumbermanager.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.phonenumbermanager.entity.Community;
import com.github.phonenumbermanager.entity.DormitoryManager;
import com.github.phonenumbermanager.exception.BusinessException;
import com.github.phonenumbermanager.exception.JsonException;
import com.github.phonenumbermanager.service.CommunityService;
import com.github.phonenumbermanager.service.DormitoryManagerService;
import com.github.phonenumbermanager.utils.CommonUtils;
import com.github.phonenumbermanager.utils.DateUtils;
import com.github.phonenumbermanager.utils.ExcelUtils;
import com.github.phonenumbermanager.validator.DormitoryManagerInputValidator;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 社区楼长控制器
 *
 * @author 廿二月的天
 */
@Controller
@RequestMapping("/dormitory")
public class DormitoryManagerAction extends BaseAction {
    @Resource
    private DormitoryManagerService dormitoryManagerService;
    @Resource
    private CommunityService communityService;
    @Resource
    private HttpServletRequest request;

    @InitBinder
    public void initBinder(DataBinder binder) {
        if (RequestMethod.POST.toString().equals(request.getMethod()) || RequestMethod.PUT.toString().equals(request.getMethod())) {
            binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
            binder.replaceValidators(new DormitoryManagerInputValidator(dormitoryManagerService, request));
        }
    }

    /**
     * 社区楼长列表
     *
     * @param session Session对象
     * @param model   前台模型
     * @return 视图页面
     */
    @GetMapping
    public String dormitoryManagerList(HttpSession session, Model model) {
        setPersonVariable(session, model);
        try {
            Map<String, Object> dormitoryManagerMap = dormitoryManagerService.findCorrelation(systemUser, communityCompanyType, subdistrictCompanyType, null, null);
            model.addAttribute("dormitoryManagers", dormitoryManagerMap.get("data"));
            model.addAttribute("pageInfo", dormitoryManagerMap.get("pageInfo"));
            model.addAttribute("dataType", 1);
            return "dormitory/list";
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
        }
    }

    /**
     * 添加社区楼长
     *
     * @param session session对象
     * @param model   前台模型
     * @return 视图页面
     */
    @GetMapping("/create")
    public String createDormitoryManager(HttpSession session, Model model) {
        getSessionRoleId(session);
        try {
            List<Community> communities = communityService.find(systemUser, communityCompanyType, subdistrictCompanyType);
            model.addAttribute("communities", JSON.toJSON(communities));
            return "dormitory/edit";
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
        }
    }

    /**
     * 编辑社区楼长
     *
     * @param session session对象
     * @param model   前台模型
     * @param id      需要编辑的社区楼长的编号
     * @return 视图页面
     */
    @GetMapping("/edit/{id}")
    public String editDormitoryManager(HttpSession session, Model model, @PathVariable String id) {
        getSessionRoleId(session);
        try {
            DormitoryManager dormitoryManager = dormitoryManagerService.findCorrelation(id);
            model.addAttribute("dormitoryManager", dormitoryManager);
            List<Community> communities = communityService.find(systemUser, communityCompanyType, subdistrictCompanyType);
            model.addAttribute("communities", communities);
            return "dormitory/edit";
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
        }
    }

    /**
     * 添加、修改社区楼长处理
     *
     * @param httpServletRequest HTTP请求对象
     * @param session            Session对象
     * @param model              前台模型
     * @param dormitoryManager   前台传递的社区楼长对象
     * @param bindingResult      错误信息对象
     * @return 视图页面
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String dormitoryManagerCreateOrEditHandle(HttpServletRequest httpServletRequest, HttpSession session, Model model, @Validated DormitoryManager dormitoryManager, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            getSessionRoleId(session);
            try {
                throwsError(communityService, model, bindingResult);
                return "dormitory/edit";
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("系统出现错误，请联系管理员！");
            }
        }
        dormitoryManager.setUpdateTime(DateUtils.getTimestamp(new Date()));
        if (RequestMethod.POST.toString().equals(httpServletRequest.getMethod())) {
            try {
                dormitoryManagerService.create(dormitoryManager);
            } catch (BusinessException be) {
                be.printStackTrace();
                throw new BusinessException(be.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("添加社区楼长失败！", e);
            }
        } else {
            try {
                dormitoryManagerService.update(dormitoryManager);
            } catch (BusinessException be) {
                be.printStackTrace();
                throw new BusinessException(be.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("修改社区楼长失败！", e);
            }
        }
        return "redirect:/dormitory";
    }

    /**
     * 使用AJAX技术通过社区楼长编号删除
     *
     * @param id 对应编号
     * @return Ajax信息
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Object> deleteDormitoryManagerForAjax(@PathVariable String id) {
        try {
            Map<String, Object> jsonMap = new HashMap<>(3);
            dormitoryManagerService.delete((Serializable) id);
            jsonMap.put("state", 1);
            jsonMap.put("message", "删除社区楼长成功！");
            return jsonMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException("删除社区楼长失败！", e);
        }
    }

    /**
     * 导入楼长信息进系统
     *
     * @param session       session对象
     * @param request       HTTP请求对象
     * @param subdistrictId 导入的街道编号
     * @return Ajax信息
     */
    @PostMapping("/import")
    @ResponseBody
    public Map<String, Object> dormitoryManagerImportAsSystem(HttpSession session, HttpServletRequest request, @RequestParam Long subdistrictId) {
        Map<String, Object> jsonMap = new HashMap<>(3);
        try {
            Workbook workbook = uploadExcel(request, session, "excel_dormitory_title");
            dormitoryManagerService.create(workbook, subdistrictId, configurationsMap);
            jsonMap.put("state", 1);
            jsonMap.put("message", "上传成功！");
            return jsonMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException("上传文件失败！", e);
        }
    }

    /**
     * 导出社区楼长信息到Excel
     *
     * @param session  session对象
     * @param response 前台响应对象
     * @param data     传递数据
     */
    @GetMapping("/download")
    public void dormitoryManagerSaveAsExcel(HttpSession session, HttpServletResponse response, @RequestParam String data) {
        try {
            List<Map<String, Object>> userData = getDecodeData(session, data);
            // 获取属性-列头
            Map<String, String> headMap = dormitoryManagerService.findPartStatHead();
            String excelDormitoryTitleUp = CommonUtils.convertConfigurationString(configurationsMap.get("excel_dormitory_title_up"));
            String excelDormitoryTitle = CommonUtils.convertConfigurationString(configurationsMap.get("excel_dormitory_title"));
            ExcelUtils.DataHandler excelDataHandler = dormitoryManagerService.getExcelDataHandler();
            // 获取业务数据集
            JSONArray dataJson = dormitoryManagerService.findCorrelation(communityCompanyType, subdistrictCompanyType, userData, new String[]{excelDormitoryTitleUp, excelDormitoryTitle});
            ByteArrayOutputStream byteArrayOutputStream = ExcelUtils.exportExcelX(excelDormitoryTitle, headMap, dataJson, 0, excelDataHandler);
            ExcelUtils.downloadExcelFile(response, request, dormitoryManagerService.getFileTitle(), byteArrayOutputStream);
        } catch (Exception e) {
            setCookieError(request, response);
            e.printStackTrace();
            throw new BusinessException("导出Excel文件失败！");
        }
    }

    /**
     * 使用AJAX技术加载社区楼长列表
     *
     * @param session     Session对象
     * @param page        分页页码
     * @param isSearch    是否搜索
     * @param companyId   单位编号
     * @param companyType 单位类别编号
     * @param name        社区楼长姓名
     * @param sex         社区楼长性别
     * @param address     社区楼长家庭地址
     * @param phone       社区楼长联系方式
     * @return Ajax消息
     */
    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> findDormitoryManagersForAjax(HttpSession session, Integer page, Boolean isSearch, Long companyId, Integer companyType, String name, Integer sex, String address, String phone) {
        getSessionRoleId(session);
        Map<String, Object> dormitoryManagerMap;
        try {
            if (isSearch != null && isSearch) {
                DormitoryManager dormitoryManager = new DormitoryManager();
                dormitoryManager.setName(name);
                dormitoryManager.setSex(sex);
                dormitoryManager.setAddress(address);
                dormitoryManager.setPhones(phone);
                dormitoryManagerMap = dormitoryManagerService.find(systemUser, systemCompanyType, communityCompanyType, subdistrictCompanyType, dormitoryManager, companyId, companyType, page, null);
            } else {
                dormitoryManagerMap = dormitoryManagerService.findCorrelation(systemUser, communityCompanyType, subdistrictCompanyType, page, null);
            }
            return setJsonMap(dormitoryManagerMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException("查找社区楼长失败！", e);
        }
    }

    /**
     * 通过社区编号加载最后一个编号
     *
     * @param communityId     社区编号
     * @param communityName   社区名称
     * @param subdistrictName 街道办事处名称
     * @return JSON数据
     */
    @PostMapping("/last_id")
    @ResponseBody
    public Map<String, Object> loadDormitoryManagerLastIdForAjax(@RequestParam Long communityId, String communityName, String subdistrictName) {
        Map<String, Object> jsonMap = new HashMap<>(3);
        try {
            String lastId = dormitoryManagerService.find(communityId, communityName, subdistrictName);
            jsonMap.put("state", 1);
            jsonMap.put("id", lastId);
            return jsonMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException("加载编号失败！", e);
        }
    }
}
