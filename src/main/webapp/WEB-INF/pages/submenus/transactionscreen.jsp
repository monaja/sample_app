<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


 <div class="col-md-6 col-sm-6 col-xs-12">
       <div class="info-box">
         <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>
         <div class="info-box-content">
           <span class="info-box-text">Tenant Invoice</span>
           <span class="info-box-number">
           <small class="text-muted">Create Tenants Invoices</small>
           </span>
            <a href="<c:url value="/protected/transactions/invoices/invlist"/>" class="btn btn-primary">Create</a>
         </div><!-- /.info-box-content -->
       </div><!-- /.info-box -->
     </div>
     <div class="col-md-6 col-sm-6 col-xs-12">
       <div class="info-box">
         <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>
         <div class="info-box-content">
           <span class="info-box-text">Revise an Invoice</span>
           <span class="info-box-number">
           <small class="text-muted">Revise Tenant Invoices Transactions</small>
           </span>
            <a href="<c:url value="/protected/rental/setups/ratetypes"/>" class="btn btn-primary">Revise</a>
         </div><!-- /.info-box-content -->
       </div><!-- /.info-box -->
     </div>
     <div class="col-md-6 col-sm-6 col-xs-12">
       <div class="info-box">
         <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>
         <div class="info-box-content">
           <span class="info-box-text">Receipt Invoices</span>
           <span class="info-box-number">
           <small class="text-muted">Receipt Tenant Invoices</small>
           </span>
            <a href="<c:url value="/protected/rental/setups/ratetypes"/>" class="btn btn-primary">Receipt</a>
         </div><!-- /.info-box-content -->
       </div><!-- /.info-box -->
     </div>
      
            
           