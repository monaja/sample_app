function showSuccess(message) {
	$('#success-alert #message').html(message);
	$("#success-alert").alert();
	$("#success-alert").show().delay(4000).addClass("in").slideUp(500);
};
function showWarning(message) {
	$('#warning-alert #message').html(message);
	$("#warning-alert").alert();
	$("#warning-alert").show().delay(9000).addClass("in").slideUp(500);
};
function showError(message) {
	$('#error-alert #message').html(message);
	$("#error-alert").alert();
	$("#error-alert").show().delay(9000).addClass("in").slideUp(500);
};

function changeLocale(url) {
    $.get(url, function () {
        location.reload()
    });
}