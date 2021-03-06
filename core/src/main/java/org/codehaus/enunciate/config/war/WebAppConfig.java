/*
 * Copyright 2006-2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codehaus.enunciate.config.war;

import org.codehaus.enunciate.main.webapp.FilterComponent;
import org.codehaus.enunciate.main.webapp.WebAppComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Manifest;
import java.util.jar.Attributes;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * Configuration for the web app.
 *
 * @author Ryan Heaton
 */
public class WebAppConfig {

  private boolean includeClasspathLibs = true;
  private boolean excludeDefaultLibs = true;
  private final List<IncludeExcludeLibs> excludeLibs = new ArrayList<IncludeExcludeLibs>();
  private final List<IncludeExcludeLibs> includeLibs = new ArrayList<IncludeExcludeLibs>();
  private String webXMLTransform;
  private URL webXMLTransformURL;
  private String mergeWebXML;
  private URL mergeWebXMLURL;
  private String preBase;
  private String postBase;
  private String dir;
  private String war;
  private final Manifest manifest = getDefaultManifest();
  private boolean disabled = false;
  private boolean doCompile = true;
  private boolean doLibCopy = true;
  private boolean doPackage = true;
  private final Map<String, String> webXmlAttributes = new HashMap<String, String>();

  private final List<FilterComponent> globalServletFilters = new ArrayList<FilterComponent>();
  private final List<CopyResources> copyResources = new ArrayList<CopyResources>();
  private final List<WebAppResource> envEntries = new ArrayList<WebAppResource>();
  private final List<WebAppResource> resourceEnvRefs = new ArrayList<WebAppResource>();
  private final List<WebAppResource> resourceRefs = new ArrayList<WebAppResource>();

  /**
   * Whether the application assembly is disabled.
   *
   * @return Whether the application assembly is disabled.
   */
  public boolean isDisabled() {
    return disabled;
  }

  /**
   * Whether the application assembly is disabled.
   *
   * @param disabled Whether the application assembly is disabled.
   */
  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  /**
   * The directory where to build the (expanded) app.
   *
   * @return The directory where to build the (expanded) app.
   */
  public String getDir() {
    return dir;
  }

  /**
   * The directory where to build the (expanded) app.
   *
   * @param dir The directory where to build the (expanded) app.
   */
  public void setDir(String dir) {
    this.dir = dir;
  }

  /**
   * The war file.
   *
   * @return The war file.
   */
  public String getWar() {
    return war;
  }

  /**
   * The war file.
   *
   * @param war The war file.
   */
  public void setWar(String war) {
    this.war = war;
  }

  /**
   * Whether to include the default libs.
   *
   * @return Whether to include the default libs.
   */
  public boolean isIncludeClasspathLibs() {
    return includeClasspathLibs;
  }

  /**
   * Whether to include the default libs.
   *
   * @param includeClasspathLibs Whether to include the default libs.
   */
  public void setIncludeClasspathLibs(boolean includeClasspathLibs) {
    this.includeClasspathLibs = includeClasspathLibs;
  }

  /**
   * Whether to exclude the default libs.
   *
   * @return Whether to exclude the default libs.
   */
  public boolean isExcludeDefaultLibs() {
    return excludeDefaultLibs;
  }

  /**
   * whether this module should take on the responsibility of compiling the server-side classes.
   *
   * @return whether this module should take on the responsibility of compiling the server-side classes
   */
  public boolean isDoCompile() {
    return doCompile;
  }

  /**
   * whether this module should take on the responsibility of compiling the server-side classes
   *
   * @param doCompile whether this module should take on the responsibility of compiling the server-side classes
   */
  public void setDoCompile(boolean doCompile) {
    this.doCompile = doCompile;
  }

  /**
   * whether this module should take on the responsibility of copying libraries to WEB-INF/lib.
   *
   * @return whether this module should take on the responsibility of copying libraries to WEB-INF/lib
   */
  public boolean isDoLibCopy() {
    return doLibCopy;
  }

  /**
   * whether this module should take on the responsibility of copying libraries to WEB-INF/lib
   *
   * @param doLibCopy whether this module should take on the responsibility of copying libraries to WEB-INF/lib
   */
  public void setDoLibCopy(boolean doLibCopy) {
    this.doLibCopy = doLibCopy;
  }

