<%@page import="com.js.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% Product p=(Product)request.getAttribute("pro");%>
<h1>UPDATE YOUR DATA</h1>
<form action="update" method="insert">
ID:<input type="number" name="id" value="<%=p.getId()%>"readonly="readonly"> <br> <br>
Name:<input type="text" name="name"value="<%=p.getName()%>"> <br> <br>
Brand:<input type="text" name="brand"value="<%=p.getBrand()%>"> <br> <br>
Price:<input type="number" name="price"value="<%=p.getPrice()%>"> <br> <br>
Quantity:<input type="number" name="quantity"value="<%=p.getQuantity()%>"> <br> <br>
       <input type="submit" value="update">
