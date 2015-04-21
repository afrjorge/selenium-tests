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

import static org.junit.Assert.assertEquals;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.pentaho.ctools.suite.CToolsTestSuite;
import org.pentaho.ctools.utils.ElementHelper;
import org.pentaho.ctools.utils.ScreenshotTestRule;

/**
 * Testing the functionalities related with Ajax Request Reference.
 *
 * Naming convention for test:
 *  'tcN_StateUnderTest_ExpectedBehavior'
 *
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class AjaxRequestReference {

  // Instance of the driver (browser emulator)
  private static WebDriver DRIVER;
  //Instance to be used on wait commands
  private static Wait<WebDriver> WAIT;
  // The base url to be append the relative url in test
  private static String BASE_URL;
  //Log instance
  private static Logger LOG = LogManager.getLogger( AjaxRequestReference.class );

  @Rule
  public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule( DRIVER );

  @BeforeClass
  public static void setUpClass() {
    LOG.info( "setUp##" + AjaxRequestReference.class.getSimpleName() );
    DRIVER = CToolsTestSuite.getDriver();
    WAIT = CToolsTestSuite.getWait();
    BASE_URL = CToolsTestSuite.getBaseUrl();
  }

  @Before
  public void setUpTestCase() {
    //Go to AddinReference
    DRIVER.get( BASE_URL + "api/repos/%3Apublic%3Aplugin-samples%3Apentaho-cdf-dd%3Apentaho-cdf-dd-require%3Atests%3AAjaxRequest%3AajaxRequest.wcdf/generatedContent" );

    //NOTE - we have to wait for loading disappear
    ElementHelper.WaitForElementInvisibility( DRIVER, By.xpath( "//div[@class='blockUI blockOverlay']" ) );
  }

  /**
   * ############################### Test Case 1 ###############################
   *
   * Test Case Name:
   *    PageContent
   * Description:
   *    The test case pretends validate the contents presented in the sample.
   * Steps:
   *    1. Check page content.
   */
  @Test( timeout = 60000 )
  public void tc01_PageContent_InformationPresent() {
    LOG.info( "tc01_PageContent_InformationPresent" );

    /*
     * ## Step 1
     */
    //Check page title
    WAIT.until( ExpectedConditions.titleIs( "Community Dashboard Editor" ) );
    assertEquals( "Community Dashboard Editor", DRIVER.getTitle() );
    //Check title
    String title = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//div[@id='Title']/span" ) );
    assertEquals( "Ajax Request Reference", title );
    //Check first paragh
    String expParag = "Ajax Request Component provides a way to build an Ajax request. Given an url, a response type and list of parameters it's possible to build an jQuery ajax call, which its result will be stored in the resultvar.";
    String actParag = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//div[@id='DescriptionBody']/p" ) );
    assertEquals( expParag, actParag );
    //Check subtitle
    String subtitle = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//div[@id='DescriptionBody']/div" ) );
    assertEquals( "Component Parameters", subtitle );
    //Check parag 1
    String parag1 = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//div[@id='DescriptionBody']/p[2]" ) );
    assertEquals( "The Component definition supports the following arguments:", parag1 );
    //Check subtitle2
    String subtitle2 = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//div[@id='DescriptionBody']/div[2]" ) );
    assertEquals( "Default values", subtitle2 );
    //Check quote
    String quote = ElementHelper.WaitForElementPresentGetText( DRIVER, By.xpath( "//div[@id='DescriptionBody']/blockquote/pre" ) );
    assertEquals( "defaults: { ajaxRequestType: json, asyncCall: true }", quote );
    //Check result
    String result = ElementHelper.WaitForElementPresentGetText( DRIVER, By.id( "column1" ) );
    assertEquals( "{\"queryInfo\":{\"totalRows\":\"19\"},\"resultset\":[[\"Car\",\"Red\",10],[\"Car\",\"Blue\",20],[\"Car\",\"Green\",30],[\"Car\",\"Yellow\",5],[\"Car\",\"Black\",25],[\"Car\",\"White\",7],[\"Bike\",\"Red\",20],[\"Bike\",\"Blue\",20],[\"Bike\",\"Green\",40],[\"Bike\",\"Yellow\",80],[\"Bike\",\"Black\",1],[\"Bike\",\"White\",23],[\"Ship\",\"Red\",2],[\"Ship\",\"Blue\",7],[\"Plane\",\"Red\",5],[\"Plane\",\"Blue\",4],[\"Train\",\"Red\",50],[\"Train\",\"Blue\",50],[\"Train\",\"Green\",7]],\"metadata\":[{\"colIndex\":0,\"colType\":\"String\",\"colName\":\"series\"},{\"colIndex\":1,\"colType\":\"String\",\"colName\":\"category\"},{\"colIndex\":2,\"colType\":\"Integer\",\"colName\":\"value\"}]}", result );
  }

  @AfterClass
  public static void tearDownClass() {
    LOG.info( "tearDown##" + AjaxRequestReference.class.getSimpleName() );
  }
}
