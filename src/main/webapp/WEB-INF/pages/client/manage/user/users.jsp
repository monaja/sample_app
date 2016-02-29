<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>



<script type="text/javascript"
	src="<c:url value="/js/modules/user/users.js"/>"></script>


        
     <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">Users</h3> 
            <div class="box-tools">
               <button type="button" class="btn btn-sm btn-primary"  onclick=""
               data-toggle="modal" data-target="#userModal">New User</button>
            </div>    
               
        </div>
		<table id="usersTable" class="table table-striped table-hover"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th><s:message code="user.name" /></th>
					<th><s:message code="user.username" /></th>
					<th><s:message code="user.status" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
      </div>
      
      
      <div class="modal fade" id="userModal" tabindex="-1" role="dialog"
		aria-labelledby="userModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userModalLabel">
						Edit/Add User
					</h4>
				</div>
				<div class="modal-body">
					<form id="user-form" class="form-horizontal">
						<input type="hidden" class="form-control" id="user-id" name="user"
							value="${user.id}">

						<div class="form-group">
							<label for="user-name" class="col-md-3 control-label"><s:message
									code="user.name" /></label>

							<div class="col-md-8">
								<input type="text" class="form-control" id="user-name"
									name="name" value="${user.name}" required>
							</div>
						</div>
						<div class="form-group">
							<label for="user-username" class="col-md-3 control-label"><s:message
									code="user.username" /></label>

							<div class="col-md-8">
								<input type="text" class="editUserCntrls form-control"
									id="user-username" name="username" value="${user.username}"
									required>
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Cancel
					</button>
					<button type="button" class="btn btn-warning" id="resetBtn">
						Reset
					</button>
					<button data-loading-text="Saving..." id="saveUpdateBtn"
						type="button" class="btn btn-primary">
						Save
					</button>
				</div>
			</div>
		</div>
	</div>
      