<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/jsp/layouts/header.jsp" />
		<title>修改社区居民 - 社区居民管理 - 社区居民联系电话管理系统</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/resident/handle.action" method="post">
			<table class="table table-bordered font-size-14">
				<thead>
					<c:if test="${messageErrors != null}">  
					    <c:forEach items="${messageErrors}" var="error">  
					        <span style="color:red">${error.defaultMessage}</span><br/>  
					    </c:forEach>
					</c:if>
				</thead>
				<tbody>
					<tr>
						<td class="text-right" style="width: 35%;">社区居民姓名</td>
						<td>
							<input type="text" name="communityResidentName" class="form-control" placeholder="请输入社区居民姓名" value="${communityResident.communityResidentName}">
						</td>
					</tr>
					<tr>
						<td class="text-right">社区居民家庭地址</td>
						<td>
							<input type="text" name="communityResidentAddress" class="form-control" placeholder="请输入社区居民家庭地址" value="${communityResident.communityResidentAddress}">
						</td>
					</tr>
					<tr>
						<td class="text-right">社区居民联系方式一</td>
						<td>
							<input type="text" name="residentPhones[]" class="form-control" placeholder="请输入社区居民联系方式一" value="${residentPhones[0]}">
						</td>
					</tr>
					<tr>
						<td class="text-right">社区居民联系方式二</td>
						<td>
							<input type="text" name="residentPhones[]" class="form-control" placeholder="请输入社区居民联系方式二" value="${residentPhones[1]}">
						</td>
					</tr>
					<tr>
						<td class="text-right">社区居民联系方式三</td>
						<td>
							<input type="text" name="residentPhones[]" class="form-control" placeholder="请输入社区居民联系方式三" value="${residentPhones[2]}">
						</td>
					</tr>
					<tr>
						<td class="text-right">社区分包人</td>
						<td>
							<input type="text" name="communityResidentSubcontractor" class="form-control" placeholder="请输入社区居民联系方式三" value="${communityResident.communityResidentSubcontractor}">
						</td>
					</tr>
					<tr>
						<td class="text-right">所属社区</td>
						<td>
							<select name="communityId" class="form-control">
								<option value="0">请选择</option>
								<c:forEach items="${communities}" var="communitiy">
									<option value="${communitiy.communityId}"<c:if test="${communitiy.communityId == communityResident.community.communityId}"> selected</c:if>>${communitiy.communityName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<input type="hidden" name="communityResidentId" value="${communityResident.communityResidentId}">
							<input type="hidden" name="submissionToken" value="${submissionToken}">
							<input type="submit" value="保存" class="btn btn-primary">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>