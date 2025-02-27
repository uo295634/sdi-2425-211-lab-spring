package com.uniovi.sdi2425211spring.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_LoginView extends PO_NavView{
    static public void fillLoginForm(WebDriver driver, String dnip, String passwordp){
        WebElement dni = driver.findElement(By.name("username"));
        dni.click();
        dni.clear();
        dni.sendKeys(dnip);
        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(passwordp);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    static public void clickLogout(WebDriver driver) {
        By boton = By.xpath("//a[@href='/logout']");
        driver.findElement(boton).click();
    }

    static public List<WebElement> login(WebDriver driver, String username, String password, String checkText) {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        fillLoginForm(driver, username, password);
        //Comprobamos que entramos en la pagina privada
        return PO_View.checkElementBy(driver, "text", checkText);
    }


    public static void logout(WebDriver driver, int language) {
        //Ahora nos desconectamos comprobamos que aparece el menú de registrarse
        String loginText = PO_HomeView.getP().getString("signup.message", language);
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }
}
