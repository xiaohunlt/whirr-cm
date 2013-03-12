/**
 * Licensed to Cloudera, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Cloudera, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudera.whirr.cm;

import static org.jclouds.scriptbuilder.domain.Statements.call;

import java.io.IOException;

import org.apache.commons.configuration.Configuration;
import org.apache.whirr.ClusterSpec;
import org.apache.whirr.service.ClusterActionEvent;
import org.apache.whirr.service.ClusterActionHandlerSupport;

public abstract class BaseHandler extends ClusterActionHandlerSupport {

  protected final static String CONFIG_IMPORT_PATH = "functions/cmf/";

  private final static String PROPERTIES_FILE = "whirr-cm-default.properties";

  protected Configuration getConfiguration(ClusterSpec spec) throws IOException {
    return getConfiguration(spec, PROPERTIES_FILE);
  }

  @Override
  protected void beforeBootstrap(ClusterActionEvent event) throws IOException {
    addStatement(event, call("configure_hostnames"));
    addStatement(event, call("retry_helpers"));
    //    addStatement(event, call(getInstallFunction(getConfiguration(event.getClusterSpec()), "java", "install_openjdk")));
    //    addStatement(event, call("install_cdh_hadoop"));
    //addStatement(event, call("install_cdh_hbase"));
  }

}
