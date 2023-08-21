package hrmstest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestProcess {

	WebDriver driver;

	@BeforeClass

	public void Setup() throws Throwable {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("http://hrms.marolix.com/login");

		driver.findElement(By.id("email")).sendKeys("sairam@marolix.com");

		driver.findElement(By.id("password")).sendKeys("Namo@12345");

		driver.findElement(By.id("login_button")).click();

		WebElement drop = driver.findElement(By.xpath("//span[text()='User Management']"));

		Actions ac = new Actions(driver);

		ac.moveToElement(drop).build().perform();

		driver.findElement(By.xpath("//span[text()='User Management']")).click();

		Thread.sleep(1000);
	}

	@Test(dataProvider = "hrmsdatatest", dataProviderClass = ExceldataTesth.class)

	public void testflow(Employee e ) throws Throwable {

		driver.findElement(By.xpath("//a[text()='User']")).click();

		driver.findElement(By.xpath("//a[@data-url='http://hrms.marolix.com/users/create']")).click();

		driver.findElement(By.id("name")).sendKeys(e.getEmployeeName());

		Thread.sleep(1000);

		driver.findElement(By.id("email")).sendKeys(e.getEmail());

		driver.findElement(By.xpath("//div[@class='choices__item choices__item--selectable']")).click();

		WebElement Drop = driver.findElement(By.xpath("//input[@class='choices__input choices__input--cloned']"));

		Drop.sendKeys(e.getRole() + Keys.ENTER);

		driver.findElement(By.id("password")).sendKeys(e.getPassword());

		driver.findElement(By.xpath("//input[@value='Create']")).click();

		Thread.sleep(2000);

	}

	@AfterClass

	public void teardown() {
	  driver.close();

	}
}
