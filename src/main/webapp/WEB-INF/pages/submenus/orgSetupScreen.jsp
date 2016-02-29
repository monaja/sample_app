<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


 <div class="col-md-6 col-sm-6 col-xs-12">
       <div class="info-box">
         <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>
         <div class="info-box-content">
           <span class="info-box-text">Organization Setup</span>
           <span class="info-box-number">
           <small class="text-muted">Define System Organization Details</small>
           </span>
            <a href="<c:url value="/protected/organization/"/>" class="btn btn-primary">Edit</a>
         </div><!-- /.info-box-content -->
       </div><!-- /.info-box -->
     </div>
      <div class="col-md-6 col-sm-6 col-xs-12">
       <div class="info-box">
         <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>
         <div class="info-box-content">
           <span class="info-box-text">Entities Definition</span>
           <span class="info-box-number">
           <small class="text-muted">Define Clients,Agents and Service Providers</small>
           </span>
            <a href="<c:url value="/protected/entities/"/>" class="btn btn-primary">Define</a>
         </div><!-- /.info-box-content -->
       </div><!-- /.info-box -->
     </div>
    
            
           