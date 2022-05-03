package com.company.jiraplugin.phase1;


import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.plugin.event.events.PluginEnabledEvent;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import com.company.jiraplugin.phase1.services.ExampleService;
import com.company.jiraplugin.phase1.services.FreeTimeService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService({ApplicationStartup.class})
@Named
@Component
public class ApplicationStartup implements InitializingBean, DisposableBean {
    @JiraImport
    EventPublisher _eventPublisher;

    final FreeTimeService freeTimeService;

    @Inject
    public ApplicationStartup(
            EventPublisher eventPublisher,
            FreeTimeService freeTimeService) {
        this.freeTimeService = freeTimeService;
        _eventPublisher = eventPublisher;
    }

    @Override
    public void destroy() {
        // remove custom field
        _eventPublisher.unregister(this);
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        _eventPublisher.register(this);
        init();
    }

    private void init() {
        this.freeTimeService.helloWorld();
    }

    @PreDestroy
    public void shutdown() {
        _eventPublisher.unregister(this);
    }

    @EventListener
    public void onPluginEnabled(PluginEnabledEvent event) throws Exception {
        if (!event.getPlugin().getKey().equals(ConstantsGlobal.PLUGIN_KEY))
            return;

    }

}