  /**
   * whether this module should take on the responsibility of packaging (zipping) up the war
   *
   * @return whether this module should take on the responsibility of packaging (zipping) up the war
   */
  public boolean isDoPackage() {
    return doPackage;
  }

  /**
   * whether this module should take on the responsibility of packaging (zipping) up the war
   *
   * @param doPackage whether this module should take on the responsibility of packaging (zipping) up the war
   */
  public void setDoPackage(boolean doPackage) {
    this.doPackage = doPackage;
  }

  /**
   * Whether to exclude the default libs.
   *
   * @param excludeDefaultLibs Whether to exclude the default libs.
   */
  public void setExcludeDefaultLibs(boolean excludeDefaultLibs) {
    this.excludeDefaultLibs = excludeDefaultLibs;
  }

  /**
   * Whether to exclude the default libs. (Support for backwards-compatability to account for a typo).
   *
   * @param excludeDefaultLibs Whether to exclude the default libs.
   */
  public void setExludeDefaultLibs(boolean excludeDefaultLibs) {
    this.excludeDefaultLibs = excludeDefaultLibs;
  }

  /**
   * Add a exclude jars.
   *
   * @param excludeLibs The exclude jars to add.
   */
  public void addExcludeLibs(IncludeExcludeLibs excludeLibs) {
    this.excludeLibs.add(excludeLibs);
  }

  /**
   * Get the list of exclude jars.
   *
   * @return The list of exclude jars.
   */
  public List<IncludeExcludeLibs> getExcludeLibs() {
    return excludeLibs;
  }

  /**
   * Add a include jars.
   *
   * @param includeLibs The include jars to add.
   */
  public void addIncludeLibs(IncludeExcludeLibs includeLibs) {
    this.includeLibs.add(includeLibs);
  }

  /**
   * Get the list of include jars.
   *
   * @return The list of include jars.
   */
  public List<IncludeExcludeLibs> getIncludeLibs() {
    return includeLibs;
  }

  /**
   * The (optional) stylesheet transformation through which to pass the generated web.xml file.
   *
   * @return The stylesheet transformation through which to pass the generated web.xml file.
   */
  public URL getWebXMLTransformURL() {
    return webXMLTransformURL;
  }

  /**
   * The (optional) stylesheet transformation through which to pass the generated web.xml file.
   *
   * @param stylesheet The stylesheet transformation through which to pass the generated web.xml file.
   */
  public void setWebXMLTransformURL(URL stylesheet) {
    this.webXMLTransformURL = stylesheet;
  }

  /**
   * The (optional) stylesheet transformation through which to pass the generated web.xml file.
   *
   * @return The (optional) stylesheet transformation through which to pass the generated web.xml file.
   */
  public String getWebXMLTransform() {
    return webXMLTransform;
  }

  /**
   * The (optional) stylesheet transformation through which to pass the generated web.xml file.
   *
   * @param stylesheet The stylesheet transformation through which to pass the generated web.xml file.
   */
  public void setWebXMLTransform(String stylesheet) throws MalformedURLException {
    this.webXMLTransform = stylesheet;
  }

  /**
   * The web xml file to merge.
   *
   * @return The web xml file to merge.
   */
  public URL getMergeWebXMLURL() {
    return mergeWebXMLURL;
  }

  /**
   * The web xml file to merge.
   *
   * @param mergeWebXMLURL The web xml file to merge.
   */
  public void setMergeWebXMLURL(URL mergeWebXMLURL) {
    this.mergeWebXMLURL = mergeWebXMLURL;
  }

  /**
   * The web xml file to merge.
   *
   * @return The web xml file to merge.
   */
  public String getMergeWebXML() {
    return mergeWebXML;
  }

  /**
   * The web xml file to merge.
   *
   * @param mergeWebXML The web xml file to merge.
   */
  public void setMergeWebXML(String mergeWebXML) throws MalformedURLException {
    this.mergeWebXML = mergeWebXML;
  }

  /**
   * The base of the war directory before copying enunciate-specific files.
   *
   * @return The base of the war directory before copying enunciate-specific files.
   */
  public String getPreBase() {
    return preBase;
  }

