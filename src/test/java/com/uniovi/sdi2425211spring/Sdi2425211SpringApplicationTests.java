package com.uniovi.sdi2425211spring;

import com.uniovi.sdi2425211spring.pageobjects.*;
import com.uniovi.sdi2425211spring.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2425211SpringApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    static String Geckodriver = "C:\\Users\\uo295634\\Desktop\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
   // static String Geckodriver = "C:\\Dev\\tools\\selenium\\geckodriver-v0.30.0-win64.exe";
    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
//static String Geckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";
// Para la  versión de Firefox 121 en adelante la ruta de firefo en MAC es
//static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox";
//Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }
    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }
    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }
    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {}
    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }
 @Test
 @Order(1)
 void PR01A() {
  PO_HomeView.checkWelcomeToPage(driver, PO_Properties.getSPANISH());
 }
 @Test
 @Order(2)
 void PR01B() {
  List<WebElement> welcomeMessageElement = PO_HomeView.getWelcomeMessageText(driver,
          PO_Properties.getSPANISH());
  Assertions.assertEquals(welcomeMessageElement.get(0).getText(),
          PO_HomeView.getP().getString("welcome.message", PO_Properties.getSPANISH()));
 }

 //PR02. Opción de navegación. Pinchar en el enlace Registro en la página home
 @Test
 @Order(3)
 public void PR02() {
  PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
 }
 //PR03. Opción de navegación. Pinchar en el enlace Identifícate en la página home
 @Test
 @Order(4)
 public void PR03() {
  PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
 }


 //PR04. Opción de navegación. Cambio de idioma de Español a Inglés y vuelta a Español
 @Test
 @Order(5)
 public void PR04() {
  PO_HomeView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish",
          PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
 }


 //PR05. Prueba del formulario de registro. registro con datos correctos
 @Test
 @Order(6)
 public void PR05() {
  //Vamos al formulario de registro
  PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
  //Rellenamos el formulario.
  PO_SignUpView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");
  //Comprobamos que entramos en la sección privada y nos nuestra el texto a buscar
  String checkText = "Notas del usuario";
  List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
  Assertions.assertEquals(checkText, result.get(0).getText());
 }

 //PR06A. Prueba del formulario de registro. DNI repetido en la BD
// Propiedad: Error.signup.dni.duplicate
 @Test
 @Order(7)
 public void PR06A() {
  PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
  PO_SignUpView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
  List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.dni.duplicate",
          PO_Properties.getSPANISH() );
  //Comprobamos el error de DNI repetido.
  String checkText = PO_HomeView.getP().getString("Error.signup.dni.duplicate",
          PO_Properties.getSPANISH());
  Assertions.assertEquals(checkText , result.get(0).getText());
 }
 //PR06B. Prueba del formulario de registro. Nombre corto.
// Propiedad: Error.signup.dni.length
 @Test
 @Order(8)
 public void PR06B() {
  PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
  PO_SignUpView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");
  List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.name.length",
          PO_Properties.getSPANISH() );
  //Comprobamos el error de Nombre corto de nombre corto .
  String checkText = PO_HomeView.getP().getString("Error.signup.name.length",
          PO_Properties.getSPANISH());
  Assertions.assertEquals(checkText , result.get(0).getText());
 }


 @Test
 @Order(9)
 public void PR07() {
  //Vamos al formulario de logueo.
  PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
  //Rellenamos el formulario
  PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
  //Comprobamos que entramos en la pagina privada de Alumno
  String checkText = "Notas del usuario";
  List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
  Assertions.assertEquals(checkText, result.get(0).getText());
 }
 @Test
 @Order(10)
 public void PR08() {
  //Vamos al formulario de logueo.
  PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
  //Rellenamos el formulario
  PO_LoginView.fillLoginForm(driver, "99999993D", "123456");
  //Comprobamos que entramos en la pagina del profesor
  String checkText = "Notas del usuario";
  List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
  Assertions.assertEquals(checkText, result.get(0).getText());
 }
 @Test
 @Order(11)
 public void PR09() {
  //Vamos al formulario de logueo.
  PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
  //Rellenamos el formulario
  PO_LoginView.fillLoginForm(driver, "99999988F", "123456");
  //Comprobamos que entramos en la pagina del profesor
  String checkText = "Notas del usuario";
  List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
  Assertions.assertEquals(checkText, result.get(0).getText());
 }

 @Test
 @Order(12)
 public void PR10() {
  //Vamos al formulario de logueo.
  PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
  //Rellenamos el formulario
  PO_LoginView.fillLoginForm(driver, "99999990A", "123465");
  //Comprobamos que no entramos en la pagina privada de Admin
  String checkText = "Identifícate";
  List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
  Assertions.assertEquals(checkText, result.get(0).getText());
 }

 @Test
 @Order(13)
 public void PR11() {
  //Vamos al formulario de logueo.
  PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
  //Rellenamos el formulario
  PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
  //Comprobamos que no entramos en la pagina privada de usuario
  String checkText = "Notas del usuario";
  List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
  Assertions.assertEquals(checkText, result.get(0).getText());
 PO_LoginView.clickLogout(driver);
  checkText = "Identifícate";
  result = PO_View.checkElementBy(driver, "text", checkText);
  Assertions.assertEquals(checkText, result.get(0).getText());

    }

