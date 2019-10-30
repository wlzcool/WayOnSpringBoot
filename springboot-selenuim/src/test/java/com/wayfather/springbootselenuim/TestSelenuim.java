package com.wayfather.springbootselenuim;



import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author IBM
 * @className TestSelenuim
 * @description TODO
 * @date 2019/10/30 9:22
 **/
public class TestSelenuim {
    @Test
    public void  main() {
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");

        //Step 1- Driver Instantiation: Instantiate driver object as FirefoxDriver
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.qichacha.com/tax");
        //获得cookie
        Set<Cookie> coo = driver.manage().getCookies();
        //打印cookie
        System.out.println(coo);
        //Step 2- Navigation: Open a website
        driver.navigate().to("https://www.qichacha.com/tax_search?key=91130000677356885K");

        //Step 3- Assertion: Check its title is correct
        //assertEquals method Parameters: Expected Value, Actual Value, Assertion Message
        assertEquals("91130000677356885K的搜索结果-企查查", driver.getTitle(), "Title check failed!");

        //Step 4- Close Driver
        driver.close();

        //Step 5- Quit Driver
        driver.quit();


    }

}

