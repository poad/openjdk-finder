import { expect as expectCDK, matchTemplate, MatchStyle } from '@aws-cdk/assert';
import * as cdk from '@aws-cdk/core';
import AdoptOpenJdkScraper = require('../lib/adopt_open_jdk_scraper-stack');

test('Empty Stack', () => {
    const app = new cdk.App();
    // WHEN
    const stack = new AdoptOpenJdkScraper.AdoptOpenJdkScraperStack(app, 'MyTestStack');
    // THEN
    expectCDK(stack).to(matchTemplate({
      "Resources": {}
    }, MatchStyle.EXACT))
});
