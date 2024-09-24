    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2017 Adobe Systems Incorporated
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package com.adobe.aem.guides.wknd.spa.react.core.models.impl;
import com.adobe.aem.guides.wknd.spa.react.core.models.HeroBannerModel;


import javax.annotation.PostConstruct;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.Image;
import com.adobe.aem.guides.wknd.spa.react.core.models.BannerModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;

    @Model(adaptables = SlingHttpServletRequest.class,
            adapters = { HeroBannerModel.class, ComponentExporter.class},
            resourceType = HeroBannerModelImpl.RESOURCE_TYPE,
            defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    @Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
    public class HeroBannerModelImpl implements HeroBannerModel {

        static final String RESOURCE_TYPE = "wknd-spa-react/components/herobanner";

        private static final String PN_FULL_WIDTH = "useFullWidth";

        @Self
        private SlingHttpServletRequest request;

        @ScriptVariable
        private ValueMap properties;

        private String classList;

        @Self
        @Via(type = ResourceSuperType.class)
        private Image image;

//        @PostConstruct
//        private void initModel() {
//            classList = getClassList();
//            image = getImage();
//

        @Override
        public String getClassList() {
//            if (classList != null) {
//                return classList;
//            }
            classList = "wknd-HeroBanner";
            if ("true".equals(properties.get(PN_FULL_WIDTH, ""))) {
                classList += " width-full";
            }
            return classList;
        }

        @Override
        public String getSrc() {
            return null != image ? image.getSrc() : null;
        }

        // method required by `ComponentExporter` interface
        // exposes a JSON property named `:type` with a value of `wknd-spa-react/components/herobanner`
        // required to map the JSON export to the SPA component props via the `MapTo`
        @Override
        public String getExportedType() {
            return HeroBannerModelImpl.RESOURCE_TYPE;
        }

    }