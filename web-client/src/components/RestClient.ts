import axios, { AxiosRequestConfig } from 'axios'
import config from './Config'
import { OpenJDK } from '../store/openjdk/types'

class RestClient {
  endpoint = config.endpoint;
  client: AxiosRequestConfig = axios.defaults;

  constructor() {
    this.client.baseURL = this.endpoint;
  }


  async fetchList(): Promise<Array<OpenJDK>> {
    const json = await axios.get<Array<OpenJDK>>('/')
    .then((res) => {
      if (res.status == 200) {
        return res.data
      } else {
        console.log('error!')
        throw res
      }
    })

    return json as Array<OpenJDK>
  }

  async fetchVedorList(): Promise<Array<string>> {
    const json = await axios.get<Array<string>>('/vendors')
    .then((res) => {
      if (res.status == 200) {
        return res.data
      } else {
        console.log('error!')
        throw res
      }
    })

    return json as Array<string>
  }

  async fetchVersionList(): Promise<Array<number>> {
    const json = await axios.get<Array<number>>('/versions')
    .then((res) => {
      if (res.status == 200) {
        return res.data
      } else {
        console.log('error!')
        throw res
      }
    })

    return json as Array<number>
  }

  async fetchTypeList(): Promise<Array<string>> {
    const json = await axios.get<Array<string>>('/types')
    .then((res) => {
      if (res.status == 200) {
        return res.data
      } else {
        console.log('error!')
        throw res
      }
    })

    return json as Array<string>
  }

  async fetchArchitectureList(): Promise<Array<string>> {
    const json = await axios.get<Array<string>>('/architectures')
    .then((res) => {
      if (res.status == 200) {
        return res.data
      } else {
        console.log('error!')
        throw res
      }
    })

    return json as Array<string>
  }

  async fetchBundleList(): Promise<Array<string>> {
    const json = await axios.get<Array<string>>('/bundles')
    .then((res) => {
      if (res.status == 200) {
        return res.data
      } else {
        console.log('error!')
        throw res
      }
    })

    return json as Array<string>
  }

  async fetchOsList(): Promise<Array<string>> {
    const json = await axios.get<Array<string>>('/os')
    .then((res) => {
      if (res.status == 200) {
        return res.data
      } else {
        console.log('error!')
        throw res
      }
    })

    return json as Array<string>
  }
}

export default RestClient
