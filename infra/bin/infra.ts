#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from '@aws-cdk/core';
import { CircleCIUserStack } from '../lib/circleci-user-stack';

const app = new cdk.App();
new CircleCIUserStack(app, 'InfraStack');
