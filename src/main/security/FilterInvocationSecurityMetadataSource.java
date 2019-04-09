package security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import www.entity.UserPrivilege;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 加载验证权限数据
 *
 * @author 廿二月的天
 */
public class FilterInvocationSecurityMetadataSource implements org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource {

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpSession session = filterInvocation.getHttpRequest().getSession();
        Set<UserPrivilege> userPrivileges = (Set<UserPrivilege>) session.getAttribute("userPrivileges");
        if (userPrivileges != null) {
            String url = filterInvocation.getRequestUrl();
            int firstQuestionMarkIndex = url.indexOf("?");
            if (firstQuestionMarkIndex != -1) {
                url = url.substring(0, firstQuestionMarkIndex);
            }
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            for (UserPrivilege userPrivilege : userPrivileges) {
                if (url.equals(userPrivilege.getUri())) {
                    SecurityConfig securityConfig = new SecurityConfig(userPrivilege.getUri());
                    configAttributes.add(securityConfig);
                    return configAttributes;
                }
            }
        }
        return null;
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
