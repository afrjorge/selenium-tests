/*!*****************************************************************************
 *
 * Selenium Tests For CTools
 *
 * Copyright (C) 2002-2015 by Pentaho : http://www.pentaho.com
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
package org.pentaho.ctools.cde.require;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.pentaho.ctools.suite.CToolsTestSuite;
import org.pentaho.ctools.utils.ElementHelper;
import org.pentaho.ctools.utils.ScreenshotTestRule;

/**
 * Testing the functionalities related with CCV V2 Show Case.
 *
 * Naming convention for test:
 *  'tcN_StateUnderTest_ExpectedBehavior'
 *
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class CCCV2ShowCase {

  // Instance of the driver (browser emulator)
  private static WebDriver driver;
  // Instance to be used on wait commands
  private static Wait<WebDriver> wait;
  // The base url to be append the relative url in test
  private static String baseUrl;
  // Log instance
  private static Logger log = LogManager.getLogger( CCCV2ShowCase.class );

  @Rule
  public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule( driver );

  @BeforeClass
  public static void setUpClass() {
    log.info( "setUp##" + CCCV2ShowCase.class.getSimpleName() );
    driver = CToolsTestSuite.getDriver();
    wait = CToolsTestSuite.getWait();
    baseUrl = CToolsTestSuite.getBaseUrl();
  }

  @Before
  public void setUpTestCase() {
    // Go to AddinReference
    driver.get( baseUrl + "api/repos/%3Apublic%3Aplugin-samples%3Apentaho-cdf-dd%3Apentaho-cdf-dd-require%3Atests%3AtestCCCv2-II.wcdf/generatedContent" );

    // NOTE - we have to wait for loading disappear
    ElementHelper.WaitForElementInvisibility( driver, By.xpath( "//div[@class='blockUI blockOverlay']" ) );
  }

  /**
   * ############################### Test Case 1 ###############################
   *
   * Test Case Name:
   *    BarChart
   * Description:
   *    The test case pretends validate the bar chart information.
   * Steps:
   *    1. Check Bar Chart
   */
  @Test( timeout = 60000 )
  public void tc01_ChartContent_DisplayedCorrect() {
    log.info( "tc01_ChartContent_DisplayedCorrect" );

    /*
     * ## Step 0
     */
    // Check page title
    wait.until( ExpectedConditions.titleIs( "Community Dashboard Editor" ) );
    assertEquals( "Community Dashboard Editor", driver.getTitle() );
    // Check title
    String title = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='Headers']/div" ) );
    assertEquals( "CCC v2 - Show Case", title );
    // Wait for the charts load
    // search for //width of barchartrect1
    // search for //width of barchartrect2
    // search for //width of barchartrect3
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][1][@width>192]" ) );
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][2][@width>249]" ) );
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][3][@width>100]" ) );

    /*
     * ## Step 1 - Bar Chart
     */
    String barChartTitle = ElementHelper.WaitForElementPresentGetText( driver, By.id( "BarChartTitleRow" ) );
    assertEquals( "Bar Chart", barChartTitle );
    // Check bars
    ElementHelper.FindElement( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][2]" ) );
    WebElement barChartRect1 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][1]" ) );
    WebElement barChartRect2 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][2]" ) );
    WebElement barChartRect3 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][3]" ) );
    assertNotNull( barChartRect1 );
    assertNotNull( barChartRect2 );
    assertNotNull( barChartRect3 );
    Double barChartRect1Width = Double.parseDouble( barChartRect1.getAttribute( "width" ) );
    Double barChartRect2Width = Double.parseDouble( barChartRect2.getAttribute( "width" ) );
    Double barChartRect3Width = Double.parseDouble( barChartRect3.getAttribute( "width" ) );
    assertThat( "Current width: " + barChartRect1Width, barChartRect1Width, greaterThan( Double.valueOf( 192 ) ) );
    assertThat( "Current width: " + barChartRect2Width, barChartRect2Width, greaterThan( Double.valueOf( 250 ) ) );
    assertThat( "Current width: " + barChartRect3Width, barChartRect3Width, greaterThan( Double.valueOf( 100 ) ) );
    // Check bars value
    String barChartRectValue1 = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='text'][1]" ) );
    String barChartRectValue2 = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='text'][2]" ) );
    String barChartRectValue3 = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='text'][3]" ) );
    assertEquals( "3.68M", barChartRectValue1 );
    assertEquals( "4.99M", barChartRectValue2 );
    assertEquals( "1.98M", barChartRectValue3 );

    // Mouse hover elements
    Actions acts = new Actions( driver );
    acts.moveToElement( barChartRect2 );
    acts.perform();
    String seriesLabel = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[1]/td[1]/span" ) );
    acts.perform();
    String seriesValue = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[1]/td[3]/span" ) );
    acts.perform();
    String timeLabel = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[2]/td[1]/span" ) );
    acts.perform();
    String timeValue = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[2]/td[3]/span" ) );
    acts.perform();
    String valueLabel = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[3]/td[1]/span" ) );
    acts.perform();
    String valueValue = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[3]/td[3]/span" ) );
    assertEquals( "Series", seriesLabel );
    assertEquals( "Sales", seriesValue );
    assertEquals( "Time", timeLabel );
    assertEquals( "2004", timeValue );
    assertEquals( "Value", valueLabel );
    assertEquals( "4,987,739.84", valueValue );
  }

  /**
   * ############################### Test Case 2 ###############################
   *
   * Test Case Name:
   *    LineChart
   * Description:
   *    The test case pretends validate the line chart information.
   * Steps:
   *    1. Check Line Chart
   */
  @Test( timeout = 90000 )
  public void tc02_ChartContent_DisplayedCorrect() {
    log.info( "tc02_ChartContent_DisplayedCorrect" );

    /*
     * ## Step 0
     */
    // Check page title
    wait.until( ExpectedConditions.titleIs( "Community Dashboard Editor" ) );
    assertEquals( "Community Dashboard Editor", driver.getTitle() );
    // Check title
    String title = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='Headers']/div" ) );
    assertEquals( "CCC v2 - Show Case", title );
    // Wait for the charts load
    // search for width of barchartrect1
    // search for width of barchartrect2
    // search for width of barchartrect3
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][1][@width>192]" ) );
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][2][@width>249]" ) );
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][3][@width>100]" ) );
    // search for cy of line1
    // search for cy of line7
    // search for cy of line11
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='LineChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='circle'][1][@cy>120]" ) );
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='LineChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='circle'][7][@cy>136]" ) );
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='LineChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='circle'][11][@cy>24]" ) );

    /*
     * ## Step 2 - Line Chart
     */
    String lineChartTitle = ElementHelper.WaitForElementPresentGetText( driver, By.id( "LineChartTitleRow" ) );
    assertEquals( "Line Chart", lineChartTitle );
    // Check lines
    WebElement lineChartCircle1 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='LineChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='circle'][1]" ) );
    WebElement lineChartCircle2 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='LineChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='circle'][7]" ) );
    WebElement lineChartCircle3 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='LineChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='circle'][11]" ) );
    Double lineChartCircle1Cy = Double.parseDouble( lineChartCircle1.getAttribute( "cy" ) );
    Double lineChartCircle2Cy = Double.parseDouble( lineChartCircle2.getAttribute( "cy" ) );
    Double lineChartCircle3Cy = Double.parseDouble( lineChartCircle3.getAttribute( "cy" ) );
    assertThat( "Current cy: " + lineChartCircle1Cy, lineChartCircle1Cy, greaterThan( Double.valueOf( 120 ) ) );
    assertThat( "Current cy: " + lineChartCircle2Cy, lineChartCircle2Cy, greaterThan( Double.valueOf( 136 ) ) );
    assertThat( "Current cy: " + lineChartCircle3Cy, lineChartCircle3Cy, greaterThan( Double.valueOf( 24 ) ) );

    // Mouse hover elements
    WebElement lineChartCircle4 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='LineChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='circle'][2]" ) );
    Actions acts2 = new Actions( driver );
    acts2.moveToElement( lineChartCircle4 );
    acts2.perform();
    String measuresLabel = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[1]/td[1]/span" ) );
    acts2.perform();
    String measuresValue = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[1]/td[3]/span" ) );
    acts2.perform();
    String categoryLabel = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[2]/td[1]/span" ) );
    acts2.perform();
    String categoryValue = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[2]/td[3]/span" ) );
    acts2.perform();
    String valueLineValue = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='tipsy tipsy-s']/div[2]/div/table/tbody/tr[4]/td[3]/span" ) );
    assertEquals( "Measures", measuresLabel );
    assertEquals( "Quantity", measuresValue );
    assertEquals( "Category", categoryLabel );
    assertEquals( "Feb", categoryValue );
    assertEquals( "7,959", valueLineValue );
  }

  /**
   * ############################### Test Case 3 ###############################
   *
   * Test Case Name:
   *    LineChart
   * Description:
   *    The test case pretends validate the pie chart information.
   * Steps:
   *    1. Check Pie Chart
   */
  @Test( timeout = 90000 )
  public void tc03_ChartContent_DisplayedCorrect() {
    log.info( "tc03_ChartContent_DisplayedCorrect" );

    /*
     * ## Step 0
     */
    // Check page title
    wait.until( ExpectedConditions.titleIs( "Community Dashboard Editor" ) );
    assertEquals( "Community Dashboard Editor", driver.getTitle() );
    // Check title
    String title = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='Headers']/div" ) );
    assertEquals( "CCC v2 - Show Case", title );
    // Wait for the charts load
    // search for width of barchartrect1
    // search for width of barchartrect2
    // search for width of barchartrect3
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][1][@width>192]" ) );
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][2][@width>249]" ) );
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='BarChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='rect'][3][@width>100]" ) );
    // search for cy of line1
    // search for cy of line7
    // search for cy of line11
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='LineChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='circle'][1][@cy>120]" ) );
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='LineChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='circle'][7][@cy>136]" ) );
    ElementHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='LineChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='circle'][11][@cy>24]" ) );

    /*
     * ## Step 3 - Pie Chart
     */
    String pieChartTitle = ElementHelper.WaitForElementPresentGetText( driver, By.id( "PieChartTitleRow" ) );
    assertEquals( "Pie Chart", pieChartTitle );
    // Check pies
    WebElement pieChartCircle1 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='PieChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='path'][1]" ) );
    WebElement pieChartCircle2 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='PieChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='path'][2]" ) );
    WebElement pieChartCircle3 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='PieChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='path'][3]" ) );
    assertNotNull( pieChartCircle1 );
    assertNotNull( pieChartCircle2 );
    assertNotNull( pieChartCircle3 );
    // Interact with pie chart disabling two series
    // Series 2003
    WebElement serie2003 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='PieChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][1]/*[local-name()='g'][2]/*[local-name()='text']" ) );
    serie2003.click();
    ElementHelper.WaitForElementInvisibility( driver, By.xpath( "//div[@id='PieChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='path'][3]" ) );
    // Series 2004
    WebElement serie2004 = ElementHelper.FindElement( driver, By.xpath( "//div[@id='PieChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][2]/*[local-name()='text']" ) );
    serie2004.click();
    ElementHelper.WaitForElementInvisibility( driver, By.xpath( "//div[@id='PieChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g']/*[local-name()='path'][2]" ) );
    // Check value of the serie2005
    String serie2005Value = ElementHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='PieChartBodyRow']/div/div/*[local-name()='svg' and namespace-uri()='http://www.w3.org/2000/svg']/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='g'][2]/*[local-name()='text']" ) );
    assertEquals( "1.98M", serie2005Value );
  }

  @AfterClass
  public static void tearDownClass() {
    log.info( "tearDown##" + CCCV2ShowCase.class.getSimpleName() );
  }
}
