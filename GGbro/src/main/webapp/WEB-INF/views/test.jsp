<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <c:if test="${userId == null}">
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=1b99bdb2b2d8b2b7b7f7c7ee8f97842b&redirect_uri=http://localhost:8181/ggbro/kakaoLogin&response_type=code">
            <img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg">
        </a>
    </c:if>
    <c:if test="${userId != null}">
        <h1>로그인 성공입니다</h1>
           <img src="${image}"><br>
           <p>ID: ${userId}</p>
        
         <input type="button" value="로그아웃" onclick="location.href='/ggbro/logout'">
    </c:if>

</body>
</html>