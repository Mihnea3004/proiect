package com.autotest.AutomationTest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class UITest {

    ExtentSparkReporter spark = new ExtentSparkReporter("target/reportUI.html");
    ExtentReports extent = new ExtentReports();
    
    @BeforeTest
    public void startReport() {
    	spark.config().setTheme(Theme.DARK);
    	spark.config().setDocumentTitle("Report UI");
    	extent.attachReporter(spark);
   }
    @AfterTest
    public void endReport() {
    	extent.flush();
    }
		@Test
		public void logoVisibility() {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
		ExtentTest testVisibility = extent.createTest("Logo Visibility");
		ExtentTest testFunctionality = extent.createTest("Logo Functionality");
		WebDriver driver = new ChromeDriver();
		try {
        	//Youtube Logo Visibility + Functionality Test
            driver.get("https://www.youtube.com/");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
                  .click();

            Thread.sleep(1000);

            WebElement logo = driver.findElement(By.id("logo-icon"));
            logo.click();
            if(logo.isDisplayed()) {
            	System.out.println("Logo Visibility Test Successful");
            	testVisibility.pass("Logo Visibility Test Successful");
                String currentUrl = driver.getCurrentUrl();
                if (!currentUrl.equals("https://www.youtube.com/")) {
                    testFunctionality.fail("Logo Functionality Test Failed");
                	throw new AssertionError("Expected URL: https://www.youtube.com/, Actual URL: " + currentUrl);
                } else {
                	System.out.println("Logo Functionality Test Successful");
                	testFunctionality.pass("Logo Functionality Test Successful");
                }
            } else
            {
            	testVisibility.fail("Logo Visibility Test Failed");
            	throw new AssertionError("Logo Visibility Test Failed");
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	driver.quit();
        }
		}
		@Test
		public void searchBar() throws InterruptedException {
            //Search Bar + Clear Button Test + Results Visibility Test
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testBarVisibility = extent.createTest("Search Bar Visibility");
			ExtentTest testClearVisibility = extent.createTest("Clear Button Visibility");
			ExtentTest testClearFunctionality = extent.createTest("Clear Button Functionality");
			ExtentTest testSearchButtonLegacy = extent.createTest("Search Button Legacy Visibility");
			ExtentTest testSearchBarFunctionality = extent.createTest("Search Bar Functionality");
			ExtentTest testVideoVisibility = extent.createTest("Video Visibility");
			WebDriver driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
			try {
			driver.get("https://www.youtube.com/");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
                  .click();

            Thread.sleep(2000);

            WebElement searchBar = driver.findElement(By.xpath("//input[@id=\"search\"]"));
            searchBar.sendKeys("adelante");
            if(searchBar.isDisplayed())
            	{
            	testBarVisibility.pass("Searchbar Visibility Test Successful");
            	System.out.println("Searchbar Visibility Test Successful");
            	}
            else
            	{
            	testBarVisibility.fail("Searchbar Visibility Test Failed");
            	throw new AssertionError("Searchbar Visibility Test Failed");
            	}

            WebElement clearButton = driver.findElement(By.cssSelector("#search-clear-button > ytd-button-renderer > yt-button-shape > button"));
            
            
            if(clearButton.isDisplayed())
            	{
            	testClearVisibility.pass("Clear Button Visibility Test Successful");
            	System.out.println("Clear Button Visibility Test Successful");
            	}
            else
            	{
            	testClearVisibility.fail("Clear Button Visibility Test Failed");
            	throw new AssertionError("Clear Button Visibility Test Failed");
            	}

            clearButton.click();
            
            if(searchBar.getText().equals(""))
            	{
            	testClearFunctionality.pass("Clear Button Functionality Test Successful");
            	System.out.println("Clear Button Functionality Test Successful");
            	}
            else
            	{
            	testClearFunctionality.fail("Clear Button Functionality Test Failed");;
            	throw new AssertionError("Clear Button Functionality Test Failed");
            	}

            searchBar.sendKeys("adelante");
            WebElement searchIconLegacy = driver.findElement(By.id("search-icon-legacy"));
            if(searchIconLegacy.isDisplayed())
            	{
            	testSearchButtonLegacy.pass("Search Icon Legacy Visibility Test Successful");
            	System.out.println("Search Icon Legacy Visibility Test Successful");
            	}
            else
            	{
            	testSearchButtonLegacy.fail("Search Icon Legacy Visibility Test Failed");
            	throw new AssertionError("Search Icon Legacy Visibility Test Failed");
            	}
            searchIconLegacy.click();

            Thread.sleep(2000);

            String searchUrl = driver.getCurrentUrl();
            if (!searchUrl.equals("https://www.youtube.com/results?search_query=adelante")) {
                testSearchBarFunctionality.fail("Search Bar Functionality Test Failed");
            	throw new AssertionError("Expected URL: https://www.youtube.com/results?search_query=adelante, Actual URL: " + searchUrl);
            }else {
            	testSearchBarFunctionality.pass("Search Bar Functionality Test Passsed");
            	System.out.println("Search Bar Functionality Test Passsed");
            }
            Thread.sleep(500);
            wait.until(ExpectedConditions.urlToBe("https://www.youtube.com/results?search_query=adelante"));
            WebElement videoRenderer = driver.findElement(By.cssSelector("#contents > ytd-video-renderer:nth-child(1)"));
            if(videoRenderer.isDisplayed())
            	{
            	testVideoVisibility.pass("Video Renderer Visibility Test Successful");
            	System.out.println("Video Renderer Visibility Test Successful");
            	}
            else
            	{
            	testVideoVisibility.fail("Video Renderer Visibility Test Failed");
            	throw new AssertionError("Video Renderer Visibility Test Failed");
            	}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
			driver.quit();
		}
		}
		@Test
		public void suggestionList() throws InterruptedException {
            //Suggestion List Test
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testSearchIconVisibility = extent.createTest("Search Icon Visibility");
			ExtentTest testSuggestion = extent.createTest("Suggestion Functionality");
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.youtube.com/");
            
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
                  .click();

            Thread.sleep(1000);
            String searchUrl = driver.getCurrentUrl();
            WebElement searchBar1 = driver.findElement(By.xpath("//input[@id=\"search\"]"));
            searchBar1.sendKeys("adelant");
            searchBar1.click();
            WebElement SearchIcon = driver.findElement(By.id("search-icon"));
            
            if(SearchIcon.isDisplayed())
            	{
            	testSearchIconVisibility.pass("Search Icon Visibility Test Successful");
            	System.out.println("Search Icon Visibility Test Successful");
            	}
            else
            	{
            	testSearchIconVisibility.fail("Search Icon Visibility Test Failed");
            	throw new AssertionError("Search Icon Visibility Test Failed");
            	}
            Thread.sleep(3000);
            List<WebElement> SuggestionList = driver.findElements(By.xpath(".//li[@role='presentation']/div"));
            if(!SuggestionList.isEmpty()) {
            for (WebElement suggestion : SuggestionList )
            {
            	if(suggestion.getText().trim().equalsIgnoreCase("adelante"))
            	{
            		suggestion.click();
                    if (!searchUrl.equals("https://www.youtube.com/results?search_query=adelante")) {
                        {
                        	testSuggestion.fail("Suggestion Test Failed");
                        	throw new AssertionError("Expected URL: https://www.youtube.com/results?search_query=adelante, Actual URL: " + searchUrl);
                        }
                   } else {
                	   System.out.println("Suggestion Test Successful");
                	   testSuggestion.pass("Suggestion Test Successful");
                   }
            		break;
            	}
            	else
            		{
            		testSuggestion.fail("Suggestion Test Failed");
            		throw new AssertionError("Suggestion Test Failed");
            		}
            	
            }
            } else {
            	testSuggestion.pass("List was present,but Empty");
            }
            driver.quit();
		}
		@Test
		public void sideDrawer() throws InterruptedException {
            //Side Drawer Test
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testDrawerVisibility = extent.createTest("Drawer Visibility");
			ExtentTest testDrawerSideVisibility = extent.createTest("Drawer Side Visibility");
			ExtentTest testButtonReveal = extent.createTest("Drawer Reveal Button Visibility");
			WebDriver driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
			try {
            driver.get("https://www.youtube.com/");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
                  .click();

            Thread.sleep(1000);
            WebElement DrawerButton1 = driver.findElement(By.id("guide-button"));
            if(DrawerButton1.isDisplayed())
            {
            	System.out.println("Reveal Button Visibility Test Successful");
            	testButtonReveal.pass("Reveal Button Visibility Test Pass");
            	DrawerButton1.click();
            	WebElement DrawerVisible = driver.findElement(By.id("contentContainer"));
            	Thread.sleep(2000);
            	if(DrawerVisible.isDisplayed())
            	{
            		testDrawerVisibility.pass("Drawer Visibility Test Successful");
            		System.out.println("Drawer Visibility Test Successful");
            		WebElement DrawerButton2 = driver.findElement(By.cssSelector("#guide-icon"));
            		wait.until(ExpectedConditions.elementToBeClickable(DrawerButton2));
            		Actions actions = new Actions(driver);
            		actions.moveToElement(DrawerButton2).click().build().perform();
            	}
            	else
            		{
            		testDrawerVisibility.fail("Drawer Visibility Test Failed");
            		throw new AssertionError("Drawer Visibility Test Failed");
            		}
            	if(DrawerVisible.isDisplayed())
            	{
            		testDrawerSideVisibility.pass("Drawer Closed Side Bar Visibility Test Successful");
            		System.out.println("Drawer Closed Side Bar Visibility Test Successful");
            	}
            	else
            		{
            		testDrawerSideVisibility.fail("Drawer Closed Side Bar Visibility Test Failed");
            		throw new AssertionError("Drawer Closed Side Bar Visibility Test Failed");
            		}
            }
            else
            	{
            	testButtonReveal.fail("Drawer Reveal Button Visibility Test Failed");
            	throw new AssertionError("Drawer Reveal Button Visibility Test Failed");
            	}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
			driver.quit();
			}
		}
		@Test
		public void shortsButton() throws InterruptedException {
            //Shorts Button Visibility + Functionality
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testShortsVisibility = extent.createTest("Shorts Button Visibility");
			ExtentTest testShortsFunctionality = extent.createTest("Shorts Button Functionality");
			WebDriver driver = new ChromeDriver();
			try {
			driver.get("https://www.youtube.com/");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
                  .click();
            Thread.sleep(3000);
            WebElement ShortsButton = driver.findElement(By.cssSelector("#items > ytd-mini-guide-entry-renderer:nth-child(2)"));
            String searchUrl = driver.getCurrentUrl();
            if(ShortsButton.isDisplayed())
            {
            	System.out.println("Shorts Button Visibility Test Successful");
            	testShortsVisibility.pass("Shorts Button Visibility Test Successful");
            	ShortsButton.click();
            	Thread.sleep(1000);
            	searchUrl = driver.getCurrentUrl();
            	if(searchUrl.contains("https://www.youtube.com/shorts"))
            	{
        		testShortsFunctionality.pass("Shorts Button Functionality Test Successful");
            	System.out.println("Shorts Button Functionality Test Successful");
            } else {
            	testShortsFunctionality.fail("Shorts Button Functionality Test Failed");
            	throw new AssertionError("Shorts Button Functionality Test Failed");
            }
            }
        	else
        	{
        		testShortsVisibility.fail("Shorts Button Visibility Test Failed");
        		throw new AssertionError("Shorts Button Visibility Test Failed");
            }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
            driver.quit();
			}
		}
		@Test
		public void homeButton() throws InterruptedException {
            //Home Button Visibility + Functionality
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testHomeFunctionality = extent.createTest("Home Button Functionality");
			ExtentTest testHomeVisibility = extent.createTest("Home Button Visibility");
			WebDriver driver = new ChromeDriver();
			try {
			driver.get("https://www.youtube.com/results?search_query=adelante");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
                  .click();

            Thread.sleep(3000);
            WebElement HomeButton = driver.findElement(By.cssSelector("#items > ytd-mini-guide-entry-renderer:nth-child(1)"));
            if(HomeButton.isDisplayed())
            {
            	testHomeVisibility.pass("Home Button Visibility Test Successful");
            	System.out.println("Home Button Visibility Test Successful");
            	HomeButton.click();
            	Thread.sleep(2000);
            	String searchUrl = driver.getCurrentUrl();
            	if(searchUrl.equals("https://www.youtube.com/"))
        		{
        			testHomeFunctionality.pass("Home Button Functionality Test Successful");
        			System.out.println("Home Button Functionality Test Successful");
        		} else {
        			testHomeFunctionality.fail("Home Button Functionality Test Failed");
        			throw new AssertionError("Home Button Functionality Test Failed");
        		}
            }
        	else
        	{
        		testHomeVisibility.fail("Home Button Visibility Test Failed");
        		throw new AssertionError("Home Button Visibility Test Failed");
            }
			}catch(Exception e) {
            	e.printStackTrace();
            }finally {
            driver.quit();
		}
			}
		@Test
		public void SubscriptionButton() throws InterruptedException {
            //Subscription Button Visibility + Functionality
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testSubscriptionVisibility = extent.createTest("Subscriptions Button Visibility");
			ExtentTest testSubscriptionFunctionality = extent.createTest("Subscriptions Button Functionality");
			WebDriver driver = new ChromeDriver();
			try {
			driver.get("https://www.youtube.com/");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
                  .click();

            Thread.sleep(3000);
            WebElement SubscriptionsButton = driver.findElement(By.cssSelector("#items > ytd-mini-guide-entry-renderer:nth-child(3)"));
            String searchUrl = driver.getCurrentUrl();
            if(SubscriptionsButton.isDisplayed())
            {
            	System.out.println("Subscriptions Button Visibility Test Successful");
            	testSubscriptionVisibility.pass("Subscriptions Button Visibility Test Successful");
            	SubscriptionsButton.click();
            	Thread.sleep(1000);
            	searchUrl = driver.getCurrentUrl();
            	if(searchUrl.equals("https://www.youtube.com/feed/subscriptions"))
        		{
            		testSubscriptionFunctionality.pass("Subscriptions Button Functionality Test Successful");
            		System.out.println("Subscriptions Button Functionality Test Successful");
        		} else {
        			testSubscriptionFunctionality.fail("Subscriptions Button Functionality Test Failed");
        			throw new AssertionError("Subscriptions Button Functionality Test Failed");
        		}
            }
        	else
        	{
        		testSubscriptionVisibility.fail("Subscriptions Button Visibility Test Failed");
        		throw new AssertionError("Subscriptions Button Visibility Test Failed");
            }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
            driver.quit();
		}
		}
		@Test
		public void YouButton() throws InterruptedException {
            //You Button Visibility + Functionality
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testYouVisibility = extent.createTest("You Button Visibility");
			ExtentTest testYouFunctionality = extent.createTest("You Button Functionality");
			WebDriver driver = new ChromeDriver();
			try {
			driver.get("https://www.youtube.com/");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
                  .click();

            Thread.sleep(3000);
            WebElement YouButton = driver.findElement(By.cssSelector("#items > ytd-mini-guide-entry-renderer:nth-child(4)"));
            String searchUrl = driver.getCurrentUrl();
            if(YouButton.isDisplayed())
            {
            	testYouVisibility.pass("You Button Visibility Test Successful");
            	System.out.println("You Button Visibility Test Successful");
            	YouButton.click();
            	Thread.sleep(1000);
            	searchUrl = driver.getCurrentUrl();
            	if(searchUrl.equals("https://www.youtube.com/feed/you"))
        		{
        			testYouFunctionality.pass("You Button Functionality Test Successful");
        			System.out.println("You Button Functionality Test Successful");
        		} else {
        			testYouFunctionality.fail("You Button Functionality Test Failed");
        			throw new AssertionError("You Button Functionality Test Failed");
        		}
            }
        	else
        	{
        		testYouVisibility.fail("You Button Visibility Test Failed");
        		throw new AssertionError("You Button Visibility Test Failed");
            }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
            driver.quit();
		}
		}
		@Test
		public void HistoryButton() throws InterruptedException {
            //History Button Visibility + Functionality
			System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
			ExtentTest testHistoryVisibility = extent.createTest("History Button Visibility");
			ExtentTest testHistoryFunctionality = extent.createTest("History Button Functionality");
			WebDriver driver = new ChromeDriver();
			try {
			driver.get("https://www.youtube.com/");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"))
                  .click();

            Thread.sleep(3000);
            WebElement HistoryButton = driver.findElement(By.cssSelector("#items > ytd-mini-guide-entry-renderer:nth-child(5)"));
            String searchUrl = driver.getCurrentUrl();
            if(HistoryButton.isDisplayed())
            {
            	testHistoryVisibility.pass("History Button Visibility Test Successful");
            	System.out.println("History Button Visibility Test Successful");
            	HistoryButton.click();
            	Thread.sleep(2000);
            	searchUrl = driver.getCurrentUrl();
            	if(searchUrl.equals("https://www.youtube.com/feed/history"))
        		{
            		System.out.println("History Button Functionality Test Successful");
            		testHistoryFunctionality.pass("History Button Functionality Test Successful");
        		}
            	else {
            		testHistoryFunctionality.fail("History Button Functionality Test Failed");
            		throw new AssertionError("History Button Functionality Test Failed");
            	}
            }
        	else
        	{
        		testHistoryVisibility.fail("History Button Visibility Test Failed");
        		throw new AssertionError("History Button Visibility Test Failed");
            }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
            driver.quit();
    }
		}
}
