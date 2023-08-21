package editInfo.hrms.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testprocess {

	WebDriver driver;

	@BeforeClass

	public void Setup() throws Throwable {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("http://hrms.marolix.com/login");

		driver.findElement(By.id("email")).sendKeys("admintest@gmail.com");

		driver.findElement(By.id("password")).sendKeys("123456789");

		driver.findElement(By.id("login_button")).click();

		WebElement drop = driver.findElement(By.xpath("//span[text()='HRM System']"));

		Actions ac = new Actions(driver);

		ac.moveToElement(drop).build().perform();

		driver.findElement(By.xpath("//span[text()='HRM System']")).click();
		Thread.sleep(2000);

	}

	@Test

	public void test1() throws Throwable {

		WebElement empsetup = driver.findElement(By.xpath("//a[@href='http://hrms.marolix.com/employee']"));
		Actions ac = new Actions(driver);

		ac.moveToElement(empsetup).perform();

		driver.findElement(By.xpath("//a[@href='http://hrms.marolix.com/employee']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@class='dataTable-input']")).sendKeys("MT-0643");
		driver.findElement(By.xpath("//a[@class='btn btn-outline-primary']")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-sm btn-primary']")).click();

		WebElement branch_drop = driver.findElement(By.id("branch_id"));
		WebElement dep_drop = driver.findElement(By.id("department_id"));
		WebElement desg_drop = driver.findElement(By.id("designation_id"));

		Select sel = new Select(branch_drop);
		Select dept = new Select(dep_drop);
		Select desgn = new Select(desg_drop);

		sel.selectByIndex(1);
		dept.selectByVisibleText("IT");
		desgn.selectByVisibleText("Trainee");

		driver.findElement(By.id("company_doj")).sendKeys("12052021");
		
		
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,150)");

		WebElement update = driver.findElement(By.xpath("//input[@value='Update']"));

		ac.moveToElement(update).perform();

		driver.findElement(By.xpath("//input[@value='Update']")).click();

	}

	@AfterClass
	public void teardown() {

	}

}
