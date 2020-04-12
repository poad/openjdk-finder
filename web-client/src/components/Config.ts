export interface Config {
  endpoint: string
}

const config: Config = {
  endpoint: process.env.NODE_ENV == 'development'
    ? 'http://localhost:8089/'
    : 'https://openjdk-finder.herokuapp.com/'
}

export default config
