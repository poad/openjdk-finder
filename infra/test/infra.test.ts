import { expect as expectCDK, matchTemplate, MatchStyle } from '@aws-cdk/assert';
import * as cdk from '@aws-cdk/core';
import * as Infra from '../lib/circleci-user-stack';

test('Empty Stack', () => {
    const app = new cdk.App();
    // WHEN
    const stack = new Infra.CircleCIUserStack(app, 'CircleCI-User');
    // THEN
    expectCDK(stack).to(matchTemplate({
      "Resources": {}
    }, MatchStyle.EXACT))
});
