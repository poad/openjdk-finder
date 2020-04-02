import { expect as expectCDK, matchTemplate, MatchStyle } from '@aws-cdk/assert';
import * as cdk from '@aws-cdk/core';
import OpenJdkReleaseInformationCollector = require('../lib/open_jdk_release_information_collector-stack');

test('Empty Stack', () => {
    const app = new cdk.App();
    // WHEN
    const stack = new OpenJdkReleaseInformationCollector.OpenJdkReleaseInformationCollectorStack(app, 'MyTestStack');
    // THEN
    expectCDK(stack).to(matchTemplate({
      "Resources": {}
    }, MatchStyle.EXACT))
});