//PR12. Loguearse, comprobar que se visualizan 4 filas de notas y desconectarse usando el rol de estudiante
 @Test
 @Order(14)
 public void PR12() {
 List<WebElement> result =  PO_LoginView.login(driver, "99999990A", "123456", "Notas del usuario");
  //Contamos el número de filas de notas
  List<WebElement> marksList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
          PO_View.getTimeout());
  Assertions.assertEquals(2, marksList.size());
  PO_LoginView.logout(driver, PO_Properties.getSPANISH());

 }
 //PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion = Nota A2.
 @Test
 @Order(15)
 public void PR13() {
  List<WebElement> result =  PO_LoginView.login(driver, "99999990A", "123456", "Notas del usuario");

  //Contamos las notas
  By enlace = By.xpath("//td[contains(text(), 'Nota A4')]/following-sibling::*[2]");
  driver.findElement(enlace).click();
  //Esperamos por la ventana de detalle
  String checkText = "Detalles de la nota";
  result = PO_View.checkElementBy(driver, "text", checkText);
  Assertions.assertEquals(checkText, result.get(0).getText());
  PO_LoginView.logout(driver, PO_Properties.getSPANISH());

 }
 //P14. Loguearse como profesor y Agregar Nota A2.
//P14. Esta prueba podría encapsularse mejor ...
 @Test
 @Order(16)
 public void PR14() {
  List<WebElement> result =  PO_LoginView.login(driver, "99999993D", "123456", "99999993D");

  //Pinchamos en la opción de menú de Notas: //li[contains(@id, 'marks-menu')]/a
  //List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'marksmenu')]/a");
  PO_NavView.accessSection(driver, "//*[@id='myNavbar']/ul[1]/li[2]", "//a[contains(@href, 'mark/add')]");
  //Ahora vamos a rellenar la nota con mas de 20 caracteres. //option[contains(@value, '4')]
  String checkText = "Nota sistemas distribuidos";
  PO_PrivateView.fillFormAddMark(driver, 3, checkText, "8");
  //Esperamos a que se muestren los enlaces de paginación de la lista de notas
  List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
  //Nos vamos a la última página
  elements.get(4).click();
  //Comprobamos que aparece la nota en la página
  elements = PO_View.checkElementBy(driver, "text", checkText);
  Assertions.assertEquals(checkText, elements.get(0).getText());

  PO_LoginView.logout(driver, PO_Properties.getSPANISH());

 }
 //*[@id="navbarDropdown"]
 @Test
 @Order(17)
 public void PR15() {
  List<WebElement> result =  PO_LoginView.login(driver, "99999993D", "123456", "99999993D");

  //Pinchamos en la opción de menú de Notas: //li[contains(@id, 'marks-menu')]/a
  //List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'marksmenu')]/a");
  PO_NavView.accessSection(driver, "//*[@id='myNavbar']/ul[1]/li[2]", "//a[contains(@href, 'mark/list')]");

  //Esperamos a que se muestren los enlaces de paginación la lista de notas
  List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
  //Nos vamos a la última página
  elements.get(4).click();
  //Esperamos a que aparezca la Nueva nota en la última página

  //Y Pinchamos en el enlace de borrado de la Nota "Nota sistemas distribuidos"
  elements = PO_View.checkElementBy(driver, "free", "//td[contains(text(), 'Nota sistemas distribuidos')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
  elements.get(0).click();
  //Volvemos a la última página
  elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
  elements.get(4).click();
  //Y esperamos a que NO aparezca la última "Creando una nota nueva"
  SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Nota sistemas distribuidos",PO_View.getTimeout());


  PO_LoginView.logout(driver, PO_Properties.getSPANISH());
 }



}
