package main.validator;

import main.entity.CommunityResident;
import main.service.CommunityResidentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 社区居民添加/更新验证
 */
public class CommunityResidentInputValidator implements Validator {
    private String message;
    private String field;
    private CommunityResidentService communityResidentService;

    public CommunityResidentInputValidator() {}

    public CommunityResidentInputValidator(CommunityResidentService communityResidentService) {
        this.communityResidentService = communityResidentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CommunityResident.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            ValidationUtils.rejectIfEmpty(errors, "communityResidentName", "communityResident.communityResidentName.required", "社区居民姓名不能为空！");
            ValidationUtils.rejectIfEmpty(errors, "communityResidentAddress", "communityResident.communityResidentAddress.required", "社区居民地址不能为空！");
            ValidationUtils.rejectIfEmpty(errors, "communityResidentSubcontractor", "communityResident.communityResidentSubcontractor.required", "社区分包人不能为空！");
            CommunityResident communityResident = (CommunityResident) target;
            if (!checkInput(communityResident)) {
                errors.rejectValue(field, null, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证输入数据
     *
     * @param communityResident
     * @return
     */
    private Boolean checkInput(CommunityResident communityResident) throws Exception {
        // 验证姓名+地址重复
        String nameAddress = communityResident.getCommunityResidentName() + communityResident.getCommunityResidentAddress();
        List<CommunityResident> isNameAndAddressRepeat = communityResidentService.findCommunityResidentByNameAndAddress(nameAddress);
        if (!checkedListData(isNameAndAddressRepeat, false)) {
            return false;
        }
        List<String> phones = new ArrayList<String>();
        if (StringUtils.isNotEmpty(communityResident.getCommunityResidentPhone1())) {
            phones.add(communityResident.getCommunityResidentPhone1());
        }
        if (StringUtils.isNotEmpty(communityResident.getCommunityResidentPhone2())) {
            phones.add(communityResident.getCommunityResidentPhone2());
        }
        if (StringUtils.isNotEmpty(communityResident.getCommunityResidentPhone3())) {
            phones.add(communityResident.getCommunityResidentPhone3());
        }
        field = "communityResidentPhones";
        // 联系方式合法
        if (!checkedPhone(phones)) {
            return false;
        }
        // 联系方式重复
        List<CommunityResident> isPhonesRepeat = communityResidentService.findCommunityResidentByPhones(phones);
        if (!checkedListData(isPhonesRepeat, true)) {
            return false;
        }
        return true;
    }

    /**
     * 验证联系方式是否合法
     *
     * @return
     */
    private boolean checkedPhone(List<String> residentPhones) {
        Pattern regex = Pattern.compile("(?iUs)^(?:[\\(（]?(?:[0-9]{3,4})?\\s*[\\)）-]?\\s*[0-9]{7,9}\\s*[-转]?\\s*(?:[0-9]{2,6})?)|(?:[\\(]?[\\+]?[\\(]?(?:86)?[\\)]?0?1[3458]{1}[0-9]{9})$");
        Matcher matcher = null;
        for (String residentPhone : residentPhones) {
            // 验证固定电话
            matcher = regex.matcher(residentPhone);
            if (!matcher.matches()) {
                message = "输入的联系方式不合法，请检查后重试！";
                return false;
            }
        }
        return true;
    }

    /**
     * 验证数据库返回数据是否为空
     *
     * @param isNameAndAddressRepeat
     * @param isCheckedPhone
     * @return
     */
    private boolean checkedListData(List<CommunityResident> isNameAndAddressRepeat, Boolean isCheckedPhone) {
        StringBuilder communityNames = new StringBuilder();
        if (isNameAndAddressRepeat.size() > 0) {
            String checkType = null;
            if (isCheckedPhone) {
                checkType = "联系方式";
                field = "communityResidentPhones";
                for (int i = 0; i < isNameAndAddressRepeat.size(); i++) {
                    if (i > 0) {
                        communityNames.append("，");
                    }
                    communityNames.append(isNameAndAddressRepeat.get(i).getCommunity().getCommunityName()).append("的").append(isNameAndAddressRepeat.get(i).getCommunityResidentName()).append("居民");
                }
            } else {
                field = "communityResidentName";
                checkType = "社区居民";
                for (int i = 0; i < isNameAndAddressRepeat.size(); i++) {
                    if (i > 0) {
                        communityNames.append("，");
                    }
                    communityNames.append(isNameAndAddressRepeat.get(i).getCommunity().getCommunityName());
                }
            }
            message = "该" + checkType + "已经存在，现存在于" + communityNames.toString();
            return false;
        }
        return true;
    }
}
