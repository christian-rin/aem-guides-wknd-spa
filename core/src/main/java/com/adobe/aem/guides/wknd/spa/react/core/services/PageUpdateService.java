package com.adobe.aem.guides.wknd.spa.react.core.services;

import com.adobe.aem.guides.wknd.spa.react.core.listeners.TemplateUpdateListener;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(service = PageUpdateService.class)
public class PageUpdateService {
    private static final Logger logger = LoggerFactory.getLogger(TemplateUpdateListener.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    private static final String TEMPLATE_PATH = "/conf/wknd-spa-react/settings/wcm/templates/spa-page-template";

    public void updatePagesBasedOnTemplate(String templatePath) {
        logger.info("inside updatePagesBasedOnTemplate : "+templatePath);
        Map<String, Object> params = new HashMap<>();
        params.put(ResourceResolverFactory.SUBSERVICE, "writeService");  // Ensure that this user has permissions to modify content
        ResourceResolver resolver = null;

        try {
            logger.info("try block");
            resolver = resolverFactory.getServiceResourceResolver(params);
            PageManager pageManager = resolver.adaptTo(PageManager.class);
            logger.info("pageManager: "+pageManager);
            if (pageManager != null) {
                logger.info("inside if (pageManager != null)");
                // Start with a root page where the pages you want to update are located
                Page rootPage = pageManager.getPage("/content/wknd-spa-react");
                logger.info("rootPage: "+rootPage);
                if (rootPage != null) {
                    logger.info("inside if (rootPage != null)");
                    // Iterate through the child pages
                    Iterator<Page> pages = rootPage.listChildren();
                    logger.info("pages: "+pages);
                    while (pages.hasNext()) {
                        Page page = pages.next();

                        // Check if the page uses the template that has been modified
                        String pageTemplate = page.getProperties().get("cq:template", String.class);
                        if (templatePath.equals(pageTemplate)) {
                            logger.info(templatePath +" = "+pageTemplate);
                            updatePageContent(page, resolver);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.info("catch block");
            e.printStackTrace();
        } finally {
            logger.info("finally block");
            if (resolver != null) {
                resolver.close();
                logger.info("resolver.close");
            }
        }
    }

    private void updatePageContent(Page page, ResourceResolver resolver) {
        logger.info("inside updatePageContent");
        logger.info("Page: "+ page);
        // Get the jcr:content node of the page where properties are stored
        Resource contentResource = page.getContentResource();
        if (contentResource != null) {
            ModifiableValueMap properties = contentResource.adaptTo(ModifiableValueMap.class);

            if (properties != null) {
                // Example: Modify a specific property based on the template change
                properties.put("jcr:title", "Updated Title Based on Template Change");
                properties.put("myCustomProperty", "New Value");

                try {
                    // Save changes to the repository
                    resolver.commit();
                    logger.info("Page updated successfully: " + page.getPath());
                } catch (PersistenceException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
