package steps;


import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class contatoEveclass {
    private WebDriver driver;

    @Before
    public void setup() throws IOException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Given("que acesso o site Testando_Eveclass")
    public void que_acesso_o_site_testando_eveclass() {
        System.out.println("Passo 1");
        driver.get("https://testando.eveclass.com/pt");
    }

    @When("clico no botao Começar_Agora")
    public void clico_no_botao_começar_agora() {
        System.out.println("Passo 2");
        driver.findElement(By.cssSelector(".button-custom:nth-child(4) > .button-text > span > span")).click();
    }

    @When("clico no menu Contato")
    public void clico_no_menu_contato() {
        System.out.println("Passo 4");
        driver.findElement(By.cssSelector(".fal.fa-comment-smile")).click();
    }

    @When("insiro o Nome")
    public void insiro_o_nome() {
        System.out.println("Passo 4");
        WebElement inputNome = driver.findElement(By.cssSelector("input[data-vv-as='Nome'][type='text']"));
        inputNome.sendKeys("Cristiano");
    }

    @When("insiro a Email")
    public void insiro_a_email() {
        System.out.println("Passo 5");
        WebElement inputEmail = driver.findElement(By.cssSelector("input[data-vv-as='Email'][type='Email']"));
        inputEmail.sendKeys("cristianobonfim@souunisuam.com.br");

    }

    @When("insiro a Mensagem")
    public void insiro_a_mensagem() {
        System.out.println("Passo 6");
        WebElement textareaMensagem = driver.findElement(By.cssSelector("textarea[placeholder='Digite aqui a sua mensagem']"));
        textareaMensagem.sendKeys("Testando Mensagem Contato");
    }

    @When("clico no botao Enviar")
    public void clico_no_botao_enviar() {
        System.out.println("Passo 7");
        WebElement buttonEnviar = driver.findElement(By.cssSelector("button.button-custom"));
        buttonEnviar.click();
    }

    @Then("exibe o pop up de confirmaçao e clico no botao Fechar")
    public void exibe_o_pop_up_de_confirmaçao_e_clico_no_botao_fechar() {
        System.out.println("Passo 8");
        WebElement buttonFechar = driver.findElement(By.cssSelector("button.swal2-confirm"));
        buttonFechar.click();
    }

}
