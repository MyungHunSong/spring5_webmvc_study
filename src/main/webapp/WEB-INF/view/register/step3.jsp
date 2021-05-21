<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
</head>
<body>
		<p><spring:message code="register.done">
				<spring:argument value="${registerRequest.name}"/>
				<spring:argument value="${registerRequest.email}"/>
		</spring:message>
		<p><a href = "<c:url value = '/main'/>">[<spring:message code="go.main"/>]</a></p>
</body>
</html>