<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="bt-br">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
      crossorigin="anonymous"
    />
    <title>Login</title>

    <style>
      .text-center {
        text-align: center;
      }

      .form-signin {
        max-width: 330px;
        padding: 15px;
      }

      .m-auto {
        margin: auto;
        margin-top: auto;
        margin-right: auto;
        margin-bottom: auto;
        margin-left: auto;
      }

      .w-100 {
        width: 100%;
      }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"
      integrity="sha512-pHVGpX7F/27yZ0ISY+VVjyULApbDlD0/X0rgGbTqCE7WFW5MezNTWG/dnhtbBuICzsd0WQPgpE4REBLv+UqChw=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    ></script>

    <% String mensagem = (String) request.getSession().getAttribute("mensagem");
    request.getSession().invalidate(); %>
  </head>

  <body class="text-center">
    <main class="container">
      <div class="position-absolute top-50 start-50 translate-middle">
        <% if(mensagem != null){%>
        <div class="row py-5">
          <div
            class="alert alert-warning alert-dismissible fade show"
            role="alert"
          >
            <%=mensagem%>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="alert"
              aria-label="Close"
            ></button>
          </div>
          <%request.getSession().setAttribute("mensagem", null);%>
        </div>
        <%}%>

        <div class="form-signin w-100 m-auto">
          <form
            class="form-container"
            action="/EcomerceLivroLES/autenticar"
            method="post"
          >
            <h1 class="h3 mb-3 fw-normal">Por favor, faça login</h1>

            <div class="form-floating">
              <input
                type="email"
                class="form-control"
                id="floatingInput"
                placeholder="name@example.com"
                name="txtEmail"
                required
              />
              <label for="floatingInput">Email</label>
            </div>

            <div class="form-floating">
              <input
                type="password"
                class="form-control"
                id="floatingPassword"
                name="txtSenha"
                placeholder="Password"
                minlength="8"
                autocomplete="off"
                required
              />
              <label for="floatingPassword">Senha</label>
            </div>

            <button
              type="submit"
              class="w-100 btn btn-lg btn-primary"
              name="operacao"
              value="Consultar"
            >
              Entrar
            </button>
          </form>

          <p class="text-secondary">Ou entao...</p>

          <button
            type="button"
            class="btn btn-secundary"
            data-bs-toggle="modal"
            data-bs-target="#modalCadastro"
          >
            Clique aqui para se cadastrar
          </button>
        </div>
      </div>
    </main>

    <!--Modal para Cadastro de Clientes-->
    <div
      class="modal fade"
      id="modalCadastro"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              Cadastro de Cliente
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>

          <form
            class="row g-3"
            action="/EcomerceLivroLES/cli-cadastrar"
            method="post"
          >
            <div class="modal-body">
              <div class="container-fluid">
                <h3 class="display text-center">Dados Pessoais</h3>

                <div class="row py-1">
                  <div class="col-sm-12">
                    <label class="visually-hidden" for="txtNome"
                      >Nome Completo</label
                    >
                    <input
                      type="text"
                      class="form-control"
                      id="txtNome"
                      name="txtNome"
                      placeholder="Nome Completo"
                    />
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-4">
                    <div class="form-floating">
                      <input
                        type="date"
                        class="form-control"
                        id="txtDtNasc"
                        name="txtDtNasc"
                      />
                      <label for="txtDtNasc">Data de Nascimento</label>
                    </div>
                  </div>

                  <div class="col-sm-4">
                    <label class="visually-hidden" for="txtCPF">CPF</label>
                    <input
                      type="text"
                      class="form-control"
                      id="txtCPF"
                      name="txtCPF"
                      placeholder="CPF"
                    />
                  </div>

                  <div class="col-sm-4">
                    <label class="visually-hidden" for="cbbGenero"
                      >Genero</label
                    >
                    <select class="form-select" id="cbbGenero" name="cbbGenero">
                      <option value="0" selected>
                        Selecione seu Genero...
                      </option>
                      <option value="1">Masculino</option>
                      <option value="2">Feminino</option>
                      <option value="3">Nao Binario</option>
                    </select>
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-6">
                    <label class="visually-hidden" for="cbbTpTelefone"
                      >Tipo de Telefone</label
                    >
                    <select
                      class="form-select"
                      id="cbbTpTelefone"
                      name="cbbTpTelefone"
                    >
                      <option value="0" selected>
                        Selecione seu Tipo de Telefone...
                      </option>
                      <option value="1">Fixo</option>
                      <option value="2">Celular</option>
                    </select>
                  </div>

                  <div class="col-sm-2">
                    <label class="visually-hidden" for="txtDDD">DDD</label>
                    <input
                      type="text"
                      class="form-control"
                      id="txtDDD"
                      name="txtDDD"
                      placeholder="DDD"
                    />
                  </div>

                  <div class="col-sm-4">
                    <label class="visually-hidden" for="txtTelefone"
                      >Telefone</label
                    >
                    <input
                      type="text"
                      class="form-control"
                      id="txtTelefone"
                      name="txtTelefone"
                      placeholder="Telefone"
                    />
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-12">
                    <label class="visually-hidden" for="txtEmail">Email</label>
                    <input
                      type="email"
                      class="form-control"
                      id="txtEmail"
                      name="txtEmail"
                      placeholder="Email"
                    />
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-6">
                    <label class="visually-hidden" for="txtSenha">Senha</label>
                    <input
                      type="password"
                      class="form-control"
                      id="txtSenha"
                      name="txtSenha"
                      placeholder="Defina uma Senha"
                    />
                  </div>

                  <div class="col-sm-6">
                    <label class="visually-hidden" for="txtConfirmarSenha"
                      >Confirmar Senha</label
                    >
                    <input
                      type="password"
                      class="form-control"
                      id="txtConfirmarSenha"
                      name="txtConfirmarSenha"
                      placeholder="Confirme Sua Senha"
                    />
                  </div>
                </div>

                <h3 class="display text-center">Endereco Residencial</h3>

                <div class="row py-1">
                  <div class="col-sm-6">
                    <label class="visually-hidden" for="cbbTpResidencia"
                      >Tipo Residencia</label
                    >
                    <select
                      class="form-select"
                      id="cbbTpResidencia"
                      name="cbbTpResidencia"
                    >
                      <option value="0" selected>Tipo de Residencia...</option>
                      <option value="1">Casa</option>
                      <option value="2">Apartamento</option>
                      <option value="3">Outro</option>
                    </select>
                  </div>

                  <div class="col-sm-6">
                    <label class="visually-hidden" for="cbbTpLogradouro"
                      >Tipo Logradouro</label
                    >
                    <select
                      class="form-select"
                      id="cbbTpLogradouro"
                      name="cbbTpLogradouro"
                    >
                      <option value="0" selected>Tipo de Logradouro...</option>
                      <option value="1">Rua</option>
                      <option value="2">Avenida</option>
                      <option value="3">Outro</option>
                    </select>
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-9">
                    <label class="visually-hidden" for="txtLogradouro"
                      >Logradouro</label
                    >
                    <input
                      type="text"
                      class="form-control"
                      id="txtLogradouro"
                      name="txtLogradouro"
                      placeholder="Logradouro"
                    />
                  </div>

                  <div class="col-sm-3">
                    <label class="visually-hidden" for="txtNumero"
                      >Numero Casa</label
                    >
                    <input
                      type="text"
                      class="form-control"
                      id="txtNumero"
                      name="txtNumero"
                      placeholder="Numero"
                    />
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-6">
                    <label class="visually-hidden" for="txtBairro"
                      >Bairro</label
                    >
                    <input
                      type="text"
                      class="form-control"
                      id="txtBairro"
                      name="txtBairro"
                      placeholder="Bairro"
                    />
                  </div>

                  <div class="col-sm-6">
                    <label class="visually-hidden" for="txtCep">Cep</label>
                    <input
                      type="text"
                      class="form-control"
                      id="txtCep"
                      name="txtCep"
                      placeholder="CEP"
                    />
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-5">
                    <label class="visually-hidden" for="txtCidade"
                      >Cidade</label
                    >
                    <input
                      type="text"
                      class="form-control"
                      id="txtCidade"
                      name="txtCidade"
                      placeholder="Cidade"
                    />
                  </div>

                  <div class="col-sm-4">
                    <label class="visually-hidden" for="txtEstado"
                      >Estado</label
                    >
                    <input
                      type="text"
                      class="form-control"
                      id="txtEstado"
                      name="txtEstado"
                      placeholder="Estado"
                    />
                  </div>

                  <div class="col-sm-3">
                    <label class="visually-hidden" for="txtPais">Pais</label>
                    <input
                      type="text"
                      class="form-control"
                      id="txtPais"
                      name="txtPais"
                      placeholder="Pais"
                    />
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-12">
                    <label class="visually-hidden" for="txtObservacao"
                      >Observacoes</label
                    >
                    <input
                      type="text"
                      class="form-control"
                      id="txtObservacao"
                      name="txtObservacao"
                      placeholder="Nome do Endereco Para identificacao"
                    />
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-12">
                    <label class="visually-hidden" for="txtComplemento"
                      >Complemento</label
                    >
                    <input
                      type="text"
                      class="form-control"
                      id="txtComplemento"
                      name="txtComplemento"
                      placeholder="Complemento"
                    />
                  </div>
                </div>

                <div class="row py-1">
                  <div class="col-sm-6">
                    <div class="form-check form-switch">
                      <input
                        class="form-check-input"
                        type="checkbox"
                        role="switch"
                        id="flexSwitchCheckDefault"
                        name="swtIsEntrega"
                      />
                      <label
                        class="form-check-label"
                        for="flexSwitchCheckDefault"
                        >Salvar como endereco de entrega.</label
                      >
                    </div>
                  </div>
                  <div class="col-sm-6">
                    <div class="form-check form-switch">
                      <input
                        class="form-check-input"
                        type="checkbox"
                        role="switch"
                        id="flexSwitchCheckDefault"
                        name="swtIsCobranca"
                      />
                      <label
                        class="form-check-label"
                        for="flexSwitchCheckDefault"
                        >Salvar como endereco de cobranca.</label
                      >
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Fechar
              </button>
              <button
                type="submit"
                class="btn btn-primary"
                name="operacao"
                value="Salvar"
              >
                Cadastrar!
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <script type="text/javascript">
      $("#txtCPF").mask("000.000.000-00");
      $("#txtDDD").mask("(00)");
      $("#txtTelefone").mask("0000-00009");
      $("#txtCep").mask("00.000-000");
    </script>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
