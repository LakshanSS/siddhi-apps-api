/*
 *   Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */

package org.wso2.carbon.siddhi.apps.api.rest;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.analytics.msf4j.interceptor.common.AuthenticationInterceptor;
import org.wso2.carbon.siddhi.apps.api.rest.factories.AppsApiServiceFactory;
import org.wso2.msf4j.Microservice;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.annotations.ApiParam;
import org.wso2.msf4j.interceptor.annotation.RequestInterceptor;

@Component(
        name = "siddhi-apps-service",
        service = Microservice.class,
        immediate = true
)

@Path("/apis/dashboards/data-search")
@io.swagger.annotations.Api(description = "The Siddhi Apps API")
@RequestInterceptor(AuthenticationInterceptor.class)
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen",
        date = "2017-11-01T11:26:25.925Z")
public class AppsApi implements Microservice {
    private Logger log = LoggerFactory.getLogger(AppsApi.class);
    private final AppsApiService delegate = AppsApiServiceFactory.getSiddhiAppsApi();

    @OPTIONS
    @Path("/{id}/{appName}")
    @GET
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Gets definitions of the siddhi app", response = Object.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Object.class, responseContainer = "List") })
    public Response getSiddhiAppDefinitions(
            @ApiParam(value = "ID of the worker.", required = true) @PathParam("id") String id
            , @ApiParam(value = "ID of the siddhi app.", required = true) @PathParam("appName") String appName
            ) {
        return delegate.getSiddhiAppDefinitions(id,appName);
    }

    @Path("/siddhi-apps")
    @GET
    @Produces({ "application/json" })
    public Response getAllSiddhiApps(){
        return delegate.getAllSiddhiApps();
    }


    /**
     * This is the activation method of ServiceComponent. This will be called when its references are
     * satisfied.
     *
     * @param bundleContext the bundle context instance of this bundle.
     * @throws Exception this will be thrown if an issue occurs while executing the activate method
     */
    @Activate
    protected void start(BundleContext bundleContext) throws Exception {
        log.debug("Siddhi Apps REST API activated.");
    }

    /**
     * This is the deactivation method of ServiceComponent. This will be called when this component
     * is being stopped or references are satisfied during runtime.
     *
     * @throws Exception this will be thrown if an issue occurs while executing the de-activate method
     */
    @Deactivate
    protected void stop() throws Exception {
        log.debug("Siddhi Apps REST API deactivated.");
    }

}