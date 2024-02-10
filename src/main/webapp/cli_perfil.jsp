<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import= "java.util.List,dominio.cliente.Endereco,dominio.cliente.Cliente,dominio.cliente.Genero,
    dominio.cliente.TipoTelefone, dominio.venda.CartaoCredito, dominio.venda.Cupom, dominio.venda.TipoCupom"%>
<!doctype html>
<html lang="bt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Perfil Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js" integrity="sha512-pHVGpX7F/27yZ0ISY+VVjyULApbDlD0/X0rgGbTqCE7WFW5MezNTWG/dnhtbBuICzsd0WQPgpE4REBLv+UqChw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <%Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
    String mensagem = (String) request.getSession().getAttribute("mensagem");
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

      <div class="row align-items-md-center">
        <div class="row align-items-md-center">
          <div class="col-md-3">
            <h3>Sua Conta</h3>
          </div>
          
          <div class="col-md-9">
 			 <% if(mensagem != null){%>
	    		
		    		<div class="alert alert-warning alert-dismissible fade show" role="alert">
						<%=mensagem%>
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
					
					<%request.getSession().setAttribute("mensagem", null);
					
 			 }%>
          </div>
        </div>
      </div>

      <hr class="my-4">
          
			<ul class="nav nav-tabs" id="myTab" role="tablist">
			  <li class="nav-item" role="presentation">
			    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#infos-tab-pane" type="button" role="tab" aria-controls="infos-tab-pane" aria-selected="true">
			    	Informações Pessoais
			    </button>
			  </li>
			  <li class="nav-item" role="presentation">
			    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#ends-tab-pane" type="button" role="tab" aria-controls="ends-tab-pane" aria-selected="false">
			    	Enderecos Cadastrados
			    </button>
			   </li>
			    <li class="nav-item" role="presentation">
			    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#cards-tab-pane" type="button" role="tab" aria-controls="cards-tab-pane" aria-selected="false">
			    	Cartoes Cadastrados
			    </button>
			   </li>
			   <li class="nav-item" role="presentation">
			    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#login-tab-pane" type="button" role="tab" aria-controls="login-tab-pane" aria-selected="false">
			    	Dados de Login
			    </button>
			   </li>
			   <li class="nav-item" role="presentation">
			    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#cupons-tab-pane" type="button" role="tab" aria-controls="cupons-tab-pane" aria-selected="false">
			    	Cupons
			    </button>
			   </li>
			</ul>
			
			<div class="tab-content" id="myTabContent">
			  <div class="tab-pane fade show active" id="infos-tab-pane" role="tabpanel" aria-labelledby="infos-tab" tabindex="0">
			  	
			  	<div class="card">
				  <div class="card-body">
				  <form action="/EcomerceLivroLES/cli-alterar" method="post">
					  <div class="row">
           			 	
           			 	<div class="col-8">
           			 		<div class="row py-1">
                                <div class="col-sm-12">
                                	<div class="form-floating">
	                                    
	                                    <input type="text" class="form-control" id="txtNome" name="txtNome" placeholder="Nome Completo"
	                                    value="<%=cliente.getNome()%>"> 
	                                    
	                                    <label for="txtNome">Nome Completo</label> 
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row py-1">
                                <div class="col-sm-4">
                                    <div class="form-floating">
                                        <input type="date" class="form-control" id="txtDtNasc" name="txtDtNasc"
                                        value=<%=cliente.getDtNascimento() %>>
                                        <label for="txtDtNasc">Data de Nascimento</label>
                                    </div>
                                </div>
                        
                                <div class="col-sm-4">
                                	<div class="form-floating">
	                                    <input type="text" class="form-control" id="txtCPF" name="txtCPF" placeholder="CPF"
	                                    value=<%=cliente.getCpf()%>>
	                                    <label for="txtCPF">CPF</label>
                                    </div>
                                </div>

                                <div class="col-sm-4">
                                	<div class="form-floating">
                                    
	                                    <select class="form-select" id="cbbGenero" name="cbbGenero">
	                                        <option value="1" <%if(cliente.getGenero().getValor() == 1){%>selected<%}%>>Masculino</option>
	                                        <option value="2" <%if(cliente.getGenero().getValor() == 2){%>selected<%}%>>Feminino</option>
	                                        <option value="3" <%if(cliente.getGenero().getValor() == 3){%>selected<%}%>>Nao Binario</option>
	                                    </select>
	                                    
	                                    <label for="cbbGenero">Genero</label>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row py-1">
                                <div class="col-sm-6">
                                	<div class="form-floating">

	                                    <select class="form-select" id="cbbTpTelefone" name="cbbTpTelefone">
	                                        <option value="1" <%if(cliente.getTelefone().getTpTelefone().getValor() == 1){%>selected<%}%>>Fixo</option>
	                                        <option value="2" <%if(cliente.getTelefone().getTpTelefone().getValor() == 2){%>selected<%}%>>Celular</option>
	                                    </select>
	                                    
	                                    <label for="cbbTpTelefone">Tipo de Telefone</label>
                                    </div>
                                </div>
                            
                                <div class="col-sm-2">
                                	<div class="form-floating">
	                                    
	                                    <input type="text" class="form-control" id="txtDDD" name="txtDDD" placeholder="DDD"
	                                    value=<%=cliente.getTelefone().getDdd()%>>
	                                    
	                                    <label for="txtDDD">DDD</label>
                                    </div>
                                </div>
                                
                                <div class="col-sm-4">
                                	<div class="form-floating">
	                                    
	                                    <input type="text" class="form-control" id="txtTelefone" name="txtTelefone" placeholder="Telefone"
	                                    value=<%=cliente.getTelefone().getNumero()%>>
	                                    
	                                    <label for="txtTelefone">Numero do Telefone</label>
                                    </div>
                                </div>
                           	</div>
           				</div>
           				
           				<div class="col-4 text-center">
           					<button type="submit" class="btn btn-danger" name="operacao" value="Alterar">Alterar Dados</button>
           					<hr>
           					<p>
           					Nessa seção estão apresentados seus dados pessoais cadastrados. Para alterar alguma informação, basta
           					clicar na caixa de entrada correspondente, editar os dados e clicar no botão de alterar dados para confirmar.
           					</p>
           				</div>
           		      </div>
           			
				  </form>
				   	
				  </div>
				</div>
			  	
			  </div>
			  
			  <div class="tab-pane fade" id="ends-tab-pane" role="tabpanel" aria-labelledby="ends-tab" tabindex="0">
			  	<div class="card mb-3">
            	
           			<div class="card-body">
           			
		             <div class="row">
           			 	
           			 	<div class="col-8">
		              <table class="table display" id="table_enderecos">
        
			            <thead>
			              <tr>
			                <th scope="col" class="text-center">Nome Identificacao</th>
			                <th scope="col" class="text-center">Logradouro</th>
			                <th scope="col" class="text-center">Numero</th>
			                <th scope="col" class="text-center">CEP</th>
			                <th scope="col" class="text-center">Opcoes</th>
			              </tr>
			            </thead>
			    	
			            <tbody>
			              <%for(Endereco endereco : cliente.getEnderecos()){%>
			              <tr>
			                <td class="text-center align-middle"><%=endereco.getNomeIdentificacao()%></td>
			                <td class="text-center align-middle"><%=endereco.getLogradouro()%></td>
			                <td class="text-center align-middle"><%=endereco.getNumero()%></td>
			                <td class="text-center align-middle"><%=endereco.getCep()%></td>
			                <td class="text-center align-middle">
			                  <div class="text-center align-middle">
			                  <form action="/EcomerceLivroLES/cli-endereco" method="post">
			                    <input type="hidden" name="txtEnderecoId" value="<%=endereco.getId()%>">
			                  
			                    <button class="btn btn-danger" name="operacao" value="Excluir" 
			                    <%if(cliente.getEnderecos().size() < 2){%> disabled <%} %>>
			                  	  Excluir 
			                    </button>
			                  
			                  </form>
							  </div>        
			                </td>
			              </tr>
						  <%}%>
			            </tbody>
						
		            </table>    
		              </div>
		              
		              <div class="col-4 text-center">
           					<button type="button" class="btn btn-primary" 
				            	data-bs-toggle="modal" data-bs-target="#modalSalvaEnd">
				              Adicionar Novo Endereco
				        	</button>
           					
           					<hr>
           					<p>
           					Nessa seção estão apresentados seus enderecos cadastrados. Para adicionar algum novo, basta
           					clicar no botão, então uma caixa será aberta para digitar as informações.
           					Caso queira remover um endereço (Disponivel apenas se houver mais de um cadastrado), basta clicar
           					no botão "Excluir" ao lado do endereco na tabela.
           					</p>
           				</div>
		              
		             </div>
           		 	</div>
          		</div>
          		
          		 <div class="modal fade" id="modalSalvaEnd" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				      <div class="modal-dialog modal-lg">
				        <div class="modal-content">
				          <div class="modal-header">
				              <h5 class="modal-title" id="exampleModalLabel">Inserir Dados</h5>
				              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				          </div>
				
				          <form class="row g-3" action="/EcomerceLivroLES/cli-endereco" method="post">
				
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
				                      <label class="visually-hidden" for="txtNum">Numero Casa</label>
				                      <input type="text" class="form-control" id="txtNum" name="txtNumero" placeholder="Numero">
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
				
				              </div>              
				
				            </div>
				        
					        <div class="modal-footer">
					          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
					          <button type="submit" class="btn btn-primary" name="operacao" value="Salvar">Salvar!</button>
					        </div>
				
				        </form>
				        
				      </div>
				      </div>
				    </div>   
		
			  </div>
			  
			   <div class="tab-pane fade" id="cards-tab-pane" role="tabpanel" aria-labelledby="cards-tab" tabindex="0">
			  	<div class="card mb-3">
            	
           			<div class="card-body">
           			 <div class="row">
           			 	
           			 	<div class="col-8">
           			 	
		             <table class="table display" id="table_cartoes">
        
			            <thead>
			              <tr>
			                <th scope="col" class="text-center">Nome Identificacao</th>
			                <th scope="col" class="text-center">Numero</th>
			                <th scope="col" class="text-center">Bandeira</th>
			                <th scope="col" class="text-center">Opcoes</th>
			              </tr>
			            </thead>
			    	
			            <tbody>
			              <%for(CartaoCredito cartao : cliente.getCartoes()){%>
			              <tr>
			                <td class="text-center align-middle"><%=cartao.getNomeIdentificacao()%></td>
			                <td class="text-center align-middle"><%=cartao.getNumero()%></td>
			                <td class="text-center align-middle"><%=cartao.getBandeira()%></td>
			                <td class="text-center align-middle">
			                  <div class="text-center align-middle">
			                  <form action="/EcomerceLivroLES/cli-cartao" method="post">
			                    <input type="hidden" name="txtIdCartao" value="<%=cartao.getId()%>">
			                    <button class="btn btn-danger" name="operacao" value="Excluir" >Excluir</button>
			                  </form>
							  </div>        
			                </td>
			              </tr>
						  <%}%>
			            </tbody>
						
		            </table>  
		            </div>
		            <div class="col-4 text-center">
			            <button type="button" class="btn btn-primary" 
			            	data-bs-toggle="modal" data-bs-target="#modalAdicionaCartao">
			               Adicionar novo Cartao
			        	</button>
           					
           					<hr>
           					<p>
           					Nessa seção estão apresentados seus cartoes cadastrados. Para adicionar algum novo, basta
           					clicar no botão, então uma caixa será aberta para digitar as informações.
           					Caso queira remover um cartao, basta clicar
           					no botão "Excluir" ao lado de seu registro na tabela.
           					</p>
           				</div>
		              
		             </div>
		            
           		 	</div>
          		</div>
          
           <div class="modal fade" id="modalAdicionaCartao" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		      <div class="modal-dialog modal-lg">
		        <div class="modal-content">
		          <div class="modal-header">
		              <h5 class="modal-title" id="exampleModalLabel">Inserir Dados</h5>
		              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		          </div>
		
		          <form class="row g-3" action="/EcomerceLivroLES/cli-cartao" method="post">
		
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
		            
		              </div>              
		
		            </div>
		        
		        <div class="modal-footer">
		          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
		          <button type="submit" class="btn btn-primary" name="operacao" value="Salvar" >Salvar!</button>
		        </div>
		
		        </form>
		        
		      </div>
		      </div>
		    </div>   
			
			  </div>
			 
			 <div class="tab-pane fade" id="login-tab-pane" role="tabpanel" aria-labelledby="login-tab" tabindex="0">
			  	<div class="card mb-3">
            	
           			<div class="card-body">
           				<form action="/EcomerceLivroLES/cli-alterar-login" method="post">
		             	 <div class="row">
           			 	
           			 	<div class="col-8">
           			 	
		             	<div class="row py-1">
                              <div class="col-sm-6">
                              	<div class="form-floating">
                                  
                                  <input type="text" class="form-control" id="txtEmail" name="txtEmail" placeholder="Email"
                                  value=<%=cliente.getUsuario().getEmail()%> readonly>
                                  
                                  <label for="txtDDD">Email</label>
                                </div>
                              </div>
                              
                              <div class="col-sm-6">
                                  <div class="form-floating">
					                <input type="password" class="form-control" id="floatingPassword" name="txtSenhaAtual" placeholder="Password" 
					                    autocomplete="off" required>
					                <label for="floatingPassword">Senha Atual</label>
					            </div>
                              </div>
                         </div>
                         <div class="row py-1"> 
                              
                              <div class="col-sm-6">
                                 <div class="form-floating">
					                <input type="password" class="form-control" id="floatingPassword" name="txtNovaSenha" placeholder="Password" 
					                    autocomplete="off" required>
					                <label for="floatingPassword">Nova Senha</label>
					            </div>
                              </div>
                              
                              <div class="col-sm-6">
                                 <div class="form-floating">
					                <input type="password" class="form-control" id="floatingPassword" name="txtConfirmarSenha" placeholder="Password" 
					                     autocomplete="off" required>
					                <label for="floatingPassword">Confirmar Nova Senha</label>
					            </div>
                              </div>
                              
                         </div>
		             </div>
		             
		              <div class="col-4 text-center">
           					<button type="submit" class="btn btn-danger" name="operacao" value="Alterar">Alterar Senha</button>
           					<hr>
           					<p>
           					Nessa sessão estão apresentados seus dados de Login. Para alterar a senha, basta
           					informar sua senha atual, a nova senha desejada e confirma-la novamente.
           					</p>
           				</div>
		              
		             </div>
		             
		             	</form>
           		 	</div>
           		 	
          		</div>
          
			  </div>
			</div>
			
			
			
			  <div class="tab-pane fade" id="cupons-tab-pane" role="tabpanel" aria-labelledby="cupons-tab" tabindex="0">
			  	<div class="card mb-3">
            	
           			<div class="card-body">
           			
		             <div class="row">
           			 	
           			 	<div class="col-8">
		              <table class="table display" id="table_cupons">
        
			            <thead>
			              <tr>
			                <th scope="col" class="text-center">Código</th>
			                <th scope="col" class="text-center">Valor</th>
			                <th scope="col" class="text-center">Tipo</th>
			              </tr>
			            </thead>
			    	
			            <tbody>
			              <%for(Cupom cupom : cliente.getCupons()){%>
			              <tr>
			                <td class="text-center align-middle"><%=cupom.getCodigo()%></td>
			                <td class="text-center align-middle"><%=cupom.getValor()%></td>
			                <td class="text-center align-middle"><%=cupom.getTpCupom().getDescricao()%></td>
			                
			              </tr>
						  <%}%>
			            </tbody>
						
		            </table>    
		              </div>
		              
		              <div class="col-4 text-center">
           					
           					<hr>
           					<p>
           					Nessa seção estão apresentados seus cupons.
           					</p>
           				</div>
		              
		             </div>
           		 	</div>
          		</div>
          		
			  </div>
			  
			
			
    </main>

	 <script type="text/javascript">
        $("#txtCPF").mask("000.000.000-00");
        $("#txtDDD").mask("(00)");
        $("#txtTelefone").mask("0000-00009");
        $("#txtCep").mask("00.000-000");
        $("#txtCVV").mask("000");
        $("#txtNumero").mask("0000.0000.0000.0000");
        $("#txtValidade").mask("00/00");
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  </body>
</html>