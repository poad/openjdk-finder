import * as cdk from '@aws-cdk/core';
import * as events from '@aws-cdk/aws-events';
import * as targets from '@aws-cdk/aws-events-targets';
import * as logs from '@aws-cdk/aws-logs';
import * as lambda from '@aws-cdk/aws-lambda';
import * as iam from '@aws-cdk/aws-iam';

import { NodejsFunction } from '@aws-cdk/aws-lambda-nodejs';
import { NODE_LAMBDA_LAYER_DIR } from './process/setup';

export interface ContextAppStackProps extends cdk.StackProps {
  user: string;
  password: string;
  host: string;
  port: string;
  database: string;
}

export class AdoptOpenJdkScraperStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props: ContextAppStackProps) {
    super(scope, id, props);

    const nodeModulesLayer = new lambda.LayerVersion(this, 'NodeModulesLayer',
      {
        code: lambda.AssetCode.fromAsset(NODE_LAMBDA_LAYER_DIR),
        compatibleRuntimes: [lambda.Runtime.NODEJS_12_X],
        license: 'MIT',
        description: 'PostgreSQL Lamnbda Layer'
      }
    );
    const adoptLambdaFn = new NodejsFunction(this, 'AdoptScraper', {
      functionName: 'adopt-scraper',
      entry: 'lambda/index.ts',
      handler: 'handler',
      timeout: cdk.Duration.seconds(14.5 * 60),
      runtime: lambda.Runtime.NODEJS_12_X,
      minify: true,
      layers: [
        nodeModulesLayer
      ],
      environment: {
        'PG_HOST': props.host,
        'PG_PORT': props.port,
        'PG_DATABASE': props.database,
        'PG_USER': props.user,
        'PG_PASSWORD': props.password
      },
    });
    if (process.env.CDK_DEFAULT_ACCOUNT) {
      nodeModulesLayer.addPermission('layer-access-permission', {
        accountId: process.env.CDK_DEFAULT_ACCOUNT
      });
    }

    new lambda.CfnPermission(this, 'layer-access-policy', {
      action: 'lambda:*',
      functionName: adoptLambdaFn.functionName,
      principal: '*',
      sourceArn: nodeModulesLayer.layerVersionArn
    });

    const rule = new events.Rule(this, 'Rule', {
      schedule: events.Schedule.expression('cron(0 0 * * ? *)')
    });

    rule.addTarget(new targets.LambdaFunction(adoptLambdaFn));

    const loggroup = new logs.LogGroup(this, 'log-group', {
      logGroupName: '/aws/lambda/' + adoptLambdaFn.functionName,
      retention: logs.RetentionDays.ONE_DAY
    })
  }
}
