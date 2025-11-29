package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.target.Target;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.edge.driver", "C:\\Development\\AiHelper\\src\\main\\resources\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("homepage=about:blank");

        EdgeDriver driver = new EdgeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // get Tuwel Tab
        var targets = devTools.send(Target.getTargets());

        for (var t : targets) {
            if (t.getUrl().contains("tuwel")) {
                devTools.send(Target.activateTarget(t.getTargetId()));
                break;
            }
        }


        // gesamten sichtbaren Text auslesen
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        WebElement body = driver.findElement(By.tagName("body"));
        String pageText = body.getText();

        System.out.println(driver.getCurrentUrl());

        OpenAiService aiService = new OpenAiService();
        String response = aiService.makeCall(pageText);
//        String response = "balbalbalafasdkfjashdlöfkjhasjdölkfjaölskdjfölaksdjfölkashdfkölhasdfasblbsadifjasdöklfjaösldkfjöasdlkjföasdkjfölasdkjfasdfasdfasdfasdf\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nsdf\n\n\n\n\n";

        System.out.println(response);
        System.setProperty("airesponse", response);

        FXApp.launchApp(args);

        System.exit(0);
    }


}