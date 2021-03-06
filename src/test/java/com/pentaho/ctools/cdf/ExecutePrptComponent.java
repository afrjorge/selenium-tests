/*
 * !*****************************************************************************
 * Selenium Tests For CTools Copyright (C) 2002-2015 by Pentaho :
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
package com.pentaho.ctools.cdf;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pentaho.ctools.utils.DirectoryWatcher;
import com.pentaho.ctools.utils.ElementHelper;
import com.pentaho.ctools.utils.PageUrl;
import com.pentaho.selenium.BaseTest;
import com.pentaho.selenium.ConfigurationSettings;

/**
 * Testing the functionalities related with Prpt Component.
 *
 * Naming convention for test:
 *  'tcN_StateUnderTest_ExpectedBehavior'
 *
 */
public class ExecutePrptComponent extends BaseTest {
  // Access to wrapper for webdriver
  private final ElementHelper elemHelper = new ElementHelper();
  //Log instance
  private final Logger log = LogManager.getLogger( ExecutePrptComponent.class );

  /**
   * ############################### Test Case 0 ###############################
   *
   * Test Case Name:
   *    Open Sample Page
   */
  @Test
  public void tc0_OpenSamplePage_Display() {
    this.log.info( "tc0_OpenSamplePage_Display" );
    // The URL for the ExecutePrptComponent under CDF samples
    // This sample is in: Public/plugin-samples/CDF/Require Samples/Documentation/Component Reference/Core Components/ExecutePrptComponent
    driver.get( PageUrl.EXECUTE_PRPT_COMPONENT );

    // NOTE - we have to wait for loading disappear
    this.elemHelper.WaitForElementInvisibility( driver, By.cssSelector( "div.blockUI.blockOverlay" ) );
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
  @Test
  public void tc1_PageContent_DisplayTitle() {
    this.log.info( "tc1_PageContent_DisplayTitle" );
    // Wait for title become visible and with value 'Community Dashboard Framework'
    wait.until( ExpectedConditions.titleContains( "Community Dashboard Framework" ) );
    // Wait for visibility of 'VisualizationAPIComponent'
    wait.until( ExpectedConditions.visibilityOfElementLocated( By.xpath( "//div[@id='dashboardContent']/div/div/div/h2/span[2]" ) ) );

    // Validate the sample that we are testing is the one
    assertEquals( "Community Dashboard Framework", driver.getTitle() );
    assertEquals( "ExecutePrptComponent", this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='dashboardContent']/div/div/div/h2/span[2]" ) ) );
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
  @Test
  public void tc2_ReloadSample_SampleReadyToUse() {
    this.log.info( "tc2_ReloadSample_SampleReadyToUse" );
    // ## Step 1
    // Render again the sample
    this.elemHelper.ClickJS( driver, By.xpath( "//div[@id='example']/ul/li[2]/a" ) );
    this.elemHelper.ClickJS( driver, By.xpath( "//div[@id='code']/button" ) );

    // NOTE - we have to wait for loading disappear
    this.elemHelper.WaitForElementInvisibility( driver, By.cssSelector( "div.blockUI.blockOverlay" ) );

    // Now sample element must be displayed
    assertTrue( this.elemHelper.FindElement( driver, By.id( "sample" ) ).isDisplayed() );

    //Check the number of divs with id 'SampleObject'
    //Hence, we guarantee when click Try Me the previous div is replaced
    int nSampleObject = driver.findElements( By.id( "sampleObject" ) ).size();
    assertEquals( 1, nSampleObject );

    WebElement elemButton = this.elemHelper.FindElement( driver, By.cssSelector( "button span" ) );
    assertNotNull( elemButton );
  }

  /**
   * ############################### Test Case 3 ###############################
   *
   * Test Case Name:
   *    Select HTML and PDF (content type)
   * Description:
   *    The test case pretends validate the result when change for option HTML
   *    and PDF for content-type.
   * Steps:
   *    1. Check the contents presented
   */
  @Test
  public void tc3_CheckDisplayPage_DataIsDisplayedAsExpected() {
    this.log.info( "tc3_CheckDisplayPage_DataIsDisplayedAsExpected" );

    // ## Step 1
    String buttonText = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//button/span" ) );
    assertEquals( "Execute Prpt", buttonText );
    this.elemHelper.FindElement( driver, By.xpath( "//button/span" ) ).click();
    wait.until( ExpectedConditions.presenceOfElementLocated( By.id( "fancybox-content" ) ) );
    //Move to iframe
    WebElement elemIFrame = this.elemHelper.FindElement( driver, By.xpath( "//iframe" ) );
    String attrId = elemIFrame.getAttribute( "id" );
    driver.switchTo().frame( attrId );
    //Wait for glasspane display and disable
    this.elemHelper.WaitForElementPresence( driver, By.id( "glasspane" ), 5 );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    //Check presence of tool bar elements
    assertNotNull( this.elemHelper.FindElement( driver, By.xpath( "//div[@id='toolbar']/div" ) ) );
    assertNotNull( this.elemHelper.FindElement( driver, By.xpath( "//div[@id='toolbar']/div[2]" ) ) );
    assertNotNull( this.elemHelper.FindElement( driver, By.xpath( "//div[@id='toolbar']/span" ) ) );
    //Check the Product Name and Output Type
    WebElement elemtLine = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.cssSelector( "div.parameter-label" ), 45 );
    assertNotNull( elemtLine );
    String prodName = this.elemHelper.WaitForElementPresentGetText( driver, By.cssSelector( "div.parameter-label" ) );
    assertEquals( "Line", prodName );
    assertNotNull( this.elemHelper.FindElement( driver, By.xpath( "//td/div/div[2]/select" ) ) );
    String outputTypeName = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@class='parameter']/div[2]/select/../../div" ) );
    assertEquals( "Output Type", outputTypeName );
    assertNotNull( this.elemHelper.FindElement( driver, By.xpath( "//div[@class='parameter']/div[2]/select" ) ) );
    //Check for View Report button
    if ( pentahoReleaseVersion.equalsIgnoreCase( ConfigurationSettings.PENTAHO_RELEASE_VERSION_6X ) ) {
      String buttonName = this.elemHelper.WaitForElementPresentGetText( driver, By.cssSelector( "button.pentaho-button" ) );
      assertEquals( "View Report", buttonName );
    } else {
      String buttonName = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//button/span" ) );
      assertEquals( "View Report", buttonName );
    }
    //Check the generated image
    this.elemHelper.WaitForElementPresence( driver, By.cssSelector( "iframe#reportContent" ) );
    WebDriver driverReportContent = driver.switchTo().frame( "reportContent" );
    WebElement elemReport = this.elemHelper.WaitForElementPresenceAndVisible( driverReportContent, By.xpath( "//tbody/tr" ) );
    assertNotNull( elemReport );
    this.elemHelper.WaitForTextPresence( driverReportContent, By.xpath( "//tbody/tr" ), "LINE: Classic Cars" );
    String strTitle = this.elemHelper.WaitForElementPresentGetText( driverReportContent, By.xpath( "//tbody/tr" ) );
    assertEquals( "LINE: Classic Cars", strTitle );
    this.elemHelper.WaitForTextPresence( driverReportContent, By.xpath( "//tbody/tr[3]/td" ), "Autoart Studio Design" );
    String strCompany = this.elemHelper.WaitForElementPresentGetText( driverReportContent, By.xpath( "//tbody/tr[3]/td" ) );
    assertEquals( "Autoart Studio Design", strCompany );
    this.elemHelper.WaitForTextPresence( driverReportContent, By.xpath( "//tbody/tr[5]/td[3]/a" ), "1958 Chevy Corvette Limited Edition" );
    String strValue = this.elemHelper.WaitForElementPresentGetText( driverReportContent, By.xpath( "//tbody/tr[5]/td[3]/a" ) );
    assertEquals( "1958 Chevy Corvette Limited Edition", strValue );
    String strHref = this.elemHelper.GetAttribute( driverReportContent, By.xpath( "//tbody/tr[5]/td[3]/a" ), "href" );
    assertEquals( "http://images.google.com/images?q=1958%20Chevy%20Corvette%20Limited%20Edition", strHref );
  }

