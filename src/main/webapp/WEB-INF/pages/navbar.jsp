<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">SYSTEM NAVIGATION</li>
            <li>
              <a href="<c:url value="/protected/home"/>">
                <i class="fa fa-dashboard"></i> <span>Dashboard</span> <i class="pull-right"></i>
              </a>
            </li>
            
             <li>
              <a href="<c:url value="/protected/home/orgsetups"/>">
                <i class="fa fa-cog"></i> <span>Company Setups</span> <i class="pull-right"></i>
              </a>
            </li>
            
             <li>
              <a href="<c:url value="/protected/home/setups"/>">
                <i class="fa fa-cog"></i> <span>Tenant/Account Setups</span> <i class="pull-right"></i>
              </a>
              
            </li>
            <li>
              <a href="<c:url value="/protected/home/setups"/>">
                <i class="fa fa-cog"></i> <span>Tenant Transactions</span> <i class="pull-right"></i>
              </a>
              
            </li>
            
             
            
          
            
            </ul>
        </section>
        <!-- /.sidebar -->
      </aside>





<div class="alert alert-success alert-dismissible fade in" role="alert" id="success-alert"
	style="display: none;">
	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	<strong><s:message code="alert.success" />!</strong>
	<div id="message"></div>
</div>
<div class="alert alert-warning alert-dismissible fade in" role="alert" id="warning-alert"
	style="display: none;">
	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	<strong><s:message code="alert.warning" />!</strong>
	<div id="message"></div>
</div>
<div class="alert alert-danger alert-dismissible fade in" role="alert" id="error-alert"
	style="display: none;">
	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	<strong><s:message code="alert.error" />!</strong>
	<div id="message"></div>
</div>


