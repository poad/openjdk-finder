export interface Config {
  endpoint: string
}

const config: Config = {
  endpoint: process.env.API_ENDPOINT
    ? process.env.API_ENDPOINT
    : 'http://localhost:8089/'
}

export default config
