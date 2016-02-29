$(document).ready(function() {
	$("#webcam").scriptcam({
		path : path,
		chatWindow : 'chatWindow',
		onError : onError,
		promptWillShow : promptWillShow,
		showMicrophoneErrors : true,
		onWebcamReady : onWebcamReady,
		connected : chatStarted,
		setVolume : setVolume,
		timeLeft : timeLeft,

		loginName : userName,

		chatRoom : 'videochat',
		connected : showRecord
	});
	setVolume(0);
	$("#slider").slider({
		animate : true,
		min : 0,
		max : 100,
		value : 50,
		orientation : 'vertical',
		disabled : true
	});
	$("#slider").bind("slidechange", function(event, ui) {
		$.scriptcam.changeVolume($("#slider").slider("option", "value"));
	});
	$("#message").keypress(function(event) {
		if (event.which == 13) {
			event.preventDefault();
			$.scriptcam.sendMessage($('#message').val());
			$('#message').val('');
		}
	});
});
function closeCamera() {
	// $("#slider").slider("option","disabled", true);
	$.scriptcam.closeCamera();
}
function onError(errorId, errorMsg) {
	alert(errorMsg);
}
function chatStarted() {
	$("#slider").slider("option", "disabled", false);
}
function onWebcamReady(cameraNames, camera, microphoneNames, microphone, volume) {
	$("#slider").slider("option", "value", volume);
	$.each(cameraNames, function(index, text) {
		$('#cameraNames').append($('<option></option>').val(index).html(text))
	});
	$('#cameraNames').val(camera);
	$.each(microphoneNames, function(index, text) {
		$('#microphoneNames').append(
				$('<option></option>').val(index).html(text))
	});
	$('#microphoneNames').val(microphone);
}
function promptWillShow() {
	alert('A security dialog will be shown. Please click on ALLOW and wait for a second chat partner to arrive.');
}
function setVolume(value) {
	value = parseInt(32 * value / 100) + 1;
	for (var i = 1; i < value; i++) {
		$('#LedBar' + i).css('visibility', 'visible');
	}
	for (i = value; i < 33; i++) {
		$('#LedBar' + i).css('visibility', 'hidden');
	}
}
function timeLeft(value) {
	$('#timeLeft').html(value);
}
function changeCamera() {
	$.scriptcam.changeCamera($('#cameraNames').val());
}
function changeMicrophone() {
	$.scriptcam.changeMicrophone($('#microphoneNames').val());
}

function showRecord() {
	$("#recordStartButton").attr("disabled", false);
}
function startRecording() {
	$("#recordStartButton").attr("disabled", true);
	$("#recordStopButton").attr("disabled", false);
	$("#recordPauseResumeButton").attr("disabled", false);
	$.scriptcam.startRecording();
}
function closeCamera() {
	$("#slider").slider("option", "disabled", true);
	$("#recordPauseResumeButton").attr("disabled", true);
	$("#recordStopButton").attr("disabled", true);
	$.scriptcam.closeCamera();
	$('#message').html('Please wait for the file conversion to finish...');
}
function pauseResumeCamera() {
	if ($("#recordPauseResumeButton").html() == 'Pause Recording') {
		$("#recordPauseResumeButton").html("Resume Recording");
		$.scriptcam.pauseRecording();
	} else {
		$("#recordPauseResumeButton").html("Pause Recording");
		$.scriptcam.resumeRecording();
	}
}
function fileReady(fileName) {
	$('#recorder').hide();
	/*
	 * $('#message').html('This file is now dowloadable for five minutes over <a
	 * href='+fileName+'">here</a>.');
	 */
	var fileNameNoExtension = fileName.replace(".mp4", "");
	/*
	 * jwplayer("mediaplayer").setup({ width:320, height:240, file: fileName,
	 * image: fileNameNoExtension+"_0000.jpg" });
	 */
	$('#mediaplayer').show();
}