  /**
   * The base of the war directory before copying enunciate-specific files.
   *
   * @param preBase The base of the war directory before copying enunciate-specific files.
   */
  public void setPreBase(String preBase) {
    this.preBase = preBase;
  }

  /**
   * The base of the war directory after copying enunciate-specific files.
   *
   * @return The base of the war directory after copying enunciate-specific files.
   */
  public String getPostBase() {
    return postBase;
  }

  /**
   * The base of the war directory after copying enunciate-specific files.
   *
   * @param postBase The base of the war directory after copying enunciate-specific files.
   */
  public void setPostBase(String postBase) {
    this.postBase = postBase;
  }

  /**
   * The manifest for this war.
   *
   * @return The manifest for this war.
   */
  public Manifest getManifest() {
    return manifest;
  }

  /**
   * Adds a manifest entry to this war's manifest.
   *
   * @param section The section.  If null, the main section is assumed.
   * @param name The name of the attribute.
   * @param value The value of the attribute.
   */
  public void addManifestAttribute(String section, String name, String value) {
    Attributes attributes;
    if (section == null) {
      attributes = this.manifest.getMainAttributes();
    }
    else {
      attributes = this.manifest.getAttributes(section);
      if (attributes == null) {
        attributes = new Attributes();
        this.manifest.getEntries().put(section, attributes);
      }
    }
    attributes.putValue(name, value);
  }

  /**
   * Get the default manifest for a war file.
   *
   * @return The default manifest for a war file.
   */
  public static Manifest getDefaultManifest() {
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().putValue(Attributes.Name.MANIFEST_VERSION.toString(), "1.0");
    manifest.getMainAttributes().putValue("Created-By", "Enunciate");
    return manifest;
  }

  /**
   * The resources to copy for this application.
   *
   * @return The resources to copy for this application.
   */
  public List<CopyResources> getCopyResources() {
    return copyResources;
  }

  /**
   * Add a copy resources.
   *
   * @param copyResources The copy resources to add.
   */
  public void addCopyResources(CopyResources copyResources) {
    this.copyResources.add(copyResources);
  }

  /**
   * The global servlet filters for this application.
   *
   * @return The global servlet filters for this application.
   */
  public List<FilterComponent> getGlobalServletFilters() {
    return globalServletFilters;
  }

  /**
   * Add a global servlet filter to be applied to all web service requests.
   *
   * @param filterConfig The filter configuration.
   */
  public void addGlobalServletFilter(FilterComponent filterConfig) {
    this.globalServletFilters.add(filterConfig);
  }

  /**
   * Web app env entries.
   *
   * @return Web app env entries.
   */
  public List<WebAppResource> getEnvEntries() {
    return envEntries;
  }

  public void addEnvEntry(WebAppResource resource) {
    if (resource.getName() == null) {
      throw new IllegalArgumentException("An env entry must have a name.");
    }
    else if (resource.getType() == null) {
      throw new IllegalArgumentException("An env entry must have a type.");
    }
    this.envEntries.add(resource);
  }

  /**
   * Web app resource env entries.
   *
   * @return Web app resource env entries.
   */
  public List<WebAppResource> getResourceEnvRefs() {
    return resourceEnvRefs;
  }

  public void addResourceEnvRef(WebAppResource resource) {
    if (resource.getName() == null) {
      throw new IllegalArgumentException("An resource env entry must have a name.");
    }
    else if (resource.getType() == null) {
      throw new IllegalArgumentException("An resource env entry must have a type.");
    }

    this.resourceEnvRefs.add(resource);
  }

  /**
   * Web app resource entries.
   *
   * @return Web app resource entries.
   */
  public List<WebAppResource> getResourceRefs() {
    return resourceRefs;
  }

  public void addResourceRef(WebAppResource resource) {
    if (resource.getName() == null) {
      throw new IllegalArgumentException("An resource entry must have a name.");
    }
    else if (resource.getType() == null) {
      throw new IllegalArgumentException("An resource entry must have a type.");
    }
    else if (resource.getAuth() == null) {
      throw new IllegalArgumentException("An resource entry must have an auth.");
    }

    this.resourceEnvRefs.add(resource);
  }

  public Map<String, String> getWebXmlAttributes() {
    return webXmlAttributes;
  }

  public void addWebXmlAttribute(String name, String value) {
    this.webXmlAttributes.put(name, value);
  }

}
