import * as cdk from '@aws-cdk/core';
import * as events from '@aws-cdk/aws-events';
import * as targets from '@aws-cdk/aws-events-targets';
import * as logs from '@aws-cdk/aws-logs';
import * as lambda from '@aws-cdk/aws-lambda';
import path = require('path');

export interface ContextAppStackProps extends cdk.StackProps {
  jdbc: string;
  user: string;
  password: string;
}
export class OpenJdkReleaseInformationCollectorStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props: ContextAppStackProps) {
    super(scope, id, props);

    const adoptLambdaFn = new lambda.Function(this, 'AdoptScraper', {
      functionName: 'adopt-scraper',
      code: lambda.Code.fromAsset(path.join(__dirname, '../', 'lambda/build/libs/OpenJdkReleaseInformationCollector-all.jar')),
      handler: 'com.github.poad.openjdk.finder.backend.AdoptOpenJdkHandler::handleRequest',
      timeout: cdk.Duration.seconds(14.5 * 60),
      runtime: lambda.Runtime.JAVA_11,
      environment: {
        'JDBC_URL': props.jdbc,
        'JDBC_USER': props.user,
        'JDBC_PASSWORD': props.password,
      },
      memorySize: 512
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
