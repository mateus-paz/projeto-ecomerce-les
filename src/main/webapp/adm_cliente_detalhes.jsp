<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import= "java.util.List,dominio.cliente.Cliente,dominio.cliente.Endereco,dominio.cliente.Genero, 
	dominio.venda.Pedido, dominio.cliente.TipoTelefone, dominio.cliente.Telefone"%>    
    
  <!DOCTYPE html>
    <html lang="pt-br">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
            <title>Perfil Cliente</title>
        
     	      <% Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
     	      	List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");%>    
     	      	
     	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			
			<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
			<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js"></script>
     	      
        </head>
        <body>
    
            <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
         <symbol id="home" viewBox="0 0 16 16">
           <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5z"/>
         </symbol>
         <symbol id="speedometer2" viewBox="0 0 16 16">
           <path d="M8 4a.5.5 0 0 1 .5.5V6a.5.5 0 0 1-1 0V4.5A.5.5 0 0 1 8 4zM3.732 5.732a.5.5 0 0 1 .707 0l.915.914a.5.5 0 1 1-.708.708l-.914-.915a.5.5 0 0 1 0-.707zM2 10a.5.5 0 0 1 .5-.5h1.586a.5.5 0 0 1 0 1H2.5A.5.5 0 0 1 2 10zm9.5 0a.5.5 0 0 1 .5-.5h1.5a.5.5 0 0 1 0 1H12a.5.5 0 0 1-.5-.5zm.754-4.246a.389.389 0 0 0-.527-.02L7.547 9.31a.91.91 0 1 0 1.302 1.258l3.434-4.297a.389.389 0 0 0-.029-.518z"/>
           <path fill-rule="evenodd" d="M0 10a8 8 0 1 1 15.547 2.661c-.442 1.253-1.845 1.602-2.932 1.25C11.309 13.488 9.475 13 8 13c-1.474 0-3.31.488-4.615.911-1.087.352-2.49.003-2.932-1.25A7.988 7.988 0 0 1 0 10zm8-7a7 7 0 0 0-6.603 9.329c.203.575.923.876 1.68.63C4.397 12.533 6.358 12 8 12s3.604.532 4.923.96c.757.245 1.477-.056 1.68-.631A7 7 0 0 0 8 3z"/>
         </symbol>
         <symbol id="table" viewBox="0 0 16 16">
           <path d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm15 2h-4v3h4V4zm0 4h-4v3h4V8zm0 4h-4v3h3a1 1 0 0 0 1-1v-2zm-5 3v-3H6v3h4zm-5 0v-3H1v2a1 1 0 0 0 1 1h3zm-4-4h4V8H1v3zm0-4h4V4H1v3zm5-3v3h4V4H6zm4 4H6v3h4V8z"/>
         </symbol>
         <symbol id="people-circle" viewBox="0 0 16 16">
           <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
           <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
         </symbol>
         <symbol id="grid" viewBox="0 0 16 16">
           <path d="M1 2.5A1.5 1.5 0 0 1 2.5 1h3A1.5 1.5 0 0 1 7 2.5v3A1.5 1.5 0 0 1 5.5 7h-3A1.5 1.5 0 0 1 1 5.5v-3zM2.5 2a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 1h3A1.5 1.5 0 0 1 15 2.5v3A1.5 1.5 0 0 1 13.5 7h-3A1.5 1.5 0 0 1 9 5.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zM1 10.5A1.5 1.5 0 0 1 2.5 9h3A1.5 1.5 0 0 1 7 10.5v3A1.5 1.5 0 0 1 5.5 15h-3A1.5 1.5 0 0 1 1 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 9h3a1.5 1.5 0 0 1 1.5 1.5v3a1.5 1.5 0 0 1-1.5 1.5h-3A1.5 1.5 0 0 1 9 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3z"/>
         </symbol>
         <symbol id="new" viewBox="0 0 24 24">
             <path d="M12,2C6.477,2,2,6.477,2,12s4.477,10,10,10s10-4.477,10-10S17.523,2,12,2z M17,13h-4v4h-2v-4H7v-2h4V7h2v4h4V13z"/>
         </symbol>
     </svg> 
    
    <header class="navbar navbar-dark bg-dark sticky-top">
          
          <ul class="nav col-12 col-lg-auto mx-5 my-2 justify-content-center my-md-0 text-small border-bottom mb-3">
            <li>
              <a href="/EcomerceLivroLES/adm-home" class="nav-link text-white">
                <svg class="bi d-block mx-auto mb-1" width="24" height="24"><use xlink:href="#home"></use></svg>
                Home
              </a>
            </li>
            <li>
              <a href="/EcomerceLivroLES/adm-grafico" class="nav-link text-white">
                <svg class="bi d-block mx-auto mb-1" width="24" height="24"><use xlink:href="#speedometer2"></use></svg>
                Grafico
              </a>
            </li>
            <li>
              <a href="/EcomerceLivroLES/adm-pedidos" class="nav-link text-white">
                <svg class="bi d-block mx-auto mb-1" width="24" height="24"><use xlink:href="#table"></use></svg>
                Pedidos
              </a>
            </li>
            <li>
              <a href="/EcomerceLivroLES/adm-pedidos-troca" class="nav-link text-white">
                <svg class="bi d-block mx-auto mb-1" width="24" height="24"><use xlink:href="#table"></use></svg>
                Trocas
              </a>
            </li>
            <li>
           	  <a href="/EcomerceLivroLES/adm-estoque" class="nav-link text-white">
                <svg class="bi d-block mx-auto mb-1" width="24" height="24"><use xlink:href="#grid"></use></svg>
                Estoque
              </a>
            </li>
            <li>
              <a href="/EcomerceLivroLES/adm-clientes" class="nav-link text-white">
                <svg class="bi d-block mx-auto mb-1" width="24" height="24"><use xlink:href="#people-circle"></use></svg>
                Clientes 
              </a> 
            </li>
            
          </ul>
          	
          <div class="text-end text-white mx-5 py-1 border-bottom">
            <a href="/EcomerceLivroLES/login" class="nav-link text-white">
            	Log Out
            </a>
          </div>	
          	
     </header>
     
         <main class="container">
               
         <h1 class="display-5 fw-normal text-center py-2">Perfil Cliente Nº: <%=cliente.getId()%></h1>   
          
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
			    	Transacoes Realizadas
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
	                                    value="<%=cliente.getNome()%>" readonly> 
	                                    
	                                    <label for="txtNome">Nome Completo</label> 
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row py-1">
                                <div class="col-sm-4">
                                    <div class="form-floating">
                                        <input type="date" class="form-control" id="txtDtNasc" name="txtDtNasc"
                                        value=<%=cliente.getDtNascimento() %> readonly>
                                        <label for="txtDtNasc">Data de Nascimento</label>
                                    </div>
                                </div>
                        
                                <div class="col-sm-4">
                                	<div class="form-floating">
	                                    <input type="text" class="form-control" id="txtCPF" name="txtCPF" placeholder="CPF"
	                                    value=<%=cliente.getCpf()%> readonly>
	                                    <label for="txtCPF">CPF</label>
                                    </div>
                                </div>

                                <div class="col-sm-4">
                                	<div class="form-floating">
                                    
                                    	<input type="text" class="form-control" id="cbbGenero" name="cbbGenero" placeholder="Genero"
	                                    value=<%=cliente.getGenero()%> readonly>
	                                    
	                                    <label for="cbbGenero">Genero</label>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row py-1">
                                <div class="col-sm-6">
                                	<div class="form-floating">

                                    	<input type="text" class="form-control" id="cbbTpTelefone" name="cbbTpTelefone" placeholder="Tipo Telefone"
	                                    value=<%=cliente.getTelefone().getTpTelefone()%> readonly>
	                                    	                  
	                                    <label for="cbbTpTelefone">Tipo de Telefone</label>
                                    </div>
                                </div>
                            
                                <div class="col-sm-2">
                                	<div class="form-floating">
	                                    
	                                    <input type="text" class="form-control" id="txtDDD" name="txtDDD" placeholder="DDD"
	                                    value=<%=cliente.getTelefone().getDdd()%> readonly>
	                                    
	                                    <label for="txtDDD">DDD</label>
                                    </div>
                                </div>
                                
                                <div class="col-sm-4">
                                	<div class="form-floating">
	                                    
	                                    <input type="text" class="form-control" id="txtTelefone" name="txtTelefone" placeholder="Telefone"
	                                    value=<%=cliente.getTelefone().getNumero()%> readonly>
	                                    
	                                    <label for="txtTelefone">Numero do Telefone</label>
                                    </div>
                                </div>
                           	</div>
                           	
                           	<div class="row py-1">
	                           	<div class="col-sm-6">
	                              	<div class="form-floating">
	                                  
	                                  <input type="text" class="form-control" id="txtEmail" name="txtEmail" placeholder="Email"
	                                  value=<%=cliente.getUsuario().getEmail()%> readonly>
	                                  
	                                  <label for="txtEmail">Email</label>
	                                </div>
	                              </div>
                              </div>
                           	
           				</div>
           				
           				<div class="col-4 text-center">
           					<hr>
           					<p>
           					Nessa sessão estão apresentados os dados pessoais cadastrados do Cliente selecionado. 
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
			              </tr>
			            </thead>
			    	
			            <tbody>
			              <%for(Endereco endereco : cliente.getEnderecos()){%>
			              <tr>
			                <td class="text-center align-middle"><%=endereco.getNomeIdentificacao()%></td>
			                <td class="text-center align-middle"><%=endereco.getLogradouro()%></td>
			                <td class="text-center align-middle"><%=endereco.getNumero()%></td>
			                <td class="text-center align-middle"><%=endereco.getCep()%></td>
			                
			              </tr>
						  <%}%>
			            </tbody>
						
		            </table>    
		              </div>
		              
		              <div class="col-4 text-center">
           					         					
           					<hr>
           					<p>
           					Nessa sessão estão apresentados seus enderecos cadastrados, do Cliente Selecionado. 
           				</div>
		              
		             </div>
           		 	</div>
          		</div>
          		
				    </div>   
		
			  </div>
			  
			   <div class="tab-pane fade" id="cards-tab-pane" role="tabpanel" aria-labelledby="cards-tab" tabindex="0">
			  	<div class="card mb-3">
            	
           			<div class="card-body">
           			 <div class="row">
           			 	
           			 	<div class="col-8">
           			 	
		             <table class="table display" id="table_pedidos">
        
			            <thead>
			              <tr>
			                <th scope="col" class="text-center">Id</th>
			                <th scope="col" class="text-center">Status</th>
			                <th scope="col" class="text-center">Data</th>
			                 <th scope="col" class="text-center">Valor</th>
			                <th scope="col" class="text-center">Opcoes</th>
			              </tr>
			            </thead>
			    	
			            <tbody>
			              <%for(Pedido pedido : pedidos){%>
			              <tr>
			                <td class="text-center align-middle"><%=pedido.getId()%></td>
			                <td class="text-center align-middle"><%=pedido.getStatus().getDescricao()%></td>
			                <td class="text-center align-middle"><%=pedido.getDtCadastro()%></td>
			                <td class="text-center align-middle"><%=pedido.getValorTotal()%></td>
			                <td class="text-center align-middle">
			                  <div class="text-center align-middle">
			                  <form action="/EcomerceLivroLES/adm-pedido-detalhes" method="post">
			                    <input type="hidden" name="idPedido" value="<%=pedido.getId()%>">
			                    <button class="btn btn-info" name="operacao" value="ConsultarPorId" >Mais Informações</button>
			                  </form>
							  </div>        
			                </td>
			              </tr>
						  <%}%>
			            </tbody>
						
		            </table>  
		            </div>
		            <div class="col-4 text-center">
                    	<hr>
      					<p>
      					Nessa sessão estão apresentados os pedidos realizados pelo Cliente Selecionado.
      					</p>
      				</div>
		              
		             </div>
		            
           		 	</div>
          		</div>
          
             </div>
			 
	</main>    
        
            <script type="text/javascript">
	          $(document).ready( function () {
	        	    $('#table_pedidos').DataTable();
	        	} );
	          
	          $('#table_pedidos').dataTable( {
	        	  "lengthMenu": [ 3, 4, 5 ]
	        	} );
	          
	          $(document).ready( function () {
	        	    $('#table_enderecos').DataTable();
	        	} );
	          
	          $('#table_enderecos').dataTable( {
	        	  "lengthMenu": [ 3, 4, 5 ]
	        	} );
	         </script>  
                      
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
            <script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
    
        </body>
    </html>