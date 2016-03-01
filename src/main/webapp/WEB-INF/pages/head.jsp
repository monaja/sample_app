<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

    
<head>
   <title><spring:message  code="project.title" /></title>
 <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width">        
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.5 -->
         <link rel="stylesheet" href="<c:url value='/libs/css/bootstrap.min.css' /> ">
	    <!-- Font Awesome -->
	    <link rel="stylesheet" href="<c:url value='/libs/css/font-awesome.min.css' /> ">
	    <!-- Ionicons -->
	    <link rel="stylesheet" href="<c:url value='/libs/css/ionicons.min.css' /> ">
	    <!-- Theme style -->
	    <link rel="stylesheet" href="<c:url value='/libs/AdminLTE.css' /> ">
	    
	    <link rel="stylesheet" href="<c:url value='/libs/skins/_all-skins.min.css' /> ">
	    <!-- iCheck -->
	    <link rel="stylesheet" href="<c:url value='/libs/iCheck/flat/blue.css' /> ">
	    
	    <link rel="stylesheet" href="<c:url value='/libs/css/validation.css' /> ">
	    
	     <link href="<c:url value="/libs/select2/select2.css"/>" rel="stylesheet">
		<link href="<c:url value="/libs/select2/select2-bootstrap.css"/>" rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="<c:url value="/libs/bootstrap/select/css/bootstrap-select.min.css"/>" >
	    
	    <!-- File Input css -->
        <link href="<c:url value='/libs/fileinput.min.css' /> " rel="stylesheet"/>
        
        <!-- Jquery UI Css -->
        <link href="<c:url value='/libs/jquery-ui-1.10.4.custom.min.css' /> " rel="stylesheet"/>
        
	   <script src="<c:url value='/libs/js/jquery-1.11.1.js' /> "></script>
	   <script src="<c:url value='/libs/js/bootstrap.min.js' /> "></script>
	   
	  <script src="<c:url value="/libs/jquery-validation/jquery.validate.min.js"/>"></script>
	  <script src="<c:url value="/libs/jquery-validation/additional-methods.min.js"/>"></script>
	  <script src="<c:url value='/libs/js/jquery.ui.datepicker.js' /> "></script>
	  <script src="<c:url value='/libs/js/fileinput.min.js' /> "></script>
	  <script type="text/javascript" src="<c:url value="/libs/js/bootbox.min.js"/>"></script>
	  <script src="<c:url value='/libs/js/app.min.js' /> "></script>
	   <link rel="stylesheet" type="text/css" href="<c:url value='/libs/DataTables/media/css/dataTables.bootstrap.css' />">
	   
	  <script type="text/javascript" src="<c:url value='/libs/DataTables/media/js/jquery.dataTables.min.js' /> "></script>
	  
	 <script type="text/javascript" src="<c:url value='/libs/DataTables/media/js/dataTables.bootstrap.js' /> "></script>
	 <script type="text/javascript" src="<c:url value="/libs/bootstrap/select/js/bootstrap-select.min.js"/>"></script>
	 <script src="<c:url value="/libs/select2/select2.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
	 
	
</head>
