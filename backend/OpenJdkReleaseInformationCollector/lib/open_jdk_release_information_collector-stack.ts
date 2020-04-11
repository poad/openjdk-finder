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

    const adoptLambdaFn = new lambda.Function(this, 'AdoptCollector', {
      functionName: 'adopt-collector',
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

    const adoptRule = new events.Rule(this, 'AdoptCollectorRule', {
      schedule: events.Schedule.expression('cron(0 0 * * ? *)')
    });

    adoptRule.addTarget(new targets.LambdaFunction(adoptLambdaFn));

    // new logs.LogGroup(this, 'AdoptCollectorLogGroup', {
    //   logGroupName: '/aws/lambda/' + adoptLambdaFn.functionName,
    //   retention: logs.RetentionDays.ONE_DAY
    // })

    const libericaLambdaFn = new lambda.Function(this, 'LibericaCollector', {
      functionName: 'liberica-collector',
      code: lambda.Code.fromAsset(path.join(__dirname, '../', 'lambda/build/libs/OpenJdkReleaseInformationCollector-all.jar')),
      handler: 'com.github.poad.openjdk.finder.backend.LibericaJdkHandler::handleRequest',
      timeout: cdk.Duration.seconds(14.5 * 60),
      runtime: lambda.Runtime.JAVA_11,
      environment: {
        'JDBC_URL': props.jdbc,
        'JDBC_USER': props.user,
        'JDBC_PASSWORD': props.password,
      },
      memorySize: 512
    });

    const libericaRule = new events.Rule(this, 'LibericaCollectorRule', {
      schedule: events.Schedule.expression('cron(15 0 * * ? *)')
    });

    libericaRule.addTarget(new targets.LambdaFunction(libericaLambdaFn));

    // new logs.LogGroup(this, 'LibericaCollectorLogGroup', {
    //   logGroupName: '/aws/lambda/' + libericaLambdaFn.functionName,
    //   retention: logs.RetentionDays.ONE_DAY
    // });

    const zuluLambdaFn = new lambda.Function(this, 'ZuluCollector', {
      functionName: 'zulu-collector',
      code: lambda.Code.fromAsset(path.join(__dirname, '../', 'lambda/build/libs/OpenJdkReleaseInformationCollector-all.jar')),
      handler: 'com.github.poad.openjdk.finder.backend.ZuluCommunityHandler::handleRequest',
      timeout: cdk.Duration.seconds(14.5 * 60),
      runtime: lambda.Runtime.JAVA_11,
      environment: {
        'JDBC_URL': props.jdbc,
        'JDBC_USER': props.user,
        'JDBC_PASSWORD': props.password,
      },
      memorySize: 512
    });

    const zuluRule = new events.Rule(this, 'ZuluCollectorRule', {
      schedule: events.Schedule.expression('cron(30 0 * * ? *)')
    });

    zuluRule.addTarget(new targets.LambdaFunction(zuluLambdaFn));

    // new logs.LogGroup(this, 'ZuluCollectorLogGroup', {
    //   logGroupName: '/aws/lambda/' + zuluLambdaFn.functionName,
    //   retention: logs.RetentionDays.ONE_DAY
    // });

    const sapMachineLambdaFn = new lambda.Function(this, 'SapMachineCollector', {
      functionName: 'sapMachine-collector',
      code: lambda.Code.fromAsset(path.join(__dirname, '../', 'lambda/build/libs/OpenJdkReleaseInformationCollector-all.jar')),
      handler: 'com.github.poad.openjdk.finder.backend.SapMachineHandler::handleRequest',
      timeout: cdk.Duration.seconds(14.5 * 60),
      runtime: lambda.Runtime.JAVA_11,
      environment: {
        'JDBC_URL': props.jdbc,
        'JDBC_USER': props.user,
        'JDBC_PASSWORD': props.password,
      },
      memorySize: 512
    });
  
    const sapMachineRule = new events.Rule(this, 'SapMachineCollectorRule', {
      schedule: events.Schedule.expression('cron(45 0 * * ? *)')
    });
  
    sapMachineRule.addTarget(new targets.LambdaFunction(sapMachineLambdaFn));

    const amazonCorrettoLambdaFn = new lambda.Function(this, 'AmazonCorrettoCollector', {
      functionName: 'corretto-collector',
      code: lambda.Code.fromAsset(path.join(__dirname, '../', 'lambda/build/libs/OpenJdkReleaseInformationCollector-all.jar')),
      handler: 'com.github.poad.openjdk.finder.backend.AmazonCorrettoHandler::handleRequest',
      timeout: cdk.Duration.seconds(14.5 * 60),
      runtime: lambda.Runtime.JAVA_11,
      environment: {
        'JDBC_URL': props.jdbc,
        'JDBC_USER': props.user,
        'JDBC_PASSWORD': props.password,
      },
      memorySize: 512
    });
  
    const amazonCorrettoRule = new events.Rule(this, 'AmazonCorrettoCollectorRule', {
      schedule: events.Schedule.expression('cron(0 1 * * ? *)')
    });
  
    amazonCorrettoRule.addTarget(new targets.LambdaFunction(amazonCorrettoLambdaFn));



    const javaNetLambdaFn = new lambda.Function(this, 'JavaNetCollector', {
      functionName: 'java-net-collector',
      code: lambda.Code.fromAsset(path.join(__dirname, '../', 'lambda/build/libs/OpenJdkReleaseInformationCollector-all.jar')),
      handler: 'com.github.poad.openjdk.finder.backend.JavaNetHandler::handleRequest',
      timeout: cdk.Duration.seconds(14.5 * 60),
      runtime: lambda.Runtime.JAVA_11,
      environment: {
        'JDBC_URL': props.jdbc,
        'JDBC_USER': props.user,
        'JDBC_PASSWORD': props.password,
      },
      memorySize: 512
    });
  
    const javaNetRule = new events.Rule(this, 'JavaNetCollectorRule', {
      schedule: events.Schedule.expression('cron(15 1 * * ? *)')
    });
  
    javaNetRule.addTarget(new targets.LambdaFunction(javaNetLambdaFn));
  }
}
