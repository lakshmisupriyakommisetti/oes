<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


</head>
<body>

	<form action="TestController" method="post">
		"${map.get(current_qno.toString())}"
		${questionsList[current_qno-1].questionId})
		<c:if test="${questionsList[current_qno-1].description.isImage}">
			<img alt="" src="${questionsList[current_qno-1].description.text}">
		</c:if>
		<c:if test="${ ! questionsList[current_qno-1].description.isImage}">
    	${questionsList[current_qno-1].description.text}
    </c:if>
		<c:if test="${questionsList[current_qno-1].isMultiAnswered}">
			<c:set var="i" value="${0}" />
			<c:set var="str" value="${map.get(current_qno.toString())}"></c:set>
			<c:set var="optarr" value="${str.toCharArray()}" />


			<c:forEach items="${questionsList[current_qno-1].options}"
				var="option" begin="0">
				<br>
				<c:if test="${ questionsList[current_qno-1].selectedAnswer != 0 }">
					<c:if test="${optarr[i].toString() eq '1'}">
						<input type="checkbox" name="c${current_qno}" value="${i}"
							checked="checked">
					</c:if>
					<c:if test="${optarr[i].toString() eq '0'}">
						<input type="checkbox" name="c${current_qno}" value="${i}">
					</c:if>
				</c:if>
				<c:if test="${ questionsList[current_qno-1].selectedAnswer == 0 }">
					<input type="checkbox" name="c${current_qno}" value="${i}">
				</c:if>


				<c:if test="${option.isImage}">
					<img alt="" src="${option.text}">
				</c:if>

				<c:if test="${ ! option.isImage}">
			${option.text}
			
		</c:if>
				<c:set var="i" value="${i+1}" />
			</c:forEach>
		</c:if>

		<c:if test="${!questionsList[current_qno-1].isMultiAnswered}">
			<c:set var="i" value="${0}" />
			<c:set var="str" value="${map.get(current_qno.toString())}"></c:set>
			<c:set var="optarr" value="${str.toCharArray()}" />
			<c:forEach items="${questionsList[current_qno-1].options}"
				var="option" begin="0">
				<br>
				<c:if test="${ questionsList[current_qno-1].selectedAnswer != 0 }">
					<c:if test="${optarr[i].toString() eq '1'}">
						<input type="radio" name="r${current_qno}" value="${i}" checked>
					</c:if>
					<c:if test="${optarr[i].toString() eq '0'}">
						<input type="radio" name="c${current_qno}" value="${i}">
					</c:if>
				</c:if>
				<c:if test="${ questionsList[current_qno-1].selectedAnswer == 0 }">
					<input type="radio" name="r${current_qno}" value="${i}">
				</c:if>


				<c:if test="${option.isImage}">
					<img alt="" src="${option.text}">
				</c:if>

				<c:if test="${ ! option.isImage}">
					${option.text}
				</c:if>
				<c:set var="i" value="${i+1}" />
			</c:forEach>
		</c:if>


		<c:if test="${current_qno == 1}">
			<br>
			<input type="submit" name="link" value="next->">
		</c:if>
		<c:if
			test="${current_qno != 1 && current_qno != questionsList.size()}">
			<br>
			<input type="submit" name="link" value="<-prev">
			<br>
			<input type="submit" name="link" value="next->">
		</c:if>
		<c:if test="${current_qno == questionsList.size()}">
			<br>
			<input type="submit" name="link" value="<-prev">
			<br>
			<input type="submit" name="link" value="Finish">
		</c:if>
		<c:forEach var="i" begin="1" end="${questionsList.size()}">
			<input type="submit" name="link" value="${i}">
		</c:forEach>





	</form>

</body>
</html>