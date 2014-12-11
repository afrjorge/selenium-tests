/*!*****************************************************************************
 *
 * Selenium Tests For CTools
 *
 * Copyright (C) 2002-2014 by Pentaho : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.ctools.cde.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.pentaho.ctools.suite.CToolsTestSuite;
import org.pentaho.ctools.utils.ElementHelper;
import org.pentaho.ctools.utils.ScreenshotTestRule;

/**
 * This script pretends to validate the sample Map Component Full Test
 *
 * Naming convention for test:
 *  'tcN_StateUnderTest_ExpectedBehavior'
 *
 * Issues History:
 * -
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapComponentFullTest {
  // Instance of the driver (browser emulator)
  private static WebDriver  driver;
  // The base url to be append the relative url in test
  private static String     baseUrl;
  //Log instance
  private static Logger     log                = LogManager.getLogger(MapComponentFullTest.class);

  @Rule
  public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule(driver);

  /**
   * Shall initialized the test before run each test case.
   */
  @BeforeClass
  public static void setUp() {
    log.info("setUp##" + MapComponentFullTest.class.getSimpleName());
    driver = CToolsTestSuite.getDriver();
    baseUrl = CToolsTestSuite.getBaseUrl();
  }

  @Before
  public void setUpTestCase() {
    //Go to AddinReference
    driver.get(baseUrl + "api/repos/%3Apublic%3Aplugin-samples%3Apentaho-cdf-dd%3Atests%3AFullMapTest.wcdf/generatedContent");

    //NOTE - we have to wait for loading disappear
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));
  }

  /**
   * ############################### Test Case 1 ###############################
   *
   * Test Case Name:
   *    Validate Page Contents
   * Description:
   *    Here we want to validate the page contents.
   * Steps:
   *    1. Check the widget's title.
   *    2. Check Sample title
   *    3. Check Map title
   */
  @Test(timeout = 60000)
  public void tc01_PageContent_DisplayContents() {
    log.info("tc01_PageContent_DisplayContents");

    /*
     * ## Step 1
     */
    //Check page title
    assertEquals("Community Dashboard Editor", driver.getTitle());
    //Check sample title
    String sampleTitle = ElementHelper.GetText(driver, By.xpath("//div[2]/div[2]/div/div/span"));
    assertEquals("Map Component Full Test", sampleTitle);
    String sampleMapTitle = ElementHelper.GetText(driver, By.xpath("//div[2]/span"));
    assertEquals("Full Map with CGG Markers and PopupWindows", sampleMapTitle);
  }

  /**
   * ############################### Test Case 2 ###############################
   *
   * Test Case Name:
   *    Full Map with CGG Markers and PopupWindows
   * Description:
   *    In this test case we pretend to check if the markers and popups windows
   *    are displayed.
   * Steps:
   *    1. Check the data exist
   *    2. Chick in each marker
   *    3. Check tooltip
   *    4. Check disabling series in pie chart
   */
  @Test(timeout = 120000)
  public void tc02_MapCGGMarkersAndPopupWindows_MarkersAndPopupsDisplayed() {
    log.info("tc02_MapCGGMarkersAndPopupWindows_MarkersAndPopupsDisplayed");

    /*
     * ## Step 1
     */
    WebElement marker1 = ElementHelper.FindElement(driver, By.xpath("//*[local-name()='image'][1]"));
    WebElement marker2 = ElementHelper.FindElement(driver, By.xpath("//*[local-name()='image'][2]"));
    WebElement marker3 = ElementHelper.FindElement(driver, By.xpath("//*[local-name()='image'][3]"));
    WebElement marker4 = ElementHelper.FindElement(driver, By.xpath("//*[local-name()='image'][4]"));
    WebElement marker5 = ElementHelper.FindElement(driver, By.xpath("//*[local-name()='image'][5]"));
    WebElement marker6 = ElementHelper.FindElement(driver, By.xpath("//*[local-name()='image'][6]"));
    WebElement marker7 = ElementHelper.FindElement(driver, By.xpath("//*[local-name()='image'][7]"));
    assertNotNull(marker1);
    assertNotNull(marker2);
    assertNotNull(marker3);
    assertNotNull(marker4);
    assertNotNull(marker5);
    assertNotNull(marker6);
    assertNotNull(marker7);

    /*
     * ## Step 2
     */
    //>>> Open Marker 1
    marker1.click();
    //Wait for loading disappear
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));
    ElementHelper.IsElementPresent(driver, By.xpath("//div[@id='HiddenContentCol']"));
    //Check we have the expect series displayed
    String marker1Serie1 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker1Serie2 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][2]//*[local-name()='text']"));
    String marker1Serie3 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][3]//*[local-name()='text']"));
    String marker1Serie4 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker1Serie5 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][2]//*[local-name()='text']"));
    assertEquals("Vintage Cars", marker1Serie1);
    assertEquals("Trucks and Buses", marker1Serie2);
    assertEquals("Ships", marker1Serie3);
    assertEquals("Motorcycles", marker1Serie4);
    assertEquals("Trains", marker1Serie5);
    String popupTitle = ElementHelper.GetText(driver, By.xpath("//*[local-name()='svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='text']"));
    assertEquals("Sales For Product", popupTitle);
    //Check the pie chart is present
    WebElement marker1SeriesTrains = ElementHelper.FindElement(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][5]"));
    assertEquals("rgb(148,103,189)", marker1SeriesTrains.getAttribute("fill"));
    //Close popup
    ElementHelper.FindElement(driver, By.id("featurePopup_close")).click();

    //>>> Open Marker 2
    marker2.click();
    //Wait for loading disappear
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));
    ElementHelper.IsElementPresent(driver, By.xpath("//div[@id='HiddenContentCol']"));
    //Check we have the expect series displayed
    String marker2Serie1 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker2Serie2 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][2]//*[local-name()='text']"));
    String marker2Serie3 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker2Serie4 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][2]//*[local-name()='text']"));
    assertEquals("Classic Cars", marker2Serie1);
    assertEquals("Vintage Cars", marker2Serie2);
    assertEquals("Trucks and Buses", marker2Serie3);
    assertEquals("Ships", marker2Serie4);
    String marker2PopupTitle = ElementHelper.GetText(driver, By.xpath("//*[local-name()='svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='text']"));
    assertEquals("Sales For Product", marker2PopupTitle);
    //Check the pie chart is present
    WebElement marker2SeriesShips = ElementHelper.FindElement(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][4]"));
    assertEquals("rgb(214,39,40)", marker2SeriesShips.getAttribute("fill"));
    //Close popup
    ElementHelper.FindElement(driver, By.id("featurePopup_close")).click();

    //>>> Open Marker 3
    marker3.click();
    //Wait for loading disappear
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));
    ElementHelper.IsElementPresent(driver, By.xpath("//div[@id='HiddenContentCol']"));
    //Check we have the expect series displayed
    String marker3Serie1 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker3Serie2 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][2]//*[local-name()='text']"));
    String marker3Serie3 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][3]//*[local-name()='text']"));
    assertEquals("Trucks and Buses", marker3Serie1);
    assertEquals("Ships", marker3Serie2);
    assertEquals("Motorcycles", marker3Serie3);
    String marker3PopupTitle = ElementHelper.GetText(driver, By.xpath("//*[local-name()='svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='text']"));
    assertEquals("Sales For Product", marker3PopupTitle);
    //Check the pie chart is present
    WebElement marker3SeriesMotorcycles = ElementHelper.FindElement(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][3]"));
    assertEquals("rgb(44,160,44)", marker3SeriesMotorcycles.getAttribute("fill"));
    //Close popup
    ElementHelper.FindElement(driver, By.id("featurePopup_close")).click();

    //>>> Open Marker 4
    marker4.click();
    //Wait for loading disappear
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));
    ElementHelper.IsElementPresent(driver, By.xpath("//div[@id='HiddenContentCol']"));
    //Check we have the expect series displayed
    String marker4Serie1 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker4Serie2 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][2]//*[local-name()='text']"));
    assertEquals("Motorcycles", marker4Serie1);
    assertEquals("Trains", marker4Serie2);
    String marker4PopupTitle = ElementHelper.GetText(driver, By.xpath("//*[local-name()='svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='text']"));
    assertEquals("Sales For Product", marker4PopupTitle);
    //Check the pie chart is present
    WebElement marker4SeriesTrains = ElementHelper.FindElement(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][2]"));
    assertEquals("rgb(255,127,14)", marker4SeriesTrains.getAttribute("fill"));
    //Close popup
    ElementHelper.FindElement(driver, By.id("featurePopup_close")).click();

    //>>> Open Marker 5
    marker5.click();
    //Wait for loading disappear
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));
    ElementHelper.IsElementPresent(driver, By.xpath("//div[@id='HiddenContentCol']"));
    //Check we have the expect series displayed
    String marker5Serie1 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker5Serie2 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][2]//*[local-name()='text']"));
    String marker5Serie3 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker5Serie4 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][2]//*[local-name()='text']"));
    String marker5Serie5 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][3]//*[local-name()='text']"));
    String marker5Serie6 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g'][1]//*[local-name()='text']"));
    assertEquals("Classic Cars", marker5Serie1);
    assertEquals("Vintage Cars", marker5Serie2);
    assertEquals("Trucks and Buses", marker5Serie3);
    assertEquals("Ships", marker5Serie4);
    assertEquals("Motorcycles", marker5Serie5);
    assertEquals("Trains", marker5Serie6);
    String marker5PopupTitle = ElementHelper.GetText(driver, By.xpath("//*[local-name()='svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='text']"));
    assertEquals("Sales For Product", marker5PopupTitle);
    //Check the pie chart is present
    WebElement marker5SeriesTrains = ElementHelper.FindElement(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][6]"));
    assertEquals("rgb(140,86,75)", marker5SeriesTrains.getAttribute("fill"));
    //Close popup
    ElementHelper.FindElement(driver, By.id("featurePopup_close")).click();

    //>>> Open Marker 6
    marker6.click();
    //Wait for loading disappear
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));
    ElementHelper.IsElementPresent(driver, By.xpath("//div[@id='HiddenContentCol']"));
    //Check we have the expect series displayed
    String marker6Serie1 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker6Serie2 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][2]//*[local-name()='text']"));
    String marker6Serie3 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker6Serie4 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][2]//*[local-name()='text']"));
    String marker6Serie5 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][3]//*[local-name()='text']"));
    String marker6Serie6 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g'][1]//*[local-name()='text']"));
    assertEquals("Classic Cars", marker6Serie1);
    assertEquals("Vintage Cars", marker6Serie2);
    assertEquals("Trucks and Buses", marker6Serie3);
    assertEquals("Ships", marker6Serie4);
    assertEquals("Motorcycles", marker6Serie5);
    assertEquals("Trains", marker6Serie6);
    String marker6PopupTitle = ElementHelper.GetText(driver, By.xpath("//*[local-name()='svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='text']"));
    assertEquals("Sales For Product", marker6PopupTitle);
    //Check the pie chart is present
    WebElement marker6SeriesTrains = ElementHelper.FindElement(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][6]"));
    assertEquals("rgb(140,86,75)", marker6SeriesTrains.getAttribute("fill"));
    //Close popup
    ElementHelper.FindElement(driver, By.id("featurePopup_close")).click();

    //>>> Open Marker 7
    marker7.click();
    //Wait for loading disappear
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));
    ElementHelper.IsElementPresent(driver, By.xpath("//div[@id='HiddenContentCol']"));
    //Check we have the expect series displayed
    String marker7Serie1 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][1]//*[local-name()='text']"));
    String marker7Serie2 = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][2]//*[local-name()='text']"));
    assertEquals("Trains", marker7Serie1);
    assertEquals("Motorcycles", marker7Serie2);
    String marker7PopupTitle = ElementHelper.GetText(driver, By.xpath("//*[local-name()='svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='text']"));
    assertEquals("Sales For Product", marker7PopupTitle);
    //Check the pie chart is present
    WebElement marker7SeriesMotorcycles = ElementHelper.FindElement(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][2]"));
    assertEquals("rgb(255,127,14)", marker7SeriesMotorcycles.getAttribute("fill"));
    //Close popup
    ElementHelper.FindElement(driver, By.id("featurePopup_close")).click();

    /*
     * ## Step 3
     */
    marker6.click();
    //Wait for loading disappear
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@class='blockUI blockOverlay']"));
    ElementHelper.IsElementPresent(driver, By.xpath("//div[@id='HiddenContentCol']"));
    //Move mouse to element
    WebElement marker6SeriesClassicCars = ElementHelper.FindElement(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][1]"));
    Actions acts = new Actions(driver);
    acts.moveToElement(marker6SeriesClassicCars);
    acts.perform();
    String tooltipProduct = ElementHelper.WaitForElementPresentGetText(driver, By.xpath("//div[@class='tipsy tipsy-sww']/div[2]/div/table/tbody/tr[1]/td[1]/span"));
    acts.perform();
    String tooltipProductValue = ElementHelper.WaitForElementPresentGetText(driver, By.xpath("//div[@class='tipsy tipsy-sww']/div[2]/div/table/tbody/tr[1]/td[3]/span"));
    acts.perform();
    String tooltipSeries = ElementHelper.WaitForElementPresentGetText(driver, By.xpath("//div[@class='tipsy tipsy-sww']/div[2]/div/table/tbody/tr[2]/td[1]/span"));
    acts.perform();
    String tooltipSeriesValue = ElementHelper.WaitForElementPresentGetText(driver, By.xpath("//div[@class='tipsy tipsy-sww']/div[2]/div/table/tbody/tr[2]/td[3]/span"));
    acts.perform();
    String tooltipValues = ElementHelper.WaitForElementPresentGetText(driver, By.xpath("//div[@class='tipsy tipsy-sww']/div[2]/div/table/tbody/tr[3]/td[1]/span"));
    acts.perform();
    String tooltipValuesValue = ElementHelper.WaitForElementPresentGetText(driver, By.xpath("//div[@class='tipsy tipsy-sww']/div[2]/div/table/tbody/tr[3]/td[3]/span[1]"));
    acts.perform();
    String tooltipValuesValueP = ElementHelper.WaitForElementPresentGetText(driver, By.xpath("//div[@class='tipsy tipsy-sww']/div[2]/div/table/tbody/tr[3]/td[3]/span[2]"));
    assertEquals("Product", tooltipProduct);
    assertEquals("Classic Cars", tooltipProductValue);
    assertEquals("Series", tooltipSeries);
    assertEquals("Quantity", tooltipSeriesValue);
    assertEquals("Value", tooltipValues);
    assertEquals("2,381", tooltipValuesValue);
    assertEquals("37.4%", tooltipValuesValueP);

    /*
     * ## Step 4
     */
    //Disable 'Classic Cars'
    ElementHelper.Click(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][1]//*[local-name()='text']"));
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][6]"));
    //Disable 'Trucks and Buses'
    ElementHelper.Click(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][1]//*[local-name()='text']"));
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][5]"));
    //Disable 'Ships'
    ElementHelper.Click(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][2]//*[local-name()='text']"));
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][4]"));
    //Disable 'Motorcycles'
    ElementHelper.Click(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][3]//*[local-name()='text']"));
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][3]"));
    //Disable 'Trains'
    ElementHelper.Click(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g'][1]//*[local-name()='text']"));
    ElementHelper.WaitForElementPresenceAndInvisibility(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='path'][2]"));
    //Check the values for the pie char for serie Vintage Cars
    String marker6SerieVintageCarsValue = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g'][2]/*[local-name()='g'][2]/*[local-name()='text'][1]"));
    String marker6SerieVintageCarsValuePer = ElementHelper.GetText(driver, By.xpath("//div[@id='HiddenContentCol']/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g'][2]/*[local-name()='g'][2]/*[local-name()='text'][2]"));
    assertEquals("1,753", marker6SerieVintageCarsValue);
    assertEquals("(100%)", marker6SerieVintageCarsValuePer);
  }

  @AfterClass
  public static void tearDown() {}
}
