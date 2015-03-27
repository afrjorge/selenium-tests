/*
 * !*****************************************************************************
 * Selenium Tests For CTools Copyright (C) 2002-2014 by Pentaho :
 * http://www.pentaho.com
 * ********************************************************
 * ********************** Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * ****************************************************************************
 */
package org.pentaho.ctools.cdf.require;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.pentaho.ctools.suite.CToolsTestSuite;
import org.pentaho.ctools.utils.ElementHelper;
import org.pentaho.ctools.utils.ScreenshotTestRule;

/**
 * Testing the functionalities related with Xaction Component.
 *
 * Naming convention for test:
 *  'tcN_StateUnderTest_ExpectedBehavior'
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExecuteXactionComponent {
  //Instance of the driver (browser emulator)
  private static WebDriver       driver;
  // Instance to be used on wait commands
  private static Wait<WebDriver> wait;
  // The base url to be append the relative url in test
  private static String          baseUrl;
  //Log instance
  private static Logger          log                = LogManager.getLogger(ExecuteXactionComponent.class);

  @Rule
  public ScreenshotTestRule      screenshotTestRule = new ScreenshotTestRule(driver);

  /**
   * Shall initialized the test before run each test case.
   */
  @BeforeClass
  public static void setUp() {
    driver = CToolsTestSuite.getDriver();
    wait = CToolsTestSuite.getWait();
    baseUrl = CToolsTestSuite.getBaseUrl();

    // Go to sample
    init();
  }

  /**
   * Go to the ExecuteXactionComponent web page.
   */
  public static void init() {
    // The URL for the CheckComponent under CDF samples
    // This samples is in: Public/plugin-samples/CDF/Documentation/Component
    // Reference/Core Components/ExecuteXactionComponent
    driver.get(baseUrl + "api/repos/%3Apublic%3Aplugin-samples%3Apentaho-cdf%3Apentaho-cdf-require%3A30-documentation%3A30-component_reference%3A10-core%3A76-ExecuteXactionComponent%3Aexecute_xaction_component.xcdf/generatedContent");

    // Not we have to wait for loading disappear
    ElementHelper.WaitForElementInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));
  }

  /**
   * ############################### Test Case 2 ###############################
   *
   * Test Case Name:
   *    Reload Sample
   * Description:
   *    Reload the sample (not refresh page).
   * Steps:
   *    1. Click in Code and then click in button 'Try me'.
   */
  @Test(timeout = 60000)
  public void tc1_PageContent_DisplayTitle() {
    // Wait for title become visible and with value 'Community Dashboard Framework'
    wait.until(ExpectedConditions.titleContains("Community Dashboard Framework"));
    // Wait for visibility of 'VisualizationAPIComponent'
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='dashboardContent']/div/div/div/h2/span[2]")));

    // Validate the sample that we are testing is the one
    assertEquals("Community Dashboard Framework", driver.getTitle());
    assertEquals("ExecuteXactionComponent", ElementHelper.WaitForElementPresentGetText(driver, By.xpath("//div[@id='dashboardContent']/div/div/div/h2/span[2]")));
  }

  /**
   * ############################### Test Case 2 ###############################
   *
   * Test Case Name:
   *    Reload Sample
   * Description:
   *    Reload the sample (not refresh page).
   * Steps:
   *    1. Click in Code and then click in button 'Try me'.
   */
  @Test(timeout = 60000)
  public void tc2_ReloadSample_SampleReadyToUse() {
    // ## Step 1
    // Render again the sample
    ElementHelper.FindElement(driver, By.xpath("//div[@id='example']/ul/li[2]/a")).click();
    ElementHelper.FindElement(driver, By.xpath("//div[@id='code']/button")).click();

    // Not we have to wait for loading disappear
    ElementHelper.WaitForElementInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));

    // Now sample element must be displayed
    assertTrue(ElementHelper.FindElement(driver, By.id("sample")).isDisplayed());

    //Check the number of divs with id 'SampleObject'
    //Hence, we guarantee when click Try Me the previous div is replaced
    int nSampleObject = driver.findElements(By.id("sampleObject")).size();
    assertEquals(1, nSampleObject);
  }

  /**
   * ############################### Test Case 3 ###############################
   *
   * Test Case Name:
   *    Execute Xacion
   * Description:
   *    We pretend validate the generated chart (in an image) and if the image
   *    has a valid url.
   * Steps:
   *    1. Click to generate chart
   *    2. Check if a chart was generated
   *    3. Check the http request for the image generated
   */
  @Test(timeout = 60000)
  public void tc3_PressToGenerateChart_ChartIsDisplayed() {
    // ## Step 1
    String buttonName = ElementHelper.WaitForElementPresentGetText(driver, By.xpath("//button/span"));
    assertEquals("Execute XAction", buttonName);
    //Click in button
    ElementHelper.FindElement(driver, By.xpath("//button")).click();

    // ## Step 1
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fancybox-content")));
    driver.switchTo().frame("fancybox-frame");
    //Check the title
    String chartTitle = ElementHelper.WaitForElementPresentGetText(driver, By.xpath("//table/tbody/tr/td"));
    assertEquals("Action Successful", chartTitle);
    //Check for the displayed image
    WebElement xactionElement = ElementHelper.FindElement(driver, By.cssSelector("img"));
    assertNotNull(xactionElement);

    String attrSrc = xactionElement.getAttribute("src");
    String attrWidth = xactionElement.getAttribute("width");
    String attrHeight = xactionElement.getAttribute("height");

    assertTrue(attrSrc.startsWith(baseUrl + "getImage?image=tmp_chart_admin-"));
    assertEquals(attrWidth, "500");
    assertEquals(attrHeight, "600");

    // ## Step 3
    try {
      URL url = new URL(attrSrc);
      URLConnection connection = url.openConnection();
      connection.connect();

      assertEquals(HttpStatus.SC_OK, ((HttpURLConnection) connection).getResponseCode());
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }

    //Close pop-up window
    driver.switchTo().defaultContent();
    ElementHelper.FindElement(driver, By.id("fancybox-close")).click();
    ElementHelper.WaitForElementInvisibility(driver, By.id("fancybox-content"));
    assertNotNull(ElementHelper.FindElement(driver, By.xpath("//button")));
  }

  @AfterClass
  public static void tearDown() {}
}
