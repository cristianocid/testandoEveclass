package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class alterarNomePerfil {

    private WebDriver driver;
    Properties prop = new Properties(); // Cria um objeto Properties

    @Before
    public void setup() throws IOException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        // Carrega o arquivo de propriedades
        FileInputStream file = new FileInputStream("src/test/resources/config.properties");
        prop.load(file);
        file.close();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Given("que acesso o site Testando Eveclass")
    public void que_acesso_o_site_testando_eveclass() {
        System.out.println("Passo 1");
        driver.get("https://testando.eveclass.com/pt");
    }

    @When("clico no botao Começar Agora")
    public void clico_no_botao_começar_agora() {
        System.out.println("Passo 2");
        driver.findElement(By.cssSelector(".button-custom:nth-child(4) > .button-text > span > span")).click();
    }

    @When("clico no botao Entrar")
    public void clico_no_botao_entrar() {
        System.out.println("Passo 3");
        driver.findElement(By.cssSelector("#support-action > span > span > span")).click();
    }

    @When("insiro o Email")
    public void insiro_o_email() {
        System.out.println("Passo 4");
        String email = prop.getProperty("email");
        WebElement inputEmail = driver.findElement(By.cssSelector("input[data-vv-as='Email'][type='Email']"));
        inputEmail.sendKeys(email);
    }

    @When("insiro a Senha")
    public void insiro_a_senha() {
        System.out.println("Passo 5");
        String senha = prop.getProperty("senha");
        WebElement inputSenha = driver.findElement(By.cssSelector("input[type='password']"));
        inputSenha.sendKeys(senha);
    }

    @When("clico no botao Entrar2")
    public void clico_no_botao_entrar2() {
        System.out.println("Passo 6");
        driver.findElement(By.xpath("//*[@id=\"auth-panel\"]/div[2]/div/div/div/div/div[2]/div/form/div[2]/button")).click();
    }

    @When("clico no icone do Usuario")
    public void clico_no_icone_do_usuario() throws InterruptedException {
        System.out.println("Passo 7");
        driver.findElement(By.cssSelector("div.user-avatar.avatar-initials")).click();
        Thread.sleep(3000);
    }

    @When("clico no nome do Usuario")
    public void clico_no_nome_do_usuario() {
        System.out.println("Passo 8");
        driver.findElement(By.cssSelector(".fal.fa-user-circle")).click();
    }

    @When("altero o nome do Usuario")
    public void altero_o_nome_do_usuario() {
        System.out.println("Passo 9");
        WebElement inputNome = driver.findElement(By.cssSelector("input[data-vv-as='Nome'][type='text']"));
        inputNome.sendKeys(Keys.CONTROL + "a");
        inputNome.sendKeys(Keys.BACK_SPACE);
        inputNome.sendKeys("Cristiano Cid");
    }

    @When("clico no botao Salvar")
    public void clico_no_botao_salvar() {
        System.out.println("Passo 10");
        driver.findElement(By.xpath("//*[@id=\"account-panel\"]/div[2]/div/div/div/div/div[1]/section/form/button")).click();
    }

    @Then("exibe o pop up de confirmaçao")
    public void exibe_o_pop_up_de_confirmaçao() {
        System.out.println("Passo 11");
        assertThat(driver.findElement(By.id("swal2-title")).getText(), is("Perfil editado com successo!"));
        System.out.println("Teste Finalizado com Sucesso!");
    }
}
