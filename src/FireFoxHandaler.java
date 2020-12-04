import org.openqa.selenium.chrome.*;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;


public class FireFoxHandaler {
	WebDriver driver;
	private LinkedList<Restaurant> restaurants;
	private LinkedList<String> resUrls;
	
	FireFoxHandaler(){
		restaurants = new LinkedList<>();
		resUrls = new LinkedList<>();
	}
	
	public LinkedList<Restaurant> getRestaurantInfo(){
		return restaurants;
	}
	
	
	public boolean openBrowser() {
		
		System.setProperty("webdriver.gecko.driver",
				"Geckodriver\\v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		return true;
	}
	
		
	public void loadUrl(String url) {
		driver.get(url);
		System.out.println("---");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	public boolean isBrowserOpen() {
		try {
			driver.findElement(By.tagName("html"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;	
	}
	
	public void closeBrowser() {
		driver.close();	
	}
	
	public void takeList() {
		
		By footerBy = By.xpath("//div[@data-test=\"footer\"]");
		scrollUpToEndOfThePage();
		
		By TitleBy = By.xpath("//div[@data-test=\"restaurant-cards\"]/div/div[2]/div[1]/div[1]/a");
		//By TitleBy = By.xpath("//div[contains(@data-test,\"restaurant-cards\")]/div/div[2]/div[1]/div[1]/a");
		
		List<WebElement> titleElements = driver.findElements(TitleBy);
		System.out.println(titleElements.size());
		Iterator<WebElement> it = titleElements.iterator();
		
		while(it.hasNext()) {
			String titleUrl = it.next().getAttribute("href");
			System.out.println(titleUrl.substring(0, titleUrl.indexOf("?")));
		}
		
		resUrls.add("");
	}
	
	public void getRestaurantData(String url){
		
		By nameBy = By.xpath("//div[@id=\"overview-section\"]//h1[1]");
		By descriptionBy = By.xpath("//div[@id=\"overview-section\"]/div[2]/div");
		By menuBy = By.xpath("//section[@id=\"oc-restaurant-profile-menu\"]//div[@class=\"menus-container\"]");
		By tweetsPresentBy = By.xpath("//div[@id=\"twitter-section\"]//h2/span[contains(text(),\"Tweets\")]");
		By tweetsBy = By.xpath("//a[@class=\"TweetAuthor-link Identity u-linkBlend\"]");
		By phoneBy = By.xpath("//aside//div[@class=\"_8ecd35dd\"]//div[@class=\"df8add00\"]/div[2][preceding-sibling::div/span[contains(text(),\"Phone number\")]]");
		By deliveryBy = By.xpath("//aside//section/div[preceding-sibling::p/span[contains(text(),\"Delivery\")]]/a");
		By addressBy = By.xpath("//a[contains(@href,\"google.com/maps\")]/span");
		By hrsOpBy = By.xpath("//aside//div[@class=\"_8ecd35dd\"]//div[@class=\"df8add00\"]/div[2][preceding-sibling::div/span[contains(text(),\"Hours of operation\")]]/div");
		By cuisinesBy = By.xpath("//aside//div[@class=\"_8ecd35dd\"]//div[@class=\"df8add00\"]/div[2][preceding-sibling::div/span[contains(text(),\"Cuisines\")]]");
		By paymentOptBy = By.xpath("//aside//div[@class=\"_8ecd35dd\"]//div[@class=\"df8add00\"]/div[2][preceding-sibling::div/span[contains(text(),\"Payment options\")]]");
		By additionalBy = By.xpath("//aside//div[@class=\"_8ecd35dd\"]//div[@class=\"df8add00\"]/div[2][preceding-sibling::div/span[contains(text(),\"Additional\")]]");
		By websiteBy = By.xpath("//aside//div[@class=\"_8ecd35dd\"]//div[@class=\"df8add00\"]/div[2][preceding-sibling::div/span[contains(text(),\"Website\")]]");
		
		By viewMoreBy = By.xpath("//aside//a[child::span[contains(text(),\"View more\")]]");
		
		Restaurant restaurant = new Restaurant();
		loadUrl(url);
		

		try {
			String name = driver.findElement(nameBy).getText();
			//dentist.setName(name);
			System.out.println(name);
			restaurant.setName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String description = driver.findElement(descriptionBy).getText();
			//dentist.setPhone(phone);
			System.out.println(description);
			restaurant.setDescription(description);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		

		try {
			driver.findElement(viewMoreBy).click();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		
		
		try {
			String phone = driver.findElement(phoneBy).getText();
			System.out.println(phone);
			restaurant.setPhoneNumbers(phone);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			List<WebElement> list = driver.findElements(deliveryBy);
			Iterator<WebElement> it = list.iterator();
			//String delivery = "";
			List<String> delivery = new LinkedList<>();
			while(it.hasNext()) {
				String urls = it.next().getAttribute("href");
				System.out.println(urls);
				delivery.add(urls.substring(0, urls.indexOf("?")));
			}
			restaurant.setDelivery(delivery);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String address = driver.findElement(addressBy).getText().trim();
			System.out.println(address);
			restaurant.setAddress(address);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String hrsOp = driver.findElement(hrsOpBy).getText();
			System.out.println(hrsOp);
			restaurant.setHoursOfOperation(hrsOp);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String cuisines = driver.findElement(cuisinesBy).getText();
			System.out.println(cuisines);
			restaurant.setCuisines(cuisines);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String paymentOpt = driver.findElement(paymentOptBy).getText();
			System.out.println(paymentOpt);
			restaurant.setPaymentOptions(paymentOpt);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String additional = driver.findElement(additionalBy).getText();
			System.out.println(additional);
			restaurant.setAdditional(additional);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try {
			String website = driver.findElement(websiteBy).getText();
			System.out.println(website);
			restaurant.setWebsite(website);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		try { // https://www.guru99.com/handling-iframes-selenium.html
			if(driver.findElement(tweetsPresentBy).getText().length() > 5) {
				driver.switchTo().frame("twitter-widget-0");
				String tweets = driver.findElement(tweetsBy).getAttribute("href");
				
				System.out.println(tweets);
				restaurant.setTweets(tweets);
				driver.switchTo().parentFrame();
				
			}
			
		
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		restaurant.setMenus(getMenu());
		
		restaurants.add(restaurant);
	}	
	

	public List<Menu> getMenu() {
		By viewFullMenuBy = By.xpath("//button[contains(text(),\"View full menu\")]");
		
		By menuSchedualBy = By.xpath("//div[contains(@class,\"menu-nav\")]/button");
		By menuContainerBy = By.xpath("//div[@class=\"menus-container\"]/div/div[@class=\"menu-body\"]/div");
		//By menuTitleBy = By.xpath("./div[@class=\"menu-section-header__3nfLpHEA\"]/h3");
		//By itemsBy = By.xpath("./div[@class=\"menu-items__2DRnPKGV\"]/div[@class=\"menu-item__2ZxJOnTY\"]");
		By menuNameBy = By.xpath("./div");
		By menuDescriptionBy = By.xpath("./p");
		
		try {
			driver.findElement(viewFullMenuBy).click();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		List<Menu> menus = new LinkedList();
		//StringBuilder sb = new StringBuilder();
		
		
		try {
		List<WebElement> menuTimeElement = driver.findElements(menuSchedualBy);
		Iterator<WebElement> menuIt = menuTimeElement.iterator();
		
		while(menuIt.hasNext()) {
			Menu menu = new Menu();
			
			WebElement headerE = menuIt.next();
			
			//sb.append(headerE.getText() + " : \n\r");
			menu.setName(headerE.getText());
			headerE.click();
			
			List<WebElement> menuContainerElement = driver.findElements(menuContainerBy);
			
			int containerSize = menuContainerElement.size();
			
			List<SubTitle> subTitles = new LinkedList<>();
			if(containerSize > 0) {
				
				for (int i = 1; i < (containerSize+1); i++) {
					By menuTitleBy = By.xpath("//div[@class=\"menus-container\"]/div/div[@class=\"menu-body\"]/div["+i+"]/div[@class=\"menu-section-header__3nfLpHEA\"]/h3");
					SubTitle subTitle = new SubTitle();
					try {
						subTitle.setName(driver.findElement(menuTitleBy).getText());
						//sb.append(driver.findElement(menuTitleBy).getText() + "\n");
					} catch (Exception e)  {}
					
					By itemsBy = By.xpath("//div[@class=\"menus-container\"]/div/div[@class=\"menu-body\"]/div["+ i +"]/div[@class=\"menu-items__2DRnPKGV\"]/div[@class=\"menu-item__2ZxJOnTY\"]");
					
					List<WebElement> itemsElement = driver.findElements(itemsBy);
					Iterator<WebElement>itemsIt = itemsElement.iterator();
					System.out.println(itemsElement.size());
					List<Item> items = new LinkedList<>();
					while(itemsIt.hasNext()) {
						Item item = new Item();
						WebElement itemE = itemsIt.next();
						try {
							item.setName(itemE.findElement(menuNameBy).getText());
							//sb.append(itemE.findElement(menuNameBy).getText()+ "\n");
						} catch (Exception e)  {}
						try {
							item.setName(itemE.findElement(menuDescriptionBy).getText());
							//sb.append(itemE.findElement(menuDescriptionBy).getText()+ "\n");
						} catch (Exception e) {}
						
						items.add(item);
						}
					subTitle.setItems(items);
					//sb.append("\n");
					subTitles.add(subTitle);
					}
					
				}
			//sb.append("\n");
			menu.setSubTitles(subTitles);	
			menus.add(menu);
			}
		
		}catch (Exception e) {}
		
		//System.out.println(sb.toString());
		
		return menus; 
		
	}
	

// for xls 
	public String getMenu1() {
			By viewFullMenuBy = By.xpath("//button[contains(text(),\"View full menu\")]");
			
			By menuSchedualBy = By.xpath("//div[contains(@class,\"menu-nav\")]/button");
			By menuContainerBy = By.xpath("//div[@class=\"menus-container\"]/div/div[@class=\"menu-body\"]/div");
			//By menuTitleBy = By.xpath("./div[@class=\"menu-section-header__3nfLpHEA\"]/h3");
			//By itemsBy = By.xpath("./div[@class=\"menu-items__2DRnPKGV\"]/div[@class=\"menu-item__2ZxJOnTY\"]");
			By menuNameBy = By.xpath("./div");
			By menuDescriptionBy = By.xpath("./p");
			
			try {
				driver.findElement(viewFullMenuBy).click();
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
			StringBuilder sb = new StringBuilder();
			
			
			try {
			List<WebElement> menuTimeElement = driver.findElements(menuSchedualBy);
			Iterator<WebElement> menuIt = menuTimeElement.iterator();
			
			while(menuIt.hasNext()) {
				
				WebElement headerE = menuIt.next();
				
				sb.append(headerE.getText() + " : \n\r");
				
				headerE.click();
				
				List<WebElement> menuContainerElement = driver.findElements(menuContainerBy);
				
				int containerSize = menuContainerElement.size();
				
				List<SubTitle> subTitles = new LinkedList<>();
				if(containerSize > 0) {
					
					for (int i = 1; i < (containerSize+1); i++) {
						By menuTitleBy = By.xpath("//div[@class=\"menus-container\"]/div/div[@class=\"menu-body\"]/div["+i+"]/div[@class=\"menu-section-header__3nfLpHEA\"]/h3");
						try {
							sb.append(driver.findElement(menuTitleBy).getText() + "\n");
						} catch (Exception e)  {}
						
						By itemsBy = By.xpath("//div[@class=\"menus-container\"]/div/div[@class=\"menu-body\"]/div["+ i +"]/div[@class=\"menu-items__2DRnPKGV\"]/div[@class=\"menu-item__2ZxJOnTY\"]");
						
						List<WebElement> itemsElement = driver.findElements(itemsBy);
						Iterator<WebElement>itemsIt = itemsElement.iterator();
						System.out.println(itemsElement.size());
						List<Item> items = new LinkedList<>();
						while(itemsIt.hasNext()) {
							WebElement itemE = itemsIt.next();
							try {
								sb.append(itemE.findElement(menuNameBy).getText()+ "\n");
							} catch (Exception e)  {}
							try {
								sb.append(itemE.findElement(menuDescriptionBy).getText()+ "\n");
							} catch (Exception e) {}
							
							}
						sb.append("\n");
						}
						
					}
				sb.append("\n");
				
				}
			
			}catch (Exception e) {}
					
		return sb.toString();	
	}
		


	public void scrollUpToEndOfThePage() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		By TitleBy = By.xpath("//div[@data-test=\"restaurant-cards\"]/div/div[2]/div[1]/div[1]/a");
		
		
			boolean go = true;
			int lastCount = 0;
			int currentCounts = 0;
			int point = 550;
			do {
				try {
				jse.executeScript("scroll(0, "+point+");");
				//Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				currentCounts = driver.findElements(TitleBy).size();
				}catch(Exception e) {
					e.printStackTrace();
				}	

				
				if(currentCounts > lastCount) {
					lastCount = currentCounts;
					point += 550;
					
				}
				else if(currentCounts == lastCount){
					go = false;	
				}else {
					point += 550;
				}
				
			}while(go);
			
		
	}
	
	public void scrollUpToFixPixel() {
		// https://stackoverflow.com/questions/42982950/how-to-scroll-down-the-page-till-bottomend-page-in-the-selenium-webdriver
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("scroll(0, 250);");
			Thread.sleep(200);
			jse.executeScript("scroll(0, 550);");
			Thread.sleep(200);
			jse.executeScript("scroll(0, 750);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 1050);");
			Thread.sleep(200);
			jse.executeScript("scroll(0, 1250);");
			Thread.sleep(500);
			
			jse.executeScript("scroll(0, 2250);");
			Thread.sleep(500);
			
			jse.executeScript("scroll(0, 3250);");
			Thread.sleep(500);
			
			jse.executeScript("scroll(0, 4250);");
			Thread.sleep(500);
			
			jse.executeScript("scroll(0, 5250);");
			Thread.sleep(500);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if I direct go to bottom of the page page full content don't load
		// jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	
    public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);

    }
	
	
	
}
