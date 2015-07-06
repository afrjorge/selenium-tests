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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.pentaho.ctools.suite.CToolsTestSuite;
import org.pentaho.ctools.utils.ElementHelper;
import org.pentaho.ctools.utils.PageUrl;
import org.pentaho.ctools.utils.ScreenshotTestRule;
import org.pentaho.gui.web.puc.BrowseFiles;

/**
 * The script is testing the issue:
 * - http://jira.pentaho.com/browse/CDE-366
 *
 * and the automation test is described:
 * - http://jira.pentaho.com/browse/QUALITY-948
 *
 * NOTE
 * To test this script it is required to have CDE plugin installed.
 *
 * Naming convention for test:
 *  'tcN_StateUnderTest_ExpectedBehavior'
 *
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class CDE366 {

  // Instance of the driver (browser emulator)
  private final WebDriver driver = CToolsTestSuite.getDriver();
  // The base url to be append the relative url in test
  private final String baseUrl = CToolsTestSuite.getBaseUrl();
  //Access to wrapper for webdriver
  private final ElementHelper elemHelper = new ElementHelper();
  // Log instance
  private final Logger log = LogManager.getLogger( CDE366.class );
  //this.failure variable ==1 if test did not fail
  private int failure = 0;
  // Getting screenshot when test fails
  @Rule
  public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule( this.driver );

  /**
   * ############################### Test Case 1 ###############################
   *
   * Test Case Name:
   *    Assert "Style" and "Dashboard Type" are saved correctly
   *
   * Description:
   *    The test pretends validate the CDE-366 issue, so when user saves the style and dashboard type
   *    options on the settings of a CDE dashboard, they are saved correctly
   *
   * Steps:
   *    1. Wait for new Dashboard to be created, open dashboard settings, click save and assert user gets error message
   *    2. Apply template and save dashboard
   *    3. Edit dashboard settings and save
   *    4. Close and reopen Dashboard, assert applied template and settings changes persist
   *    5. Delete created dashboard
   */
  @Test( timeout = 240000 )
  public void tc01_CdeDashboard_SettingsPersist() {
    this.log.info( "tc01_CdeDashboard_SettingsPersist" );

    /*
     * ## Step 1
     */
    //Go to New CDE Dashboard
    this.driver.get( PageUrl.CDE_DASHBOARD );
    this.elemHelper.WaitForElementInvisibility( this.driver, By.xpath( "//div[@class='blockUI blockOverlay']" ) );

    //Open Dashboard Settings
    WebElement settingsLink = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='headerLinks']//a[@onclick='cdfdd.saveSettings()']" ) );
    assertNotNull( settingsLink );
    settingsLink.click();
    WebElement settingsPopup = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='popup']//div[@id='popupstates']" ) );
    assertNotNull( settingsPopup );

    //Click save and assert user gets a message of "Error saving settings"
    WebElement saveButton = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='popup']//div[@id='popupstates']//button[@id='popup_state0_buttonSave']" ) );
    assertNotNull( saveButton );
    saveButton.click();
    WebElement notifyError = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='notifyBar']" ) );
    assertNotNull( notifyError );
    String errorMessage = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//div[@id='notifyBar']/div[@class='notify-bar-message']" ) );
    assertEquals( "Errors saving settings", errorMessage );

    /*
     * ## Step 2
     */
    //Click Apply Template and wait for popup
    WebElement applyTemplate = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='table-cdfdd-layout-treeOperations']/a[@title='Apply Template']" ) );
    assertNotNull( applyTemplate );
    applyTemplate.click();
    WebElement templatePopup = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='popupTemplate']" ) );
    assertNotNull( templatePopup );

    //Find the Two Columns Template and apply it
    this.elemHelper.WaitForFrameReady( this.driver, By.id( "popupTemplatebox" ) );
    String templateText = this.elemHelper.FindElement( this.driver, By.xpath( "//div[@id='thumbs']/div[2]/p" ) ).getText();
    assertEquals( "Two Columns Template", templateText );
    this.elemHelper.Click( this.driver, By.xpath( "//div[@id='thumbs']/div[2]/p" ) );
    this.elemHelper.WaitForAttributeValue( this.driver, By.xpath( "//div[@id='thumbs']/div[2]" ), "class", "hover active" );
    String text = this.elemHelper.GetAttribute( this.driver, By.xpath( "//div[@id='thumbs']/div[2]" ), "class" );
    assertEquals( "hover active", text );
    WebElement okButton = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@class='popupTemplatebuttons']/button[@id='popupTemplate_state0_buttonOk']" ) );
    assertNotNull( okButton );
    okButton.click();
    WebElement confirmationMessage = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@class='popupTemplatemessage']" ) );
    assertNotNull( confirmationMessage );
    String warningText = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//div[@class='popupTemplatemessage']" ) );
    assertEquals( "Are you sure you want to load the template?WARNING: Dashboard Layout will be overwritten!", warningText );
    okButton = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@class='popupTemplatebuttons']/button[@id='popupTemplate_state0_buttonOk']" ) );
    assertNotNull( okButton );
    this.elemHelper.Click( this.driver, By.xpath( "//div[@class='popupTemplatebuttons']/button[@id='popupTemplate_state0_buttonOk']" ) );

    //Assert Template was applied
    WebElement columnExpander = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[5]/td/span" ) );
    assertNotNull( columnExpander );
    this.elemHelper.Click( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[5]/td/span" ) );
    WebElement firstPanel = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[6]" ) );
    assertNotNull( firstPanel );
    WebElement secondPanel = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[7]" ) );
    assertNotNull( secondPanel );
    String tr6tdText = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[6]/td" ) );
    String tr6td2Text = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[6]/td[2]" ) );
    String tr7tdText = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[7]/td" ) );
    String tr7td2Text = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[7]/td[2]" ) );
    assertEquals( "Column", tr6tdText );
    assertEquals( "Panel_1", tr6td2Text );
    assertEquals( "Column", tr7tdText );
    assertEquals( "Panel_2", tr7td2Text );

    //Save Dashboard
    WebElement saveDashboard = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='headerLinks']//a[@id='Save']" ) );
    assertNotNull( saveDashboard );
    saveDashboard.click();
    WebElement folderSelector = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='container_id']//a[@rel='public/']" ) );
    assertNotNull( folderSelector );
    folderSelector.click();
    WebElement inputName = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.id( "fileInput" ) );
    assertNotNull( inputName );
    inputName.sendKeys( "CDE366" );
    okButton = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@class='popupbuttons']/button[@id='popup_state0_buttonOk']" ) );
    okButton.click();
    this.elemHelper.WaitForElementNotPresent( this.driver, By.xpath( "//div[@class='popupbuttons']" ) );
    WebElement title = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@title='CDE366']" ) );
    assertNotNull( title );

    /*
     * ## Step 3
     */
    //Open Dashboard Settings
    settingsLink = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='headerLinks']//a[@onclick='cdfdd.saveSettings()']" ) );
    assertNotNull( settingsLink );
    settingsLink.click();
    settingsPopup = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='popup']//div[@id='popupstates']" ) );
    assertNotNull( settingsPopup );

    //Edit Style and Dashboard Type
    Select style = new Select( this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.id( "styleInput" ) ) );
    style.selectByVisibleText( "WDDocs" );
    Select dashType = new Select( this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.id( "rendererInput" ) ) );
    dashType.selectByVisibleText( "blueprint" );

    //Click save and assert user gets a message of "Error saving settings"
    saveButton = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='popup']//div[@id='popupstates']//button[@id='popup_state0_buttonSave']" ) );
    assertNotNull( saveButton );
    saveButton.click();
    WebElement notifySuccess = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='notifyBar']" ) );
    assertNotNull( notifySuccess );
    String successMessage = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//div[@id='notifyBar']/div[@class='notify-bar-message']" ) );
    assertEquals( "Dashboard Settings saved successfully", successMessage );

    /*
     * ## Step 4
     */
    //Open Home Folder
    this.driver.get( this.baseUrl );
    this.elemHelper.WaitForElementInvisibility( this.driver, By.xpath( "//div[@class='busy-indicator-container waitPopup']" ) );

    //Open Dashboard in edit mode
    this.driver.get( this.baseUrl + "api/repos/%3Apublic%3ACDE366.wcdf/wcdf.edit" );
    //this.elemHelper.WaitForElementInvisibility( this.driver, By.xpath( "//div[@class='blockUI blockOverlay']" ) );

    //Check template is applied
    columnExpander = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[5]/td/span" ) );
    assertNotNull( columnExpander );
    this.elemHelper.Click( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[5]/td/span" ) );
    firstPanel = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[6]" ) );
    assertNotNull( firstPanel );
    secondPanel = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[7]" ) );
    assertNotNull( secondPanel );
    tr6tdText = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[6]/td" ) );
    tr6td2Text = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[6]/td[2]" ) );
    tr7tdText = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[7]/td" ) );
    tr7td2Text = this.elemHelper.WaitForElementPresentGetText( this.driver, By.xpath( "//table[@id='table-cdfdd-layout-tree']/tbody/tr[7]/td[2]" ) );
    assertEquals( "Column", tr6tdText );
    assertEquals( "Panel_1", tr6td2Text );
    assertEquals( "Column", tr7tdText );
    assertEquals( "Panel_2", tr7td2Text );

    //Open Settings and assert Style and Dashboard Type were saved 
    settingsLink = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='headerLinks']//a[@onclick='cdfdd.saveSettings()']" ) );
    assertNotNull( settingsLink );
    settingsLink.click();
    settingsPopup = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//div[@id='popup']//div[@id='popupstates']" ) );
    assertNotNull( settingsPopup );
    WebElement selectedStyle = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//select[@id='styleInput']/option[@selected='']" ) );
    String selectedValue = selectedStyle.getAttribute( "value" );
    assertEquals( "WDDocs", selectedValue );
    WebElement selectedDash = this.elemHelper.WaitForElementPresenceAndVisible( this.driver, By.xpath( "//select[@id='rendererInput']/option[@selected='']" ) );
    selectedValue = selectedDash.getAttribute( "value" );
    assertEquals( "blueprint", selectedValue );

    /*
     * ## Step 4
     */
    BrowseFiles browse = new BrowseFiles( this.driver );
    browse.DeleteMultipleFilesByName( "/public", "CDE366" );
    browse.EmptyTrash();
    this.failure = 1;
  }

  @AfterClass
  public void tearDown() {
    this.log.info( "tearDown##" + CDE366.class.getSimpleName() );
    if ( this.failure == 0 ) {
      BrowseFiles browse = new BrowseFiles( this.driver );
      browse.DeleteMultipleFilesByName( "/public", "CDE366" );
      browse.EmptyTrash();
    }

  }
}