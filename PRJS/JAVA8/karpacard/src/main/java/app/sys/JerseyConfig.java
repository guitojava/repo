/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.sys;

import app.sys.errorhandling.GenericExceptionMapper;
import app.sys.errorhandling.ServiceExceptionMapper;
import app.deals.api.DealAPI;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        this.registerEndpoints();
    }

    @PostConstruct
    public void init() {
        //empty for now
    }

    private void registerEndpoints() {
        register(WadlResource.class);
        register(DealAPI.class);
        register(ServiceExceptionMapper.class);
        register(GenericExceptionMapper.class);
    }

}
