<%@page import="config.UserDetails"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="org.redisson.api.RedissonClient"%>
<%@page import="config.AppProperties"%>
<%@page import="org.redisson.api.RMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>success</title>
</head>
<body>
		<h3>  User: <%= request.getAttribute("loggedInUser") %>  is successfully logged in using  <%= request.getAttribute("src")  %></h3>
</body>
</html>