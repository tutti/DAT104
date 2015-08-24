function onGenerateSuccess(data) {
    var name = data.charAt(0).toUpperCase() + data.slice(1);
    jQuery(".old").prepend(jQuery("<li>" + name + "</li>"));
    jQuery(".current").text(name);
}

function generate() {
    var length = jQuery("#length").val();
    jQuery.get(
        "/Fun/generate?length=" + length,
        onGenerateSuccess
    );
}

function toggleExplanation(e) {
	if (jQuery("#explanation").css("display") == "block") {
		jQuery("#explanation").css("display", "none");
	} else {
		jQuery("#explanation").css("display", "block");
	}
	e.preventDefault();
}

jQuery(document).ready(function() {
    jQuery("#generateButton").click(generate);
    jQuery("#showExplanation").click(toggleExplanation);
});