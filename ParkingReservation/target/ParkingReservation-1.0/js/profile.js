var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {


    fillUsuario().then(function () {

        $("#user-saldo").html("$" + user.saldo.toFixed());

        getReservados(user.username);
    });

    $("#reservar-btn").attr("href", `home.html?username=${username}`);

    $("#form-modificar").on("submit", function (event) {

        event.preventDefault();
        modificarUsuario();
    });

    $("#aceptar-eliminar-cuenta-btn").click(function () {

        eliminarCuenta().then(function () {
            location.href = "index.html";
        })
    })

});

async function fillUsuario() {
    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;

                $("#input-username").val(parsedResult.username);
                $("#input-contrasena").val(parsedResult.contrasena);
                $("#input-nombre").val(parsedResult.nombre);
                $("#input-apellidos").val(parsedResult.apellido);
                $("#input-email").val(parsedResult.email);
                $("#input-saldo").val(parsedResult.saldo.toFixed(2));
                $("#input-premium").prop("checked", parsedResult.premium);

            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

function getReservados(username) {


    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletReservarListar",
        data: $.param({
            username: username,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {

                mostrarHistorial(parsedResult)

            } else {
                console.log("Error recuperando los datos de las reservas");
            }
        }
    });
}

function mostrarHistorial(puestos) {

    let contenido = "";

    if (puestos.length >= 1) {

        $.each(puestos, function (index, puesto) {

            puesto = JSON.parse(puesto);

            contenido += '<tr><th scope="row">' + puesto.id + '</th>' +
                    '<td>' + puesto.tipo + '</td>' +
                    '<td>' + puesto.zona + '</td>' +
                    '<td>' + puesto.fechaEntrada + '</td>' +
                    '<td><button id="devolver-btn" onclick= "retirarVehiculo(' + puesto.id
                    + ');" class="btn btn-danger">Retirar Vehiculo</button></td></tr>';

        });
        $("#historial-tbody").html(contenido);
        $("#historial-table").removeClass("d-none");
        $("#historial-vacio").addClass("d-none");

    } else {
        $("#historial-vacio").removeClass("d-none");
        $("#historial-table").addClass("d-none");
    }
}


function retirarVehiculo(id) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletPuestoDevolver",
        data: $.param({
            username: username,
            id: id,
        }),
        success: function (result) {

            if (result != false) {

                location.reload();

            } else {
                console.log("Error devolviendo el Puesto");
            }
        }
    });

}



function modificarUsuario() {

    let username = $("#input-username").val();
    let contrasena = $("#input-contrasena").val();
    let nombre = $("#input-nombre").val();
    let apellido = $("#input-apellidos").val();
    let email = $("#input-email").val();
    let saldo = $("#input-saldo").val();
    let premium = $("#input-premium").prop('checked');

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioModificar",
        data: $.param({
            username: username,
            contrasena: contrasena,
            nombre: nombre,
            apellido: apellido,
            email: email,
            saldo: saldo,
            premium: premium,
        }),
        success: function (result) {

            if (result != false) {
                $("#modificar-error").addClass("d-none");
                $("#modificar-exito").removeClass("d-none");
            } else {
                $("#modificar-error").removeClass("d-none");
                $("#modificar-exito").addClass("d-none");
            }

            setTimeout(function () {
                location.reload();
            }, 3000);

        }
    });

}

async function eliminarCuenta() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioEliminar",
        data: $.param({
            username: username
        }),
        success: function (result) {

            if (result != false) {

                console.log("Usuario eliminado")

            } else {
                console.log("Error eliminando el usuario");
            }
        }
    });
}