  /**
   * ############################### Test Case 4 ###############################
   *
   * Test Case Name:
   *    Toggle Prompt Panel
   * Description:
   *    The test case pretends disable and enable the prompt panel.
   * Steps:
   *    1. Enable prompt panel
   *    2. Disable prompt panel
   */
  @Test
  public void tc4_TogglePromptPanel_PromptPanelEnableDisable() {
    this.log.info( "tc4_TogglePromptPanel_PromptPanelEnableDisable" );
    driver.switchTo().defaultContent();

    WebElement elemIFrame = this.elemHelper.FindElement( driver, By.xpath( "//iframe" ) );
    String attrId = elemIFrame.getAttribute( "id" );
    driver.switchTo().frame( attrId );

    // ## Step 1
    assertNotNull( this.elemHelper.FindElement( driver, By.id( "reportControlPanel" ) ) );
    this.elemHelper.Click( driver, By.xpath( "//span[@id='toolbar-parameterToggle']/span" ) );
    assertTrue( this.elemHelper.WaitForElementInvisibility( driver, By.id( "reportControlPanel" ) ) );

    // ## Step 2
    this.elemHelper.Click( driver, By.xpath( "//span[@id='toolbar-parameterToggle']/span" ) );
    assertNotNull( this.elemHelper.WaitForElementPresenceAndVisible( driver, By.id( "reportControlPanel" ) ) );
  }

