<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@page import= "java.util.List, dominio.cliente.Endereco, dominio.cliente.Cliente, dominio.venda.CartaoCredito,
dominio.venda.Cupom, dominio.venda.CartaoCompra, dominio.venda.Carrinho, dominio.venda.Pagamento, dominio.venda.TipoCupom"%>    
    
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<%
	Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
	String mensagem = (String) request.getAttribute("mensagemErro");
	
	Pagamento pagamento = (Pagamento) request.getSession().getAttribute("pagamento");

	List<CartaoCompra> cartoes = pagamento.getCartoes();
	List<Cupom> cupons = pagamento.getCupons();
%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js" integrity="sha512-pHVGpX7F/27yZ0ISY+VVjyULApbDlD0/X0rgGbTqCE7WFW5MezNTWG/dnhtbBuICzsd0WQPgpE4REBLv+UqChw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

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
          <h1>Selecione a Forma de Pagamento</h1>
        </div>
        
        <div class="col-3">
        
          <form action="/EcomerceLivroLES/cli-finalizar-pedido" method="post">
	          <button type="submit" class="btn btn-danger" name="operacao" value="Salvar" <%if((pagamento.getTotalCompra().subtract(pagamento.getTotalAlocado())).doubleValue()>0){ %>disabled <%} %> >
	              Finalizar Compra
	          </button>
          </form>
          
        </div>
      </div>
      

      <hr class="my-4">
      
      <%if(mensagem != null){ %>
			<div class="alert alert-info" role="alert">
			  <%=mensagem%>
			</div>
		<%} %>
      
      
		<div class="row">
			<div class="col-6">
				
				<div class="h4 pb-2 mb-4 text-dark border-bottom border-secundary">
			<div class="row">
				<div class="col-7">
					Cartoes Salvos
				</div>
				
				<div class="col-5">
				 	<button type="button" class="btn btn-primary" 
		            	data-bs-toggle="modal" data-bs-target="#modalAdicionaCartao"
		            	<%if(pagamento.getTotalCompra().subtract(pagamento.getTotalAlocado()).doubleValue() == 0){%> disabled <%} %>>
		               Adicionar novo Cartao
		        	</button>
				</div>
			
			</div>	 	
				 	
		</div>
			
				<%if(cliente.getCartoes().size() > 0){%>
				<div class="row py-2">
					<% for(CartaoCredito cartao : cliente.getCartoes()){%>
					<div class="col-6">
				      <div class="card">
				        <div class="card-body">
				          <h5 class="card-title"><%=cartao.getNomeIdentificacao()%></h5>
				          <p class="card-text">
				            Titular: <%=cartao.getTitular()%> <br>
				            Numero: <%=cartao.getNumero()%> <br>
				            Bandeira: <%=cartao.getBandeira()%> <br>
				          </p>
				           <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSelect<%=cartao.getId()%>" aria-expanded="false" aria-controls="collapseExample">
							   Selecionar Cartao
						  </button>
						  
				      	  <div class="collapse" id="collapseSelect<%=cartao.getId()%>">
							   <form action="/EcomerceLivroLES/cli-selecionar-pagamento" method="post">
							   	<input type="hidden" name="txtIdCartao" value="<%=cartao.getId()%>">
								<input class="form-control" type="text" name="txtValor" required>
							  	<button type="submit" class="btn btn-danger" name="operacao" value="Selecionar">salvar</button>
							  	</form>
						  </div>
				        </div>
				      </div>
			     	</div>
			      <% }%>
			      </div>
				<%}else{%>
					<div class="alert alert-info" role="alert">
						Nao Ha Cartoes Salvos, Associados a Esse Perfil!
					</div>
				  <%}%>
				  				
			<div class="h4 pb-2 mb-4 text-dark border-bottom border-secundary">
				<div class="row">
					<div class="col-6">
				         Cartoes Selecionados
					</div>
				</div>
			</div>
				
			<table class="table">
					  <thead>
					    <tr>
					      <th scope="col">Numero</th>
					      <th scope="col">Bandeira</th>
					      <th scope="col">Valor a Ser pago</th>
					      <th scope="col"></th>
					    </tr>
					  </thead>
					  <tbody>
					  <%if(pagamento.getCartoes() !=null)
					  for(CartaoCompra c : pagamento.getCartoes()){%>
					    
					    <tr>
					      <td><%=c.getCartao().getNumero()%></td>
					      <td><%=c.getCartao().getBandeira()%></td>
					      <td>
					      
					      <%=c.getValor() %>
					     
				    	  <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapse<%=c.getCartao().getId()%>" aria-expanded="false" aria-controls="collapseExample">
						    Editar Valores
						  </button>
						  
				      	  <div class="collapse" id="collapse<%=c.getCartao().getId()%>">
							   <form action="/EcomerceLivroLES/cli-selecionar-pagamento" method="post">
							   	<input type="hidden" name="idCartao" value="<%=c.getId()%>">
								<input type="text" name="txtValor" value="<%=c.getValor()%>">
							  	<button type="submit" class="btn btn-danger" name="operacao" value="Alterar">salvar</button>
							  	</form>
						  </div>
				      	  
					      
					      </td>
				      	<td>
				      	
				      	  <form action="/EcomerceLivroLES/cli-selecionar-pagamento" method="post">
					      	<input type="hidden" name="idCartao" value="<%=c.getId()%>">
					      	<button type="submit" class="btn btn-danger" name="operacao" value="Remover">Remover</button>
					      </form>
				      	
			
					</td>
					
					    </tr>
					  <%} %>
					  </tbody>
					</table>	
				
				
			</div>
			
			<div class="col-5">
			
				<div class="card mb-3">
            		<div class="card-body">
		              <p class="card-text">
		              	Valor Total: R$ <%=pagamento.getTotalCompra()%><br>
		              </p>
		              <p class="card-text text-secondary">*Valor dos produtos: R$<%=pagamento.getCarrinho().getValorTotal()%></p>
		              <p class="card-text text-secondary">*Valor do frete: R$<%=pagamento.getEndereco().getFrete().getValor()%></p>
           		 	</div>
          		</div>
          		
			 <%if(pagamento.getTotalCompra().subtract(pagamento.getTotalAlocado()).doubleValue() > 0){ %>
			 <div class="alert alert-info" role="alert">
				Antes de Prosseguir, e preciso definir como sera pago o seguinte valor: R$<%=pagamento.getTotalCompra().subtract(pagamento.getTotalAlocado())%>
			</div>
			<%} %>
			
				<div class="h4 pb-2 mb-4 text-dark border-bottom border-secundary">
				 	Utilizar Cupom
				</div>
				<form action="/EcomerceLivroLES/cli-selecionar-pagamento" method="post">	
					<div class="input-group mb-3">		
							<input type="text" class="form-control" name="txtCodigo" placeholder="Cupom" aria-label="Recipient's username" aria-describedby="button-addon2">
						  	<button class="btn btn-outline-secondary" type="submit" name="operacao" value="Adicionar" id="button-addon2">Validar</button>
						
					</div>
				</form>
			
			
				<div class="h4 pb-2 mb-4 text-dark border-bottom border-secundary">
				 	Cupons Selecionados
				</div>
				 	
			 	<table class="table">
				  <thead>
				    <tr>
				      <th scope="col">Codigo</th>
				      <th scope="col">Valor</th>
				      <th scope="col">Tipo</th>
				      <th scope="col"></th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
			  if(pagamento.getCupons().size() > 0)
				  for(Cupom c : pagamento.getCupons()){%>
					  					
				    <tr>
				      <td><%=c.getCodigo()%></td>
				      <td><%=c.getValor() %></td>
				      <td><%=c.getTpCupom().getDescricao() %></td>
				      <td>
					      <form action="/EcomerceLivroLES/cli-selecionar-pagamento" method="post">
					      	<input type="hidden" name="idCupom" value="<%=c.getId()%>">
					      	<button type="submit" class="btn btn-danger" name="operacao" value="Remover">Remover</button>
					      </form>
				      </td>
				    </tr>
				
				  <%} %>
				  </tbody>
				</table>
				
			</div>
		</div>
      
    </div>
    
  </main>

 <div class="modal fade" id="modalAdicionaCartao" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Inserir Dados</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <form class="row g-3" action="/EcomerceLivroLES/cli-selecionar-pagamento" method="post">

            <div class="modal-body">
              
              <div class="container-fluid">

                <h3 class="display text-center">Cadastrar novo Cartao</h3>
        
                <div class="row py-1">
                  <div class="col-sm-6">
                      <label class="visually-hidden" for="cbbBandeira">Bandeira</label>
                      <select class="form-select" id="cbbBandeira" name="cbbBandeira">
                          <option value="0" selected>Bandeira do cartao...</option>
                          <option value="1">Visa</option>
                          <option value="2">Mastercard</option>
                      </select>
                  </div>

                  <div class="col-sm-6">
                      <label class="visually-hidden" for="txtTitular">Titular</label>
                      <input type="text" class="form-control" id="txtTitular" name="txtTitular" placeholder="Nome do Titular" required>
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-9">
                      <label class="visually-hidden" for="txtNumero">Numero</label>
                      <input type="text" class="form-control" id="txtNumero" name="txtNumero" placeholder="Numero do cartao" required>
                  </div>

                  <div class="col-sm-3">
                      <label class="visually-hidden" for="txtCVV">Codigo de seguranca</label>
                      <input type="text" class="form-control" id="txtCVV" name="txtCVV" placeholder="CVV">
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-6">
                      <label class="visually-hidden" for="txtValidade">Data de Validade</label>
                      <input type="text" class="form-control" id="txtValidade" name="txtValidade" placeholder="Data de Validade" required>
                  </div>

                  <div class="col-sm-6">
                      <label class="visually-hidden" for="txtNomeIdentificacao">Nome para identificacao</label>
                      <input type="text" class="form-control" id="txtNomeIdentificacao" name="txtNomeIdentificacao" placeholder="Nome para identificacao do cartao">
                  </div>
                </div>
            
            	<div class="row py-1">
            	
            	<div class="col-sm-6">
            		<div class="row">
            			<label for="txtValor" class="col-4 col-form-label">Valor a ser pago.</label>
            			<div class="col-8">
            				<input type="text" class="form-control" name="txtValor" id="txtValor" placeholder="Ex: 10,00">
            			</div>
            		</div>
            	</div>
            	
            	<div class="col-sm-6">
            		<div class="form-check form-switch form-check-reverse">
					  <input class="form-check-input" name="swtSalvarCartao" type="checkbox" id="flexSwitchCheckReverse">
					  <label class="form-check-label" for="flexSwitchCheckReverse">Salvar Cartao para futuras Compras</label>
					</div>
				</div>	
					
            	</div>
              </div>              

            </div>
        
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
          <button type="submit" class="btn btn-primary" name="operacao" value="Adicionar" >Cadastrar!</button>
        </div>

        </form>
        
      </div>
      </div>
    </div>   
	
	<script type="text/javascript">
		$("#txtCVV").mask("000");
        $("#txtNumero").mask("0000.0000.0000.0000");
        $("#txtValidade").mask("00/00");
        $("#txtValor").mask("9999,99");
    </script>

  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	
</body>
</html>