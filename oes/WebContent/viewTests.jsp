
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.svecw.oes.dto.*"%>
	<%@ page import="com.svecw.oes.dao.*"%>
<%@ page import="java.util.*"%>

<form action="HomeController" method="post">
	<%!TestDAO vt = new TestDAO();%>
	<%
		List<Test> test = vt.getTests();
	%>
	/*<table border = 2>
		<tr>
			<th>TestId</th>
			<th>TestName</th>
			<th>StartTime</th>
			<th>EndTime</th>
			<th>ViewProfile</th>
		</tr>*/
		
			<%
				for (Test i : test) {
			%>
		
		/*<tr>
			<th><%=i.getTestId()%></th>
			<th><%=i.getName()%></th>
			<th><%=i.getStartDate()%></th>
			<th>
				<%=
					i.getEndDate()
				%>
			</th>
			<th><input type="button" value="viewreport" id="viewreport"
				name="viewreport"></th>
		</tr>
       
		<%
			}
		%>
	</table>*/




</form>