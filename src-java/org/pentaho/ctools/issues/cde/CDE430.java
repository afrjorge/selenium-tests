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
package org.pentaho.ctools.issues.cde;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

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
import org.pentaho.ctools.suite.CToolsTestSuite;
import org.pentaho.ctools.utils.ElementHelper;
import org.pentaho.ctools.utils.ScreenshotTestRule;

/**
 * The script is testing the issue:
 *  - http://jira.pentaho.com/browse/CDE-430
 *  
 * and the automation test is described:
 *  - http://jira.pentaho.com/browse/QUALITY-1085
 *  
 * NOTE To test this script it is required to have CDE plugin installed.
 *
 * Naming convention for test: 'tcN_StateUnderTest_ExpectedBehavior'
 *
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class CDE430 {

  // Instance of the driver (browser emulator)
  private static WebDriver  DRIVER;
  // The base url to be append the relative url in test
  private static String     BASE_URL;
  // Log instance
  private static Logger     LOG                = LogManager.getLogger( CDE430.class );
  // Getting screenshot when test fails
  @Rule
  public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule( DRIVER );

  @BeforeClass
  public static void setUpClass() {
    LOG.info( "setUp##" + CDE430.class.getSimpleName() );
    DRIVER = CToolsTestSuite.getDriver();
    BASE_URL = CToolsTestSuite.getBaseUrl();
  }

  /**
   * ############################### Test Case 1 ###############################
   *
   * Test Case Name: 
   *   Assert Popup Export Component properties show list with type options
   *
   * Description: 
   *   The test pretends validate the CDE-430 issue, so when user clicks the down arron when Chart Exprt or Data Export Types are
   *   selected, a drop down list is shown with valid type options
   *   
   * Steps:
   *  1. Open new CDE dashboard and go to Components Panel
   *  2. Add Export Popup Component and go to Advanced Properties
   *  3. Assert list is shown for Chart Export Type
   *  4. Assert list is shown for Data Export Type
   *  
   *
   */
  @Test( timeout = 360000 )
  public void tc01_PopupExportComponent_TypeListShown() {
    LOG.info( "tc01_PopupExportComponent_TypeListShown" );

    /* 
     * ## Step 1 
     */
    //New CDE dashboard
    DRIVER.get( BASE_URL + "api/repos/wcdf/new" );
    ElementHelper.WaitForElementInvisibility( DRIVER, By.xpath( "//div[@class='blockUI blockOverlay']" ) );

    //Go to components Panel
    WebElement componentsButton = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//div[@class='componentsPanelButton']" ) );
    assertNotNull( componentsButton );
    componentsButton.click();
    WebElement componentsPanel = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.id( "panel-componentens_panel" ) );
    assertNotNull( componentsPanel );

    /* 
     * ## Step 2 
     */
    //Add Export Popup Component
    WebElement expandOthers = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//div[@id='cdfdd-components-pallete']/div[@id='cdfdd-components-palletePallete']/div[2]/h3/a" ) );
    assertNotNull( expandOthers );
    expandOthers.click();
    WebElement exportPopup = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//div[@id='cdfdd-components-palletePallete']//a[@title='Export Popup Component']" ) );
    assertNotNull( exportPopup );
    exportPopup.click();

    //Click Advanced Properties
    WebElement advancedProperties = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//div[@id='cdfdd-components-properties']/div/div/div[@class='advancedProperties propertiesUnSelected']" ) );
    assertNotNull( advancedProperties );
    advancedProperties.click();
    ElementHelper.WaitForAttributeValueEqualsTo( DRIVER, By.xpath( "//div[@id='cdfdd-components-properties']/div/div/div[3]" ), "class", "advancedProperties propertiesSelected" );

    /* 
     * ## Step 3 
     */
    //Assert list appears for Chart Types to Export
    WebElement chartType = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//td[@title='Type for Chart Image to Export']/../td[2]" ) );
    assertNotNull( chartType );
    chartType.click();
    WebElement inputChart = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//td[@title='Type for Chart Image to Export']/../td[2]/form/input" ) );
    assertNotNull( inputChart );
    inputChart.clear();
    //chartType.click();
    Robot robot;
    try {
      robot = new Robot();
      robot.keyPress( KeyEvent.VK_DOWN );
      robot.keyRelease( KeyEvent.VK_DOWN );
    }
    catch (AWTException e) {
      e.printStackTrace();
    }
    WebElement listOption1 = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//body/ul/li/a" ) );
    assertNotNull( listOption1 );
    String textOption1 = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//body/ul/li/a" ) );
    assertEquals( "png", textOption1 );
    WebElement listOption2 = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//body/ul/li[2]/a" ) );
    assertNotNull( listOption2 );
    String textOption2 = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//body/ul/li[2]/a" ) );
    assertEquals( "svg", textOption2 );

    /* 
     * ## Step 4 
     */
    //Assert list appears for Data Types to Export
    WebElement dataType = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//td[@title='Type for Data File to Export']/../td[2]" ) );
    assertNotNull( dataType );
    dataType.click();
    WebElement inputData = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//td[@title='Type for Data File to Export']/../td[2]/form/input" ) );
    assertNotNull( inputData );
    inputData.clear();
    //dataType.click();
    try {
      robot = new Robot();
      robot.keyPress( KeyEvent.VK_DOWN );
      robot.keyRelease( KeyEvent.VK_DOWN );
    }
    catch (AWTException e) {
      e.printStackTrace();
    }
    listOption1 = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//body/ul/li/a" ) );
    assertNotNull( listOption1 );
    textOption1 = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//body/ul/li/a" ) );
    assertEquals( "xls", textOption1 );
    listOption2 = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//body/ul/li[2]/a" ) );
    assertNotNull( listOption2 );
    textOption2 = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//body/ul/li[2]/a" ) );
    assertEquals( "csv", textOption2 );
    WebElement listOption3 = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//body/ul/li[3]/a" ) );
    assertNotNull( listOption3 );
    String textOption3 = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//body/ul/li[3]/a" ) );
    assertEquals( "xml", textOption3 );
    WebElement listOption4 = ElementHelper.WaitForElementPresenceAndVisible( DRIVER, By.xpath( "//body/ul/li[4]/a" ) );
    assertNotNull( listOption4 );
    String textOption4 = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//body/ul/li[4]/a" ) );
    assertEquals( "json", textOption4 );
  }

  @AfterClass
  public static void tearDownClass() {
    LOG.info( "tearDown##" + CDE430.class.getSimpleName() );
  }
}
