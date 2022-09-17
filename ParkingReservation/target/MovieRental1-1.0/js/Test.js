$(document).ready(function(){
    $.ajax({
        type:"GET",
        dataType:"html",
        url: "./ServletTest",
        data: $.param(),
        success: function(data){
            let parsedData = JSON.parse(data);
            console.log("Peticion Correcta");
        }
    });
});

