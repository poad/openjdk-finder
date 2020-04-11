export interface Config {
  endpoint: string
}

const config: Config = {
  endpoint: process.env.API_ENDPOINT
    ? process.env.API_ENDPOINT
    : 'https://openjdk-finder.netlify.com/'
}

export default config
