$(document).ready(function(){
	console.log("blabla console");
	$("h1").text("balise h1 changée!");
	$("#toggle").click(function(){
		$("#idul").toggle(1000);	
	});
	$("li:first").text(" balise li changée de manière moins rapide");
	$("li").first().text(" balise li changée de manière plus rapide");
	var i =0;
	$("#colors").click(function(){
		i++;
		var colorR = Math.floor((Math.random() * 64)+192);
		var colorG = Math.floor((Math.random() * 64)+192);
		var colorB = Math.floor((Math.random() * 64)+192);
		$("#textecouleurs").append("<p id='textecouleurs" + i+"'> Texte en couleurs ! Texte en couleurs ! </p>");
		$("#textecouleurs" + i).css({"background-color": "rgb(" + colorR + "," + colorG + "," + colorB + ")"});
	});
	
});

