<%@page import="demo.TalkServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Talk</title>
		<script src="jquery.js"></script>
		<script>
			$(document).ready(function() {
				setTimeout('location.reload()', 2000);
			});
		</script>
	</head>
	<body>
		<fieldset>
			<legend>Messages</legend>
			<ul>
			<% for (String message : TalkServlet.getMessages()) { %>
				<li>
					<%= message %>
				</li>
			<% } %>
			</ul>
		</fieldset>
	</body>
</html>