package la.isx.phone_number_manager.main.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import la.isx.phone_number_manager.annotation.SystemUserAuth;
import la.isx.phone_number_manager.exception.BusinessException;
import la.isx.phone_number_manager.main.entity.Subdistrict;
import la.isx.phone_number_manager.main.service.SubdistrictService;

/**
 * 街道控制器
 *
 */
@Controller
@SystemUserAuth
@RequestMapping("/subdistrict")
public class SubdistrictAction {
	@Resource
	private SubdistrictService subdistrictService;
	/**
	 * 街道列表
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String subdistrictList(Model model, Integer page) {
		try {
			Map<String, Object> subdistricts = subdistrictService.findObjects(page, null);
			model.addAttribute("subdistricts", subdistricts.get("data"));
			model.addAttribute("pageInfo", subdistricts.get("pageInfo"));
		} catch (Exception e) {
			throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
		}
		return "subdistrict/list";
	}
	/**
	 * 添加街道
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createSubdistrict() {
		return "subdistrict/create";
	}
	/**
	 * 编辑街道
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editSubdistrict(Model model, Integer id) {
		try {
			Subdistrict subdistrict = subdistrictService.findObject(id);
			model.addAttribute("subdistrict", subdistrict);
		} catch (Exception e) {
			throw new BusinessException("系统异常！找不到数据，请稍后再试！", e);
		}
		return "subdistrict/edit";
	}
	/**
	 * 添加、修改街道处理
	 * @param model
	 * @param subdistrict
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/handle", method = RequestMethod.POST)
	public String subdistrictCreateOrEditHandle(Model model, @Validated Subdistrict subdistrict, BindingResult bindingResult) {
		if (subdistrict.getSubdistrictId() == null) {
			if (bindingResult.hasErrors()) {
				// 输出错误信息
				List<ObjectError> allErrors = bindingResult.getAllErrors();
				model.addAttribute("messageErrors", allErrors);
				return "subdistrict/edit";
			}
			try {
				subdistrictService.createObject(subdistrict);
			} catch (Exception e) {
				throw new BusinessException("添加街道失败！", e);
			}
		} else {
			try {
				subdistrictService.updateObject(subdistrict);
			} catch (Exception e) {
				throw new BusinessException("修改街道失败！", e);
			}
		}
		return "redirect:/subdistrict/list.action";
	}
	/**
	 * 使用AJAX技术通过街道ID删除街道
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax_delete", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> deleteSubdistrictForAjax(Integer id) {
		Map<String, Object> map = null;
		try {
			subdistrictService.deleteObjectById(id);
			map = new HashMap<String, Object>();
			map.put("message", 1);
			return map;
		} catch (Exception e) {
			throw new BusinessException("删除街道失败！", e);
		}
	}
}