package webTest.chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EnvioMensagemSuporteEveclass {
    private Map<String, Object> vars;
    JavascriptExecutor js;
    private WebDriver driver;
    Properties prop = new Properties(); // Cria um objeto Properties

    @BeforeEach()
    public void setUp() throws IOException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Espera até que o elemento seja encotrado
        // Carrega o arquivo de propriedades
        FileInputStream file = new FileInputStream("src/test/resources/config.properties");
        prop.load(file);
        file.close();

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @Test // Logar antes de Iniciar os Testes
    public void loginPaginaEveclass() throws IOException, InterruptedException {
        // Iniciando Teste acessando o site Testando Eveclass
        driver.get("https://testando.eveclass.com/pt");
        driver.manage().window().maximize();
        // Aciona o botão "Começar Agora"
        driver.findElement(By.cssSelector(".button-custom:nth-child(4) > .button-text > span > span")).click();
        // Aciona o botão "Entrar"
        driver.findElement(By.cssSelector("#support-action > span > span > span")).click();

        // Lê os valores de e-mail do arquivo de propriedades
        String email = prop.getProperty("email");
        // Lê os valores de senha do arquivo de propriedades
        String senha = prop.getProperty("senha");

        // Seleciona o Input "Email" e inseri os valores
        WebElement inputEmail = driver.findElement(By.cssSelector("input[data-vv-as='Email'][type='Email']"));
        inputEmail.sendKeys(email);

        // Seleciona o Input "password" e inseri os valores
        WebElement inputSenha = driver.findElement(By.cssSelector("input[type='password']"));
        inputSenha.sendKeys(senha);

        // Aciona o botão "Entrar"
        driver.findElement(By.xpath("//*[@id=\"auth-panel\"]/div[2]/div/div/div/div/div[2]/div/form/div[2]/button")).click();

        Thread.sleep(3000); // espera por 3 segundos
        WebElement elemento = (driver.findElement(By.xpath("//span[@data-v-10e00821='']")));
        String textoDoElemento = elemento.getText();
        assertThat(textoDoElemento, is("Admin")); // Confirma que na tela de Admin

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/test/resources/utils/print/evidenciaLogin.png"));


    }

    @Test
    public void testarEnvioDeMensagemSuporte() throws IOException, InterruptedException {
        driver.get("https://testando.eveclass.com/pt/");
        // Aciona o botão "Começar Agora"
        driver.findElement(By.cssSelector(".button-custom:nth-child(4) > .button-text > span > span")).click();
        // Aciona o botão "Entrar"
        driver.findElement(By.cssSelector("#support-action > span > span > span")).click();

        // Lê os valores de e-mail do arquivo de propriedades
        String email = prop.getProperty("email");
        // Lê os valores de senha do arquivo de propriedades
        String senha = prop.getProperty("senha");

        // Seleciona o Input "Email" e inseri os valores
        WebElement inputEmail = driver.findElement(By.cssSelector("input[data-vv-as='Email'][type='Email']"));
        inputEmail.sendKeys(email);

        // Seleciona o Input "password" e inseri os valores
        WebElement inputSenha = driver.findElement(By.cssSelector("input[type='password']"));
        inputSenha.sendKeys(senha);

        // Aciona o botão "Entrar"
        driver.findElement(By.xpath("//*[@id=\"auth-panel\"]/div[2]/div/div/div/div/div[2]/div/form/div[2]/button")).click();

        driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down"));
            dropdown.click();
            WebElement pElement = driver.findElement(By.xpath("//p[normalize-space()='Suporte']"));
            pElement.click();
        }

        assertThat(driver.findElement(By.cssSelector("a[data-v-41d7c25e].nuxt-link-exact-active.nuxt-link-active")).getText(), is("Contatar Suporte"));

        driver.findElement(By.xpath("//textarea[@placeholder='Escreva sua mensagem para nós']")).sendKeys("Teste de Mensagem Suporte ");

        driver.findElement(By.cssSelector("span[data-v-651599fe].button-text > span[data-v-651599fe]")).click();
        Thread.sleep(3000); // espera por 3 segundos

        assertThat(driver.findElement(By.id("swal2-title")).getText(), is("Mensagem enviada com sucesso!"));

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/test/resources/utils/print/evidenciaSuporteChrome.png"));
    }
}