package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class selecionarPassagem {

    private WebDriver driver;

    @Before
    public void setup() {
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

    @Given("que acesso o site Blazedemo")
    public void que_acesso_o_site_blazedemo() {
        driver.get("https://blazedemo.com/");
        String tituloPagina = driver.getTitle();
        assertEquals("BlazeDemo", tituloPagina);
        System.out.println("Passo 1");
    }

    @When("seleciono a origem como {string} e destino {string}")
    public void seleciono_a_origem_como_e_destino(String origem, String destino) {
        driver.findElement(By.name("fromPort")).click();
        // selecionar a cidade na lista
        { //inicio da seleção dentro da lista
            WebElement lista = driver.findElement(By.name("fromPort"));
            lista.findElement(By.xpath("//option[. = 'São Paolo']")).click();
        } //fim da seleção dentro da lista

        driver.findElement(By.name("toPort")).click();
        // selecionar a cidade na lista
        { //inicio da seleção dentro da lista
            WebElement lista = driver.findElement(By.name("toPort"));
            lista.findElement(By.xpath("//option[. = 'Berlin']")).click();
        }
        System.out.println("Passo 2");

    }

    @When("clico em Procurar Voo")
    public void clico_em_procurar_voo() {
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        System.out.println("Passo 3");
    }

    @Then("exibe a frase indicando voo {string} e {string}")
    public void exibe_a_frase_indicando_voo_e(String string1, String string2) {
        System.out.println("Passo 4");
        assertEquals("Flights from São Paolo to Berlin:",driver.findElement(By.cssSelector("div.container h3")).getText());
    }

}
