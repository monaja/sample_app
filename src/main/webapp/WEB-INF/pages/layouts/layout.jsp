<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<tiles:insertAttribute name="header"></tiles:insertAttribute>

<body class="hold-transition skin-blue sidebar-mini">

      <div class="wrapper">
         <tiles:insertAttribute name="head" />
         <tiles:insertAttribute name="menu" />
         
           <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            <tiles:getAsString name="title"/>
          </h1>
          
        </section>

        <!-- Main content -->
        <section class="content">
            <tiles:insertAttribute name="body" />
        </section>
        </div>
      </div>
</body>


</html>
