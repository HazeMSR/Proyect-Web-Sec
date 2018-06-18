<%-- 
    Document   : ListaProyectos
    Created on : 14/06/2018, 11:35:55 AM
    Author     : jllpz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Bitacoras</title>

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


    <h1>Bitacoras</h1>
            <br>
            
  <section class="portfolio" id="portfolio">
    <div class="container">
        <h2 class="text-center text-uppercase text-secondary mb-0">Proyectos</h2>
        <hr class="star-dark mb-5">
    
        <div class="row">
 
     <c:forEach items="${datos}" var="c">
          <div class="col-md-6 col-lg-4">
            <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-${c.id}">
              <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                  <div class="portfolio-item-caption-content my-auto w-100 text-center text-white" style="margin: 0 auto; text-align: center;">
                  <i class="fa fa-search-plus fa-3x"></i>
                </div>
              </div>
              <img class="img-fluid" src="<c:out value="${c.imagen}"/>" alt="">
            </a>
          </div>                
     </c:forEach>   
        </div>
           <a href="<c:url value="CrearProyecto.htm"/>" class="btn btn-primary btn-xl">Crear Proyecto</a>
    </div>        
  </section>
            
  <c:forEach items="${datos}" var="c">

    <div class="portfolio-modal mfp-hide" id="<c:out value="portfolio-modal-${c.id}"/>">
      <div class="portfolio-modal-dialog bg-white">
        <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#">
          <i class="fa fa-3x fa-times"></i>
        </a>
        <div class="container text-center">
          <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2 class="text-secondary text-uppercase mb-0"><c:out value="${c.nombre}"/></h2>
              <hr class="star-dark mb-5">
              <img class="img-fluid mb-5" style="width: 400px;" src="<c:out value="${c.imagen}"/>" alt="">
              <br>
              <b class="mb-5" style="font-size: 25px;">Arquitecto: </b><p class="mb-5" style="font-size: 25px;"><c:out value="${c.arqui}"/></p>
              <b class="mb-5" style="font-size: 25px;">Creado: </b><p class="mb-5" style="font-size: 25px;"><c:out value="${c.created}"/></p>
              <b class="mb-5" style="font-size: 25px;">Tipo de Obra: </b><p class="mb-5" style="font-size: 25px;"><c:out value="${c.tipoObra}"/></p>
              <b class="mb-5" style="font-size: 25px;">Municipio/Delegación: </b><p class="mb-5" style="font-size: 25px;"><c:out value="${c.municipio}"/></p>
              <b class="mb-5" style="font-size: 25px;">Ubicación: </b><p class="mb-5" style="font-size: 25px;"><c:out value="${c.ubicacion}"/></p>
              <b class="mb-5" style="font-size: 25px;">Propietario: </b><p class="mb-5" style="font-size: 25px;"><c:out value="${c.propietario}"/></p>
              <b class="mb-5" style="font-size: 25px;">Contenido: </b><p class="mb-5" style="font-size: 25px;"><c:out value="${c.contenido}"/></p>
              <a class="btn btn-primary" href="<c:url value="CrearBitacora.htm?id=${c.id}"/>">
                <i class="fa fa-book"></i>
                Crear Bitacora</a>
              <a class="btn btn-primary" href="<c:url value="ModificarProyecto.htm?id=${c.id}"/>">
                <i class="fa fa-code"></i>
                Modificar Proyecto</a>
              <a class="btn btn-primary" href="<c:url value="Eliminar.htm?id=${c.id}"/>">
                <i class="fa fa-close"></i>
                Eliminar Proyecto</a>
            </div>
          </div>
        </div>
      </div>
    </div>

    </c:forEach>
      
            
            
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
