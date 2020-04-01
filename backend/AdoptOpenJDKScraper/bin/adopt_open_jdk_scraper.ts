#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from '@aws-cdk/core';
import { AdoptOpenJdkScraperStack } from '../lib/adopt_open_jdk_scraper-stack';
import { bundleNpm } from '../lib/process/setup';

// pre-process
bundleNpm();

const app = new cdk.App();
new AdoptOpenJdkScraperStack(app, 'AdoptOpenJdkScraperStack', {
    user: app.node.tryGetContext('user'),
    password: app.node.tryGetContext('password'),
    host: app.node.tryGetContext('host'),
    port: app.node.tryGetContext('port'),
    database: app.node.tryGetContext('database')
});
