<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import= "java.util.List, dominio.cliente.Endereco, dominio.cliente.Cliente, dominio.venda.EnderecoEntrega, dominio.venda.Frete"%>
    
    
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js" integrity="sha512-pHVGpX7F/27yZ0ISY+VVjyULApbDlD0/X0rgGbTqCE7WFW5MezNTWG/dnhtbBuICzsd0WQPgpE4REBLv+UqChw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<%	
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente"); 
		EnderecoEntrega endEntrega = (EnderecoEntrega) request.getSession().getAttribute("enderecoSelecionado");
		
		String mensagem = (String) request.getAttribute("mensagemErro");%>

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
  <main>
    <div class="container">
      
      <div class="row align-items-md-center">
        <div class="col-9">
          <h2>Selecione um Endereco para Entrega</h2>
          
        </div>
        
         <div class="col-3">
                  
         <div class="row">
	         <form action="/EcomerceLivroLES/cli-selecionar-pagamento" method="post">
		          <button type="submit" class="btn btn-success" <% if(endEntrega == null){%> disabled <%} %> >
		              Selecionar Forma de Pagamento
		          </button>
	          </form>
          </div>
          
        </div>
      </div>
      
      <hr class="my-4">

		<%if(mensagem != null){ %>
			<div class="alert alert-info" role="alert">
			  <%=mensagem%>
			</div>
		<%} %>

	<div class=row>
		<div class="col-6">
		
		<div class="h4 pb-2 mb-4 text-dark border-bottom border-secundary">
			<div class="row">
				<div class="col-7">
					Enderecos Salvos
				</div>
				
				<div class="col-5">
					<button type="button" class="btn btn-primary" 
		            	data-bs-toggle="modal" data-bs-target="#modalSalvaEnd"
		            	<% if(endEntrega != null){%> disabled <%} %>>
		              Adicionar novo endereco
		        	</button>
				</div>
			
			</div>	 	
				 	
		</div>
			
      <%if( cliente != null){%>
    	  <div class="row py-2">
     	 <%for(Endereco end : cliente.getEnderecos()){%>
		     	<div class="col-6"> 
		      <div class="card">
		        <div class="card-body">
		          <h5 class="card-title"><%=end.getNomeIdentificacao()%></h5>
		          <p class="card-text">
		            Logradouro: <%=end.getLogradouro()%> <br>
		            Numero: <%=end.getNumero()%> <br>
		            Bairro: <%=end.getBairro()%> <br>
		            CEP: <%=end.getCep()%> <br>
		            Cidade: <%=end.getCidade().getNome()%> <br>
		            Estado: <%=end.getCidade().getEstado().getNome()%> <br>
		            Pais: <%=end.getCidade().getEstado().getPais().getNome()%> <br>
		          </p>
		          
		          <form action="/EcomerceLivroLES/cli-selecionar-endereco" method="post">
		          	<input type="hidden" name="txtEnderecoId" value="<%=end.getId()%>">
		          	<button type="submit" class="btn btn-primary" name="operacao" value="Selecionar" 
		          	<% if(endEntrega != null){%> disabled <%} %>> Selecionar</button>
		          </form>
		          
		        </div>
		      </div>
		      </div>
      		<%}%>
      	</div>
      <%}%>
		</div>
	
		<div class="col-6">
			<div class="h4 pb-2 mb-4 text-dark border-bottom border-secundary">
				 	Endereco Selecionado
			</div>
				
			<table class="table">
				  <thead>
				    <tr>
				      <th scope="col">Logradouro</th>
				      <th scope="col">Numero</th>
				      <th scope="col">CEP</th>
				      <th scope="col"></th>
				    </tr>
				  </thead>
				  <tbody>
				  <%if(endEntrega != null){
				 	 Endereco endereco = endEntrega.getEndereco();%>
				  
				    <tr>
				      <td><%=endereco.getLogradouro()%></td>
				      <td><%=endereco.getNumero()%></td>
				      <td><%=endereco.getCep()%>
				      
			      	<td>
			      	  <form action="/EcomerceLivroLES/cli-selecionar-endereco" method="post">
				      	<input type="hidden" name="txtEnderecoId" value="<%=endereco.getId()%>">
				      	<button type="submit" class="btn btn-danger" name="operacao" value="Remover">Remover</button>
				      </form>
			      	</td>
			      	
				    </tr>
				  <%} %>
				  </tbody>
			</table>
			<%if(endEntrega != null){%>
				
			<div class="alert alert-dark" role="alert">
			  <p>Entrega via Correios - SEDEX</p>
			  <p>Valor: R$<%=endEntrega.getFrete().getValor()%></p>
			  <p>Prazo de entrega: <%=endEntrega.getFrete().getPrazo()%> <%if(endEntrega.getFrete().getPrazo()>1){%>dias uteis<%}else{%>dia util<%}%></p>
			</div>
			
			<%}%>

		</div>
		
		<hr>
		
		
	</div>
		
    </div>
  </main>

 <div class="modal fade" id="modalSalvaEnd" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Inserir Dados</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <form class="row g-3" action="/EcomerceLivroLES/cli-selecionar-endereco" method="post">

            <div class="modal-body">
              
              <div class="container-fluid">

                <h3 class="display text-center">Cadastrar novo endereco</h3>
        
                <div class="row py-1">
                  <div class="col-sm-6">
                      <label class="visually-hidden" for="cbbTpResidencia">Tipo Residencia</label>
                      <select class="form-select" id="cbbTpResidencia" name="cbbTpResidencia">
                          <option selected value="0">Tipo de Residencia...</option>
                          <option value="1">Casa</option>
                          <option value="2">Apartamento</option>
                          <option value="3">Outro</option>
                      </select>
                  </div>

                  <div class="col-sm-6">
                      <label class="visually-hidden" for="cbbTpLogradouro">Tipo Logradouro</label>
                      <select class="form-select" id="cbbTpLogradouro" name="cbbTpLogradouro">
                          <option selected value="0">Tipo de Logradouro...</option>
                          <option value="1">Rua</option>
                          <option value="2">Avenida</option>
                          <option value="3">Outro</option>
                      </select>
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-9">
                      <label class="visually-hidden" for="txtLogradouro">Logradouro</label>
                      <input type="text" class="form-control" id="txtLogradouro" name="txtLogradouro" placeholder="Logradouro">
                  </div>

                  <div class="col-sm-3">
                      <label class="visually-hidden" for="txtNumero">Numero Casa</label>
                      <input type="text" class="form-control" id="txtNumero" name="txtNumero" placeholder="Numero">
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-6">
                      <label class="visually-hidden" for="txtBairro">Bairro</label>
                      <input type="text" class="form-control" id="txtBairro" name="txtBairro" placeholder="Bairro">
                  </div>

                  <div class="col-sm-6">
                      <label class="visually-hidden" for="txtCep">Cep</label>
                      <input type="text" class="form-control" id="txtCep" name="txtCep" placeholder="CEP">
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-5">
                      <label class="visually-hidden" for="txtCidade">Cidade</label>
                      <input type="text" class="form-control" id="txtCidade" name="txtCidade" placeholder="Cidade">
                  </div>

                  <div class="col-sm-4">
                      <label class="visually-hidden" for="txtEstado">Estado</label>
                      <input type="text" class="form-control" id="txtEstado" name="txtEstado"  placeholder="Estado">
                  </div>

                  <div class="col-sm-3">
                      <label class="visually-hidden" for="txtPais">Pais</label>
                      <input type="text" class="form-control" id="txtPais" name="txtPais" placeholder="Pais">
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-12">
                      <label class="visually-hidden" for="txtObservacao">Observacoes</label>
                      <input type="text" class="form-control" id="txtObservacao" name="txtObservacao" placeholder="Nome do Endereco Para identificacao">
                  </div>
                </div>
                
                <div class="row py-1">
                  <div class="col-sm-12">
                      <label class="visually-hidden" for="txtComplemento">Complemento</label>
                      <input type="text" class="form-control" id="txtComplemento" name="txtComplemento" placeholder="Complemento">
                  </div>
                </div>
            	
            	<div class="row py-1">
            		<div class="form-check form-switch form-check-reverse">
					  <input class="form-check-input" name="swtSalvarEndereco" type="checkbox" id="flexSwitchCheckReverse">
					  <label class="form-check-label" for="flexSwitchCheckReverse">Salvar Endereco para futuras Compras</label>
					</div>
            	</div>
            	
              </div>              

            </div>
        
	        <div class="modal-footer">
	          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
	          <button type="submit" class="btn btn-primary" name="operacao" value="Salvar">Cadastrar!</button>
	        </div>

        </form>
        
      </div>
      </div>
    </div>   
	<script type="text/javascript">
        
        $("#txtCep").mask("00.000-000");
       
    </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>

</body>
</html>