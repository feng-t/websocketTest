var stompClient = null;
var user = null;
var users = [];

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        user = frame.headers['user-name'].toString();
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).name);
        });
        stompClient.subscribe("/user/queue/sendtouser", handleNotification)
        stompClient.subscribe('/topic/getuser', function (data) {
            data = JSON.parse(data.body);
            if (data.falg) {
                var select = document.getElementById("select");
                for (var i = 0; i < data.data.length; i++) {
                    if (user !== data.data[i].id.toString()) {
                        var optionElement = document.createElement("option");
                        optionElement.text = data.data[i].username;
                        optionElement.value = i;
                        users[i] = data.data[i].id.toString();
                        select.appendChild(optionElement);
                    }
                }

            }
        });
        stompClient.send("/app/get");
    });


    function handleNotification(message) {
        var body = JSON.parse(message.body);
        console.log("------message-------");
        console.log(body);
        console.log("------message-------");
        if (body.code===20100&&!body.falg) {
            alert(body.msg)
            location.href = "/ws"
        }else{
            $("#greetings").append("<tr><td>" + body.data.content + "</td></tr>");
        }


    }
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }

    setConnected(false);
    console.log("Disconnected");
}

function sendName() {

    var select = document.getElementById("select");


    if (select.options[select.selectedIndex] !== undefined) {
        var msg = {
            content: $("#name").val(),
            createtime: new Date(),
            form: user,
            to: users[select.options[select.selectedIndex].value]
        }
        console.log(msg);
        //stompClient.send("/chat", {}, JSON.stringify(msg));
        stompClient.send("/app/chat", {}, JSON.stringify(msg));
    }else{
        var msg = {
            content: $("#name").val(),
            createtime: new Date(),
            form: user,
            to:"1112270255367979008"
        }
        console.log(msg);
        //stompClient.send("/chat", {}, JSON.stringify(msg));
        stompClient.send("/app/chat", {}, JSON.stringify(msg));
    }

}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();

    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });

});