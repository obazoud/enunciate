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

package org.codehaus.enunciate.modules.amf.config;

/**
 * Configuration element for a Flex app.
 *
 * @author Ryan Heaton
 */
public class FlexApp {

  private String name = "";
  private String srcDir;
  private String outputPath;
  private String mainMxmlFile;

  /**
   * The name of this Flex app.
   *
   * @return The name of this Flex app.
   */
  public String getName() {
    return name;
  }

  /**
   * The name of this Flex app.
   *
   * @param name The name of this Flex app.
   */
  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("A name must be specified for a Flex app.");
    }
    this.name = name;
  }

  /**
   * The source directory of the app (relative to the configuration file).
   *
   * @return The source directory.
   */
  public String getSrcDir() {
    return srcDir;
  }

  /**
   * The source directory of the app (relative to the configuration file).
   *
   * @param srcDir The source directory of the app (relative to the configuration file).
   */
  public void setSrcDir(String srcDir) {
    this.srcDir = srcDir;
  }

  /**
   * The output directory of the app (relative to the base webapp directory).
   *
   * @return The output directory of the app (relative to the base webapp directory).
   */
  public String getOutputPath() {
    return outputPath;
  }

  /**
   * The output directory of the app (relative to the base webapp directory).
   *
   * @param outputPath The output directory of the app (relative to the base webapp directory).
   */
  public void setOutputPath(String outputPath) {
    this.outputPath = outputPath;
  }

  /**
   * The path to the main mxml file.
   *
   * @return The path to the main mxml file.
   */
  public String getMainMxmlFile() {
    return mainMxmlFile;
  }

  /**
   * The path to the main mxml file.
   *
   * @param mainMxmlFile The path to the main mxml file.
   */
  public void setMainMxmlFile(String mainMxmlFile) {
    this.mainMxmlFile = mainMxmlFile;
  }
}
