<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<style>
    body, html {
        height: 100%;
        margin: 0;
    }
    .bgimg {
        background-image: url('https://www.w3schools.com/w3images/forestbridge.jpg');
        height: 100%;
        background-position: center;
        background-size: cover;
        position: relative;
        color: white;
        font-family: "Courier New", Courier, monospace;
        font-size: 25px;
    }
    .topleft {
        position: absolute;
        top: 0;
        left: 16px;
    }
    .bottomleft {
        position: absolute;
        bottom: 0;
        left: 16px;
    }
    .middle {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        text-align: center;
    }
    hr {
        margin: auto;
        width: 40%;
    }
    a {
        color: white;
    }
</style>
<body>

<p>Click the button to get your coordinates.</p>

<button onclick="getLocation()">Try It</button>

<p id="demo"></p>

<script>
    var long = document.getElementById("long");
    var lat = document.getElementById("lat");
    var alt = document.getElementById("alt");
    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            long.innerHTML = "Geolocation is not supported by this browser.";
        }
    }

    function showPosition(position) {
        long.innerHTML= position.coords.longitude;
        lat.innerHTML= position.coords.latitude;
        alt.innerHTML= position.coords.altitude;
    }
</script>
<div class = container>
    <form:form modelAttribute="location" method="POST" action="/getLocation">
        <form:errors path="*" cssClass="errorblock" element="div"/>
            <div><label for="longitude"><p id="long"></p> </label></div>
            <div><label for="latitude"><p id="lat"></p></label></div>
            <div><label for="altitude"><p id="alt"></p></label></div>
            <div><input type="submit" class="btn btn-lg btn-primary" style="margin-top: 20px;" role="button" value="Send Location"></div>

    </form:form>
</div>
</body>
</html><%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>