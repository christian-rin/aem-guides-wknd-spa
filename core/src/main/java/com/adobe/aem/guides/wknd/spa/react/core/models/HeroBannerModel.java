package com.adobe.aem.guides.wknd.spa.react.core.models;
import com.adobe.cq.wcm.core.components.models.Image;
import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface HeroBannerModel extends Image {
    public String getClassList() ;
}
