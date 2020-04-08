import fetch from 'node-fetch'
import config from './Config'
import { OpenJDK } from '../store/openjdk/types'

class RestClient {
  endpoint = config.endpoint

  async fetchList(): Promise<Array<OpenJDK>> {
    const json = await fetch(this.endpoint, {
      method: 'GET',
      headers: {
        Accept: 'application/json'
      }
    }).then((res) => {
      if (res.ok) {
        return res.json()
      } else {
        console.log('error!')
        return []
      }
    })

    return json as Array<OpenJDK>
  }

  async fetchVedorList(): Promise<Array<string>> {
    const json = await fetch(`${this.endpoint}vendors`, {
      method: 'GET',
      headers: {
        Accept: 'application/json'
      }
    }).then((res) => {
      if (res.ok) {
        return res.json()
      } else {
        console.log('error!')
        return []
      }
    })

    return json as Array<string>
  }

  async fetchVersionList(): Promise<Array<number>> {
    const json = await fetch(`${this.endpoint}versions`, {
      method: 'GET',
      headers: {
        Accept: 'application/json'
      }
    }).then((res) => {
      if (res.ok) {
        return res.json()
      } else {
        console.log('error!')
        return []
      }
    })

    return json as Array<number>
  }

  async fetchTypeList(): Promise<Array<string>> {
    const json = await fetch(`${this.endpoint}types`, {
      method: 'GET',
      headers: {
        Accept: 'application/json'
      }
    }).then((res) => {
      if (res.ok) {
        return res.json()
      } else {
        console.log('error!')
        return []
      }
    })

    return json as Array<string>
  }

  async fetchArchitectureList(): Promise<Array<string>> {
    const json = await fetch(`${this.endpoint}architectures`, {
      method: 'GET',
      headers: {
        Accept: 'application/json'
      }
    }).then((res) => {
      if (res.ok) {
        return res.json()
      } else {
        console.log('error!')
        return []
      }
    })

    return json as Array<string>
  }

  async fetchBundleList(): Promise<Array<string>> {
    const json = await fetch(`${this.endpoint}bundles`, {
      method: 'GET',
      headers: {
        Accept: 'application/json'
      }
    }).then((res) => {
      if (res.ok) {
        return res.json()
      } else {
        console.log('error!')
        return []
      }
    })

    return json as Array<string>
  }

  async fetchOsList(): Promise<Array<string>> {
    const json = await fetch(`${this.endpoint}os`, {
      method: 'GET',
      headers: {
        Accept: 'application/json'
      }
    }).then((res) => {
      if (res.ok) {
        return res.json()
      } else {
        console.log('error!')
        return []
      }
    })

    return json as Array<string>
  }
}

export default RestClient
