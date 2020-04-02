#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from '@aws-cdk/core';
import { OpenJdkReleaseInformationCollectorStack } from '../lib/open_jdk_release_information_collector-stack';
import { buildJar } from '../lib/process/setup';

buildJar();

const app = new cdk.App();
new OpenJdkReleaseInformationCollectorStack(app, 'OpenJdkReleaseInformationCollectorStack', {
    jdbc: app.node.tryGetContext('jdbc'),
    user: app.node.tryGetContext('user'),
    password: app.node.tryGetContext('password')
});
