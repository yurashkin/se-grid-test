/**
 * Copyright 2014, Grid Dynamics International, Inc.
 * Licensed under the Apache License, Version 2.0.
 * Classification level: Public
 */
package com.griddynamics.cd.selenium.monitoring;

import org.openqa.grid.internal.Registry;

public class SeleniumGridGeneralMetrics implements SeleniumGridGeneralMetricsMBean {
    private final Registry registry;

    public SeleniumGridGeneralMetrics(Registry registry) {
        this.registry = registry;
    }

    /**
     * Because Selenium hides most of information about sessions in queue, we'd need to get information via lots of
     * reflection in order to get it for particular capabilities, thus it was decided to show a general metric for the
     * whole grid - how many sessions are waiting to be started in Selenium.
     *
     * @return number of sessions established with Se Hub but didn't forwarded to the Se Nodes for execution because
     * there is not enough of free slots
     */
    @Override
    public int getNumberOfSessionsInQueue() {
        return registry.getNewSessionRequestCount();
    }

    /**
     * Number of nodes Hub thinks it has.
     *
     * @return number of nodes Hub thinks it has
     */
    @Override
    public int getNumberOfActiveNodes() {
        return registry.getAllProxies().size();
    }

    public static String getJmxObjectName() {
        return SeleniumGridGeneralMetrics.class.getPackage().getName()
                + ":type=" + SeleniumGridGeneralMetrics.class.getSimpleName();
    }
}
