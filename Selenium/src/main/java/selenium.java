import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

    public class selenium {

        public static void main(String[] args) throws Exception {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\97252\\Downloads\\chromedriver.exe");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter username:");
            String username = scanner.next();
            System.out.println("Enter password:");
            String password = scanner.next();

            ChromeDriver driver = new ChromeDriver();
            driver.get("https://www.aac.ac.il");
            Thread.sleep(1000);
            //login
            WebElement personalInfo = driver.findElement(By.cssSelector("a[href='https://portal.aac.ac.il']"));
            personalInfo.click();
            Thread.sleep(1000);
            WebElement userId = driver.findElement(By.id("Ecom_User_ID"));
            userId.sendKeys(username);
            WebElement passwordInfo = driver.findElement(By.id("Ecom_Password"));
            passwordInfo.sendKeys(password);
            WebElement submitButton = driver.findElement(By.id("wp-submit"));
            submitButton.click();
            WebElement moodleSystem = driver.findElement(By.cssSelector("a[href='https://moodle.aac.ac.il/login/index.php']"));
            moodleSystem.click();
            Thread.sleep(4000);

            //print courses
            List<WebElement> coursesList = driver.findElements(By.xpath("//div[contains(@class,'card dashboard-card') and contains(@role,'listitem')]"));
            System.out.println("Your courses list:");
            for (int i = 0; i < coursesList.size(); i++) {
                String courseName = null;
                try {
                    courseName = coursesList.get(i).findElement(By.className("multiline")).getText();
                } catch (Exception ignored) {

                }
                if (courseName != null)
                    System.out.println(i + 1 + "." + (courseName));
            }

            //choose course
            int courseOption=0;
            boolean error = false;
              do {
                  error = false;
                  try {
                      System.out.println("Which course would you like to choose :");
                      courseOption = scanner.nextInt();

                  }catch (Exception e){
                      System.out.println("Wrong option,try again.");
                      error = true;
                      scanner.nextLine();
                  }
              }while (error);
              coursesList.get(courseOption - 1).click();

                Thread.sleep(2000);
                // logout
                WebElement accountBar = driver.findElements(By.id("action-menu-toggle-1")).get(0);
                accountBar.click();
                driver.findElement(By.xpath("//a[@data-title='logout,moodle']")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//a[@href='https://portal.aac.ac.il/AGLogout']")).click();
                System.out.println("Good bye! ");


            }
        }


