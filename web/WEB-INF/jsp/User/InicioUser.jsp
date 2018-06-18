<%-- 
    Document   : InicioUser
    Created on : 14/06/2018, 11:35:26 AM
    Author     : jllpz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Constructora PRO</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

    <!-- Custom fonts for this template -->
    <link href="<c:url value="/Resources/vendor/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- Plugin CSS -->
    <link href="<c:url value="/Resources/vendor/magnific-popup/magnific-popup.css"/>" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/Resources/css/freelancer.min.css"/>" rel="stylesheet">

  </head>

  <% HttpSession sesion = request.getSession();
       out.println(sesion.getAttribute("sesion"));
        sesion.setAttribute("sesion", sesion.getAttribute("sesion")); %>

  <body id="page-top">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase" id="mainNav">
      <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">Constructora PRO</a>
        <button class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="<c:url value="inicioUser.htm"/>">Inicio</a>
            </li>
            <li class="nav-item mx-0 mx-lg-1">
                <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="<c:url value="listaBitacoras.htm"/>">Bitacoras</a>
            </li>
            <li class="nav-item mx-0 mx-lg-1">
                <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="<c:url value="listaProyectos.htm"/>">Proyectos</a>
            </li>
            <li class="nav-item mx-0 mx-lg-1">
                <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="<c:url value="inicio.htm"/>">Salir</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
            
  
        <!-- Header -->
    <header class="masthead bg-primary text-white text-center">
      <div class="container">
          <img class="img-fluid mb-5 d-block mx-auto" src="<c:url value="/Resources/img/piramide.png"/>" alt="">
        <h1 class="text-uppercase mb-0">Bienvenido</h1>
        <hr class="star-light">
        <h2 class="font-weight-light mb-0">Proyecto Web Security - Constructora PRO -
        <c:forEach var="datos" items="${listaDatos}">
            <h2>Nombre: <c:out value="${datos.nombre}"/> <c:out value="${datos.apellidos}"/> </h2>
            <h2>Website: <c:out value="${datos.website}"/></h2>
        </c:forEach>

      </div>
    </header>


    <!-- Footer -->
    <footer class="footer text-center">
      <div class="container">
        <div class="row">
          <div class="col-md-4 mb-5 mb-lg-0">
            <h4 class="text-uppercase mb-4">ESCOM</h4>
           
          </div>
          <div class="col-md-4 mb-5 mb-lg-0">
            <h4 class="text-uppercase mb-4">Redes Sociales</h4>
            <ul class="list-inline mb-0">
              <li class="list-inline-item">
                <a class="btn btn-outline-light btn-social text-center rounded-circle" href="http://www.fb.com">
                  <i class="fa fa-fw fa-facebook"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a class="btn btn-outline-light btn-social text-center rounded-circle" href="http://www.google.com">
                  <i class="fa fa-fw fa-google-plus"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a class="btn btn-outline-light btn-social text-center rounded-circle" href="http://www.twitter.com">
                  <i class="fa fa-fw fa-twitter"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a class="btn btn-outline-light btn-social text-center rounded-circle" href="http://www.likendin.com">
                  <i class="fa fa-fw fa-linkedin"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a class="btn btn-outline-light btn-social text-center rounded-circle" href="http://www.dribbble.com">
                  <i class="fa fa-fw fa-dribbble"></i>
                </a>
              </li>
            </ul>
          </div>
          <div class="col-md-4">
            <h4 class="text-uppercase mb-4">Constructora PRO</h4>
            <p class="lead mb-0">Proyecto Web Security
          </div>
        </div>
      </div>
    </footer>

    <div class="copyright py-4 text-center text-white">
      <div class="container">
        <small>Develop by JL</small>
      </div>
    </div>

    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
    <div class="scroll-to-top d-lg-none position-fixed ">
      <a class="js-scroll-trigger d-block text-center text-white rounded" href="#page-top">
        <i class="fa fa-chevron-up"></i>
      </a>
    </div>

    <!-- Bootstrap core JavaScript -->
    <script src="<c:url value="/Resources/vendor/jquery/jquery.min.js"/>"></script>
    <script src="<c:url value="/Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

    <!-- Plugin JavaScript -->
    <script src="<c:url value="/Resources/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
    <script src="<c:url value="/Resources/vendor/magnific-popup/jquery.magnific-popup.min.js"/>"></script>

    <!-- Contact Form JavaScript -->
    <script src="<c:url value="/Resources/js/jqBootstrapValidation.js"/>"></script>
    <script src="<c:url value="/Resources/js/contact_me.js"/>"></script>

    <!-- Custom scripts for this template -->
    <script src="<c:url value="/Resources/js/freelancer.min.js"/>"></script>
            
    </body>
</html>
