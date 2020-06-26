import * as iam from '@aws-cdk/aws-iam';
import * as cdk from '@aws-cdk/core';
import { ManagedPolicy } from '@aws-cdk/aws-iam';

export class CircleCIUserStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const user = new iam.User(this, 'CircleCI-User', {
      userName: `aws-cdk-circleci-deploy-user`,
      managedPolicies: [
        ManagedPolicy.fromAwsManagedPolicyName('AWSLambdaFullAccess'),
        ManagedPolicy.fromAwsManagedPolicyName('AWSCloudFormationFullAccess'),
        ManagedPolicy.fromAwsManagedPolicyName('IAMFullAccess'),
      ]
    })
  }
}