  /**
   * ############################### Test Case 5 ###############################
   *
   * Test Case Name:
   *    Select Several Products
   * Description:
   *    The test case pretends to validate that when we select a product a new
   *    report is generated.
   * Steps:
   *    1. Disable Classic Cars and assert results
   *    2. Enable Motorcycles and assert results
   */
  @Test
  public void tc5_SelectSeveralProducts_ReportIsRefreshed() {
    this.log.info( "tc5_SelectSeveralProducts_ReportIsRefreshed" );
    driver.switchTo().defaultContent();
    WebElement elemIFrame = this.elemHelper.FindElement( driver, By.xpath( "//iframe" ) );
    String attrIframeId = elemIFrame.getAttribute( "id" );
    driver.switchTo().frame( attrIframeId );

    /*
     * ## Step 1
     */
    WebElement element = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@class='pentaho-toggle-button-container']/div/div/button" ) );
    assertNotNull( element );
    String text = element.getText();
    assertEquals( "Classic Cars", text );
    element.click();
    this.elemHelper.WaitForElementPresence( driver, By.id( "glasspane" ), 5 );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    driver.switchTo().frame( "reportContent" );
    element = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//tbody/tr" ) );
    assertNotNull( element );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr" ), "LINE: Planes" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr" ) );
    assertEquals( "LINE: Planes", text );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr[3]/td" ), "Autoart Studio Design" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr[3]/td" ) );
    assertEquals( "Autoart Studio Design", text );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ), "P-51-D Mustang" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ) );
    assertEquals( "P-51-D Mustang", text );
    text = this.elemHelper.GetAttribute( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ), "href" );
    assertEquals( "http://images.google.com/images?q=P-51-D%20Mustang", text );

    /*
     *  ## Step 2
     */
    driver.switchTo().defaultContent();
    elemIFrame = this.elemHelper.FindElement( driver, By.xpath( "//iframe" ) );
    attrIframeId = elemIFrame.getAttribute( "id" );
    driver.switchTo().frame( attrIframeId );
    element = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@class='pentaho-toggle-button-container']/div/div[2]/button" ) );
    assertNotNull( element );
    text = element.getText();
    assertEquals( "Motorcycles", text );
    element.click();
    this.elemHelper.WaitForElementPresence( driver, By.id( "glasspane" ), 5 );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    driver.switchTo().frame( "reportContent" );
    element = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//tbody/tr" ) );
    assertNotNull( element );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr" ), "LINE: Motorcycles" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr" ) );
    assertEquals( "LINE: Motorcycles", text );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr[3]/td" ), "Autoart Studio Design" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr[3]/td" ) );
    assertEquals( "Autoart Studio Design", text );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ), "1997 BMW F650 ST" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ) );
    assertEquals( "1997 BMW F650 ST", text );
    text = this.elemHelper.GetAttribute( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ), "href" );
    assertEquals( "http://images.google.com/images?q=1997%20BMW%20F650%20ST", text );
  }

  /**
   * ############################### Test Case 6 ###############################
   *
   * Test Case Name:
   *    Output Type
   * Description:
   *    We pretend to test all Output Type options.
   * Steps:
   *    1. Select: HTML (Paginated)
   *    2. Select: HTML (Single Page)
   *    3. Select: PDF
   *    4. Select: Excel
   *    5. Select: Excel 2007
   *    6. Select: Comma Separated Value
   *    7. Select: Rich-Text-Format
   *    8. Select: Text
   */
  @Test
  public void tc6_SelectAllOutputTypeOptions_DialogBoxIsRaised() {
    this.log.info( "tc6_SelectAllOutputTypeOptions_DialogBoxIsRaised" );

    DirectoryWatcher dw = new DirectoryWatcher();

    /*
     *  ## Step 1
     */
    driver.switchTo().defaultContent();
    String attrIframeId = this.elemHelper.GetAttribute( driver, By.xpath( "//iframe" ), "id" );
    driver.switchTo().frame( attrIframeId );
    Select select = new Select( this.elemHelper.FindElement( driver, By.xpath( "//div[@class='parameter']/div[2]/select" ) ) );
    select.selectByValue( "table/html;page-mode=page" );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    //Check the generated image
    this.elemHelper.WaitForElementPresence( driver, By.cssSelector( "iframe#reportContent" ) );
    driver.switchTo().frame( "reportContent" );
    WebElement element = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//tbody/tr" ) );
    assertNotNull( element );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr" ), "LINE: Motorcycles" );
    String text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr" ) );
    assertEquals( "LINE: Motorcycles", text );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr[3]/td" ), "Autoart Studio Design" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr[3]/td" ) );
    assertEquals( "Autoart Studio Design", text );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ), "1997 BMW F650 ST" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ) );
    assertEquals( "1997 BMW F650 ST", text );
    text = this.elemHelper.GetAttribute( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ), "href" );
    assertEquals( "http://images.google.com/images?q=1997%20BMW%20F650%20ST", text );

    /*
     *  ## Step 2
     */
    driver.switchTo().defaultContent();
    attrIframeId = this.elemHelper.GetAttribute( driver, By.xpath( "//iframe" ), "id" );
    driver.switchTo().frame( attrIframeId );
    select = new Select( this.elemHelper.FindElement( driver, By.xpath( "//div[@class='parameter']/div[2]/select" ) ) );
    select.selectByValue( "table/html;page-mode=stream" );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    //Check the generated image
    this.elemHelper.WaitForElementPresence( driver, By.cssSelector( "iframe#reportContent" ) );
    driver.switchTo().frame( "reportContent" );
    element = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//tbody/tr" ) );
    assertNotNull( element );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr" ), "LINE: Motorcycles" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr" ) );
    assertEquals( "LINE: Motorcycles", text );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr[3]/td" ), "Autoart Studio Design" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr[3]/td" ) );
    assertEquals( "Autoart Studio Design", text );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ), "1997 BMW F650 ST" );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ) );
    assertEquals( "1997 BMW F650 ST", text );
    text = this.elemHelper.GetAttribute( driver, By.xpath( "//tbody/tr[5]/td[3]/a" ), "href" );
    assertEquals( "http://images.google.com/images?q=1997%20BMW%20F650%20ST", text );

    /*
     *  ## Step 3
     */
    driver.switchTo().defaultContent();
    attrIframeId = this.elemHelper.GetAttribute( driver, By.xpath( "//iframe" ), "id" );
    driver.switchTo().frame( attrIframeId );
    select = new Select( this.elemHelper.FindElement( driver, By.xpath( "//div[@class='parameter']/div[2]/select" ) ) );
    select.selectByValue( "pageable/pdf" );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    //Check the generated image
    this.elemHelper.WaitForElementPresence( driver, By.cssSelector( "iframe#reportContent" ) );
    driver.switchTo().frame( "reportContent" );
    WebElement elemTextLayer = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='pageContainer1']/div[@class='textLayer']" ) );
    assertNotNull( elemTextLayer );
    WebElement elemTextLayerdiv1 = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='pageContainer1']/div[@class='textLayer']/div" ) );
    assertNotNull( elemTextLayerdiv1 );
    WebElement elemTextLayerdiv2 = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='pageContainer1']/div[@class='textLayer']/div[2]" ) );
    assertNotNull( elemTextLayerdiv2 );
    WebElement elemTextLayerdiv3 = this.elemHelper.WaitForElementPresenceAndVisible( driver, By.xpath( "//div[@id='pageContainer1']/div[@class='textLayer']/div[3]" ) );
    assertNotNull( elemTextLayerdiv3 );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//div[@id='pageContainer1']/div[@class='textLayer']/div" ), "L I N E :", 60 );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='pageContainer1']/div[@class='textLayer']/div" ) );
    assertEquals( "L I N E :", text );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//div[@id='pageContainer1']/div[@class='textLayer']/div[2]" ), "M o t o r c y c l e s", 60 );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='pageContainer1']/div[@class='textLayer']/div[2]" ) );
    assertEquals( "M o t o r c y c l e s", text );
    this.elemHelper.WaitForTextPresence( driver, By.xpath( "//div[@id='pageContainer1']/div[@class='textLayer']/div[3]" ), "MSRP", 60 );
    text = this.elemHelper.WaitForElementPresentGetText( driver, By.xpath( "//div[@id='pageContainer1']/div[@class='textLayer']/div[3]" ) );
    assertEquals( "MSRP", text );

    /*
     *  ## Step 4
     */
    driver.switchTo().defaultContent();
    attrIframeId = this.elemHelper.GetAttribute( driver, By.xpath( "//iframe" ), "id" );
    driver.switchTo().frame( attrIframeId );
    new File( downloadDir + "\\InventorybyLine.xls" ).delete();
    select = new Select( this.elemHelper.FindElement( driver, By.xpath( "//div[@class='parameter']/div[2]/select" ) ) );
    select.selectByValue( "table/excel;page-mode=flow" );
    //Wait for file to be created in the destination dir
    dw.WatchForCreate( downloadDir );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    assertTrue( new File( downloadDir + "\\InventorybyLine.xls" ).exists() );
    new File( downloadDir + "\\InventorybyLine.xls" ).delete();

    /*
     *  ## Step 5
     */
    new File( downloadDir + "\\InventorybyLine.xlsx" ).delete();
    select = new Select( this.elemHelper.FindElement( driver, By.xpath( "//div[@class='parameter']/div[2]/select" ) ) );
    select.selectByValue( "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;page-mode=flow" );
    //Wait for file to be created in the destination dir
    dw.WatchForCreate( downloadDir );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    assertTrue( new File( downloadDir + "\\InventorybyLine.xlsx" ).exists() );
    new File( downloadDir + "\\InventorybyLine.xlsx" ).delete();

    /*
     *  ## Step 6
     */
    new File( downloadDir + "\\InventorybyLine.csv" ).delete();
    select = new Select( this.elemHelper.FindElement( driver, By.xpath( "//div[@class='parameter']/div[2]/select" ) ) );
    select.selectByValue( "table/csv;page-mode=stream" );
    //Wait for file to be created in the destination dir
    dw.WatchForCreate( downloadDir );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    assertTrue( new File( downloadDir + "\\InventorybyLine.csv" ).exists() );
    new File( downloadDir + "\\InventorybyLine.csv" ).delete();

    /*
     *  ## Step 7
     */
    new File( downloadDir + "\\InventorybyLine.rtf" ).delete();
    select = new Select( this.elemHelper.FindElement( driver, By.xpath( "//div[@class='parameter']/div[2]/select" ) ) );
    select.selectByValue( "table/rtf;page-mode=flow" );
    //Wait for file to be created in the destination dir
    dw.WatchForCreate( downloadDir );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    assertTrue( new File( downloadDir + "\\InventorybyLine.rtf" ).exists() );
    new File( downloadDir + "\\InventorybyLine.rtf" ).delete();

    /*
     *  ## Step 8
     */
    driver.switchTo().defaultContent();
    attrIframeId = this.elemHelper.GetAttribute( driver, By.xpath( "//iframe" ), "id" );
    driver.switchTo().frame( attrIframeId );
    select = new Select( this.elemHelper.FindElement( driver, By.xpath( "//div[@class='parameter']/div[2]/select" ) ) );
    select.selectByValue( "pageable/text" );
    this.elemHelper.WaitForElementPresence( driver, By.id( "glasspane" ), 5 );
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "glasspane" ) );
    //Check the generated image
    this.elemHelper.WaitForElementPresence( driver, By.cssSelector( "iframe#reportContent" ) );
    WebDriver reportContentFrame = driver.switchTo().frame( "reportContent" );
    element = this.elemHelper.WaitForElementPresenceAndVisible( reportContentFrame, By.xpath( "//pre" ) );
    assertNotNull( element );
    text = this.elemHelper.WaitForElementPresentGetText( reportContentFrame, By.xpath( "//pre" ) );
    assertTrue( text.contains( "LINE: Motorcycles" ) );

  }

  /**
   * ############################### Test Case 7 ###############################
   *
   * Test Case Name:
   *    Close pop-up
   * Description:
   *    We pretend to test close the displayed pop-up.
   * Steps:
   *    1. Click in close window
   */
  @Test
  public void tc7_ClosePrpt_PopUpIsClosed() {
    this.log.info( "tc7_ClosePrpt_PopUpIsClosed" );

    // ## Step 1
    driver.switchTo().defaultContent();
    this.elemHelper.FindElement( driver, By.id( "fancybox-close" ) ).click();
    this.elemHelper.WaitForElementInvisibility( driver, By.id( "fancybox-content" ) );
    assertNotNull( this.elemHelper.FindElement( driver, By.xpath( "//button/span" ) ) );
  }
}
