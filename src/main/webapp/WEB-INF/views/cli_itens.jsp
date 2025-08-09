<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import= "java.util.List, dominio.cliente.Cliente, dominio.cliente.Usuario, dominio.venda.Estoque, dominio.venda.ItemEstoque, 
dominio.livro.Livro"%>

<!DOCTYPE html>
<html lang="bt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  	<%
  		Estoque estoque = (Estoque) request.getSession().getAttribute("estoque");
  	%>
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
            <h1>Livros</h1>
      </div>

      <hr class="my-4">
      
      <div class="row">
      	<div class="col-2">
     		    <form action="/EcomerceLivroLES/cli-itens" method="post">
  				<input type="text" class="form-control" name="txtPesquisa" placeholder="Texto para Busca..." aria-label="Pesquisa" aria-describedby="basic-addon1">
  				<hr>
     		    <ul class="list-group">
     		    	<li class="list-group-item text-center">Parâmetros</li>
     		    	
				  <li class="list-group-item">
				    <input class="form-check-input me-1" type="checkbox" name="swtTitulo" id="swtTitulo">
				    <label class="form-check-label stretched-link" for="swtTitulo">Titulo</label>
				  </li>
				  <li class="list-group-item">
				    <input class="form-check-input me-1" type="checkbox" name="swtAutores" id="swtAutores">
				    <label class="form-check-label stretched-link" for="swtAutores">Autores</label>
				  </li>
				  <li class="list-group-item">
				    <input class="form-check-input me-1" type="checkbox" name="swtCategorias" id="swtCategorias">
				    <label class="form-check-label stretched-link" for="swtCategorias">Categorias</label>
				  </li>
				  <li class="list-group-item">
				    <input class="form-check-input me-1" type="checkbox" name="swtIsbn" id="swtIsbn">
				    <label class="form-check-label stretched-link" for="swtIsbn">ISBN</label>
				  </li>
					<li class="list-group-item">
						<input class="form-check-input me-1" type="checkbox" name="swtEditora" id="swtEditora">
				    	<label class="form-check-label stretched-link" for="swtEditora">Editora</label>
					</li>
				</ul>
				<hr>
				<div class="text-center">
     		   		 <button type="submit" class="btn btn-success" name="operacao" value="Consultar"> Consultar</button>
     		    </div>
     		    </form>
      	</div> 


      <div class="col-10">
	      	 
	    <div class="row">
	      	 
			<%for(ItemEstoque item : estoque.getItens()){ %>
			
			<div class="col-3">
	      		<div class="card">
	      		<a href="/EcomerceLivroLES/cli-item-detalhes?idItem=<%=item.getId()%>">
				  <img src="./imagens/capas/<%=item.getLivro().getUrlCapa()%>" class="card-img-top" alt="<%=item.getLivro().getTitulo()%>">
				</a>
				  <div class="card-body">
				  	<h6><%=item.getLivro().getTitulo()%></h6>
				  	<h5>R$ <%=item.getValorVenda()%></h5>
				  </div>
				</div>
			</div>
			
			<%} %>
			
		</div>

      </div>
      
      </div>
      <hr class="my-4">
      
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  </body>
</html>