<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "java.util.List, dominio.cliente.Usuario, dominio.cliente.Notificacao, java.time.LocalDate, java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html lang="bt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  	<%
  	  		
  		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
  	
  	if(usuario == null){
  			request.getSession().invalidate();
  			response.sendRedirect("login");
  		}	
  	%>
  	
  	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js"></script>
  	
  </head>
  <body>

    <header class="p-2 text-bg-dark mb-3 border-bottom">

        <div class="container px-2">
        
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
               
               <ul class="nav col-12 col-lg-auto me-lg-auto mb-1 justify-content-center ">
               <li>
	                <form action="/EcomerceLivroLES/cli-home" method="post">
	                	<input type="hidden" name="operacao" value="Consultar">
	                	<button type="submit" class="btn nav-link px-2 text-secondary" >Home</button>
	                </form>
                </li>
                <li>
	                <form action="/EcomerceLivroLES/cli-itens" method="post">
	                	<input type="hidden" name="operacao" value="Consultar">
	                	<button type="submit" class="btn nav-link px-2 link-secondary text-white" >Livros</button>
	                </form>
                </li>
				<li>
	                <form action="/EcomerceLivroLES/cli-pedidos" method="post">
	                	<input type="hidden" name="operacao" value="Consultar">
	                	<button type="submit" class="btn nav-link px-2 link-secondary text-white" >Pedidos</button>
	                </form>
                </li>
                <li>
	                <form action="/EcomerceLivroLES/cli-pedidos-troca" method="post">
	                	<input type="hidden" name="operacao" value="Consultar">
	                	<button type="submit" class="btn nav-link px-2 link-secondary text-white" >Trocas</button>
	                </form>
                </li>
                
              </ul>
              
                <a class="d-flex link-secondary text-white mb-1 px-3" href="/EcomerceLivroLES/cli-carrinho-compras">
                  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-cart3 mb-1 me-3" viewBox="0 0 16 16">
                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                  </svg>
                </a>
              
                <div class="dropdown text-end px-2 ">
	                <a href="#" class="d-flex link-secondary text-white dropdown-item mb-1 px-1" data-bs-toggle="dropdown" aria-expanded="false">
	                  <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
	                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
	                    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
	                  </svg>
	                </a>
	
	                <ul class="dropdown-menu text-small">
	                  <li>
		                  <form action="/EcomerceLivroLES/cli-perfil" method="post">
		                  	<button type="submit" class="dropdown-item" name="operacao" value="ConsultarPorId">Sua Conta</button>
		                  </form>
	                  </li>
	                                    
	                  <li><hr class="dropdown-divider"></li>
	                  <li><a class="dropdown-item" href="/EcomerceLivroLES/login">Log out</a></li>
	                </ul>
              </div>
              
            </div>
          </div>

    </header>

    <main class="container">
      
      <div class="row text-center">
            <h1>Olá, <%=usuario.getCliente().getNome()%>! </h1>
          
      </div>

      <hr class="my-4">
      <div class="row">
      <div class="col-7">
      
      <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		  	 <img src="imagens/banners/Banner_1.PNG"class="d-block w-100" alt="...">

		    </div>
		    <div class="carousel-item">
			 <img src="imagens/banners/Banner_2.PNG" class="d-block w-100" alt="...">
		     
		    </div>
		    <div class="carousel-item">
			    <img src="imagens/banners/Banner_3.PNG" class="d-block w-100" alt="...">
		      
		    </div>
		  </div>
  
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
      
      </div> 
      
      <div class="col-5">
      	 	<h5 class="text-center">Notificações</h5>
      	 	<hr>
      		<table class="table display" id="table_cupons">
                   <thead>
                     <tr>
                       <th scope="col" class="text-center">Mensagem</th>
                       <th scope="col" class="text-center">Data</th>
                     </tr>
                   </thead>
                   <tbody>
	                   <%for(Notificacao notificacao : usuario.getNotificacoes()){ %>
	      				<tr>
	                        <td scope="col" class="text-center"><%=notificacao.getMensagem()%></td>
	                        <td scope="col" class="text-center"><%=notificacao.getDtCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%></td>
	                   </tr>
	           			<%}%>
                  </tbody>
     			
      		</table>

      </div>
      
      </div>
      <hr class="my-4">
      
    </main>

	<script type="text/javascript">
          $(document).ready( function () {
        	    $('#table_cupons').DataTable();
        	} );
          
          $('#table_cupons').dataTable( {
        	  "lengthMenu": [ 3 ]
        	} );
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  </body>
</html>