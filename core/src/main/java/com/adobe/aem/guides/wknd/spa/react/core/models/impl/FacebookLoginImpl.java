package com.adobe.aem.guides.wknd.spa.react.core.models.impl;

import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import com.adobe.aem.guides.wknd.spa.react.core.models.FacebookLogin;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { FacebookLogin.class,
        ComponentExporter.class }, resourceType = FacebookLoginImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class FacebookLoginImpl implements FacebookLogin {

    @ValueMapValue
    private String message;

    static final String RESOURCE_TYPE = "wknd-spa-react/components/facebook-login";

    @Override
    public String getMessage() {
        return StringUtils.isNotBlank(message) ? message.toUpperCase() : null;
    }

    @Override
    public String getExportedType() {
        return FacebookLoginImpl.RESOURCE_TYPE;
    }


}