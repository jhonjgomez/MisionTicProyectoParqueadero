var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {

        $("#mi-perfil-btn").attr("href", "profile.html?username=" + username);

        $("#user-saldo").html("$" + user.saldo.toFixed());

        console.log("funciona hasta aqui");

        getPuestos(false, "ASC");

        $("#ordenar-Zona").click(ordenarPuestos);
    });

    async function getUsuario() {

        await $.ajax({
            type: "GET",
            dataType: "html",
            url: "./ServletUsuarioPedir",
            data: $.param({
                username: username
            }),
            success: function (result) {
                let parsedResult = JSON.parse(result);
                if (parsedResult != false) {
                    user = parsedResult;
                } else {
                    console.log("Error recuperando los datos del usuario");
                }
            }
        });
    }


    function getPuestos(ordenar, orden) {

        $.ajax({
            type: "GET",
            dataType: "html",
            url: "./ServletPuestoListar",
            data: $.param({
                ordenar: ordenar,
                orden: orden
            }),
            success: function (result) {
                let parsedResult = JSON.parse(result);

                if (parsedResult != false) {
                    mostrarPuestos(parsedResult);
                } else {
                    console.log("Error recuperando los datos de los puestos");
                }
            }
        });
    }

    function mostrarPuestos(puestos) {
        let contenido = "";

        $.each(puestos, function (index, puesto) {
            puesto = JSON.parse(puesto);
            let precio = puesto.costo;
            console.log(precio);

            if (puesto.disponible === true) {

                if (user.premium) {

                    precio = precio * 0.70;

                }
                contenido += '<tr><th scope="row">' + puesto.id + '</th>' +
                        '<td>' + puesto.tipo + '</td>' +
                        '<td>' + puesto.zona + '</td>' +
                        '<td><input type="checkbox" name="disponible" id="disponible' + puesto.id + '" disabled ';
                if (puesto.disponible) {
                    contenido += 'checked';
                }
                contenido += '></td>' +
                        '<td>' + precio + '</td>' +
                        '<td><button onclick="reservarPuesto(' + puesto.id + ',' + precio + ');" class="btn btn-success" ';
                if (user.saldo < precio) {
                    contenido += ' disabled ';
                }

                contenido += '>Reservar</button></td></tr>'

            }
        });
        $("#puestos-tbody").html(contenido);
    }

});

    function ordenarPuestos() {
        if ($("#icono-ordenar").hasClass("fa-sort")) {
            getPuestos(true, "ASC");
            $("#icono-ordenar").removeClass("fa-sort");
            $("#icono-ordenar").addClass("fa-sort-down");
        } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
            getPuestos(true, "DESC");
            $("#icono-ordenar").removeClass("fa-sort-down");
            $("#icono-ordenar").addClass("fa-sort-up");
        } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
            getPuestos(false, "ASC");
            $("#icono-ordenar").removeClass("fa-sort-up");
            $("#icono-ordenar").addClass("fa-sort");
        }
    }



function reservarPuesto(id, precio) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletPuestoReservar",
        data: $.param({
            id: id,
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult != false) {
                restarDinero(precio).then(function () {
                    location.reload();
                });

            } else {
                console.log("Error en la reserva del puesto");
            }
        }
    });

}

async function restarDinero(precio) {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioRestarDinero",
        data: $.param({
            username: username,
            saldo: parseFloat(user.saldo - precio)
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult != false) {
                console.log("Saldo actualizado")
            } else {
                console.log("Error en el proceso de pago");
            }
        }
    });
}

