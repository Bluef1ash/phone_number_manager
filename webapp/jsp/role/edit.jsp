<%@ page language="java" pageEncoding="utf-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/jsp/layouts/header.jsp" %>
		<title>修改角色 - 角色管理 - 社区居民联系电话管理系统</title>
	</head>
	<body>
        <c:if test="${messageErrors != null}">
            <c:forEach items="${messageErrors}" var="error">
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <span>${error.defaultMessage}</span>
                </div>
            </c:forEach>
        </c:if>
		<form action="${pageContext.request.contextPath}/system/user_role/role/handle.action" method="post">
			<table class="table table-bordered font-size-14">
				<thead></thead>
				<tbody>
					<tbody>
					<tr>
						<td class="text-right" style="width: 35%;">角色名称</td>
						<td>
							<input type="text" name="roleName" value="${userRole.roleName}" class="form-control" placeholder="请输入角色名">
						</td>
					</tr>
					<tr>
						<td class="text-right">角色描述</td>
						<td>
							<input type="text" name="roleDescription" value="${userRole.roleDescription}" class="form-control" placeholder="请输入描述">
						</td>
					</tr>
					<tr>
						<td class="text-right">上级角色</td>
						<td>
							<select name="higherRole" class="form-control">
								<option value="0"<c:if test="${userRole.roleId == 0}"> selected</c:if>>无</option>
								<c:forEach items="${userRoles}" var="uRole">
									<c:if test="${uRole.roleId != userRole.roleId}">
										<option value="${uRole.roleId}"<c:if test="${userRole.higherRole == uRole.roleId}"> selected</c:if>>${uRole.roleName}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="text-right">角色权限</td>
						<td>
							<input type="hidden" name="privilegeIds" value="0">
							<c:forEach items="${userPrivileges}" var="userPrivilege">
								<p class="privilege-checkbox">
									<label class="checkbox-inline">
										<input type="checkbox" name="privilegeIds" value="${userPrivilege.privilegeId}">${userPrivilege.privilegeName}
									</label>
									<c:if test="${userPrivilege.subUserPrivileges != null}">
										(
										<c:forEach items="${userPrivilege.subUserPrivileges}" var="subUserPrivilege">
											<label class="checkbox-inline">
												<input type="checkbox" name="privilegeIds" value="${subUserPrivilege.privilegeId}" data-pid="${userPrivilege.privilegeId}">${subUserPrivilege.privilegeName}
											</label>
										</c:forEach>
										)
									</c:if>
								</p>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<input type="hidden" name="roleId" value="${userRole.roleId}">
							<input type="hidden" name="_token" value="${_token}">
                            <input type="hidden" name="_method" value="PUT">
							<spring:htmlEscape defaultHtmlEscape="true" />
							<input type="submit" value="保存" class="btn btn-primary">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<script type="text/javascript">
            require(["jquery", "bootstrap"], function () {
                $(function () {
                    var arr = [11];
                    // 获取系统用户角色拥有的权限
                    $.get("${pageContext.request.contextPath}/system/user_role/privilege/ajax_get_privileges.action", {"roleId" : ${userRole.roleId}}, function (data) {
                        if (data !== null) {
                            for (i = 0; i < data.privileges.length; i++) {
                                $("input[name='privilegeIds'][value='" + data.privileges[i].privilegeId + "']").prop("checked", true);
                            }
                        }
                    });
                    // 打开页面时，选中已选中项
                    var pid = [];
                    $("input[name='privilegeIds']").each(function (index, element) {
                        pid[index] = $(element).val();
                        if (pid.toString().indexOf($(element).data("pid")) != -1 && arr.toString().indexOf($(element).val()) == -1) {
                            $(element).attr({"checked": false, "disabled": true});
                        }
                    });
                    // 子权限启用与禁用
                    $("input[name='privilegeIds']").change(function () {
                        var privilege_id = $(this).val();
                        if ($(this).prop("checked") == true) {
                            $("input[name='privilegeIds']").each(function (index, element) {
                                if ($(element).data("pid") == privilege_id && arr.toString().indexOf($(element).val()) == -1) {
                                    $(element).attr({"checked": false, "disabled": true});
                                }
                            });
                        } else {
                            $("input[name='privilegeIds']").each(function (index, element) {
                                if ($(element).data("pid") == privilege_id && arr.toString().indexOf($(element).val()) == -1) {
                                    $(element).attr("disabled", false);
                                }
                            });
                        }
                    });
                });
            });
		</script>
	</body>
</html>
