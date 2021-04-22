package iterasys;


import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class consulta {
		String url;
		WebDriver driver;
	
	@Before
	public void iniciar() {
		url = "https://www.iterasys.com.br";
		System.setProperty("webdriver.chrome.driver", "D:\\Projetos_Eclipse\\iterasys\\drivers\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);		
	}
	
	@After
	public void finalizar() {
		driver.quit();		
	}
	
	
	@Dado("^que o usuario acessa o site da Interasys$")
	public void que_o_usuario_acessa_o_site_da_Interasys() throws Throwable {
		driver.get(url);	    
	}

	@Quando("^realizo uma consulta por \"([^\"]*)\"$")
	public void realizo_uma_consulta_por(String termo) throws Throwable {
		driver.findElement(By.id("searchtext")).clear();
		driver.findElement(By.id("searchtext")).sendKeys(Keys.chord(termo));
		driver.findElement(By.id("btn_form_search")).click();
	  
	}

	@E("^exibe um conjuto de cursos$")
	public void exibe_um_conjuto_de_cursos() throws Throwable {
		//Não está validando a lista, apenas o titulo da aba
		assertEquals("Teste de Software e QA", driver.getTitle());  
	}

	@Entao("^valido que a duracao do curso sera de \"([^\"]*)\"$")
	public void valido_que_a_duracao_do_curso_sera_de_horas(String horas) throws Throwable {
		Thread.sleep(5000);
		assertEquals("60 horas", driver.findElement(By.cssSelector("span.subtitulo")).getText());
	}
}

