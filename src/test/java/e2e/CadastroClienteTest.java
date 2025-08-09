package e2e;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class CadastroClienteTest {

    @Test
    public void cadastrarCliente() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
            Page page = browser.newPage();
            page.navigate("http://localhost:8080/login");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clique aqui para se cadastrar"))
                    .click();

            // Preencher dados pessoais
            page.locator("#txtNome").fill("Nome Completo Teste");
            page.locator("#txtDtNasc").fill("2000-01-01");
            page.locator("#txtCPF").fill("123.456.789-00");
            page.locator("#cbbGenero").selectOption("1");
            page.locator("#cbbTpTelefone").selectOption("2");
            page.locator("#txtDDD").fill("11");
            page.locator("#txtTelefone").fill("99999-9999");
            page.locator("#txtEmail").fill("teste@email.com");
            page.locator("#txtSenha").fill("senha123");
            page.locator("#txtConfirmarSenha").fill("senha123");

            // Preencher endereço residencial
            page.locator("#cbbTpResidencia").selectOption("1");
            page.locator("#cbbTpLogradouro").selectOption("1");
            page.locator("#txtLogradouro").fill("Rua Teste");
            page.locator("#txtNumero").fill("123");
            page.locator("#txtBairro").fill("Bairro Teste");
            page.locator("#txtCep").fill("12.345-678");
            page.locator("#txtCidade").fill("Cidade Teste");
            page.locator("#txtEstado").fill("Estado Teste");
            page.locator("#txtPais").fill("Pais Teste");
            page.locator("#txtObservacao").fill("Observacao Teste");
            page.locator("#txtComplemento").fill("Complemento Teste");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cadastrar!")).click();

            // Adicione uma asserção para verificar se o cadastro foi bem-sucedido
            // Por exemplo, verificar se o usuário foi redirecionado para a página de login
            assertThat(page).hasURL("http://localhost:8080/login");
        }
    }
}
