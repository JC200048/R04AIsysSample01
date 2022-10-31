<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sentiment</title>
</head>
<%
float message1 = (float) request.getAttribute("message1");
float message2 = (float) request.getAttribute("message2");
float message3 = (float) request.getAttribute("message3");
%>
<%
String string = request.getParameter("string");
%>

<body>


<h1>Sentiment</h1>
<h2>入力した文字列</h2>
<h3><%= string %></h3>
<h2>結果</h2>
<h3>Negative:<%= message1%></h3>
<h3>Neutral<%= message2%></h3>
<h3>Positive<%= message3%></h3>
</body>
</html>