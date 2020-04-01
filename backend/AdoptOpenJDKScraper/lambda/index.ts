import * as request from 'request';
import * as pg from 'pg';
// import * as fs from 'fs';
import * as childProcess from 'child_process';

const host = process.env['PG_HOST']
const port = process.env['PG_PORT']
const user = process.env['PG_USER']
const password = process.env['PG_PASSWORD']
const db = process.env['PG_DATABASE']

interface Releases {
  available_lts_releases: Array<Number>,
  available_releases: Array<Number>,
  most_recent_feature_release: Number,
  most_recent_lts: Number
}

interface Version {
  adopt_build_number: Number,
  build: Number,
  major: Number,
  minor: Number,
  openjdk_version: String,
  optional?: String,
  pre?: String,
  security: Number,
  semver: String
}

interface Versions {
  versions: Array<Version>
}

interface Instller {
  checksum: String,
  checksum_link: String,
  download_count: Number,
  link: String,
  name: String,
  signature_link: String,
  size: Number
}

interface Package {
  checksum: String,
  checksum_link: String,
  download_count: Number,
  link: String,
  name: String,
  signature_link: String,
  size: Number
}

interface Binary {
  architecture: String,
  download_count: Number,
  heap_size: String,
  image_type: String,
  installer: Instller,
  jvm_impl: String,
  os: String,
  package: Package,
  project: String,
  scm_ref: String,
  updated_at: String
}

interface Source {
  link: String,
  name: String,
  size: Number
}

interface Release {
  binaries: Array<Binary>,
  download_count: Number,
  id: String,
  release_link: String,
  release_name: String,
  release_type: String,
  source: Source,
  timestamp: String,
  updated_at: String,
  vendor: String,
  version_data: Version
}

export async function handler(event: any, context: any, callback: any): Promise<void> {

  const client = new pg.Client({
    host: host,
    port: Number(port),
    user: user,
    password: password,
    database: db
  });

  client.connect()
    .then(() => {
      request({
        uri: 'https://api.adoptopenjdk.net/v3/info/available_releases',
        method: 'GET',
        json: true
      },
        (error: any, response: request.Response, releases: Releases) => {
          releases.available_releases.forEach((majorVersion) => {
            let vendors = ['adoptopenjdk', 'openjdk']
            vendors.forEach(async (vendor) => {
              request({
                uri: `https://api.adoptopenjdk.net/v3/info/release_versions?page=0&page_size=1000&release_type=ga&sort_order=ASC&vendor=${vendor}`,
                method: 'GET',
                json: true
              },
                (error: any, response: request.Response, versions: Versions) => {
                  const v: Map<Number, Version> = new Map(versions.versions.map((version) => [version.major, version]))
                  Array.from(v.values()).forEach(async (version: Version) => {
                    request({
                      uri: encodeURI(`https://api.adoptopenjdk.net/v3/assets/version/${version.semver}?heap_size=normal&page=0&page_size=100&project=jdk&release_type=ga&sort_order=ASC&vendor=${vendor}`),
                      method: 'GET',
                      json: true
                    },
                      (error: any, response: request.Response, releases: Array<Binary>) => {
                        releases.forEach(async (binary: Binary) => {
                          const id = `${vendor}-${version.major}-${binary.architecture}-${binary.jvm_impl}-${binary.image_type}`
                          const query = {
                            text: 'INSERT INTO java_version(id, vendor, majorVersion, arch, version, url, type, timestamp)',
                            values: [id, vendor, version.major, binary.architecture, version.semver, binary.image_type, binary.updated_at]
                          }
                          // await client.query(query)
                          //   .catch(err => {
                          //     console.error(err)
                          //     throw err;
                          //   })
                        })
                      })
                  })
                });
            })
          })
        })
    })

  callback(null, '');
};
