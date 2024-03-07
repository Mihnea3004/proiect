package com.autotest.AutomationTest;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

//import org.junit.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class YoutubePlayerCommands {
    ExtentSparkReporter spark = new ExtentSparkReporter("target/reportCommands.html");
    ExtentReports extent = new ExtentReports();
    
    @BeforeTest
    public void startReport() {
    	spark.config().setTheme(Theme.DARK);
    	spark.config().setDocumentTitle("Report Commands");
    	extent.attachReporter(spark);
   }
    @AfterTest
    public void endReport() {
    	extent.flush();
    }
			@Test
			public void PlayPause() throws InterruptedException {
			// Play/Pause Test
			ExtentTest testVisibility = extent.createTest("Player Visibility");
			ExtentTest testPause = extent.createTest("Pause Button Functionality");
			ExtentTest testPlay = extent.createTest("Play Button Functionality");
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ChromeOptions opt = new ChromeOptions();
			opt.addExtensions(new File("./Extensions/Adblock.crx"));
			WebDriver driver = new ChromeDriver(opt);
			try {
			driver.get("https://www.youtube.com/watch?v=bziEoDoH7ks");
			Thread.sleep(6000);
			String originalWindow = driver.getWindowHandle();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
            try{wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                }
            }

            driver.close();

            driver.switchTo().window(originalWindow);
            } catch (TimeoutException e) {
            	e.getStackTrace();
            }
    		Actions actions = new Actions(driver);
    		Thread.sleep(3000);
    		try {
    		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
            .click();
    		}catch(ElementNotInteractableException f) {
    			f.getStackTrace();
    		}
            WebElement Player = driver.findElement(By.id("player"));
            WebElement PlayButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > button"));
            if(Player.isDisplayed())
            {
            	System.out.println("Player Visibility Test Successful");
            	testVisibility.pass("Player Visibility pass");
            	JavascriptExecutor js = (JavascriptExecutor) driver;

            	Thread.sleep(500);
            	try {
            	    actions.moveToElement(PlayButton).click().build().perform();
            	} catch (StaleElementReferenceException e) {
            	    PlayButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > button"));
            	    actions.moveToElement(PlayButton).click().build().perform();
            	}
            	Object isPlaying = js.executeScript("return document.querySelector('#movie_player').getPlayerState();");
            	long paused = (long) isPlaying;
            	Thread.sleep(3000);
            	
            	if(paused == 2)
            	{
            		System.out.println("Pause Button Functionality Test Successful");
            		testPause.pass("Pause Button Functionality Test Successful");
            	}
            	else
            	{
            		testPause.fail("Pause Button Functionality Test Failed");
            		throw new AssertionError("Pause Button Functionality Test Failed");
            	}
            		try {
            	    actions.moveToElement(PlayButton).click().build().perform();
            	} catch (StaleElementReferenceException e) {
            	    PlayButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > button"));
            	    actions.moveToElement(PlayButton).click().build().perform();
            	}
            	isPlaying = js.executeScript("return document.querySelector('#movie_player').getPlayerState();");
            	paused = (long) isPlaying;
            	Thread.sleep(3000);
            	if(paused != 2)
            		{
            		System.out.println("Play Button Functionality Test Successful");
            		testPlay.pass("Play Button Functionality Test Successful");
            		}
            	else {
            		testPlay.fail("Play Button Functionality Test Failed");
            		throw new AssertionError("Play Button Functionality Test Failed");
            }
            	}
            else
            {
            	System.out.println("Player Visibility Test Failed");
            	testVisibility.fail("Player Visibility Test Failed");
            }
			}catch (Exception e)
            {
            	e.printStackTrace();
            } finally
            {
            	driver.quit();
            }
		}
		@Test
		public void nextTest() throws InterruptedException {
            //Next Button Test
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testNext = extent.createTest("Player Next Button");
			ChromeOptions opt = new ChromeOptions();
			opt.addExtensions(new File("./Extensions/Adblock.crx"));
			WebDriver driver = new ChromeDriver(opt);
			Actions action = new Actions(driver);
			try {
            driver.get("https://www.youtube.com/watch?v=bziEoDoH7ks");
            Thread.sleep(6000);
            String originalWindow = driver.getWindowHandle();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
            try{wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                }
            }

            driver.close();

            driver.switchTo().window(originalWindow);
            } catch (TimeoutException e) {
            	e.getStackTrace();
            }
    		Thread.sleep(1000);
    		try {
    		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
            .click();
    		}catch(ElementNotInteractableException f) {
    			f.getStackTrace();
    		}
    		Thread.sleep(1000);
    		WebElement videoElement = driver.findElement(By.tagName("video"));
    		action.moveToElement(videoElement);
    		WebElement PlayButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > a.ytp-next-button.ytp-button"));
    		try {
    			PlayButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > a.ytp-next-button.ytp-button"));
        	    action.moveToElement(PlayButton).click().build().perform();
        	} catch (StaleElementReferenceException e) {
        		action.moveToElement(videoElement);
        	    PlayButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > a.ytp-next-button.ytp-button"));
        	    action.moveToElement(PlayButton).click().build().perform();
        	}
        	Thread.sleep(5000);
            String currentUrl = driver.getCurrentUrl();
            if(!currentUrl.equals("https://www.youtube.com/watch?v=bziEoDoH7ks"))
            	{
            	System.out.println("Next Button Functionality Test Successful");
            	testNext.pass("Next Button Functionality Test Successful");
            	}
            else {
            	testNext.fail("Next Button Functionality Test Failed");
            	throw new AssertionError("Next Button Functionality Test Failed");
            }
            }catch (Exception e) {
				e.printStackTrace();	
			} finally
			{
				driver.quit();
			}
		}
            // Volume Button
		@Test
		public void volumeTest() throws InterruptedException {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testVolume = extent.createTest("Player Volume Test");
			ChromeOptions opt = new ChromeOptions();
			opt.addExtensions(new File("./Extensions/Adblock.crx"));
			WebDriver driver = new ChromeDriver(opt);
			try {
			driver.get("https://www.youtube.com/watch?v=bziEoDoH7ks");
			Thread.sleep(6000);
			String originalWindow = driver.getWindowHandle();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
            try{wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                }
            }

            driver.close();

            driver.switchTo().window(originalWindow);
            } catch (TimeoutException e) {
            	e.getStackTrace();
            }
    		Thread.sleep(1000);
    		try {
    		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
            .click();
    		}catch(ElementNotInteractableException f) {
    			f.getStackTrace();
    		}
    		Thread.sleep(1000);
    		WebElement video = driver.findElement(By.tagName("video"));
    		Actions action = new Actions(driver);
    		action.moveToElement(video);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object VolumeObject = js.executeScript("return document.querySelector('#movie_player').getVolume();");
            long InitValue = (long) VolumeObject;
            
        	int InitialVolume = (int) InitValue;
        	Thread.sleep(1000);
        	js.executeScript("return document.querySelector('#movie_player').setVolume(50);");

            VolumeObject = js.executeScript("return document.querySelector('#movie_player').getVolume();");
            long NValue = (long) VolumeObject;
            int NewVolume = (int) NValue;
            Thread.sleep(1000);
            if(NewVolume != InitialVolume)
            {
            	testVolume.pass("Volume Change Test Successful");
            	System.out.println("Volume Change Test Successful");
            }
            else {
            	testVolume.fail("Volume Change Test Failed");
            	throw new AssertionError("Volume Change Test Failed");
            }
            }catch(Exception e) {
				e.printStackTrace();
			}finally {
				driver.quit();
			}
		}
		@SuppressWarnings("deprecation")
		@Test
		public void AutoPlayOn() throws InterruptedException {
            //Autoplay On
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testAutoPlayOn = extent.createTest("AutoPlay On Test");
			ChromeOptions opt = new ChromeOptions();
			opt.addExtensions(new File("./Extensions/Adblock.crx"));
			WebDriver driver = new ChromeDriver(opt);
			try {
			driver.get("https://www.youtube.com/watch?v=bziEoDoH7ks");
            String originalWindow = driver.getWindowHandle();
            Thread.sleep(6000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
            try{wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                }
            }

            driver.close();

            driver.switchTo().window(originalWindow);
            } catch (TimeoutException e) {
            	e.getStackTrace();
            }
            Thread.sleep(1000);
    		try {
    		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
            .click();
    		}catch(ElementNotInteractableException f) {
    			f.getStackTrace();
    		}
            Thread.sleep(1000);
            WebElement video = driver.findElement(By.tagName("video"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].playbackRate = 4", video);
            ((JavascriptExecutor) driver).executeScript("arguments[0].play()", video);

            driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            Thread.sleep(60000);
            String currentUrl = driver.getCurrentUrl();
            if(currentUrl.equals("https://www.youtube.com/watch?v=bziEoDoH7ks"))	
            	{
            	testAutoPlayOn.fail("AutoPlay On Test Failed");
            	throw new AssertionError("AutoPlay On Test Failed");
            	}
            else {
            	testAutoPlayOn.pass("Playback speed changed, video changed and video ended successfully!");
            	System.out.println("Playback speed changed, video changed and video ended successfully!");
            }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			driver.quit();
		}
		}
            @SuppressWarnings("deprecation")
			@Test
            public void AutoPlayOff() throws InterruptedException {
            //AutoPlay Off
    		System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
    		ExtentTest testAutoPlayOff = extent.createTest("AutoPlay Off Test");
    		ChromeOptions opt = new ChromeOptions();
			opt.addExtensions(new File("./Extensions/Adblock.crx"));
			WebDriver driver = new ChromeDriver(opt);
    		try {
    		driver.get("https://www.youtube.com/watch?v=bziEoDoH7ks");
            String originalWindow = driver.getWindowHandle();
            Thread.sleep(6000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
            try{wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                }
            }

            driver.close();

            driver.switchTo().window(originalWindow);
            } catch (TimeoutException e) {
            	e.getStackTrace();
            }
            Thread.sleep(1000);
    		try {
    		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
            .click();
    		}catch(ElementNotInteractableException f) {
    			f.getStackTrace();
    		}
    		Thread.sleep(5000);
            Actions action = new Actions(driver);
            WebElement videoElement = driver.findElement(By.tagName("video"));
            action.moveToElement(videoElement);
            WebElement AutoPlay = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-right-controls > button:nth-child(2)"));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", AutoPlay);
            WebElement video = driver.findElement(By.tagName("video"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].playbackRate = 4", video);
            ((JavascriptExecutor) driver).executeScript("arguments[0].play()", video);

            driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
            videoElement = driver.findElement(By.tagName("video"));
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            String currentUrl = driver.getCurrentUrl();
            Thread.sleep(60000);
            if(currentUrl.equals("https://www.youtube.com/watch?v=bziEoDoH7ks"))	
            	{
            	testAutoPlayOff.pass("Playback speed changed, video hasn't changed and video ended successfully!");
            	System.out.println("Playback speed changed, video hasn't changed and video ended successfully!");
            	}
            else {
            	testAutoPlayOff.fail("AutoPlay Off Test Failed");
            	throw new AssertionError("AutoPlay Off Test Failed");
            }
            }catch(Exception e) {
    			e.printStackTrace();
    		}finally {
    			driver.quit();
    		}
            }
            @Test
            public void captionsOnOff() throws InterruptedException {
            //Captions on/off
    		System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
    		ExtentTest testCaptions = extent.createTest("Captions On/Off Test");
    		ExtentTest testCaptionsPressed = extent.createTest("Capsions Pressed Test");
    		ExtentTest testnotCaptionsPressed = extent.createTest("Capsions Not Pressed Test");
    		ChromeOptions opt = new ChromeOptions();
			opt.addExtensions(new File("./Extensions/Adblock.crx"));
			WebDriver driver = new ChromeDriver(opt);
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    		Actions action = new Actions(driver);
    		try {
    		driver.get("https://www.youtube.com/watch?v=bziEoDoH7ks");
            String originalWindow = driver.getWindowHandle();
            Thread.sleep(6000);
            try{wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                }
            }

            driver.close();

            driver.switchTo().window(originalWindow);
            } catch (TimeoutException e) {
            	e.getStackTrace();
            }
    		try {
    		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
            .click();
    		}catch(ElementNotInteractableException f) {
    			f.getStackTrace();
    		}
    		Thread.sleep(1000);
            WebElement videoElement = driver.findElement(By.tagName("video"));
            action.moveToElement(videoElement).perform();
            WebElement captionsButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-right-controls > button.ytp-subtitles-button.ytp-button"));
            captionsButton.click();
            if(captionsButton.getAttribute("aria-pressed").equals("true"))
            	{
            	testCaptionsPressed.pass("Captions Button pressed successfully");
            	System.out.println("Captions Button pressed successfully");
            	}
            else {
            	testCaptionsPressed.fail("Captions button not pressed");
            	throw new AssertionError("Captions button not pressed");
            }
            WebElement captionsWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#caption-window-1 > span")));
            if(captionsWindow.isDisplayed())
            	{
            	testCaptions.pass("Caption Visibility Successful");
            	System.out.println("Caption Visibility Successful");
            	}
            else {
            	testCaptions.fail("Captions window not visible");
            	throw new AssertionError("Captions window not visible");
            }
            	videoElement = driver.findElement(By.tagName("video"));
            action.moveToElement(videoElement).perform();
            captionsButton.click();
            if(captionsButton.getAttribute("aria-pressed").equals("false"))
            	{
            	testnotCaptionsPressed.pass("Captions Button Not Pressed Test Successful");
            	System.out.println("Captions Button not pressed successfully");
            	}
            else
            	{
            	testnotCaptionsPressed.fail("Captions Button Not Pressed Test Failed");
            	throw new AssertionError("Captions button still pressed");
            	}

    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		finally{
    			driver.quit();
    		}
            }
            @SuppressWarnings("deprecation")
			@Test
            public void MiniPlayerOnOff() throws InterruptedException {
            //Miniplayer on/off
    		System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
    		ExtentTest testMiniplayerButton = extent.createTest("Miniplayer Button On/Off Test");
    		ExtentTest testExpandButton = extent.createTest("Expand Button Test");
    		ChromeOptions opt = new ChromeOptions();
			opt.addExtensions(new File("./Extensions/Adblock.crx"));
			WebDriver driver = new ChromeDriver(opt);
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    		Actions action = new Actions(driver);
    		try {
            driver.get("https://www.youtube.com/watch?v=bziEoDoH7ks");
            String originalWindow = driver.getWindowHandle();
            Thread.sleep(6000);
            try{wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                }
            }

            driver.close();

            driver.switchTo().window(originalWindow);
            } catch (TimeoutException e) {
            	e.getStackTrace();
            }
            Thread.sleep(2000);
    		try {
    		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
            .click();
    		}catch(ElementNotInteractableException f) {
    			f.getStackTrace();
    		}
            Thread.sleep(1000);
            WebElement videoElement = driver.findElement(By.tagName("video"));
            action.moveToElement(videoElement);
            WebElement miniplayerButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-right-controls > button.ytp-miniplayer-button.ytp-button"));
        	try {
        	    action.moveToElement(miniplayerButton).click().build().perform();
        	} catch (StaleElementReferenceException e) {
        		action.moveToElement(videoElement).perform();
        		miniplayerButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-right-controls > button.ytp-miniplayer-button.ytp-button"));
        	    action.moveToElement(miniplayerButton).click().build().perform();
        	}
            Thread.sleep(4000);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String currentUrl = driver.getCurrentUrl();
            if(!currentUrl.equals("https://www.youtube.com/watch?v=bziEoDoH7ks"))
            	{
            	testMiniplayerButton.pass("Minibutton Test Successful");
            	System.out.println("Minibutton Test Successful");
            	}
            else {
            	testMiniplayerButton.fail("Miniplayer button Test Failed");
            	throw new AssertionError("Miniplayer button Test Failed");
            }
            	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            Thread.sleep(6000);
            videoElement = driver.findElement(By.tagName("video"));
            action.moveToElement(videoElement);
            WebElement expandButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-miniplayer-ui > div > button.ytp-miniplayer-expand-watch-page-button.ytp-button.ytp-miniplayer-button-top-left"));
        	try {
        		wait.until(ExpectedConditions.elementToBeClickable(expandButton)).click();
                System.out.println("Miniplayer button Test Successful");
        	} catch (StaleElementReferenceException e) {
        		action.moveToElement(videoElement).perform();
        		expandButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-miniplayer-ui > div > button.ytp-miniplayer-expand-watch-page-button.ytp-button.ytp-miniplayer-button-top-left"));
        		wait.until(ExpectedConditions.elementToBeClickable(expandButton)).click();
        	}
            Thread.sleep(500);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            currentUrl = driver.getCurrentUrl();
            if(currentUrl.equals("https://www.youtube.com/watch?v=bziEoDoH7ks"))
            	{
            	testExpandButton.pass("Miniplayer Expand Button Test Successful");
            	System.out.println("Miniplayer Expand Button Test Successful");
            	}
            else {
            	testExpandButton.fail("");
            	throw new AssertionError("Miniplayer Expand Button Test Failed");
            }
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		finally{
    			driver.quit();
    		}
            }
		}


