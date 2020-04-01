# Welcome to your CDK TypeScript project'!'

This is a blank project for TypeScript development with CDK.

The `cdk.json` file tells the CDK Toolkit how to execute your app.

## Useful commands

* `npm run build`   compile typescript to js
* `npm run watch`   watch for changes and compile
* `npm run test`    perform the jest unit tests
* `cdk deploy`      deploy this stack to your default AWS account/region
* `cdk diff`        compare deployed stack with current state
* `cdk synth`       emits the synthesized CloudFormation template

## Run

```$sh
docker run --rm -it -v $(pwd):/work -v ~/.aws:/root/.aws amazonlinux:latest bash
```

### on Docker container

```$bash
curl --silent --location https://rpm.nodesource.com/setup_12.x | bash -
curl -sL https://dl.yarnpkg.com/rpm/yarn.repo | tee /etc/yum.repos.d/yarn.repo
yum update
yum install -y gcc-c++ make postgresql-static postgresql-libs postgresql-devel tar bzip2 readline-devel zlin zlib-devel openssl-devel python3-devel
yum install -y nodejs yarn
npm install -g cdk
cd /work/
npm install

TMP=$(mktemp -d)
cd ${TMP}
curl https://ftp.postgresql.org/pub/source/v10.12/postgresql-10.12.tar.bz2 > postgresql-10.12.tar.bz2
tar xf postgresql-10.12.tar.bz2
cd postgresql-10.12
./configure --with-openssl
gmake

cdk synth --path-metadata false --version-reporting false --asset-metadata false
cdk bootstrap --profile default
cdk deploy -c user=${DB_USER} -c password=${DB_PASSWORD} -c host=${DB_HOST} -c port=5432 -c database=${DB_NAME} -c layer=${LAMBDA_LAYER_VERSION}
```
