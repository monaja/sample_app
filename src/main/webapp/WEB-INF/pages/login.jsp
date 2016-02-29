
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

     
    <jsp:include page="./head.jsp"/>

<body class="hold-transition login-page">


    <div class="login-box">
      <div class="login-logo">
        <spring:message code='project.name'/>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>
        
	         <div id="login-error" class="alert alert-danger alert-dismissible">
            <spring:message code="login.invalid.username.or.password" />
        </div>
		
        <form action="j_spring_security_check" method="post"  novalidate="novalidate" autocomplete="off">
          <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="Username" name="j_username">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="Password" name="j_password">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat"><spring:message code="login.signIn" /></button>
            </div><!-- /.col -->
          </div>
        </form>

       
<script type="text/javascript" src="<c:url value="/js/modules/login/login.js"/>"></script>
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
</body>
</html>