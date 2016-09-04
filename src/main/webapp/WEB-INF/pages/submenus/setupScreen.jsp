<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

   <div class="col-md-6 col-sm-6 col-xs-12">
       <div class="info-box">
         <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>
         <div class="info-box-content">
           <span class="info-box-text">Account Types</span>
           <span class="info-box-number">
           <small class="text-muted">Define Account Types</small>
           </span>
            <a href="<c:url value="/protected/setups/accttypes"/>" class="btn btn-primary">Define</a>
         </div><!-- /.info-box-content -->
       </div><!-- /.info-box -->
     </div>
     <div class="col-md-6 col-sm-6 col-xs-12">
       <div class="info-box">
         <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>
         <div class="info-box-content">
           <span class="info-box-text">Accounts</span>
           <span class="info-box-number">
           <small class="text-muted">Define Accounts</small>
           </span>
            <a href="<c:url value="/protected/organization/"/>" class="btn btn-primary">Define</a>
         </div><!-- /.info-box-content -->
       </div><!-- /.info-box -->
     </div>
     <div class="col-md-6 col-sm-6 col-xs-12">
       <div class="info-box">
         <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>
         <div class="info-box-content">
           <span class="info-box-text">Tenants</span>
           <span class="info-box-number">
           <small class="text-muted">Define Tenant Details</small>
           </span>
            <a href="<c:url value="/protected/organization/"/>" class="btn btn-primary">Define</a>
         </div><!-- /.info-box-content -->
       </div><!-- /.info-box -->
     </div>
