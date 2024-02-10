<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import= "java.util.List,dominio.cliente.Cliente,dominio.cliente.Endereco,dominio.cliente.Genero, dominio.venda.Pedido,
dominio.venda.Item, dominio.livro.Livro, dominio.livro.Autor,dominio.livro.Categoria, dominio.venda.ItemEstoque, dominio.venda.Estoque"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Item Detalhes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
	
	<%
		ItemEstoque item = (ItemEstoque) request.getSession().getAttribute("item");
		Livro livro = item.getLivro();
	%>
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
       
            <div class="row px-5 py-2">
              <div class="col-3 text-center">
                <input class="form-control" type="text" id="txtIdLivro"
                    value="Id: <%=item.getId()%>" aria-label="Disabled input example" disabled readonly>
              </div>
              <div class="col-9 text-center">
                <h1><%=livro.getTitulo()%></h1>
              </div>
            
            </div>
            
            <hr>

            <div class="row px-5 py-2">

                <div class="col-lg-3 ">
                  <figure class="figure">
                    <img src="imagens/capas/<%=livro.getUrlCapa()%>" class="figure-img img-fluid rounded" alt="<%=livro.getTitulo()%>>">
                  </figure>
                </div>
                <div class="col-lg-6">
                  
              <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                  <button class="nav-link active" id="profile-tab" data-bs-toggle="tab" data-bs-target="#detalhes-tab-pane" type="button" role="tab" aria-controls="detalhes-tab-pane" aria-selected="true">Detalhes do Produto</button>
                </li>
                <li class="nav-item" role="presentation">
                  <button class="nav-link " id="home-tab" data-bs-toggle="tab" data-bs-target="#sinopse-tab-pane" type="button" role="tab" aria-controls="sinopse-tab-pane" aria-selected="false">Sinopse</button>
                </li>
                
              </ul>
              <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade" id="sinopse-tab-pane" role="tabpanel" aria-labelledby="sinopse-tab" tabindex="0">
                  
                  <div class="card">
                    <div class="card-body">
                       <%=livro.getSinopse() %> 
                    </div>
                  </div>
                  
                  </div>
                  <div class="tab-pane fade  show active" id="detalhes-tab-pane" role="tabpanel" aria-labelledby="detalhes-tab" tabindex="0">
                    <div class="card mb-3">
                        
                           <div class="card-body">
                            <p class="card-text">
                              Autor(es): <%
                                    for(Autor s : livro.getAutores()){
                                      
                                      out.print(s.getNome());
                                      out.print("; ");
                                      
                                    }%> </br>
                              Categoria(s): <% 
                                    
                                      for(Categoria s : livro.getCategorias()){
                                      
                                      out.print(s.getDescricao());
                                      out.print("; ");
                                      
                                    }%> </br>
                              ISBN: <%=livro.getISBN()%>;  </br>
                              
                              Publicado no ano de : <%=livro.getAno()%>; </br>
                              Edicao: <%=livro.getEdicao()%>; </br>
                              Numero de Paginas: <%=livro.getNumPaginas()%>; </br>
                              Dimensoes: (Altura: <%=livro.getDimensao().getAltura()%>; X
                                     Largura: <%=livro.getDimensao().getLargura()%>
                                      X Profundidade: <%=livro.getDimensao().getProfundidade()%>) cm; </br>
                              Peso aproximado: <%=livro.getDimensao().getPeso()%> g.
                              
                            </p>
                            </div>
                        </div>
                    
                  </div>
                 
                </div>

                </div>
             

                <div class="col-3 text-center">

                  <input class="form-control" type="text" name="status" value="Status: <%if(item.isAtivo()){%>Ativo<%}else{%>Inativo<%}%>" disabled/>
                  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#alteraStatus">Alterar Status</button>                     
                  
                  <!--Modal para alterar Status-->
                  <div class="modal fade" id="alteraStatus" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h1 class="modal-title fs-5" id="exampleModalLabel">Alterar Status</h1>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="/EcomerceLivroLES/admin-estoque-detalhe" method="post">
                        <div class="modal-body">
                            <div class="mb-3">
                              <label for="cbbStatus">Status</label>
                              <select class="form-select" id="cbbStatus" name="cbbStatus">
                                  <option value="Ativo" <%if(item.isAtivo()){%>selected<%}%> >Ativo</option>
                                  <option value="Inativo" <%if(!item.isAtivo()){%>selected<%}%>> Inativo</option>
                              </select>
                            </div>
                            <div class="mb-3">
                              <label for="txtMotivo" class="col-form-label">Motivo da Mudaça:</label>
                              <textarea class="form-control" id="txtMotivo" name="txtMotivo"
                              placeholder="Digite o motivo para a mudança de status." minlength="10" required></textarea>
                            </div>
                          
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Voltar</button>
                          <input type="hidden" name="idItem" value="<%=item.getId()%>"/>
                          <button type="submit" class="btn btn-primary" name="operacao" value="alterar">Salvar Status</button>
                        </div>
                      </form>
                      </div>
                    </div>
                  </div>

                  <hr>
                  <input class="form-control" type="text" name="status" value="Estoque Disponivel = <%=item.getQuantidade()%>" disabled/>
                  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#adicionaEstoque">Adicionar Estoque</button>                     

                  <!--Modal para adicionar Estoque-->
                  <div class="modal fade" id="adicionaEstoque" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h1 class="modal-title fs-5" id="exampleModalLabel">Adicionar Estoque</h1>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="/EcomerceLivroLES/admin-estoque-detalhe" method="post">
                        <div class="modal-body">
	         	
                          <div class="col">
                            <input type="text" class="form-control" id="txtQnt" placeholder="Digite a Quantidade a Ser Adicionada:" name="txtQnt" onkeypress="return event.charCode >= 48 && event.charCode <= 57">
                          </div> 

                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Voltar</button>
                          <input type="hidden" name="idItem" value="<%=item.getId()%>"/>
                          <button type="submit" class="btn btn-primary" name="operacao" value="Alterar">Adicionar</button>
                        </div>
                      </form>
                      </div>
                    </div>
                  </div>

					tabelinha da busca historiada desse item;
					

                </div>
                        
            </div>
            <hr>

			<form action="/EcomerceLivroLES/adm-estoque" method="post">
				<button type="submit" class="btn btn-secondary">Voltar</button>
			</form>
            	

      </main>


       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>

</body>
</html>