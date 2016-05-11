<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Pessoa</title>
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/bootstrap.css">
	</head>
<body class="container">

	<jsp:include page="page-header.jsp"></jsp:include>

	<div class="page-header">
		<h1>Pessoa</h1>
	</div>
	
	<form action="${ pageContext.request.contextPath }/pessoa" method="post"> 
		<div class="row">
			<div class="col-md-2">
				<div class="form-group">
			    	<label for="inputID">ID:</label>
			    	<input type="text" class="form-control" value="${ pessoa.id }" name="id" id="inputID" readonly="readonly">
			  	</div>
			</div>
			<div class="col-md-10">
			  	<div class="form-group">
			    	<label for="inputNome">Nome:</label>
			    	<input type="text" class="form-control" value="${ pessoa.nome }" name="nome" id="inputNome" placeholder="Nome...">
			  	</div>
			</div>
		</div>
  		<button type="submit" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Save</button>
	</form>
	
	<table class="table table-hover table-condensed table-striped">
		<thead>
			<tr>
				<th class="col-md-1 text-center">ID</th>
				<th>Nome</th>
				<th class="col-md-1 text-center">
					<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="pessoa" items="${ pessoas }">
				<tr>
					<td class="col-md-1 text-center">
						<a href="${ pageContext.request.contextPath }/pessoa/${ pessoa.id }">${ pessoa.id }</a>
					</td>
					<td>
						<a href="${ pageContext.request.contextPath }/pessoa/${ pessoa.id }">${ pessoa.nome }</a>
					</td>
					<td class="col-md-1 text-center">
						<a href="${ pageContext.request.contextPath }/pessoa/${ pessoa.id }">
							<span class="glyphicon glyphicon-pencil text-success" aria-hidden="true"></span>
						</a>
						<a href="${ pageContext.request.contextPath }/pessoa/delete/${ pessoa.id }">
							<span class="glyphicon glyphicon-trash text-danger" aria-hidden="true"></span>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.js"></script>
	<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/bootstrap.js"></script>
		
</body>
</html>