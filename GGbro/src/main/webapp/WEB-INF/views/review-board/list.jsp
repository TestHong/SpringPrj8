<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../include/header.jsp" />
<style>
header.masthead {
	
	display: none;
}	
</style>
<br><br>

	<!-- Article Grid -->
	<div class="container">
	  <h2 class="page-header"><span style="color: #ff52a0;">IZONE</span> 리뷰 게시판
			<a href="<c:url value='/review-board/write'/>" class="btn float-right" style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">글쓰기</a>
	  </h2>
	  <section class="bg-light" id="portfolio" style="padding-top: 90px; padding-bottom: 0;">
	    <div class="container">		  
	      <div class="row">
	      
	      <c:forEach var="b" items="${articles}">
	        <div class="col-md-4 portfolio-item">
	          <a class="portfolio-link" data-toggle="modal" href="#">
	            <div class="portfolio-hover"></div>
		        <c:set var="len" value="${fn:length(b.fileName)}"/>
				<c:set var="filetype" value="${fn:toUpperCase(fn:substring(b.fileName, len-4, len))}"/>
				<c:if test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.PNG') or (filetype eq '.GIF')}">				
		            <img class="img-thumbnail img-fluid" src="<c:url value='/review-board/file/${b.fileId}'/>" alt="" style="width:100%; height:400px;">		        
		        </c:if>
	          </a>
	          <div class="portfolio-caption">
	            <h4>${b.title}</h4>
	            <p class="text-muted"><fmt:formatDate pattern="yyyy-MM-dd" value="${b.writeDate}" /></p>
	          </div>
	        </div>
	       </c:forEach> 
	        
	        
	       </div>
	    </div>
	  </section>
	</div>

	
				
<jsp:include page="../include/footer.jsp" />