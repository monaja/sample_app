<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
	var formDataUserNameHeader = '<s:message code="submissions.user" />';
	var formDataDeviceModelHeader = '<s:message code="submissions.device.model" />';
	var formDataDeviceIMEIHeader = '<s:message code="submissions.device.imei" />'; 
	var formDataDateTimeHeader = '<s:message code="submissions.date.time" />'; 
	var formDataLocationHeader = '<s:message code="submissions.location" />'; 
	var formDataLinkToMapCellLabel = '<s:message code="submissions.link.to.map" />';
	var locationNotValid = '<s:message code="submissions.link.to.map.na" />'; 
	var labelViewVideo = '<s:message code="submissions.link.view.video" />';
	var destinationCheckboxAsPDF = '<s:message code="email.destination.form.attachments.aspdf" />';
	var destinationCheckboxAsExcel = '<s:message code="email.destination.form.attachments.asexcel" />';
	var formSubmissionNoDataSubmitted = '<s:message code="builder.submissions.dialog.nodatasubmitted" />';
	var formSubmissionsPrintButton = '<s:message code="form.submissions.button.print.title" />';
	var formSubmissionsPrintTooltip = '<s:message code="form.submissions.button.print.tooltip" />';
	var formSubmissionsPrintMessage = '<s:message code="form.submissions.button.print.message" />';
	var applicationURL = "http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>";
	var requiredFieldMessage = '<s:message code="email.destination.body.template.message.required" />';
	
	var computationalLabel = '<s:message code="builder.computationalexp.label" />';
	var computationalFinalLabel = '<s:message code="builder.finalexpression.label" />';
	var computationalLoadControlsHint = '<s:message code="builder.expression.loadcontrols.hint" />';
	var computationalExpNotValid = '<s:message code="builder.expression.field.notvalid" />';
	var ruleEditorBuilder = '<s:message code="builder.ruleeditor.rule.builder" />';
	var ruleEditorAction = '<s:message code="builder.ruleeditor.rule.action" />';
	var ruleEditorSelectAction = '<s:message code="builder.ruleeditor.select.action" />';
	var ruleEditorMandatoryAction = '<s:message code="builder.ruleeditor.action.mandatory" />';
	var ruleEditorReadonlyAction = '<s:message code="builder.ruleeditor.action.readonly" />';
	var ruleEditorChangeColorAction = '<s:message code="builder.ruleeditor.action.changecolor" />';
	var ruleEditorSetValueAction = '<s:message code="builder.ruleeditor.action.setvalue" />';
	var ruleEditorColor = '<s:message code="builder.ruleeditor.action.color" />';
	var ruleEditorValue = '<s:message code="builder.ruleeditor.action.value" />';
	var fieldRequired = '<s:message code="builder.requiredfield" />';
	var isHiddenControlLabel = '<s:message code="builder.ruleeditor.action.hideControlLabel" />';
	var contextPath = "<%=request.getContextPath()%>";
</script>
