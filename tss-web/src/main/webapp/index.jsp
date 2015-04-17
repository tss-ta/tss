<%-- 
    Document   : index
    Created on : Apr 10, 2015, 3:59:10 AM
    Author     : Olga Gorbatiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <title>Taxi Service System</title>

        <!-- Bootstrap -->
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/custom.css" rel="stylesheet">
        <link href="resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" 
                            data-toggle="collapse" data-target="#navbar" 
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">TSS</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="rh_info.html">App info</a></li>
                        <li><a href="snoop.jsp">Snoop page</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <div class="container-fluid">

            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <h1>Taxi Service System</h1>
                    <p>Content should be here.</p>
                </div>
                <div class="col-md-1"></div>
            </div><!-- /.row -->

        </div><!-- /.container -->

        <footer class="footer">
            <div class="container">
                <p class="text-muted">&copy; 2015 A-Team, ver. 0.1</p>
            </div>
        </footer>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="/resources/js/jquery-1.11.2.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="/resources/js/bootstrap.min.js"></script>
    </body>
</html>
