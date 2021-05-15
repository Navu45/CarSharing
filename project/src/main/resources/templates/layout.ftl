
<#macro layout isHomePage>
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org" lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
                integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
                integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
                crossorigin="anonymous"></script>

        <#if isHomePage>
            <style type="text/css">
                /* Set the size of the div element that contains the map */
                #map {
                    height: 100%;
                    /* The height is 400 pixels */
                    width: 100%;
                    /* The width is the width of the web page */
                }
            </style>
        </#if>
        <title th:text="title"></title>
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <span class="navbar-brand mb-0 h1" href="#">CarSharing</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/home">Home</a>
                </li>
                <#assign known = Session.SPRING_SECURITY_CONTEXT??>
                <#if known>
                    <#assign
                        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
                        isAdmin = user.isAdmin()>
                    <#if isAdmin>
                        <li class="nav-item active">
                            <a class="nav-link" href="/user/list">Editor</a>
                        </li>
                    </#if>
                </#if>
                <li class="nav-item active">
                    <a class="nav-link" href="/bookings">Book a car</a>
                </li>
            </ul>
            <span class="navbar-text"><a href="/login">Sign in</a></span>
            <form action="/logout" method="post">
                <button class="btn btn-primary ml-3" type="submit">Sign Out</button>
            </form>
        </div>
    </nav>
    <div class="container mt-5">
        <#nested>
    </div>
    </body>
    </html>
</#macro>