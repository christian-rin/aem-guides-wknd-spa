package com.adobe.aem.guides.wknd.spa.react.core.listeners;
import com.adobe.aem.guides.wknd.spa.react.core.services.PageUpdateService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.apache.sling.api.resource.observation.ResourceChange;

import java.util.List;

@Component(
        service = ResourceChangeListener.class,
        immediate = true,
        property = {
                ResourceChangeListener.CHANGES + "=CHANGED",  // Listen for changes
                ResourceChangeListener.PATHS + "=/conf/wknd-spa-react/settings/wcm/templates/spa-page-template"  // Path to editable templates
        }
)
public class TemplateUpdateListener implements ResourceChangeListener {
    private static final Logger logger = LoggerFactory.getLogger(TemplateUpdateListener.class);

    @Reference
    private PageUpdateService pageUpdateService;

    private static final String TEMPLATE_PATH = "/conf/wknd-spa-react/settings/wcm/templates/spa-page-template";

    @Override
    public void onChange(List<ResourceChange> changes) {
        logger.info("inside onChange method.");
        for (ResourceChange change : changes) {
            logger.info("Start");
            if (change.getType() == ResourceChange.ChangeType.CHANGED) {
                String changedPath = change.getPath();

                // Check if the change is related to a specific template
                if (changedPath.contains(TEMPLATE_PATH)) {
                    // Call the service to update pages based on the modified template
                    handleTemplateUpdate(TEMPLATE_PATH);
                }
            }
            logger.info("Finish");
        }
    }

    private void handleTemplateUpdate(String templatePath) {
        // Call a service to update existing pages based on the modified template
        logger.info("handleTemplateUpdate called for "+ TEMPLATE_PATH);
        pageUpdateService.updatePagesBasedOnTemplate(templatePath);
    }

}

